import React, {useState} from 'react';
import './Login.css';

export function Login(){

    const [emailFormValue, setEmailFormValue] = useState('');
    const [passwordFormValue, setPasswordFormValue] = useState('');

    function isFormFilled(){
        return emailFormValue.length > 2 && passwordFormValue.length > 2;
    }

    async function fetchForLogin(){

        let formData = {
            'email': emailFormValue,
            'password': passwordFormValue,
        }

        const requestOptions = {
            method: 'POST',
            mode: 'cors',
            headers: { 'Content-Type': 'application/json',},
            body: JSON.stringify(formData)
        };

        //      ASYNC FETCH
        const response = await fetch('http://localhost:8080/api/user/login',
            requestOptions);

    }


    return (
        <div className={'login-form-container'}>
            <form>
                <h2>Login</h2>

                <p>E-mail</p>
                <input type="text"  name={'email'} placeholder={'Enter your E-mail'}
                onChange={(value) => setEmailFormValue(value.target.value)}/>

                <p>Password</p>
                <input type="text"  name={'password'} placeholder={'Enter your Password'}
                       onChange={(value) => setPasswordFormValue(value.target.value)}/>

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