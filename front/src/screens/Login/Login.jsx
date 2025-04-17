import React, { useState } from 'react';
import styles from './Login.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { useNavigate } from 'react-router-dom';

import axios from 'axios';

const API = "http://localhost:8080";

export default function Login() {

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    

    const navigate = useNavigate();

    function goToHome() {
        navigate('/');
    }

    function goToHomeUser() {
        navigate('/homeUser');
    }

    const submitLogin = async (e) => {
        e.preventDefault();

        try {
            await axios.post("http://localhost:8080/auth/login", {
                email: email,
                password: password
            }, {
                withCredentials: true,
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            // await axios.post("http://localhost:8080/login",
            //     new URLSearchParams({ email, password }),
            //     { withCredentials: true }
            // ); 

            // const res = await axios.get(API + "/authors/me", {
            //         withCredentials: true,
            //     }
            // );
            // console.log('Usuário autenticado: ', res.data);
            goToHomeUser();

        } catch(error) {
            setError("Email ou Senha inválidos")
            console.log(error.errorMessage)
        }
    }

    return(
        <div className={styles.container}>
            <div className={styles.formContainer}>
                <div className={styles.divLogin}>
                    <h3>LOGIN</h3>
                </div>
                <p className={styles.errorMessage}>{error}</p>
                <form onSubmit={submitLogin}>
                    <div className={styles.inputContainer}>
                        <label htmlFor="">Email:</label><br />
                        <input
                            type="email"
                            placeholder="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
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