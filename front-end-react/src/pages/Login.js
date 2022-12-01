import React, {useState} from 'react';
import './Login.css';

export function Login(){

    const [userNameFormValue, setUserNameFormValue] = useState('');
    const [passwordFormValue, setPasswordFormValue] = useState('');

    function isFormFilled(){
        // todo implement
        console.log("check if forms filled")
    }

    function fetchForLogin(){
        // todo implement
        console.log("trying to fetch login")
    }


    return (
        <div className={'login-form-container'}>
            <form>
                <h2>Login</h2>

                <p>Username</p>
                <input type="text"  name={'username'} placeholder={'Enter your Username'}
                onChange={(value) => setUserNameFormValue(value.target.value)}/>

                <p>Password</p>
                <input type="text"  name={'password'} placeholder={'Enter your Password'}
                       onChange={(value) => setPasswordFormValue(value.target.value)}/>

                {(isFormFilled()) && <button type={'submit'} onClick={async (e) => {
                    e.preventDefault()
                    await fetchForLogin()
                }}>
                    Sign up
                </button>}

            </form>
        </div>
    )
}