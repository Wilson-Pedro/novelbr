import React from 'react';
import styles from './UserRegister.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { useNavigate } from 'react-router-dom';

export default function UserRegister() {

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
                    <h3>CADASTRO</h3>
                </div>
                <form>
                    <div className={styles.inputContainer}>
                        <label htmlFor="">Nome Completo:</label><br />
                        <input
                            type="text"
                            placeholder="nome completo"
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Usuário</label><br />
                        <input
                            type="text"
                            placeholder="usuário"
                            required
                        />
                    </div>

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
                        <button onClick={goToHomeUser} type="submit" class="btn btn-primary">Cadastrar</button>
                        <button onClick={goToHome} type="button" class="btn btn-danger">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    );
}