import React, {useContext, useState} from "react";
import {useNavigate} from "react-router-dom";
import {cookiesContext} from "../App";


export function NewProjectForm(props) {
    const {cookies, setCookies} = useContext(cookiesContext);
    const navigate = useNavigate();
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
            headers: {
                'Authorization': `Bearer ${cookies.token}`,
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        }
        await fetch(`http://localhost:8080/api/project`, requestOptionsForNewProjectFetch);


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
                    await fetchForCreateNewProject().then(() => {
                        props.setHasClicked(true);
                        navigate('/', {state: {index: null}});
                    });
                }}>
                    New Project
                </button>}
            </form>
        </div>
    )
}
