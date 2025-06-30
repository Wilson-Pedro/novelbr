import React, { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './Table.module.css';
import { useNavigate } from 'react-router-dom';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

interface LastChapters {
    index?:number;
    novelName:string;
    chapterNumber:number;
    title:string;
    dateRegistration:string
}

const Table: React.FC = () => {

    const [lastChapters, setLastChapters] = useState<LastChapters[]>([]);
    const navigate = useNavigate();

    function goToChapter(novelName:string, chapterNumber:number) {
        navigate(`/novel/${novelName}/chapter/${chapterNumber}`);
    }

    useEffect(() => {
        const fetchLastChapters = async () => {
            try { 
                const response = await axios.get(`${API_URL}/chapters/lastChapters`);
                setLastChapters(response.data);
            } catch(error) {
                console.log('Error ao buscar últimos capítulos: ', error)
            }
        }
        fetchLastChapters();
    }, [])

    return(
        <div className="container mt-4">
            {/* <h2 className="mb-3">Mais Populares</h2> */}
            {lastChapters.length === 0 ? (
                <>
                    <p className={styles.pCenter}>Nenhum capítulo foi registrado.</p>
                </>
            ) : (
                <table className="table table-striped table-hover">
                    <thead className="table-light"> 
                        <tr>
                            <th>Obra</th>
                            <th>Títutlo</th>
                            <th>Capítulo</th>
                            <th>Data de publicação</th>
                        </tr>
                    </thead>
                    <tbody>
                        {lastChapters.map((chapter) => (
                            <tr onClick={() => goToChapter(chapter.novelName, chapter.chapterNumber)}>
                                <th>{chapter.novelName}</th>
                                <th>{chapter.title}</th>
                                <th className={styles.chapterNumber}>{chapter.chapterNumber}</th>
                                <th>{chapter.dateRegistration}</th>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default Table;