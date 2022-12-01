import React, {useState} from 'react';
import './Register.css';
import {useNavigate} from "react-router-dom";


export function Register() {
    const navigate = useNavigate();

    const [formData, setFormData] = useState(
        {
            'gitProfile': '',
            'journeyProfile': '',
            'userName': '',
            'email': '',
            'password': ''
        });
    const [passwordValue2, setPasswordValue2] = useState('');
    const [badRegister, setBadRegister] = useState(false);


    function isFormFilled() {
        return ((formData['password'] === passwordValue2)
            && formData['password'].length >= 3
            && formData['gitProfile']
            && formData['journeyProfile']
            && formData['userName'].length >= 3
            && validateEmail());
    }


    function validateEmail() {
        return /\S+@\S+\.\S+/.test(formData['email']);
    }


    async function fetchForCheckEmail() {

        const requestOptions = {
            method: 'POST',
            mode: 'cors',
            headers: {'Content-Type': 'application/json',}

        };

        //      ASYNC FETCH
        const response = await fetch(`http://localhost:8080/api/user/registration/check-email/${formData['email']}`,
            requestOptions);
        return await response.json();
    }

    async function sendRegisterForm() {
        const requestOptions = {
            method: 'POST',
            mode: 'cors',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(formData)
        };

        await fetch(`http://localhost:8080/api/user/registration/`, requestOptions)
    }

    function rejectRegister() {
        setPasswordValue2('');
        formData['email'] = '';
        setBadRegister(true);
    }

    return (
        <div className={'reg-form-container'}>
            <form>
                <h2>Sign Up</h2>

                <p>Git Profile</p>
                <input value={formData['gitProfile']} type={'text'} name={'gitProfile'}
                       placeholder={'Enter your Git profile'}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'gitProfile': value.target.value}))
                       }}/>

                <p>Journey Profile</p>
                <input value={formData['journeyProfile']} type={'text'} name={'journeyProfile'}
                       placeholder={'Enter your Journey profile'}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'journeyProfile': value.target.value}))
                       }}/>

                <p>Username</p>
                <input value={formData['userName']} type={'text'} name={'userName'} placeholder={'Enter your Username'}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'userName': value.target.value}))
                       }}/>

                <p>E-mail</p>
                <input value={formData['email']} type={'text'} name={'email'} placeholder={'Enter your E-mail'}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'email': value.target.value}))
                       }}/>
                {(badRegister) && <p className={'error'}><small>This E-Mail is already in use!</small></p>}

                <p>Password</p>
                <input value={formData['password']} type={'password'} name={'password'}
                       placeholder={'Enter your Password'}
                       onChange={(value) => {
                           setFormData(prevFormData => ({...prevFormData, 'password': value.target.value}))
                       }}/>

                <p>Password</p>
                <input value={passwordValue2} type={'password'} name={'passwordRe'}
                       placeholder={'Enter your Password again'}
                       onChange={(value) => {
                           setPasswordValue2(value.target.value)
                       }}/>

                {(isFormFilled()) && <button type={'submit'} onClick={async event => {
                    event.preventDefault()
                    if (await fetchForCheckEmail()) {
                        await sendRegisterForm().then(() => navigate('/'));
                        setBadRegister(false);
                    } else {
                        rejectRegister();
                    }
                }}>
                    Sign up
                </button>}

            </form>
        </div>
    )
}