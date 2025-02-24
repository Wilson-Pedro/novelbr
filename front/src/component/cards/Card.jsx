import React from 'react';
import styles from './Card.module.css';

export default function Card({ imagePath="", title="" }) {
    return(
        <div className={styles.containerCard}>
            <div className={styles.containerImage}>
                <img className={styles.responsiveImage} src={imagePath} />
            </div>
            <div className={styles.containerInfo}>
                <h3>{title}</h3>
                <p>Capítulo 23</p>
            </div>
        </div>
    );
}