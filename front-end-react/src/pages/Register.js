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
/*
    componentDidMount() {
        // Simple POST request with a JSON body using fetch
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ title: 'React POST Request Example' })
        };
        fetch('https://reqres.in/api/posts', requestOptions)
            .then(response => response.json())
            .then(data => this.setState({ postId: data.id }));
    }
 */

    async function fetchForCheckEmail(){

            let emailForBackendCheck = { 'email' : emailValue};

        const requestOptions = {
            method: 'POST',
            mode: 'cors',
            headers: { 'Content-Type': 'application/json',}

        };

        console.log(requestOptions)
        //      ASYNC FETCH
        const response = await fetch('http://10.44.22.187:8080/api/user/registration/check-email/megaseves@gmail.com',
            requestOptions);
        const data = await response.json();
        console.log(data);

        /* //       NORMAL FETCH
        fetch('https:.....', requestOptions)
            .then(response => response.json())
            .then(data => console.log(data));

         */
    }



    // todo
    function sendRegistrationData() {

        const request = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify()
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

                {(isFormFilled()) && <button type={'submit'} onClick={async (e) => {
                    e.preventDefault()
                    await fetchForCheckEmail()
                }}>
                    Sign up
                </button>}

            </form>
        </div>
    )
}