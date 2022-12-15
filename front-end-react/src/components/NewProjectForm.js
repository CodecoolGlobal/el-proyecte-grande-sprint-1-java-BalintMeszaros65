import React, {useState} from "react";
import {getTokenForCurrentUser} from "./RouteGuard";


export function NewProjectForm() {
    const [formData, setFormData] = useState(
        {
            'teamName': '',
            'projectName': '',
            'gitRepo': ''
        });

    function isFormFilled() {
        return formData['teamName'].length > 2
            && formData['projectName'].length > 2
            && formData['gitRepo'].length > 2
    }


    async function fetchForCreateNewProject() {

        const requestOptionsForNewProjectFetch = {
            method: 'POST',
            mode: 'cors',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        // TODO get rid of localhost
        const user_id = getTokenForCurrentUser();
        await fetch(`http://localhost:8080/api/project/save/${user_id}`, requestOptionsForNewProjectFetch);


    }

    return (
        <div className={'project-form-container'}>
            <form>
                <p>Team name</p>
                <input type={'text'} name={'teamName'} placeholder={'Enter your Team name'} value={formData['teamName']}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'teamName': value.target.value}))
                       }}/>
                <p>Project name</p>
                <input type={'text'} name={'projectName'} placeholder={'Enter your Project name'}
                       value={formData['projectName']}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'projectName': value.target.value}))
                       }}/>
                <p>Git Repository</p>
                <input type={'text'} name={'gitRepository'} placeholder={'Enter your Git Repo'}
                       value={formData['gitRepo']}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'gitRepo': value.target.value}))
                       }}/>
                {(isFormFilled()) && <button type={'submit'} onClick={async (e) => {
                    e.preventDefault()
                    await fetchForCreateNewProject()
                }}>
                    New Project
                </button>}
            </form>
        </div>
    )
}
