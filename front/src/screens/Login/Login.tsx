import React, { useState } from 'react';
import styles from './Login.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { useNavigate } from 'react-router-dom';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function Login() {

    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [error, setError] = useState<string>('');

    const navigate = useNavigate();

    localStorage.clear();

    function goToHome() {
        navigate('/');
    }

    function goToHomeUser() {
        navigate('/homeUser');
    }

    const submitLogin = async (e:any) => {
        e.preventDefault();

        try {
            const response = await axios.post(`${API_URL}/auth/login`, {
                login: username,
                password: password
            }, {
                withCredentials: true,
                headers: {
                    'Content-Type': 'application/json'
                }
            })

            if(response != null) {
                const data = response.data;
                localStorage.setItem('userId', data.userId)
                localStorage.setItem('token', data.token);
                localStorage.setItem('username', data.username)
            }

            goToHomeUser(); 
        } catch(error) {
            setError("Username ou Senha inválidos")
            console.log(error)
        }
    }

    return(
        <div className={styles.container}>
            <div className={styles.formContainer}>
                <div className={styles.divLogin}>
                    <h3>LOGIN</h3>
                </div>
                <p >{error}</p>
                <form onSubmit={submitLogin}>
                    <div className={styles.inputContainer}>
                        <label htmlFor="">Username:</label><br />
                        <input
                            type="text"
                            placeholder="username"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Senha:</label><br />
                        <input
                            type="password"
                            placeholder="Senha"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </div>

                    <div className={styles.buttonContainer}>
                        <button type="submit" className="btn btn-primary">Login</button>
                        <button onClick={goToHome} className="btn btn-danger">Voltar</button>
                    </div>
                </form>
            </div>
        </div>
    );
}