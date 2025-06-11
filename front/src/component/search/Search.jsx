import React, { useState, useEffect } from 'react';
import styles from './Search.module.css';
import { FaSearch } from "react-icons/fa";

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function Search() {

    const [novelName, setNovelName] = useState('');
    const [novelsFinded, setNovelsFinded] = useState([]);

    useEffect(() => {
        const search = async () => {
            if(novelName.length < 2) {
                setNovelsFinded([]);
                return;
            }

            try {
                const response = await axios.get(`${API_URL}/novels/search/${novelName}`);
                setNovelsFinded(response.data);
            } catch(error) {
                console.log('Error ao tentar buscar novels ', error.errorMessage);
            }
        };

        const delay = setTimeout(search, 100);

        return () => clearTimeout(delay);
    }, [novelName]);

    return (
        <div className={styles.searchContainer} >
            <input 
                type="text" 
                placeholder='Buscar...'
                onChange={(e) => setNovelName(e.target.value)}
            />
            <FaSearch className={styles.searchIncon} />
            {novelsFinded.length > 0 && (
                <ul className={styles.ul}>
                    {novelsFinded.map(novel => (
                        <li className={styles.li}>{novel.novelName}</li>
                    ))}
                </ul>
            )

            }
        </div>
    );
}