import React, { useState, useEffect } from 'react';
import styles from './Chapter.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useLocation, useParams } from 'react-router-dom';

import axios from 'axios';

const API = "http://localhost:8080";

export default function Chapter() {

    const [chapterInfo, setChapterInfo] = useState({});

    const location = useLocation();
    const { isAuth } = location.state || {};

    const params = useParams();
    const novelName = params.novelName;
    const chapterNumber =  parseInt(params.chapterNumber);

        useEffect(() => {
    
            const fetchChapter = async () => {
                try {
                    const response = await axios.get(`${API}/chapters/${novelName}/${chapterNumber}`);
                    setChapterInfo(response.data);
                } catch(error) {
                    console.log("Error ao buscar capítulo: ", error)
                }
            }
            fetchChapter();
        }, []); 

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={isAuth}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.divTitle}>
                    <h1>{chapterInfo.title}</h1>
                </div>
                <div className={styles.divButtons}>
                    <button type="button" class="btn btn-warning">&#60; Anterior </button>
                    <button type="button" class="btn btn-warning">Próximo &#62;</button>
                </div>
                <div className={styles.divChapter}>
                    {chapterInfo.chapterText}
                </div> <br /><br />
                <div className={styles.divButtons}>
                    <button type="button" class="btn btn-warning">&#60; Anterior </button>
                    <button type="button" class="btn btn-warning">Próximo &#62;</button>
                </div>
            </div>
            <Footer />
        </div>
    );
}