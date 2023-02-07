import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {Home} from "./pages/Home";
import {Register} from "./pages/Register";
import {Login} from "./pages/Login";
import {Navbar} from "./components/Navbar";
import {HomeWithLoggedIn} from "./pages/HomeWithLoggedIn";
import {PageNotFound} from "./pages/PageNotFound";
import './components/FontawsomeIcons';
import {createContext, useState} from "react";
import {Profile} from "./pages/Profile";


export const currentToken = createContext();
export const currentProjects = createContext();

function App() {
    const [token, setToken] = useState('');
    const [projects, setProjects] = useState('');

    return (
        <currentToken.Provider value={{token, setToken}}>
            <currentProjects.Provider value={{projects, setProjects}}>
                <Navbar setToken={setToken}/>
                <Router>
                    {token ?
                        <Routes>
                            <Route path={"/"} element={<HomeWithLoggedIn/>}/>
                            <Route path={"*"} element={<PageNotFound/>}/>
                            <Route path={"/profile"} element={<Profile/>}/>
                        </Routes>
                        :
                        <Routes>
                            <Route path={"/"} element={<Home/>}/>
                            <Route path={"/login"} element={<Login/>}/>
                            <Route path={"/register"} element={<Register/>}/>
                            <Route path={"*"} element={<PageNotFound/>}/>
                        </Routes>
                    }
                </Router>
            </currentProjects.Provider>
        </currentToken.Provider>
    );
}

export default App;
