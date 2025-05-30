import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './Table.module.css';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function Table() {

    const [lastChapters, setLastChapters] = useState([]);

    useEffect(() => {
        const fetchLastChapters = async () => {
            try { 
                const response = await axios.get(`${API_URL}/chapters/lastChapters`);
                setLastChapters(response.data);
            } catch(error) {
                console.log('Error ao buscar últimos capítulos: ', error.errorMessage)
            }
        }
        fetchLastChapters();
    }, [])

    return(
        <div className="container mt-4">
            {/* <h2 className="mb-3">Mais Populares</h2> */}
            <table className="table table-striped table-hover">
                <thead className="table-light"> 
                    <tr>
                        <th>Obra</th>
                        <th>Títutlo</th>
                        <th>Capítulo</th>
                    </tr>
                </thead>
                <tbody>
                    {lastChapters.map((chapter, index) => (
                        <tr index={index}>
                            <th>{chapter.novelName}</th>
                            <th>{chapter.title}</th>
                            <th className={styles.chapterNumber}>{chapter.chapterNumber}</th>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}