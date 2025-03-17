import React from 'react';
import styles from './Login.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { useNavigate } from 'react-router-dom';

export default function Login() {

    const navigate = useNavigate();

    function goToHome() {
        navigate('/');
    }

    function goToHomeUser() {
        navigate('/homeUser');
    }

    return(
        <div className={styles.container}>
            <div className={styles.formContainer}>
                <div className={styles.divLogin}>
                    <h3>LOGIN</h3>
                </div>
                <form>
                    <div className={styles.inputContainer}>
                        <label htmlFor="">Email:</label><br />
                        <input
                            type="email"
                            placeholder="email"
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Senha:</label><br />
                        <input
                            type="password"
                            placeholder="Senha"
                            required
                        />
                    </div>

                    <div className={styles.buttonContainer}>
                        <button onClick={goToHomeUser} type="submit" class="btn btn-primary">Login</button>
                        <button onClick={goToHome} class="btn btn-danger">Voltar</button>
                    </div>
                </form>
            </div>
        </div>
    );
}