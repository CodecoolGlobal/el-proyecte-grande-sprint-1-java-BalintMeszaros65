import React from 'react';
import './HomeWithLoggedIn.css';

export function HomeWithLoggedIn() {
    //                <Logged In Version of Home>
    return (
        <div className={'home-container'}>
            <div className={'project-form-container'}>
                <form>
                    <p>*Team name</p>
                    <input type={'text'} name={'teamName'} placeholder={'Enter your Team name'} />
                    <p>*Project name</p>
                    <input type={'text'} name={'projectName'} placeholder={'Enter your Project name'} />
                    <p>*Git Repository</p>
                    <input type={'text'} name={'gitRepository'} placeholder={'Enter your Git Repository'} />
                    <p>Team members</p>
                    <input type={'text'} name={'teamMembers'} placeholder={'Enter your team members'} />
                    <p className={'required-field-text'}>* is a required field</p>
                    <button>New Project</button>
                </form>
            </div>
        </div>
    )
}