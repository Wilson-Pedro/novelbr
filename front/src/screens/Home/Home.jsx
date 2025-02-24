import React from 'react';
import Navbar from '../../layout/navbar/Navbar';
import Card from '../../component/cards/Card';
import styles from './Home.module.css';
import imagePath1 from '../../assets/A_casa_ao_lado.jpg';
import imagePath2 from '../../assets/Isto é vida.jpg';
import imagePath3 from '../../assets/Trem para o Nunca.jpg';

export default function Home() {
    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar />
            </nav>
            <section>
                <h1>Sua História começa aqui!</h1>
            </section>
            <div className={styles.lastReleases}>
                <Card 
                    imagePath={imagePath1}
                    title="A casa ao Lado."
                />

                <Card 
                    imagePath={imagePath2}
                    title="Isto é Vida."
                />

                <Card 
                    imagePath={imagePath3}
                    title="Trem para o Nunca."
                />
            </div>

        </div>
    );
}