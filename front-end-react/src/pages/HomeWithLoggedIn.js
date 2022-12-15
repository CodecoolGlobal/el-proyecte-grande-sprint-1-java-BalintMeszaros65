import React, {useEffect} from 'react';
import './HomeWithLoggedIn.css';
import {getTokenForCurrentUser} from "../components/RouteGuard";

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>
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
                console.log(data);
            });

    }, []);

    return (
        <div className={'home-container'}>
            <div className={'project-form-container'}>
                <form>
                    <p>*Team name</p>
                    <input type={'text'} name={'teamName'} placeholder={'Enter your Team name'} />
                    <p>*Project name</p>
                    <input type={'text'} name={'projectName'} placeholder={'Enter your Project name'} />
                    <p>*Git Repository</p>
                    <input type={'text'} name={'gitRepository'} placeholder={'Enter your Git Repository'} />
                    <p>Team members</p>
                    <input type={'text'} name={'teamMembers'} placeholder={'Enter your team members'} />
                    <p className={'required-field-text'}>* is a required field</p>
                    <button>New Project</button>
                </form>
            </div>
        </div>
    )
}