import React from "react";
import {Navbar} from "../components/Navbar";


export function Profile() {



    return (
        <div className={'profile-container'}>
            <h1 >Profile Page</h1>
            <div className={'user-datas'}>
                <p>datas about project (git,journey,username,email)</p>
            </div>
            <div className={'user-teams'}>
                <p>teams of user</p>
            </div>
        </div>
    );
}