import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {Home} from "./pages/Home";
import {Register} from "./pages/Register";
import {Login} from "./pages/Login";
import {Navbar} from "./components/Navbar";
import {HomeWithLoggedIn} from "./pages/HomeWithLoggedIn";
import {PageNotFound} from "./pages/PageNotFound";
import './components/FontawsomeIcons';
import {getTokenForCurrentUser, hasJWT} from "./components/RouteGuard";
import {useEffect, useState} from "react";
import {Profile} from "./pages/Profile";
import {NewProjectForm} from "./components/NewProjectForm";
import {ProjectInfo} from "./components/ProjectInfo";


function App() {
    const [token, setToken] = useState('');
    const [projects, setProjects] = useState([]);
    const [hasProjects, setHasProjects] = useState(false);
    let [hasClicked, setHasClicked] = useState(false);


    return (
        <>
            <Navbar setToken={setToken} projects={projects} />
            <Router>
                {hasJWT() ?
                <Routes>
                    <Route path={"/"} element={<HomeWithLoggedIn hasClicked={hasClicked} setHasClicked={setHasClicked} projects={projects} setProjects={setProjects} hasProjects={hasProjects} setHasProjects={setHasProjects} />} />
                    <Route path={"/profile"} element={<Profile projects={projects} hasProjects={hasProjects} />} />
                    <Route path={"/add-new-project"} element={<NewProjectForm hasClicked={hasClicked} setHasClicked={setHasClicked} />} />
                    <Route path={"/project/:id"} element={<ProjectInfo />} />
                    <Route path={"*"} element={<PageNotFound />} />
                </Routes>
                :
                <Routes>
                    <Route path={"/"} element={<Home/>} />
                    <Route path={"/login"} element={<Login setToken={setToken} />}/>
                    <Route path={"/register"} element={<Register setToken={setToken}/>}/>
                    <Route path={"*"} element={<PageNotFound />} />
                </Routes>
                }
            </Router>
        </>
    );
}

export default App;
