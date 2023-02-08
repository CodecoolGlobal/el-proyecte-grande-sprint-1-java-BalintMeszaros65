import React, {useContext, useEffect, useState} from "react";
import './Profile.css';
import {cookiesContext} from "../App";


export function Profile() {
    const {cookies} = useContext(cookiesContext);
    const [userDetails, setUserDetails] = useState([]);


    async function getUserDetails() {
        console.log("get user details function called")
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${cookies.token}`,
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

    useEffect(() => {
        getUserDetails();
    }, []);

    return (
        <div className={'profile-container'}>
            <h1>Profile Page</h1>
            <div className={'user-datas'}>
                <h3>User Details</h3>
                <h4>
                    Name
                </h4>
                <p>
                    {userDetails.userName}
                </p>
                <h4>
                    E-mail
                </h4>
                <p>
                    {userDetails.email}
                </p>
                <h4>
                    Git Profile
                </h4>
                <p>
                    {userDetails.gitProfile}
                </p>
                <h4>
                    Journey Profile
                </h4>
                <p>
                    {userDetails.journeyProfile}
                </p>

            </div>
            <div className={'user-projects'}>
                <h3>projects of user</h3>
                {cookies.projects.map((project, index) => <p key={index}>{project.projectName}</p>)}
            </div>
        </div>
    );
}