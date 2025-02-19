import React from 'react';
import Navbar from '../../layout/navbar/Navbar';
import styles from './Home.module.css';

export default function Home() {
    return(
        <div className={styles.container}>
            <nav>
                <Navbar />
            </nav>
        </div>
    );
}