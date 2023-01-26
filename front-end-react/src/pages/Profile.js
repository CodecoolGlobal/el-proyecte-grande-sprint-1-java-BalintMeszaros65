import React from 'react';

export function Profile() {
    let projects = JSON.parse(localStorage.getItem('projects'));
    console.log(projects);
    return (
        <div className={'profile-container'}>This is the profile page</div>
    )
}
