import React, {useState} from 'react';
import './Login.css';
import {useNavigate} from "react-router-dom";

export function Login(props) {
    const navigate = useNavigate();

    const [formData, setFormData] = useState(
        {
            'email': '',
            'password': ''
        });
    const [badLogin, setBadLogin] = useState(false);
    const [loggedInUserID, setLoggedInUserId] = useState('');

    function isFormFilled() {
        return formData['email'].length > 2
                && formData['password'].length > 2
                && validateEmail();
    }

    async function fetchForLogin() {
        setBadLogin(false);
        const requestOptions = {
            method: 'POST',
            mode: 'cors',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(formData)
        };
           //   ASYNC FETCH
        // try {
            const data = await (await fetch('http://localhost:8080/api/user/login',
                requestOptions)).json();
            console.log(data)
            // setLoggedInUserId(data);
            // console.log(loggedInUserID);
            navigate('/');
        // } catch (e) {
          //  rejectLogin()
        // }

    }

    function rejectLogin() {
        formData['email'] = '';
        formData['password'] = '';
        setBadLogin(true);
    }

    function validateEmail() {
        return (/\S+@\S+\.\S+/.test(formData['email']));
    }

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