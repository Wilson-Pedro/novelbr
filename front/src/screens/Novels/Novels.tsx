import React, { useEffect, useState } from 'react';
import styles from './Novels.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Card from '../../component/cards/Card';
import Footer from '../../layout/footer/Rodape';
import { Link, Navigate, useParams, useNavigate } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import { NovelCard } from '../../interfaces/NovelInterfaces';
import { Page } from '../../interfaces/ChapterInterfaces';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

const Novels: React.FC = () => {

    const params = useParams();
    const navigate = useNavigate();

    const novelName = params.novelName || '';

    const [novelNameSearch, setNovelNameSearch] = useState<string>('');
    const page = 0;
    const size = 20;
    const [cards, setCards] = useState<NovelCard[]>([]);

    useEffect(() => {

        const findNovelCards = async () => {

            try {
                const response = await axios.get(`${API_URL}/novels/search/${novelName}?page=${page}&size=${size}`);
                const pageData: Page<NovelCard> = response.data;
                setCards(pageData.content);
            } catch(error) {
                console.log("Error ao buscar Card por Username: ", error);
            }

        }

        findNovelCards();
    }, [novelName]);

    useEffect(() => {

        const findNovelCardsByNovelName = setTimeout(() =>{
            axios.get(`${API_URL}/novels/search/${novelNameSearch}?page=${page}&size=${size}`)
            .then(response => {
                const pageData: Page<NovelCard> = response.data;
                setCards(pageData.content);
            }).catch(error => console.log("Error ao buscar Card por Username: ", error));

        }, 500); 
        return () => clearTimeout(findNovelCardsByNovelName);
    }, [novelNameSearch]);

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
                    <h1>Novels 📖</h1>
                </div>

                <div className={styles.searchNovel}>
                    <div className="input-group input-group-lg">
                        <span className="input-group-text" id="inputGroup-sizing-lg">Buscar Novel</span>
                        <input 
                            type="text" 
                            className="form-control" 
                            aria-label="Sizing example input" 
                            aria-describedby="inputGroup-sizing-lg"
                            value={novelNameSearch}
                            onChange={(e) => setNovelNameSearch(e.target.value)}
                        />
                    </div>
                </div>

                <hr />
                <div className={styles.divMain}>
                    {cards.length === 0 ? (
                        <>
                            <br/>
                            <p className={styles.pCenter}>Nenhuma Novel cadastrada.</p>
                        </>
                    ) : (
                        <div className={styles.cardContainer}>
                            {cards.map((card, index) => (
                                <Card
                                    index={index}
                                    authorId={card.authorId}
                                    novelId={card.novelId}
                                    imagePath={card.imageUri}
                                    novelName={card.novelName}
                                    author={card.username}
                                    userAuthenticate={true}
                                />
                            ))}
                        </div>
                    )}
                    <br/><br/>
                    <Link to="/homeUser"><button type="button" className="btn btn-danger">Voltar</button></Link>
                </div>
            </div>
            <Footer />
        </div>
    );
}

export default Novels;