import React, {useContext} from 'react';
import React, {useEffect, useRef, useState} from 'react';
import './Navbar.css';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {cookiesContext} from '../App.js';


export function Navbar(props){
    const [isOpenDropDown, setIsOpenDropDown] = useState(false);


    let projects = {};

    (localStorage.getItem('projects')) ? projects = JSON.parse(localStorage.getItem('projects')) : projects = {};
export function Navbar() {
    let {cookies, setCookies, removeCookies} = useContext(cookiesContext);

    let menuRef = useRef();



    useEffect(() => {
        let handler = (event) => {
            if (!menuRef.current.contains(event.target)) {
                setIsOpenDropDown(false);
            }
        }
        document.addEventListener("mousedown", handler);

        return () => {
            document.removeEventListener("mousedown", handler);
        }
    });
    function logout() {
        removeCookies("token");
        removeCookies("projects");
    }

    return (
        <div className={'navbar-container'}>

            <div className={'logo-container'}>
                <a href={'/'} className={'logo-text'}>Procrastination</a>
            </div>
            {cookies.token &&
                <div className={'container'}>
                    <div className={'menu-container'}><a href={'/profile'}>Profile</a></div>

                    <div className={'menu-container'}><a href={'/'} onClick={logout}>Logout</a>

                    </div>
                    <div ref={menuRef} className={'new-project-container'} onClick={() => setIsOpenDropDown(true) } >
                        <span className={'project'}>Projects</span>
                        <FontAwesomeIcon icon="fa-caret-down" />
                    </div>
                    {(isOpenDropDown) &&
                        <div ref={menuRef} className={'drop-down-div'}>
                            {Object.keys(projects).map(key => (
                                <div className={'project-name'} onClick={() => window.location.href = `/project/${key}`}>{projects[key].projectName}</div>
                            ))}
                            <div className={'new-project-button'}>
                                <a href={'/add-new-project'}>Add new project</a>
                            </div>
                        </div> }
                </div>
            }
        </div>
    )
}