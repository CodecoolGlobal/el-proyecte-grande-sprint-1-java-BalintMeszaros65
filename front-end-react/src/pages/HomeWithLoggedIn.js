import React, {useContext, useEffect, useState} from 'react';
import './HomeWithLoggedIn.css';
import {NewProjectForm} from "../components/NewProjectForm";
import {ProjectInfo} from "../components/ProjectInfo";
import {cookiesContext} from "../App.js";
import {useLocation} from "react-router-dom";

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>
    const location = useLocation();
    const {cookies, setCookies} = useContext(cookiesContext)
    const [hasClicked, setHasClicked] = useState(false);

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
                    setCookies("projects", data, {maxAge: 172800});
                }
            });
    }, [hasClicked]);

    return (
        <div className={'home-container'}>
            {location.state ? location.state.project
                    ? <ProjectInfo project={location.state.project}/>
                    : <ProjectInfo project={cookies.projects.at(-1)}/>
                : <NewProjectForm setHasClicked={setHasClicked}/>
            }
        </div>
    )
}