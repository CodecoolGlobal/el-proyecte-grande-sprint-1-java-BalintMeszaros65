import React from 'react';
import './Home.css';
import {Link} from 'react-router-dom';

export function Home() {
    //                <Unregistered Version of Home>
    return (
        <div className={'home-container'}>
            <div className={'btn-container'}>
                <a className={'btn home-navigation-link'} href={'/login'}> Login </a>
            </div>
            <div className={'btn-container'}>
                <a className={'btn home-navigation-link'} href={'/register'}> Register </a>
            </div>
        </div>

    )
}