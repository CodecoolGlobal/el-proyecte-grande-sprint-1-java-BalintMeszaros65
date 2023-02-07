import React, {useContext, useEffect, useState} from 'react';
import './HomeWithLoggedIn.css';
import {NewProjectForm} from "../components/NewProjectForm";
import {ProjectInfo} from "../components/ProjectInfo";
import {currentProjects, currentToken} from "../App.js";

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>
    const {token, setToken} = useContext(currentToken)
    const [hasProjects, setHasProjects] = useState(false);
    const {projects, setProjects} = useContext(currentProjects);
    let [hasClicked, setHasClicked] = useState(false);
    let indexOfLastProject = 0

    useEffect(() => {
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        }
        fetch(`/api/project`, requestOptions)
            .then(response => {
                return response.json();
            })
            .then(data => {
                if (data.length > 0) {
                    setHasProjects(true);
                    setProjects(data);
                    indexOfLastProject = data.length
                    console.log(data)
                } else {
                    setHasProjects(false);
                    setProjects(data);
                }
            });
    }, [hasClicked]);


    return (
        <div className={'home-container'}>
            {(hasProjects) ? <ProjectInfo project={projects[indexOfLastProject]}/> :
                <NewProjectForm setHasClicked={setHasClicked}/>}
        </div>
    )
}