import React from 'react';
import styles from './Navbar.module.css';
import Search from './../../component/search/Search';

export default function Navbar() {
    return(
        <>
            <div className={styles.divTitle}>
                <h1>NOVELS BR</h1>
            </div>
            <div className={styles.search}>
                <Search />
            </div>
            <nav>
                <p>Casdastrar</p>
                <p>Login</p>
            </nav>
        </>
    );
}