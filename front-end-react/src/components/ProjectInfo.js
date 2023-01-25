import React from 'react';
import './ProjectInfo.css';
import {useNavigate} from "react-router-dom";
import {getTokenForCurrentUser} from "./RouteGuard";
import {getValue} from "@testing-library/user-event/dist/utils";


export function ProjectInfo(props) {

    const project = props.project;


    function visit_github_link(){
        window.open(project.gitRepo, "_blank");
    }

    async function sendNewProjectMessage(newProjectMessage) {
        const message = {"message": newProjectMessage};
        const user_id = getTokenForCurrentUser();
        const project_id = project.id;
        const requestOptions = {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${user_id}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(message)
        }
        await fetch(`http://localhost:8080/api/messages/save/${project_id}`, requestOptions)
            .then(response => {
                if (response.ok) {
                    console.log("response is ok");
                } else {
                    console.log("response is NOT ok");
                }
            })
    }

    return (
        <div className={'project_info_container'}>
            <div className={'project_dashboard'}>This is a dashboard
                <div>here are the messages</div>
                <div>
                    <input type="text" placeholder={"type something"}
                           onKeyDown={(event) => {
                               if (event.key === 'Enter'){
                                   sendNewProjectMessage(event.currentTarget.value);
                               }
                           }}
                           />
                </div>
            </div>
            <div className={'right_side_container'}>
                <div className={'project_members'}>
                    Members
                    <div className={"member_container"}>

                        {project.members.map( (member) =>(
                            <p className={"project_member"} key={member["realUserName"]}>{member.realUserName}</p>
                        ))}
                    </div>
                </div>
                <div className={"project_github_link"}>
                    <button className={"github_button"} onClick={ visit_github_link}>Github Link</button>
                </div>
            </div>
        </div>
    )
}