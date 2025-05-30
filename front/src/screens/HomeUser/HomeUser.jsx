import React, {useState, useEffect} from 'react';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import Card from '../../component/cards/Card';
import styles from './../Home/Home.module.css';
import Table from '../../layout/table/Table';

import axios from 'axios';

import { Navigate } from 'react-router-dom';

const API_URL = process.env.REACT_APP_API;

export default function HomeUser() {

    const [novelCards, setNovelCards] = useState([])

    useEffect(() => {
        const fetchNovelCards = async () => {
            try {
                const response = await axios.get(`${API_URL}/novels/novelCards`);
                setNovelCards(response.data);
            } catch(error) {
                console.log(error.errorMessage)
            }
        }

        fetchNovelCards();
    }, [])

    const token = localStorage.getItem('token');
    if(!token) return <Navigate to="/login" /> 

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={true}
                /> 
            </nav>
            <section>
                <h1>Sua História começa aqui!</h1>
            </section>
            <div className={styles.lastReleases}>
                <div className={styles.divTitle}>
                    <h1>MAIS POPULARES</h1>
                </div>
                <div className={styles.cardContainer}>
                    {novelCards.map((novelCard, index) => (
                        <Card
                            index={index}
                            authorId={novelCard.authorId}
                            novelId={novelCard.novelId}
                            imagePath={novelCard.imageUri}
                            title={novelCard.novelName}
                            author={novelCard.username}
                            userAuthenticate={true}
                        /> 
                    ))}
                </div>
            </div>
            <div className={styles.morePopular}>
                <div className={styles.divTitle}>
                    <h1>ÚLTIMOS LANÇAMENTOS</h1>
                </div>
                <Table />
            </div>
            <Footer />
        </div>
    );
}