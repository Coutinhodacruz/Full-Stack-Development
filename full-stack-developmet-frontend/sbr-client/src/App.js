import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "/node_modules/bootstrap/dist/js/bootstrap.min.js";
import "./App.css";
import Home from "./Home";
import StudentsView from "./component/student/StudentsView";
import NavBar from "./component/common/NavBar";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import AddStudent from "./component/student/AddStudent";
import EditStudent from "./component/student/EditStudent";

function App() {
  return (
    <main className="container mt-5">
      <Router>
      <NavBar />
        <Routes>
          <Route exact 
          path="/" 
          element={<Home />}></Route>
          <Route exact 
          path="/view-student" 
          element={<StudentsView />}>
          </Route>
          <Route exact
          path="/add-student"
          element={<AddStudent />}>
          </Route>
          <Route exact
          path="/edit-student/:id"
          element={<EditStudent />}>
          </Route>
        </Routes>
      </Router>
    </main>
  );
}
 
export default App;