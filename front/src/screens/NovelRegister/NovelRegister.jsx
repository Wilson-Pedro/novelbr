import React, { useState, useEffect } from 'react';
import styles from './NovelRegister.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import { Link, useNavigate } from 'react-router-dom';

import axios from 'axios';

import 'bootstrap/dist/css/bootstrap.min.css';

export default function NovelRegister() {

    const [novelName, setNovelName] = useState('');
    const [genders, setGenders] = useState([]);
    const [synopsis, setSynopsis] = useState('');
    const [authorId, setAuthorId] = useState(0);

    const navigate = useNavigate();

    const[gendersBackend, setGendersBackend] = useState([]);

    useEffect(() => {

        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('userId');

        if(!token) return navigate('/login');

        setAuthorId(userId);
        //setTokenUser(token)

        const fetchGenders = async () => {
            try {
                const response = await axios.get("http://localhost:8080/genders", {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                })
                setGendersBackend(response.data);
            } catch(error) {
                console.log("Error ao buscar gêneros: ", error)
            }
        }

        fetchGenders();
    }, [navigate]); 

    const submitNovel = async () => {
        const token = localStorage.getItem('token');
        try {
            await axios.post("http://localhost:8080/novels/", {
                novelName,
                authorId,
                genders,
                synopsis
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
        } catch (error) {
            console.log(error.errorMessage)
        }
    }

    function addGenders(e) {
        const gender = e.target.value;
        const newGenders = [...genders, gender];
        setGenders(newGenders);
    }

    return (
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar
                    userAuthenticate={true}
                />
            </nav>
            <div className={styles.main}>
                <form onSubmit={submitNovel}>
                    <div className={styles.formDiv}>
                        <label>Nome da Obra </label>
                        <input
                            type="text"
                            className="form-control"
                            value={novelName}
                            onChange={(e) => setNovelName(e.target.value)}
                            placeholder="Nome da Obra"
                            aria-label="Nome da Obra"
                            aria-describedby="basic-addon1"
                            required
                        />
                    </div>

                    <div className={styles.formDiv}>
                        <label>Gênros</label>
                    </div>
                    <div className={styles.div_genders}>
                        {gendersBackend.map((gender, index) => (
                            <div className="form-check" key={index}>
                                    <input className="form-check-input" type="checkbox" value={gender.genderType} onChange={(e) => addGenders(e)} />
                                    <label>
                                        {gender.genderType}
                                    </label>
                            </div>
                        ))}
                    </div>

                    <div className={styles.formDiv}>
                        <label>Sinopse</label>
                        <textarea
                            className="form-control"
                            value={synopsis}
                            onChange={(e) => setSynopsis(e.target.value)}
                            placeholder="Sinopse"
                            id="floatingTextarea2"
                            required
                        >
                        </textarea>
                    </div>

                    <div className={styles.divBtn}>
                        <button type="submit" className="btn btn-primary">Criar</button>
                        <button type="button" className="btn btn-danger">
                            <Link className={styles.linkNone} to="/profile">Cancelar</Link>
                        </button>
                    </div>
                </form>
            </div>
            <Footer />
        </div>
    );
}