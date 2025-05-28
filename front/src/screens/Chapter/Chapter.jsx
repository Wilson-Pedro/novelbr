import React, { useState, useEffect } from 'react';
import styles from './Chapter.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useLocation, useParams, useNavigate } from 'react-router-dom';

import axios from 'axios';

const API = "http://localhost:8080"; 

export default function Chapter() {

    const [chapterInfo, setChapterInfo] = useState({});
    const [maxChapterNumber, setMaxChapterNumber] = useState(1);
    const [novelId, setNovelId] = useState(0);

    const location = useLocation();
    const { isAuth } = location.state || {};

    const params = useParams();
    const novelName = params.novelName;
    const chapterNumber =  parseInt(params.chapterNumber);

    const navigate = useNavigate();

    function goToNovel() {
        navigate(`/novel/${chapterInfo.novelId}`);
    }

    useEffect(() => {

        const fetchChapter = async () => {
            try {
                const response = await axios.get(`${API}/chapters/${novelName}/${chapterNumber}`);
                setChapterInfo(response.data);
                setNovelId(response.data.novelId);
            } catch(error) {
                console.log("Error ao buscar capítulo: ", error.errorMessage)
            }
        }

        fetchChapter(); 
    }, [novelName, chapterNumber]);

    useEffect(() => {
        if(novelId !== 0) {
            const fetchMaxChapterNumber = async () => {
                try {
                    const response = await axios.get(`${API}/chapters/chapterNumber/novel/${novelId}`);
                    setMaxChapterNumber(response.data.chapterNumber)
                    console.log(response)
                } catch(error) {
                    console.log('Error ao buscar o último capítulo da Novel ', error.errorMessage)
                }
            }
            fetchMaxChapterNumber();
        }
    }, [novelId]);

    function goToChapter(chapterNumber) {
        navigate(`/novel/${novelName}/chapter/${chapterNumber}`);
    }

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={isAuth}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.divTitle}>
                    <h1 onClick={goToNovel}>{chapterInfo.novelName}</h1>
                </div>
                <div className={styles.divButtons}>
                    {chapterNumber- 1 === 0 ? ( <></> ) : (
                        <> <button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber - 1)}>&#60; Anterior </button> </>
                    )}
                    {chapterNumber + 1 > maxChapterNumber ? (
                        <></>
                    ) : ( <button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber + 1)}>Próximo &#62;</button> )}
                </div>
                <div className={styles.divChapter} 
                dangerouslySetInnerHTML={{ __html: chapterInfo.chapterText }}
                >
                </div> <br /><br />
                <div className={styles.divButtons}>
                    {chapterNumber- 1 === 0 ? ( <></> ) : (
                        <> <button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber - 1)}>&#60; Anterior </button> </>
                    )}
                    {chapterNumber + 1 > maxChapterNumber ? (
                        <></>
                    ) : ( <button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber + 1)}>Próximo &#62;</button> )}
                </div>
            </div>
            <Footer />
        </div>
    );
}