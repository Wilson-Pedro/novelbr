import React from 'react';
import Navbar from '../../layout/navbar/Navbar';
import Card from '../../component/cards/Card';
import styles from './Home.module.css';
import Table from './../../layout/table/Table';
import imagePath1 from '../../assets/A_casa_ao_lado.jpg';
import imagePath2 from '../../assets/Isto é vida.jpg';
import imagePath3 from '../../assets/Trem para o Nunca.jpg';
import imagePath4 from '../../assets/Jornada para o Além.jpg';

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
                <div className={styles.divTitle}>
                    <h1>ÚLTIMOS LANÇAMENTOS</h1>
                </div>
                <div className={styles.cardContainer}>
                    <Card
                        imagePath={imagePath1}
                        title="A casa ao Lado."
                        charpter={23}
                    />
                    <Card
                        imagePath={imagePath2}
                        title="Isto é Vida."
                        charpter={103}
                    />
                    <Card
                        imagePath={imagePath3}
                        title="Trem para o Nunca."
                        charpter={70}
                    />
                    <Card
                        imagePath={imagePath4}
                        title="Jornada para o Além."
                        charpter={16}
                    />
                </div>
            </div>
            <div className={styles.morePopular}>
                <div className={styles.divTitle}>
                    <h1>MAIS POPULARES</h1>
                </div>
                <Table />
            </div>

        </div>
    );
}