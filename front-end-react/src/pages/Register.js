import React, {useState} from 'react';
import './Register.css';
import {Grid, Paper, Typography, TextField, Button} from '@mui/material';


export function Register(){
    const [emailValue, setEmailValue] = useState('');
    const [emailValid, setEmailValid] = useState('');

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
                <form>
                    <TextField fullWidth label={'Git Profile'} className={'text-field'} placeholder={'Enter your Git profile'}/>
                    <TextField fullWidth label={'Journey Profile'} className={'text-field'} placeholder={'Enter your Journey profile'}/>
                    <TextField fullWidth label={'Username'} className={'text-field'} placeholder={'Enter your Username'}/>
                    <TextField fullWidth label={'E-mail'} className={'text-field'} placeholder={'Enter your E-mail'}  onChange={
                        (emailValue) =>  {
                            setEmailValue(emailValue.target.value)
                            validateEmail()
                        }
                    }/>
                    <TextField fullWidth label={'Password'} className={'text-field'} placeholder={'Enter your Password'}/>
                    <TextField fullWidth label={'Password again'} className={'text-field'} placeholder={'Enter your Password again'}/>
                    <Button type={'submit'} variant={'contained'} color={'primary'}>Sign up</Button>
                </form>
                <p>{emailValue}</p>
                <p>{emailValid}</p>
            </Paper>
        </Grid>

    )
}