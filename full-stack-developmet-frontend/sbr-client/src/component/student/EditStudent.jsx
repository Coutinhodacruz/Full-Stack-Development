import React, { useEffect, useState } from "react";
import {Link, useNavigate, useParams} from "react-router-dom";
import axios from "axios";

const EditStudent = () => {

    const {id} = useParams();
   const [student, setStudents] = useState({
    firstName: "",
    lastName: "",
    email: "",
    department: " ",
    password: " ",
  });

  const { firstName, 
    lastName, 
    email, 
    department,
    password,
   } = student;

   useEffect(() => {
    loadStudents();
  }, []);
  
  const loadStudents = async () => {
   const response = await axios.get(
    `http://localhost:8080/students/student/${id}`);
   setStudents(response.data);
  };


  const handleInputChange = (e) => {
    setStudents({
      ...student,
      [e.target.name]: e.target.value,
    });
  };

  const navigate = useNavigate();

  const updateStudent = async (e) => {
    e.preventDefault();
    await axios.put(`http://localhost:8080/students/update/${id}`, student);
    setStudents({
      firstName: "",
      lastName: "",
      email: "",
      department: " ",
      password: " ",

    })
    navigate("/view-student")
  };

  return (
    <div className="col-sm-8 py-2 px-5">
      <form onSubmit={(e) => updateStudent(e)}>
        <h2 className='mt-5'>Edit Student</h2>
        <div className="input-group mb-5">
          <label className="input-group-text" htmlFor="firstName">
            First Name
          </label>
          <input
            className="form-control col-m-6"
            type="text"
            name="firstName"
            id="firstName"
            required
            value={firstName}
            onChange={(e) => handleInputChange(e)}
          />
        </div>

        <div className="input-group mb-5">
          <label className="input-group-text" htmlFor="lastName">
            Last Name
          </label>
          <input
            className="form-control col-m-6"
            type="text"
            name="lastName"
            id="lastName"
            required
            value={lastName}
            onChange={(e) => handleInputChange(e)}
          />
        </div>

        <div className="input-group mb-5">
          <label className="input-group-text" htmlFor="email">
            Email
          </label>
          <input
            className="form-control col-m-6"
            type="text"
            name="email"
            id="email"
            required
            value={email}
            onChange={(e) => handleInputChange(e)}
          />
        </div>

        <div className="input-group mb-5">
          <label className="input-group-text" htmlFor="department">
            Department
          </label>
          <input
            className="form-control col-m-6"
            type="text"
            name="department"
            id="department"
            required
            value={department}
            onChange={(e) => handleInputChange(e)}
          />
        </div>

        <div className="input-group mb-5">
          <label className="input-group-text" htmlFor="password">
            Password
          </label>
          <input
            className="form-control col-m-6"
            type="text"
            name="passowrd"
            id="password"
            required
            value={password}
            onChange={(e) => handleInputChange(e)}
          />
        </div>

        <div className="row mb-5">
          <div className="col-sm-2">
            <button type="submit" className="btn btn-outline-success btn-lg">
              Save
            </button>
          </div>

          <div className="col-sm-2">
            <Link to={"/view-student"}
            type="submit" className="btn btn-outline-warning btn-lg">
              Cancel
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
}

export default EditStudent