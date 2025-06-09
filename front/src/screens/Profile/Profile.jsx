import React, { useEffect, useState } from 'react';
import styles from './Profile.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Card from './../../component/cards/Card';
import Footer from './../../layout/footer/Rodape';
import { Link, Navigate, useParams } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;
const IMG_PATH = process.env.REACT_APP_IMG_PATH;

export default function Profile() {

    const [cards, setCards] = useState([]);
    const params = useParams();
    const username = params.username;

    useEffect(() => {

        const fetchNovelCardByUsername = async () => {

            try {
                const response = await axios.get(`${API_URL}/novels/novelCards/author/${username}`);
                setCards(response.data);
            } catch(error) {
                console.log("Error ao buscar Card por Username: ", error.errorMessage);
            }

        }

        fetchNovelCardByUsername();
    }, []) 

    const token = localStorage.getItem('token');
    if(!token) return <Navigate to="/login"/>

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={true}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.divMain}>
                    <h1>Informações do Usuário ℹ️</h1>
                    <h4>Nome: <span className={styles.noWeight}>ROBERTO FIRMINO SILVA</span></h4>
                    <h4>Pseudônimo: <sapn className={styles.noWeight}>All Star</sapn></h4>
                    <h4>Email: <span className={styles.noWeight}>roberto@gmail.com</span></h4>
                </div>

                <hr />
                <div className={styles.divMain}>
                    <h1>Configurações ⚙️</h1>
                    <p className={styles.info}>Crie uma nova jornada.</p>
                    <button type="button" className="btn btn-success">
                        <Link className={styles.linkNone} to="/novelRegister">Criar História</Link>
                    </button>
                </div>

                <hr />
                <div className={styles.divMain}>
                    <h1>Minhas Obras 📖</h1>
                    {cards.length == 0 ? (
                        <>
                            <br/>
                            <p className={styles.pCenter}>Você não possui obras cadastradas.</p>
                        </>
                    ) : (
                        <div className={styles.cardContainer}>
                            {cards.map((card, index) => (
                                <Card
                                    index={index}
                                    authorId={card.authorId}
                                    novelId={card.novelId}
                                    imagePath={card.imageUri}
                                    title={card.novelName}
                                    author={card.username}
                                    userAuthenticate={true}
                                />
                            ))}
                        </div>
                    )}
                    <Link to="/homeUser"><button type="button" className="btn btn-danger">Voltar</button></Link>
                </div>
            </div>
            <Footer />
        </div>
    );
}