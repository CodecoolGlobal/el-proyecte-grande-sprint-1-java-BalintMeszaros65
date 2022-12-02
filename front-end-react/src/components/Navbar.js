import React from 'react';
import './Navbar.css';
import {FontAwesomeIcon } from "@fortawesome/react-fontawesome";


export function Navbar(props){
    return (
        <div className={'navbar-container'}>

            <div className={'logo-container'}>
                <p className={'logo-text'}>Procrastination</p>
            </div>
            {props.token &&
                <div className={'container'}>
                <div className={'menu-container'}><a href={'/profile'} >Profile</a></div>
                <div className={'new-project-container'}><a href={'/team'} >New Project - New Team <FontAwesomeIcon icon="fa-caret-down" /></a></div>
                </div>
            }
        </div>
    )
}