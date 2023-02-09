import './App.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {Home} from "./pages/Home";
import {Register} from "./pages/Register";
import {Login} from "./pages/Login";
import {Navbar} from "./components/Navbar";
import {HomeWithLoggedIn} from "./pages/HomeWithLoggedIn";
import {PageNotFound} from "./pages/PageNotFound";
import './components/FontawsomeIcons';
import {createContext} from "react";
import {Profile} from "./pages/Profile";
import {useCookies} from "react-cookie";


export const cookiesContext = createContext();

function App() {
    const [cookies, setCookies, removeCookies] = useCookies(["token", "projects"]);

    return (
        <cookiesContext.Provider value={{cookies, setCookies, removeCookies}}>
                <Navbar/>
                <Router>
                    {cookies.token ?
                        <Routes>
                            <Route path={"/"} element={<HomeWithLoggedIn/>}/>
                            <Route path={"*"} element={<PageNotFound/>}/>
                            <Route path={"/profile"} element={<Profile/>}/>
                            <Route path={"/project/*"} element={}/>
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
        </cookiesContext.Provider>
    );
}

export default App;
