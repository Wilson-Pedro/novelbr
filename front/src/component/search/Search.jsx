import React from 'react';
import styles from './Search.module.css';
import { FaSearch } from "react-icons/fa";


export default function Search() {
    return (
        <>
            <input type="text" placeholder='Buscar...'/>
            <FaSearch className={styles.searchIncon} />
        </>
    );
}