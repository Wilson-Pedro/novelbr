import React from 'react';
import styles from './Navbar.module.css';
import Search from './../../component/search/Search';
import  { Link } from 'react-router-dom';

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
                <p><Link className={styles.linkNone} to="/register">Casdastrar</Link></p>
                <p><Link className={styles.linkNone} to="/login">Login</Link></p>
            </nav>
        </>
    );
}