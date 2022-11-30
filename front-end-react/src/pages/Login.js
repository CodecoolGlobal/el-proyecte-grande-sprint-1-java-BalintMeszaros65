import React from 'react';
import {Grid, Paper, Typography, TextField, Button} from '@mui/material';
import '../components/Navbar.css';

export function Login(){
    return (

        <Grid className={'grid'}>
            <Paper className={'paper'}>
                <Grid align={'center'}>
                    <h2>Sign In</h2>
                    <Typography className={'typography'} variant={'caption'}>Fill this form for login!</Typography>
                    <form>
                        <TextField fullWidth label={'Username'} className={'text-field'} placeholder={'Enter your username'} />
                        <TextField fullWidth label={'Password'} className={'text-field'} placeholder={'Enter your password'}/>
                        <Button type={'submit'} variant={'contained'} color={'primary'}>Sign In</Button>
                    </form>
                </Grid>
            </Paper>
        </Grid>
    )
}