import React, { useRef, useState } from 'react';
import styles from './ChapterRegister.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Navigate, useParams, useNavigate } from 'react-router-dom';
import JoditEditor from "jodit-react";

import 'bootstrap/dist/css/bootstrap.min.css';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function ChapterRegister() {

    const navigate = useNavigate();
    const params = useParams();
    const novelId = parseInt(params.novelId) || 0;
    const editor = useRef(null);
    
    const [title, setTitle] = useState('');
    const [chapterText, setChapterText] = useState('');

    // const location = useLocation();
    // const { isAuth } = location.state || {};

    const config = {
        readonly: false,
        height: 400,
        enter: 'p',
        cleanHTML: false,
        useDefaultParser: true,
        allowScriptTags: false,
        toolbarButtonSize: 'medium',
        buttons: [
            'bold', 'italic', 'underline',
            '|', 'ul', 'ol',
            '|', 'outdent', 'indent',
            '|', 'link', 'unlink',
            '|', 'hr',
            '|', 'undo', 'redo'
        ],
        removeButtons: ['source', 'brush', 'file', 'video', 'copyformat'],
        toolbarSticky: false
    }

    const token = localStorage.getItem('token');
    if(!token) return <Navigate to="/login"/>

    const submitChapter = async (e) => { 
        e.preventDefault();
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

            navigate(`/novel/${novelId}`, { replace: true });
        } catch(error) {
            console.log(error);
        }
    }

    function goToNovel() {
        navigate(`/novel/${novelId}`);
    }

    const handleBlur = () => {
        if (editor.current) {
            const html = editor.current.value;
            setChapterText(html);
        }
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
                    <JoditEditor 
                        ref={editor}
                        value={chapterText}
                        config={config}
                        onBlur={handleBlur}
                        onChange={() => {}}
                    />
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