import React, {useEffect, useState} from 'react';
import './HomeWithLoggedIn.css';
import {getTokenForCurrentUser, hasJWT} from "../components/RouteGuard";
import {NewProjectForm} from "../components/NewProjectForm";

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>

    const [noProjectYet, setNoProjectYet] = useState(false);

    useEffect(() => {
        const requestOptions = {
                method: 'GET',
                mode: 'cors',
                // TODO inject token to header
                headers: {
                    'Content-Type': 'application/json'
                }
            }
            const user_id = getTokenForCurrentUser();
        // TODO get rid of localhost
        fetch(`http://localhost:8080/api/project/${user_id}`, requestOptions)
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