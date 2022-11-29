import React, {useState} from 'react';
import './Register.css';

function sendRegistrationData(form) {
    console.log(form.email);
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(form)
    }
   // fetch()
}




export function Register() {
    const [emailValue, setEmailValue] = useState('');
    const [emailValid, setEmailValid] = useState(false);


    const sendEmailToCheck = () => {
        fetch(`http://localhost:8080/api/user/registration/check-email/${emailValue}`)
            .then()
    }


    const validateEmail = () => {
        if (/\S+@\S+\.\S+/.test(emailValue)) {
            setEmailValid(true); //TODO switch to @sendEmailToCheck
        } else {
            setEmailValid(false)
        }
    }
    return (
        <div className={'reg-form-container'}>
            <form onSubmit={(form) => sendRegistrationData(form.target)}>
                <h2>Sign Up</h2>
                <p>Git Profile</p>
                <input type={'text'} name={'gitProfile'} placeholder={'Enter your Git profile'}/>
                <p>Journey Profile</p>
                <input type={'text'} name={'journeyProfile'} placeholder={'Enter your Journey profile'}/>
                <p>Username</p>
                <input type={'text'} name={'userName'} placeholder={'Enter your Username'}/>
                <p>E-mail</p>
                <input type={'text'} name={'email'} placeholder={'Enter your E-mail'}
                       onChange={
                           (value) => {
                               setTimeout(() => {
                                   setEmailValue(value.target.value)
                                   validateEmail()
                               }, 100);
                           }
                       }/>
                <p>Password</p>
                <input type={'password'} name={'password'}
                       placeholder={'Enter your Password'}/>
                <p>Password</p>
                <input type={'password'} name={'passwordRe'} placeholder={'Enter your Password again'}/>
                <button hidden={!emailValid} type={'submit'}>
                    Sign up
                </button>
            </form>
        </div>
    )
}