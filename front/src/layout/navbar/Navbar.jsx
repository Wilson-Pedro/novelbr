import React from 'react';
import styles from './Navbar.module.css';

export default function Navbar() {
    return(
        <div className={styles.navContainer}>
            <div className={styles.title}>
                <h1>NOVELS BR</h1>
            </div>
            <div className={styles.search}>
                pesqusar...
            </div>
            <nav>
                <p >Casdastrar</p>
                <p>Login</p>
            </nav>
        </div>
    );
}