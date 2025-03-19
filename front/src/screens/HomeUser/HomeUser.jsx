import React from 'react';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import Card from '../../component/cards/Card';
import styles from './../Home/Home.module.css';
import Table from '../../layout/table/Table';
import imagePath1 from '../../assets/A_casa_ao_lado.jpg';
import imagePath2 from '../../assets/Isto é vida.jpg';
import imagePath3 from '../../assets/Trem para o Nunca.jpg';
import imagePath4 from '../../assets/Jornada para o Além.jpg';

export default function HomeUser() {

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
                    <Card
                        imagePath={imagePath1}
                        title="A casa ao Lado"
                        author="All Star"
                        userAuthenticate={true}
                    />
                    <Card
                        imagePath={imagePath2}
                        title="Isto é Vida"
                        author="J. Key"
                        userAuthenticate={true}
                    />
                    <Card
                        imagePath={imagePath3}
                        title="Trem para o Nunca"
                        author="Light"
                        userAuthenticate={true}
                    />
                    <Card
                        imagePath={imagePath4}
                        title="Jornada para o Além"
                        author="S. Elppa"
                        userAuthenticate={true}
                    />
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