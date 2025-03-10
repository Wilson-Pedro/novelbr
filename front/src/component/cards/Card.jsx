import React from 'react';
import styles from './Card.module.css';

export default function Card({ imagePath="", title="", author="" }) {
    return(
        <div className={styles.containerCard}>
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