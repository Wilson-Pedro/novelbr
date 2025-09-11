import React, { useEffect, useState } from 'react';
import styles from './AuthorNovel.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Card from '../../component/cards/Card';
import Footer from '../../layout/footer/Rodape';
import { Link, Navigate, useParams, useNavigate } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import { NovelCard } from '../../interfaces/NovelInterfaces';
import { Author } from '../../interfaces/AuthorInterfaces';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function AuthorNovel() {

    const [cards, setCards] = useState<NovelCard[]>([]);
    const [author, setAuthor] = useState<Author>({} as Author);
    const params = useParams();
    const username = params.username;

    const navigate = useNavigate();

    useEffect(() => {

        const token = localStorage.getItem('token');

        if(!token) {
            navigate('/login');
            return;
        } 

        const fetchNovelCardByUsername = async () => {

            try {
                const response = await axios.get(`${API_URL}/novels/novelCards/author/${username}`);
                setCards(response.data);
            } catch(error) {
                console.log("Error ao buscar Card por Username: ", error);
            }

        }

        const fetchAuthorInfo = async () => {

            try {
                const response = await axios.get(`${API_URL}/authors/username/${username}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setAuthor(response.data);
            } catch(error) {
                console.log(error);
            }

        }

        fetchNovelCardByUsername();
        fetchAuthorInfo();
    }, [username]);

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
                    <h1>Novels do autor {author.username} 📖</h1>
                </div>

                <hr />
                <div className={styles.divMain}>
                    {cards.length === 0 ? (
                        <>
                            <br/>
                            <p className={styles.pCenter}>{author.username} possui obras cadastradas.</p>
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