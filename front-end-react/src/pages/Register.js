import React, {useState} from 'react';
import './Register.css';
import {Button, Grid, Paper, TextField, Typography} from '@mui/material';

function sendRegistrationData () {
    const request = {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify()
    }
}




export function Register(){
    const [emailValue, setEmailValue] = useState('');


    const [emailValid, setEmailValid] = useState('');

   /*
    const sendEmailToCheck =  () => {
        // teszt   http://localhost:8080/api/user/registration     all data
        fetch(`http://localhost:8080/api/user/email-check?email=${emailValue}`)
            .then(res => res.json())
            .then( //json => //this.setState({ data: json }));
    }
*/

    const validateEmail = () => {
        if (/\S+@\S+\.\S+/.test(emailValue)) {
            setEmailValid('Valid Email :)');
        } else {
            setEmailValid('Enter valid email!')
        }
    }
    return (
        <Grid className={'grid'}>
            <Paper className={'paper'}>
                <Grid align={'center'}>
                    <h2>Sign Up</h2>
                    <Typography className={'typography'} variant={'caption'}>Fill this form for create an account!</Typography>
                </Grid>
                <form action={'http://localhost:8080/api/user/registration'} method={'POST'} encType={'multipart/form-data'} >
                    <TextField name={'gitProfile'} fullWidth label={'Git Profile'} className={'text-field'} placeholder={'Enter your Git profile'}/>
                    <TextField name={'journeyProfile'} fullWidth label={'Journey Profile'} className={'text-field'} placeholder={'Enter your Journey profile'}/>
                    <TextField name={'userName'} fullWidth label={'Username'} className={'text-field'} placeholder={'Enter your Username'}/>
                    <TextField name={'email'} fullWidth label={'E-mail'} className={'text-field'} placeholder={'Enter your E-mail'}  onChange={
                        (emailValue) =>  {
                            setEmailValue(emailValue.target.value)
                            validateEmail()
                        }
                    }/>
                    <TextField name={'password'} fullWidth label={'Password'} className={'text-field'} placeholder={'Enter your Password'}/>
                    <TextField fullWidth label={'Password again'} className={'text-field'} placeholder={'Enter your Password again'}/>
                    <Button className={'hiddenRegisterButton'} type={'submit'} variant={'contained'} color={'primary'} >Sign up</Button>
                </form>
                <p>{emailValue}</p>
                <p>{emailValid}</p>
            </Paper>
        </Grid>

    )
}