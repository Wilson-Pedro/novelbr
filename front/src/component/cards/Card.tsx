import React from 'react';
import styles from './Card.module.css';

import { useNavigate } from 'react-router-dom';

const IMG_PATH = process.env.REACT_APP_IMG_PATH;

interface CardProps {
    index?:number;
    imagePath?:string;
    title?:string;
    author?:string;
    userAuthenticate?:boolean;
    authorId:number;
    novelId:number;
}

const Card: React.FC<CardProps> = ({ imagePath, title, author, userAuthenticate, authorId, novelId }) => {

    const navigate = useNavigate();
    
    const userAuth = { isAuth: userAuthenticate }

    const srcPath = `${IMG_PATH}/${imagePath}`;

    function navigateToNovel() {
        navigate(`/novel/${novelId}`, { state: userAuth });
    }

    return(
        <div className={styles.containerCard} onClick={navigateToNovel}>
            <div className={styles.containerImage}>
                {/* <img className={styles.responsiveImage} src={imagePath} /> */}
                <img className={styles.responsiveImage} src={srcPath}/>
            </div>
            <div className={styles.containerInfo}>
                <h3><abbr title={title}>{title}</abbr></h3>
                <p>by {author}</p>
            </div>
        </div>
    );
}

export default Card;