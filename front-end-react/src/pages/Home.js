import React from 'react';
import './Home.css';
import {Button} from "@mui/material";

export function Home() {
    //                <Unregistered Version of Home>
    return (
        <div className={'home-container'}>
            <div className={'btn-container'}>
                <Button variant={'contained'} color={'primary'} href={'/login'}>Login</Button>
            </div>
            <div className={'btn-container'}>
                <Button variant={'contained'} color={'primary'} href={'/register'}>Register</Button>
            </div>
        </div>

    )
}