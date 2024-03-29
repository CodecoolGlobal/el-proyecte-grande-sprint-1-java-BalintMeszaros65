import React, {useState} from 'react';
import './Login.css';
import {useNavigate} from "react-router-dom";
import {getTokenForCurrentUser} from "../components/RouteGuard";

export function Login(props) {


    const navigate = useNavigate();


    // username is email, it's called `username` because of backend
    const [formData, setFormData] = useState(
        {
            'email': '',
            'password': ''
        });
    const [badLogin, setBadLogin] = useState(false);



    function isFormFilled() {
        return formData['email'].length > 2
                && formData['password'].length > 2
                && validateEmail();
    }

    async function fetchForLogin() {


        setBadLogin(false);
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        };

        await fetch('/api/user/login', requestOptions)
            .then(response => {
                if (response.ok) {
                    return response;
                } else {
                    rejectLogin();
                }
            }).then(data => data.text())
            .then(data => {
                props.setToken([{'token': data}])
                localStorage.setItem('token', JSON.stringify(data));
                navigate('/');

            })
    }

    function rejectLogin() {
        formData['email'] = '';
        formData['password'] = '';
        setBadLogin(true);
    }

    function validateEmail() {
        return (/\S+@\S+\.\S+/.test(formData['email']));
    }
    // TODO input onChange lambda to useEffect
    return (
        <div className={'login-form-container'}>
            <form>
                <h2>Login</h2>
                {(badLogin) && <p className={'error'}><small>E-Mail or Password is invalid!</small></p>}
                <p>E-mail</p>
                <input value={formData['email']} type="text" name={'email'} placeholder={'Enter your E-mail'}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'email': value.target.value}))
                       }}/>

                <p>Password</p>
                <input value={formData['password']} type="password" name={'password'} placeholder={'Enter your Password'}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'password': value.target.value}))
                       }}/>

                {(isFormFilled()) && <button type={'submit'} onClick={async (e) => {
                    e.preventDefault()
                    await fetchForLogin()
                }}>
                    Login
                </button>}
            </form>
        </div>
    )
}