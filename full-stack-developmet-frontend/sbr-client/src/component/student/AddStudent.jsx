import React, { useState } from "react";
import { Form, Link, useNavigate } from "react-router-dom";
import axios from 'axios';

const AddStudent = () => {
  
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

  const handleInputChange = (e) => {
    setStudents({
      ...student,
      [e.target.name]: e.target.value,
    });
  };

  const navigate = useNavigate();

  const saveStudent = async (e) => {
    e.preventDefault();
    await axios.post("http://localhost:8080/students", student);
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
      <form onSubmit={(e) => saveStudent(e)}>
        <h2>Add Student</h2>
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
};

export default AddStudent;
