import React from 'react';
import styles from './UserRegister.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';

export default function UserRegister() {
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
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                        <Link to="/"><button type="button" class="btn btn-danger">Cancelar</button></Link>
                    </div>
                </form>
            </div>
        </div>
    );
}