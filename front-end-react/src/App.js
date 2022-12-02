import './App.css';
import {BrowserRouter as Router, Routes, Route} from "react-router-dom";
import {Home} from "./pages/Home";
import {Register} from "./pages/Register";
import {Login} from "./pages/Login";
import {Navbar} from "./components/Navbar";
import {HomeWithLoggedIn} from "./pages/HomeWithLoggedIn";
import {PageNotFound} from "./pages/PageNotFound";
import './components/FontawsomeIcons';


function App() {
    const token = true;
    return (
        <>
            <Navbar token={token} />
            <Router>
                {token ?
                <Routes>
                    <Route path={"/"} element={<HomeWithLoggedIn/>} />
                    <Route path={"*"} element={<PageNotFound />} />
                </Routes>
                :
                <Routes>
                    <Route path={"/"} element={<Home/>} />
                    <Route path={"/login"} element={<Login/>}/>
                    <Route path={"/register"} element={<Register/>}/>
                    <Route path={"*"} element={<PageNotFound />} />
                </Routes>
                }
            </Router>
        </>
    );
}

export default App;
