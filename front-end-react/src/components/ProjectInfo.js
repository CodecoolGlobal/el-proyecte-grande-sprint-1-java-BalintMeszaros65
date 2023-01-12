import React from 'react';
import './ProjectInfo.css';
import {useNavigate} from "react-router-dom";


export function ProjectInfo(props) {



    const project = props.project;

    function visit_github_link(){
        window.open(project.gitRepo, "_blank");
    }


    return (
        <div className={'project_info_container'}>
            <div className={'project_dashboard'}>This is a dashboard</div>
            <div className={'right_side_container'}>
                <div className={'project_members'}>Members</div>
                <div className={"project_github_link"}>
                    <button className={"github_button"} onClick={ visit_github_link}>Github Link</button>
                </div>
            </div>
        </div>
    )
}