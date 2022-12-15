import React, {useEffect, useState} from 'react';
import './HomeWithLoggedIn.css';
import {getTokenForCurrentUser} from "../components/RouteGuard";
import {NewProjectForm} from "../components/NewProjectForm";

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>

    const [noProjectYet, setNoProjectYet] = useState(false);

    useEffect(() => {
        const user_id = getTokenForCurrentUser();
        const requestOptions = {
                method: 'GET',
                mode: 'cors',
                // TODO inject token to header
                headers: {
                    'Authorization': `Bearer ${user_id}`,
                    'Content-Type': 'application/json'
                }
            }
        // TODO get rid of localhost
        fetch(`/api/project`, requestOptions)
            .then(response => {
                return response.json();
            })
            .then(data => {
                if(data.length < 1){
                    setNoProjectYet(true);
                }
                console.log(data);
            });

    }, []);


    return (
        <div className={'home-container'}>
            {(noProjectYet) && <NewProjectForm/>}
        </div>
    )
}