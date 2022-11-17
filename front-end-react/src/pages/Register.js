import React from 'react';
import {Grid, Paper, Typography, TextField, Button} from '@mui/material';


export function Register(){
    return (


        <Grid>
            <Paper>

                <Grid align={'center'}>
                    <h2>Sign Up</h2>
                    <Typography variant={'caption'}>Fill this form for create an account!</Typography>
                </Grid>
                <form>
                    <TextField fullWidth label={'Git Profile'} />
                    <TextField fullWidth label={'Journey Profile'} />
                    <TextField fullWidth label={'Username'} />
                    <TextField fullWidth label={'E-mail'} />
                    <TextField fullWidth label={'Password'} />
                    <TextField fullWidth label={'Password re'} />
                    <Button type={'submit'} variant={'contained'} color={'primary'}>Sign up</Button>
                </form>
            </Paper>
        </Grid>

    )
}