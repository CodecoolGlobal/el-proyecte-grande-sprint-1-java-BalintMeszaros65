import React, {useContext, useEffect, useState} from 'react';
import './HomeWithLoggedIn.css';
import {NewProjectForm} from "../components/NewProjectForm";
import {ProjectInfo} from "../components/ProjectInfo";
import {cookiesContext} from "../App.js";

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>
    const {cookies, setCookies} = useContext(cookiesContext)
    const [hasProjects, setHasProjects] = useState(false); //TODO get rid of and check cookies.projects value
    let [hasClicked, setHasClicked] = useState(false);

    useEffect(() => {
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${cookies.token}`,
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
                    setCookies("projects", data, { maxAge: 172800 });
                } else {
                    setHasProjects(false);
                }
            });
    }, [hasClicked]);

    return (
        <div className={'home-container'}>
            {(hasProjects) ? <ProjectInfo project={cookies.projects.at(-1)}/> :
                <NewProjectForm setHasClicked={setHasClicked}/>}
        </div>
    )
}