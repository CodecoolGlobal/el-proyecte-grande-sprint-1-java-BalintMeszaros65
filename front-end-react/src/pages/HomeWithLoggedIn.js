import React, {useEffect, useState} from 'react';
import './HomeWithLoggedIn.css';
import {getTokenForCurrentUser} from "../components/RouteGuard";
import {NewProjectForm} from "../components/NewProjectForm";
import {ProjectInfo} from "../components/ProjectInfo";

export function HomeWithLoggedIn(props) {
    //                <Logged In Version of Home>


    let indexOfLastProject = 0

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
                    props.setHasProjects(true);
                    props.setProjects(data);
                    indexOfLastProject = data.length;
                    localStorage.setItem('projects', JSON.stringify(parseObject(data)));
                    console.log(data.length)
                } else {
                    props.setHasProjects(false);
                    props.setProjects(data);
                }
            });
    }, [props.hasClicked]);

    function parseObject(data) {
        let map = {};
        for(let i=0; i<data.length; i++) {
            map[i] = data[i];
        }
        return map;
    }

    function lastProjectIndex() {
        let result = 0;
        let projects = JSON.parse(localStorage.getItem('projects'));

        return Object.keys(projects).length-1;
    }


return (
        <div className={'home-container'}>
            {(props.hasProjects) ? <ProjectInfo id={lastProjectIndex()} /> : <NewProjectForm setHasClicked={props.setHasClicked} />  }
        </div>
    )
}