import React, {useEffect, useState} from 'react';
import './ProjectInfo.css';
import {useNavigate, useParams} from "react-router-dom";
import {getTokenForCurrentUser} from "./RouteGuard";
import {getValue} from "@testing-library/user-event/dist/utils";


export function ProjectInfo(props) {

    const projectId = props.id;
    //console.log(projectId)

    let projects = JSON.parse(localStorage.getItem('projects'));
    const { id } = useParams();
    let thisProject = {};

    if (id === undefined) {
        thisProject = projects[projectId];

    } else {
        thisProject = projects[id];
    }
    console.log(thisProject);



    const [projectMessages, setProjectMessages] = useState([]);


    function visit_github_link() {
        window.open(thisProject.gitRepo, "_blank");
    }

    async function getProjectMessages() {
        const user_id = getTokenForCurrentUser();
        const project_id = thisProject.id;
        const requestOptions = {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${user_id}`,
                'Content-Type': 'application/json'
            }
        }
        await fetch(`/api/messages/load/${project_id}`, requestOptions)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
            })
            .then(data => {
                if (data.length > 0) {
                    setProjectMessages(data);
                }
            })
    }

    async function sendNewProjectMessage(newProjectMessage) {
        const user_id = getTokenForCurrentUser();
        const project_id = thisProject.id;
        const message = {"message": newProjectMessage};
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${user_id}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(message)
        }
        await fetch(`/api/messages/save/${project_id}`, requestOptions)
            .then(response => {
                if (response.ok) {
                    getProjectMessages();
                }
            })
    }

    useEffect(() => {
        getProjectMessages();
    }, []);

    return (
        <div className={'project_info_container'}>
            <div className={'project_dashboard'}>
                <h3>Messages</h3>
                <div className={'messages_container'} ref={() => {
                    let element = document.querySelector(".messages_container");
                    element.scrollTop = element.scrollHeight;
                }}>
                    {projectMessages.length > 0 && (
                        <div>
                            {projectMessages.map(message => (
                                    <p key={message}>{message}</p>
                                )
                            )}
                        </div>
                    )}
                </div>
                <div>
                    <input type="text" placeholder={"type something"}
                           onKeyDown={(event) => {
                               if (event.key === 'Enter') {
                                   if (event.currentTarget.value.trim().length > 0) {
                                       sendNewProjectMessage(event.currentTarget.value)

                                   }
                                   event.currentTarget.value = "";
                               }
                           }}
                    />
                </div>
            </div>
            <div className={'right_side_container'}>
                <div className={'project_members'}>
                    <h3>Members</h3>
                    <div className={"member_container"}>

                        {thisProject.members.map((member) => (
                            <p className={"project_member"} key={member["realUserName"]}>{member.realUserName}</p>
                        ))}
                    </div>
                </div>
                <div className={"project_github_link"}>
                    <button className={"github_button"} onClick={visit_github_link}>Github Link</button>
                </div>
            </div>
        </div>
    )
}