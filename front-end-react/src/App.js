import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {Home} from "./pages/Home";
import {Register} from "./pages/Register";
import {Login} from "./pages/Login";
import {Navbar} from "./components/Navbar";
import {HomeWithLoggedIn} from "./pages/HomeWithLoggedIn";
import {PageNotFound} from "./pages/PageNotFound";
import './components/FontawsomeIcons';
import {hasJWT} from "./components/RouteGuard";
import {useState} from "react";
import {Profile} from "./pages/Profile";


function App() {
    const [token, setToken] = useState('');

    return (
        <>
            <Navbar setToken={setToken}/>
            <Router>
                {hasJWT() ?
                    <Routes>
                        <Route path={"/"} element={<HomeWithLoggedIn/>}/>
                        <Route path={"*"} element={<PageNotFound/>}/>
                        <Route path={"/profile"} element={<Profile/>}/>
                    </Routes>
                    :
                    <Routes>
                        <Route path={"/"} element={<Home/>}/>
                        <Route path={"/login"} element={<Login setToken={setToken}/>}/>
                        <Route path={"/register"} element={<Register setToken={setToken}/>}/>
                        <Route path={"*"} element={<PageNotFound/>}/>
                    </Routes>
                }
            </Router>
        </>
    );
}

export default App;
