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
            && passwordValue1.length > 4
            && gitFormValue
            && journeyFormValue
            && useNameFormValue.length > 4
            && emailValid);
    }


    function validateEmail() {
        setEmailValid(/\S+@\S+\.\S+/.test(emailValue));
    }


    async function fetchForCheckEmail() {

        const requestOptions = {
            method: 'POST',
            mode: 'cors',
            headers: {'Content-Type': 'application/json',}

        };

        //      ASYNC FETCH
        const response = await fetch(`http://localhost:8080/api/user/registration/check-email/${emailValue}`,
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
                {(badRegister) && <p className={'error'}><small>This E-Mail is already in use!</small></p>}

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