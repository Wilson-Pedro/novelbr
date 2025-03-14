import React from 'react';
import styles from './Card.module.css';

import { useNavigate } from 'react-router-dom';

export default function Card({ imagePath="", title="", author="" }) {

    const navigate = useNavigate();

    function navigateToNovel() {
        navigate("/novel");
    }

    return(
        <div className={styles.containerCard} onClick={navigateToNovel}>
            <div className={styles.containerImage}>
                <img className={styles.responsiveImage} src={imagePath} />
            </div>
            <div className={styles.containerInfo}>
                <h3><abbr title={title}>{title}</abbr></h3>
                <p>by {author}</p>
            </div>
        </div>
    );
}