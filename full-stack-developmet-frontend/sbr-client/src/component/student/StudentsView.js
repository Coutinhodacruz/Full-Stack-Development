import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { FaTrashAlt, FaEye, FaEdit } from "react-icons/fa";
import axios from "axios";


const StudentsView = () => {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    loadStudents();
  }, []);

  const loadStudents = async () => {
    await fetch("http://localhost:8080/students")
      .then((response) => response.json())
      .then((data) => {
        setStudents(data);
        console.log(data);
      })
      .catch((error) => console.error(error));
  };


  const handleDelete = async(id) =>{
    await axios.delete(`http://localhost:8080/students/delete/${id}`
    );
    loadStudents();
  }

  return (
    <section>
      <table className="table table-bordered table-hover shadow">
        <thead>
          <tr className="text-center">
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Department</th>
            <th>Password</th>
            <th colSpan="3">Actions</th>
          </tr>
        </thead>
        <tbody className="text-center">
          {students.map((student, index) => (
            <tr key={student.id}>
              <th scope="row" key={index}>
                {index + 1}
              </th>
              <td>{student.firstName}</td>
              <td>{student.lastName}</td>
              <td>{student.email}</td>
              <td>{student.department}</td>
              <td>{student.password}</td>
              <td className="mx-2">
                <Link
                  to={`/student-profile/${student.id}`}
                  className="btn btn-info">
                  {/* Update */}
                  <FaEye />
                </Link>
              </td>
              <td className="mx-2">
                <Link
                  to={`/edit-student/${student.id}`}
                  className="btn btn-warning">
                  {/* Update */}
                  <FaEdit />
                </Link>
              </td>
              <td className="mx-2">
                <button className="btn btn-danger"
                onClick={() => handleDelete(student.id)}
                >
                  {/* Button */}
                  <FaTrashAlt/>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </section>
  );
};

export default StudentsView;

// fetch("http://localhost:8080/students")
// .then(response => response.json())
// .then(data => {
//   setStudents(data)
//   console.log(data)
// })
// .catch(error => console.error(error));

// try {
//   const response = await axios.get("http://localhost:8080/students", {
//     validateStatus: (status) => {
//       return true;
//     },
//   });
//   if (response.status === 200) {

//     const students = response.data;
//     setStudents(students);
//   } else if (response.status === 302) {

//     const redirectedData = JSON.parse(response.data);
//   } else {
//     console.error("Request failed with status:", response.status);
//   }
// } catch (error) {
//   console.error("Error fetching data:", error);
// }
