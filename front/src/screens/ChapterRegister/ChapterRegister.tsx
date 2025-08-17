import React, { useEffect, useRef, useState } from 'react';
import styles from './ChapterRegister.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Navigate, useParams, useNavigate } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function ChapterRegister() {

    const navigate = useNavigate();
    const params = useParams();
    const novelName = params.novelName || '';
    const [novelId, setNovelId] = useState<number>(0);
    const editor = useRef<any | null>(null);
    
    const [title, setTitle] = useState<string>('');
    const [chapterText, setChapterText] = useState<string>('');

    useEffect(() => {

        const token = localStorage.getItem('token');

        if (!token) {
            navigate('/login');
            return;
        }

        const fecthNovelName = async () => {
            try {
                const response = await axios.get(`${API_URL}/novels/${novelName}`);
                setNovelId(response.data.id);
            } catch(error) {
                console.log(error)
            }
        }
        fecthNovelName();
    }, [novelId, novelName]);

    const submitChapter = async (e:any) => { 
        e.preventDefault();
        const token = localStorage.getItem('token');
        try {
            const response = await axios.post(`${API_URL}/chapters/`, {
                title,
                chapterText,
                novelId
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            navigate(`/novel/${novelName}`, { replace: true });
        } catch(error) {
            console.log(error);
        }
    }

    function goToNovel() {
        navigate(`/novel/${novelName}`);
    }

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={true}
                />
            </nav>
            <form onSubmit={submitChapter} className={styles.main}>
                <div className={styles.divTitle}>
                    <h1>Cadastrar Capítulo.</h1>
                </div>
                <div className={styles.formDiv}>
                    <label>Título do capítulo</label>
                    <input
                        type="text"
                        className="form-control"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        placeholder="Título do capítulo"
                        aria-label="Título do capítulo"
                        aria-describedby="basic-addon1"
                        required
                    /> 
                </div> <br />
                <h3>Capítulo</h3> <br />
                <div className={styles.textEditor}>
                    <textarea
                        className="form-control"
                        value={chapterText}
                        onChange={(e) => setChapterText(e.target.value)}
                        placeholder="Capítulo"
                        id="floatingTextarea2"
                        required
                    >
                    </textarea>
                </div>
                <div className={styles.divBtn}>
                    <button type="submit" className="btn btn-primary">Criar</button>
                    <button type="button" className="btn btn-danger" onClick={() => goToNovel()}>
                        Cancelar
                    </button>
                </div>
            </form>
            <Footer />
        </div>
    );
}