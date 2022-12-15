import React from 'react';
import './Navbar.css';
import {FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { hasJWT } from "./RouteGuard";


export function Navbar(props){

    function logout() {
        localStorage.setItem("token", "");
        //props.setToken("token", "");
    }

    return (
        <div className={'navbar-container'}>

            <div className={'logo-container'}>
                <p className={'logo-text'}>Procrastination</p>
            </div>
            {hasJWT() &&
                <div className={'container'}>
                    <div className={'menu-container'}><a href={'/profile'} >Profile</a></div>

                    <div className={'menu-container'}><button onClick={logout} >logout</button>

                    </div>
                <div className={'new-project-container'}><a href={'/team'} >New Project - New Team <FontAwesomeIcon icon="fa-caret-down" /></a></div>
                </div>
            }
        </div>
    )
}