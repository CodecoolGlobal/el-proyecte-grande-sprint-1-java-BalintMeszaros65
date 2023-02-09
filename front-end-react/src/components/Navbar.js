import React, {useContext, useState} from 'react';
import './Navbar.css';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {cookiesContext} from '../App.js';
import {useNavigate} from "react-router-dom";


export function Navbar() {

    const [isOpenDropDown, setIsOpenDropDown] = useState(false);
    const {cookies, setCookies, removeCookies} = useContext(cookiesContext);
    const navigate = useNavigate();

    function logout() {
        removeCookies("token");
        removeCookies("projects");
    }

    return (
        <div className={'navbar-container'}>

            <div className={'logo-container'} onClick={() => navigate("/", {state: {project: null}})}>
                <a className={'logo-text'}>Procrastination</a>
            </div>
            {cookies.token &&
                <div className={'container'}>
                    <div className={'menu-container'} onClick={() => navigate("/profile")}>Profile</div>

                    <div className={'menu-container'} onClick={() => {
                        logout();
                        navigate("/");
                    }}>Logout
                    </div>
                    <div className={'new-project-container'} onClick={() => setIsOpenDropDown(true)}>
                        <span className={'project'}>Projects</span>
                        <FontAwesomeIcon icon="fa-caret-down"/>
                    </div>
                    {(isOpenDropDown) &&
                        <div onMouseLeave={() => setIsOpenDropDown(false)} className={'drop-down-div'}>
                            {cookies.projects?.map((project, index) => (
                                <div key={index} className={'project-name'}
                                     onClick={() => {
                                         navigate("/", {state: {project: project}}); //TODO some reason wont rerender if already on the same component
                                     }}>
                                    {project.projectName}
                                </div>
                            ))}
                            <div className={'new-project-button'} onClick={() => navigate("/")}>
                                Add new project
                            </div>
                        </div>}
                </div>
            }
        </div>
    )
}