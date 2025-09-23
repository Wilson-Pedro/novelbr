import React, { useState } from 'react';
import styles from './UserRegister.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { useNavigate } from 'react-router-dom';

import axios from 'axios';
const API_URL = process.env.REACT_APP_API;

export default function UserRegister() {

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const submitUser = async (e:any) => {
        e.preventDefault();

        try {
            const response = await axios.post(`${API_URL}/authors/`, {
                name,
                username,
                email,
                password,
            });
            //console.log(response)
            navigate('/login');
        } catch(error) {
            console.error(error)
        }
    };

    function goToHome() {
        navigate('/');
    }

    return(
        <div className={styles.container}>
            <div className={styles.formContainer}>
                <div className={styles.divUserRegister}>
                    <h3>CADASTRO</h3>
                </div>
                <form onSubmit={submitUser}>
                    <div className={styles.inputContainer}>
                        <label htmlFor="">Nome Completo:</label><br />
                        <input
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            placeholder="nome completo"
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Usuário</label><br />
                        <input
                            type="text"
                            value={username}
                            onChange={(e)  => setUsername(e.target.value)}
                            placeholder="usuário"
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Email:</label><br />
                        <input
                            type="email"
                            value={email}
                            onChange={(e)  => setEmail(e.target.value)}
                            placeholder="email"
                            required
                        />
                    </div>

                    <div className={styles.inputContainer}>
                        <label htmlFor="">Senha:</label><br />
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            placeholder="Senha"
                            required
                        />
                    </div>
                    <div className={styles.buttonContainer}>
                        <button type="submit" className="btn btn-primary">Cadastrar</button>
                        <button onClick={goToHome} type="button" className="btn btn-danger">Cancelar</button>
                    </div>
                </form>
            </div>
        </div>
    );
}