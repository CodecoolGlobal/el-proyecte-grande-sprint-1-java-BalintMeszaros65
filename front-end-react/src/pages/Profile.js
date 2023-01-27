import React, {useEffect, useState} from "react";
import {getTokenForCurrentUser} from "../components/RouteGuard";
import './Profile.css';


export function Profile() {

    const [userDetails, setUserDetails] = useState([]);
    const [userProjects, setUserProjects] = useState([]);


    async function getUserDetails() {
        console.log("get user details function called")
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

    async function getUserProjects(){
        console.log("get user projects function called")
        const user_id = getTokenForCurrentUser();
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${user_id}`,
                'Content-Type': 'application/json'
            }
        }
        fetch(`/api/project`, requestOptions)
            .then(response => {
                return response.json();
            })
            .then(data => {
                if(data.length > 0){
                    // make USESTATE for 'has projects ?' --> give the user a message to make a project if its false
                    //setHasProjects(true);
                    setUserProjects(data);
                    console.log(data)
                }
            });
    }


    useEffect(() => {
        getUserDetails();
        getUserProjects();
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
                <p>Under construction</p>
            </div>
        </div>
    );
}