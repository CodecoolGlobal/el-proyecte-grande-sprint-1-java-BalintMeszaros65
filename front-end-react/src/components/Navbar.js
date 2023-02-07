import React, {useContext} from 'react';
import './Navbar.css';
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {currentToken} from '../App.js';


export function Navbar() {
    let {token, setToken} = useContext(currentToken);

    function logout() {
        setToken('');
    }

    return (
        <div className={'navbar-container'}>

            <div className={'logo-container'}>
                <p className={'logo-text'}>Procrastination</p>
            </div>
            {token &&
                <div className={'container'}>
                    <div className={'menu-container'}><a href={'/profile'}>Profile</a></div>

                    <div className={'menu-container'}><a href={'/'} onClick={logout}>Logout</a>

                    </div>
                    <div className={'new-project-container'}><a href={'/team'}>New Project - New Team <FontAwesomeIcon
                        icon="fa-caret-down"/></a></div>
                </div>
            }
        </div>
    )
}