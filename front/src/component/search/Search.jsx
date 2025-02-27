import React from 'react';
import styles from './Search.module.css';
import { FaSearch } from "react-icons/fa";


export default function Search() {
    return (
        <div className={styles.searchContainer} >
            <input type="text" placeholder='Buscar...'/>
            <FaSearch className={styles.searchIncon} />
        </div>
    );
}