import React, {useState, useEffect} from 'react';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import Card from '../../component/cards/Card';
import styles from './Home.module.css';
import Table from '../../layout/table/Table';

import 'bootstrap/dist/css/bootstrap.min.css';

import { NovelCard } from '../../interfaces/NovelInterfaces';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function Home() {

    const [novelCards, setNovelCards] = useState<NovelCard[]>([]);

    useEffect(() => {
        const fetchNovelCards = async () => {
            try {
                const response = await axios.get(`${API_URL}/novels/novelCards`);
                setNovelCards(response.data);
            } catch(error) {
                console.log(error)
            }
        }

        fetchNovelCards();
    }, [])

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar />
            </nav>
            <section>
                <h1>Sua História começa aqui!</h1>
            </section>
            <div className={styles.lastReleases}>
                <div className={styles.divTitle}>
                    <h1>NOVAS HISTÓRIAS</h1>
                </div>
                <div className={styles.cardContainer}>
                    {novelCards.map((novelCard, index) => (
                        <Card
                            index={index}
                            authorId={novelCard.authorId}
                            novelId={novelCard.novelId}
                            imagePath={novelCard.imageUri}
                            novelName={novelCard.novelName}
                            author={novelCard.username}
                            userAuthenticate={false}
                        />
                    ))}
                </div>
            </div>
            <div className={styles.morePopular}>
                <div className={styles.divTitle}>
                    <br />
                    <h1>ÚLTIMOS LANÇAMENTOS</h1>
                </div>
                <Table />
            </div>
            <Footer />
        </div>
    );
}