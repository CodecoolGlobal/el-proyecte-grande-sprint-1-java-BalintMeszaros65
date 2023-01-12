import React from 'react';
import './projectInfo.css';
export function ProjectInfo(props) {
    const test = props.project;
    return (
        <div className={'project_info_container'}>
            <div className={'project_dashboard'}>This is a dashboard</div>
            <div className={'project_members'}>Members</div>
        </div>
    )
}