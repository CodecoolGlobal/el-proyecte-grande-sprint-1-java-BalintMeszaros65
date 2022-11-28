import React, {useState} from 'react';
import './Register.css';

function sendRegistrationData(form) {
    console.log(form.email);
    // const request = {
    //     method: 'POST',
    //     headers: {'Content-Type': 'application/json'},
    //     body: JSON.stringify(form)
    // }
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
            setEmailValid(true);
        } else {
            setEmailValid(false)
        }
    }
    return (
        <div>
            <h2>Sign Up</h2>
            <form onSubmit={(form) => sendRegistrationData(form.target)}>
                <input type={'text'} name={'gitProfile'} placeholder={'Enter your Git profile'}/>
                <input type={'text'} name={'journeyProfile'} placeholder={'Enter your Journey profile'}/>
                <input type={'text'} name={'userName'} placeholder={'Enter your Username'}/>
                <input type={'text'} name={'email'} placeholder={'Enter your E-mail'}
                       onChange={
                           (value) => {
                               setTimeout(() => {
                                   setEmailValue(value.target.value)
                                   validateEmail()
                               }, 100);
                           }
                       }/>
                <input type={'password'} name={'password'} className={'text-field'}
                       placeholder={'Enter your Password'}/>
                <input type={'password'} name={'passwordRe'} placeholder={'Enter your Password again'}/>
                <button hidden={!emailValid} type={'submit'} color={'primary'}>
                    Sign up
                </button>
            </form>
        </div>
    )
}