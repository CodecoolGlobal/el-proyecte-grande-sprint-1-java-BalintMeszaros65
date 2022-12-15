import React from 'react';
import './Home.css';
import {useNavigate} from "react-router-dom";

//                <Unregistered Version of Home>
export function Home() {

    let navigate = useNavigate();

    function routeChange(path) {
        navigate(path)
    }

    return (
        <div className={'home-container'}>
            <div className={'btn-container'}>
                <button color={'primary'}  onClick={() => routeChange(`/login`)}>Login</button>
            </div>
            <div className={'btn-container'}>
                <button color={'primary'} onClick={() => routeChange(`/register`)}>Register</button>
            </div>
        </div>

    )
}