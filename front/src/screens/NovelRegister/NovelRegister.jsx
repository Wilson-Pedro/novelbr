import React, { useState, useEffect } from 'react';
import styles from './NovelRegister.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import { Link } from 'react-router-dom';

import axios from 'axios';

import 'bootstrap/dist/css/bootstrap.min.css';

export default function NovelRegister() {

    const [novelName, setNovelName] = useState('');
    const [author, setAuthor] = useState('');
    const [genders, setGenders] = useState([]);
    const [synopsis, setSynopsis] = useState('');

    const[gendersBackend, setGendersBackend] = useState([]);

    const submitNovel = async () => {
        try {
            const response = await axios.post("http://localhost:8080/novels/", {
                novelName,
                author,
                genders,
                synopsis
            })
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
        axios.get("http://localhost:8080/genders")
        .then((res) => {
            setGendersBackend(res.data);
        })
        .catch((error) => {
            console.log(error)
        })
    }, []);

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
                        <label>Nome da Obra</label>
                        <input
                            type="text"
                            class="form-control"
                            value={novelName}
                            onChange={(e) => setNovelName(e.target.value)}
                            placeholder="Nome da Obra"
                            aria-label="Nome da Obra"
                            aria-describedby="basic-addon1"
                            required
                        />
                    </div>

                    <div className={styles.formDiv}>
                        <label>Autor</label>
                        <input
                            type="text"
                            class="form-control"
                            value={author}
                            onChange={(e) => setAuthor(e.target.value)}
                            placeholder="Autor"
                            aria-label="Autor"
                            aria-describedby="basic-addon1"
                            required
                        />
                    </div>

                    <div className={styles.formDiv}>
                        <label>Gênros</label>
                    </div>
                    <div className={styles.div_genders}>
                        {gendersBackend.map((gender, index) => (
                            <div className="form-check">
                                    <input className="form-check-input" type="checkbox" value={gender.genderType} onChange={(e) => addGenders(e)} />
                                    <label>
                                        {gender.genderType}
                                    </label>
                            </div>
                        ))}
                        {/* <div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="ADVENTURE" onChange={(e) => addGenders(e)} />
                                <label class="form-check-label" for="checkDefault">
                                    Aventura
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="ACTION" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Ação
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="HISTORICAL" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Histórico
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="HORROR" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Horror
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="LGBTQ" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    LGBTQ
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="MAGICAL" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Mágico
                                </label>
                            </div>
                        </div>
                        <div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="MYSTERY" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Mistério
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="ADULT" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Adulto
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="ROMANCE" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Romance
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="SCIENCE_FICTION" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Ficção científica
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="MEDIEVAL" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Medieval
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="THRILLER" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Thriller
                                </label>
                            </div>
                        </div>
                        <div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="SUSPENSEFUL" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Suspense
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="WESTERN" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Velho Oeste
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="SCIENCE_FICTION" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Ficção científica
                                </label>
                            </div>
                            <div class="form-check">
                                <input className="form-check-input" type="checkbox" value="DYSTOPIA" onChange={(e) => addGenders(e)}  />
                                <label class="form-check-label" for="checkChecked">
                                    Distopia
                                </label>
                            </div>
                        </div> */}
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
                        <button type="submit" class="btn btn-primary">Criar</button>
                        <button type="button" class="btn btn-danger">
                            <Link className={styles.linkNone} to="/profile">Cancelar</Link>
                        </button>
                    </div>
                </form>
            </div>
            <Footer />
        </div>
    );
}