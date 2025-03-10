import React from 'react';
import styles from './Login.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';

export default function Login() {
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
                        <Link to="/homeUser"><button type="submit" class="btn btn-primary">Login</button></Link>
                        <Link to="/"><button type="button" class="btn btn-danger">Voltar</button></Link>
                    </div>
                </form>
            </div>
        </div>
    );
}