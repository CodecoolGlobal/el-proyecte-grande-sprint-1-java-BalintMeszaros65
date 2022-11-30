import React, {useState} from 'react';
import './Register.css';


export function Register() {

    const [gitFormValue, setGitFormValue] = useState('');
    const [journeyFormValue, setJourneyFormValue] = useState('');
    const [useNameFormValue, setUserNameFormValue] = useState('');

    const [passwordValue1, setPasswordValue1] = useState('');
    const [passwordValue2, setPasswordValue2] = useState('');

    const [emailValue, setEmailValue] = useState('');
    const [emailValid, setEmailValid] = useState(false);


    function isFormFilled() {
        return ((passwordValue1 === passwordValue2)
            && passwordValue1      //    .length > 0       not needed, works the same:
            && gitFormValue
            && journeyFormValue
            && useNameFormValue
            && emailValid);
    }


    function validateEmail() {
        if (/\S+@\S+\.\S+/.test(emailValue)) {
            setEmailValid(true); //TODO switch to @sendEmailToCheck
        } else {
            setEmailValid(false);
        }
    }


    // todo to function
    const sendEmailToCheck = () => {
        fetch(`http://localhost:8080/api/user/registration/check-email/${emailValue}`)
            .then()
    }

    // todo
    function sendRegistrationData(form) {
        console.log(form.email);
        const request = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(form)
        }
        // fetch()
    }


    return (
        <div className={'reg-form-container'}>
            <form onSubmit={(form) => sendRegistrationData(form.target)}>
                <h2>Sign Up</h2>

                <p>Git Profile</p>
                <input type={'text'} name={'gitProfile'} placeholder={'Enter your Git profile'}
                       onChange={(value) => setGitFormValue(value.target.value)}/>

                <p>Journey Profile</p>
                <input type={'text'} name={'journeyProfile'} placeholder={'Enter your Journey profile'}
                       onChange={(value) => setJourneyFormValue(value.target.value)}/>

                <p>Username</p>
                <input type={'text'} name={'userName'} placeholder={'Enter your Username'}
                       onChange={(value) => setUserNameFormValue(value.target.value)}/>

                <p>E-mail</p>
                <input type={'text'} name={'email'} placeholder={'Enter your E-mail'}
                       onChange={(value) => {
                           setEmailValue(value.target.value)
                           validateEmail()
                       }}/>

                <p>Password</p>
                <input type={'password'} name={'password'} placeholder={'Enter your Password'}
                       onChange={(value) => {
                           setPasswordValue1(value.target.value)
                       }}/>

                <p>Password</p>
                <input type={'password'} name={'passwordRe'} placeholder={'Enter your Password again'}
                       onChange={(value) => {
                           setPasswordValue2(value.target.value)
                       }}/>

                {(isFormFilled()) && <button type={'submit'}>
                    Sign up
                </button>}

            </form>
        </div>
    )
}