import React, {useEffect, useState} from "react";
import {getTokenForCurrentUser} from "../components/RouteGuard";


export function Profile() {

    const [userDetails, setUserDetails] = useState([]);


    useEffect(() => {
        getUserDetails();
    })

    async function getUserDetails() {
        const user_id = getTokenForCurrentUser();
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${user_id}`,
                'Content-Type': 'application/json'
            }
        }
        await fetch(`/api/user/get-details`, requestOptions)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
            })
            .then(data => {
                    setUserDetails(data)
                }
            )
    }


    return (
        <div className={'profile-container'}>
            <h1>Profile Page</h1>
            <div className={'user-datas'}>
                <p>
                    {userDetails.userName}
                </p>
                <p>
                    {userDetails.email}
                </p>
                <p>
                    {userDetails.gitProfile}
                </p>
                <p>
                    {userDetails.journeyProfile}
                </p>

            </div>
            <div className={'user-teams'}>
                <p>teams of user</p>
            </div>
        </div>
    );
}