import React from 'react';
import styles from './NovelRegister.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Link } from 'react-router-dom';

export default function NovelRegister() {
    return(
        <div className={styles.container}>
            <div className={styles.formContainer}>
                <div className={styles.divLogin}>
                    <h3>CRIAR NOVA HISTÓRIA</h3>
                </div>
                <form>
                    <div className={styles.inputContainer}>
                        <label htmlFor="">Nome da Obra:</label><br />
                        <input
                            type="text"
                            placeholder="nome da obra"
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Autor</label><br />
                        <input
                            type="text"
                            placeholder="Autor"
                            disabled
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Sinopse:</label><br />
                        <input
                            type="text"
                            placeholder="Sinopse"
                            required
                        />
                    </div>

                    {/* <div className={styles.inputContainer}>
                        <label htmlFor="">Senha:</label><br />
                        <input
                            type="password"
                            placeholder="Senha"
                            required
                        />
                    </div> */}

                    <div className={styles.buttonContainer}>
                        <button type="submit" class="btn btn-primary">Cadastrar</button>
                        <Link to="/homeUser"><button type="button" class="btn btn-danger">Cancelar</button></Link>
                    </div>
                </form>
            </div>
        </div>
    );
}