import React, { useState } from 'react';
import styles from './UserRegister.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { useNavigate } from 'react-router-dom';

import axios from 'axios';

export default function UserRegister() {

    const navigate = useNavigate();

    const [name, setName] = useState("");
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const submit = async () => {

        try {
            const response = await axios.post("http://localhost:8080/users/register", {
                name,
                username,
                email,
                password,
            });
            console.log(response)
        } catch(error) {
            console.error(error)
        }

        //goToHomeUser();
    };

    function goToHome() {
        navigate('/');
    }

    // function goToHomeUser() {
    //     navigate('/homeUser');  
    // }

    return(
        <div className={styles.container}>
            <div className={styles.formContainer}>
                <div className={styles.divLogin}>
                    <h3>CADASTRO</h3>
                </div>
                <form onSubmit={submit}>
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