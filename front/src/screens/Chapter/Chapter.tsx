import React, { useState, useEffect } from 'react';
import styles from './Chapter.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useLocation, useParams, useNavigate } from 'react-router-dom';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

interface ChapterInfo {
    title:string;
    novelId:number;
    novelName:string;
    chapterText:string;
}

export default function Chapter() {

    const [chapterInfo, setChapterInfo] = useState<ChapterInfo>({} as ChapterInfo);
    const [maxChapterNumber, setMaxChapterNumber] = useState<number>(1);
    const [novelId, setNovelId] = useState<number>(0);

    const location = useLocation();
    const { isAuth } = location.state || {};

    const params = useParams();
    const novelName = params.novelName;
    const chapterNumber =  parseInt(params.chapterNumber || '');

    const navigate = useNavigate();

    function goToNovel() {
        navigate(`/novel/${novelName}`);
    }

    useEffect(() => {

        const fetchChapter = async () => {
            try {
                const response = await axios.get(`${API_URL}/chapters/${novelName}/${chapterNumber}`);
                setChapterInfo(response.data);
                setNovelId(response.data.novelId);
            } catch(error) {
                console.log("Error ao buscar capítulo: ", error)
            }
        }

        fetchChapter(); 
    }, [novelName, chapterNumber]);

    useEffect(() => {
        if(novelId !== 0) {
            const fetchMaxChapterNumber = async () => {
                try {
                    const response = await axios.get(`${API_URL}/chapters/chapterNumber/novel/${novelId}`);
                    setMaxChapterNumber(response.data.chapterNumber)
                    console.log(response)
                } catch(error) {
                    console.log('Error ao buscar o último capítulo da Novel ', error)
                }
            }
            fetchMaxChapterNumber();
        }
    }, [novelId]);

    function goToChapter(chapterNumber:number) {
        navigate(`/novel/${novelName}/chapter/${chapterNumber}`);
    }

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
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
                <div className={styles.divTitle}>
                    <h1>{chapterInfo.title}</h1>
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