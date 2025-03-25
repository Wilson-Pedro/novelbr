import React, { useRef, useState } from 'react';
import styles from './ChapterRegister.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useLocation } from 'react-router-dom';
import JoditEditor from "jodit-react";

export default function ChapterRegister() {

    const editor = useRef(null);
    const [content, setContent] = useState('');

    const location = useLocation();
    const { isAuth } = location.state || {};

    const config = {
        readonly: false,
        height: 400
    }

    const handleChange = (event) => {
        const editorContent = event.target.value;
        setContent(editorContent);
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
                    <h1>Cadastrar Capítulo.</h1>
                </div>
                <div className={styles.textEditor}>
                    <JoditEditor 
                        ref={editor}
                        value={content}
                        config={config}
                        onBlur={handleChange}
                        onChange={(newContent) => {}}
                    />
                </div>
            </div>
            <Footer />
        </div>
    );
}