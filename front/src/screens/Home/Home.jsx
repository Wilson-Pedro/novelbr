import React from 'react';
import Navbar from '../../layout/navbar/Navbar';
import styles from './Home.module.css';


export default function Home() {
    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar />
            </nav>
            <section>
                <h1>Sua História começa aqui!</h1>
            </section>
            <div className={styles.banner}>
            </div>
        </div>
    );
}