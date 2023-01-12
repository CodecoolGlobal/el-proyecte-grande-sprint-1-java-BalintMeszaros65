import React, {useEffect, useState} from 'react';
import './HomeWithLoggedIn.css';
import {getTokenForCurrentUser} from "../components/RouteGuard";
import {NewProjectForm} from "../components/NewProjectForm";
import {ProjectInfo} from "../components/ProjectInfo";

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>

    const [hasProjects, setHasProjects] = useState(false);
    const [projects, setProjects] = useState();
    let [hasClicked, setHasClicked] = useState(false);


    useEffect(() => {
        console.log('useEffect Triggered')
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
                    setHasProjects(true);
                } else {
                    setProjects(data);
                }
                console.log(data)
                console.log('test ' + data.length)
            });
            // return hasProjects

    }, [hasClicked]);





return (
        <div className={'home-container'}>
            {(hasProjects) ? <ProjectInfo project={projects[0]} /> : <NewProjectForm setHasClicked={setHasClicked} />  }
        </div>
    )
}