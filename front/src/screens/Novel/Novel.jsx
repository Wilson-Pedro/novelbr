import React, { useState, useEffect } from 'react';
import styles from './Novel.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import imagePath from '../../assets/Jornada para o Além.jpg';
import axios from 'axios';

import { useParams, useLocation, useNavigate } from 'react-router-dom';

const API_URL = process.env.REACT_APP_API;
const IMG_PATH = process.env.REACT_APP_IMG_PATH;

export default function Novel() {

    const params = useParams();
    const novelId =  parseInt(params.novelId);

    const location = useLocation();
    const { isAuth } = location.state || {};
    const userAuth = { isAuth: isAuth }
    const [novelInfo, setNovelInfo] = useState({});
    const [genders, setGenders] = useState([]);
    const [chapterTiles, setChapterTitles] = useState([]);
    const [novelName, setNovelName] = useState('');
    const [authorId, setAuthorId] = useState(0);

    const navigate = useNavigate();

    const token = localStorage.getItem('token');
    const[userAuthenticate, setUserAuthenticate] = useState(isAuth);

    useEffect(() => {

        const userId = parseInt(localStorage.getItem('userId'));
        setAuthorId(userId);

        const fetchNovelInfo = async () => {
            try {
                const response = await axios.get(`${API_URL}/novels/novelCards/${novelId}`);
                setNovelInfo(response.data);
                setNovelName(response.data.novelName);

            } catch(error) {
                console.log(error.errorMessage)
            }
        }

        const fetchNovelGenders = async () => {
            try {
                const response = await axios.get(`${API_URL}/genders/novel/${novelId}`);
                setGenders(response.data);
            } catch(error) {
                console.log(error.errorMessage)
            }
        }

        const fetchNovelsChapterTitles = async () => {
            try {
                const response = await axios.get(`${API_URL}/chapters/novelsTile/novel/${novelId}`);
                setChapterTitles(response.data)
            } catch(error) {
                console.log(error.errorMessage)
            }
        }

        fetchNovelsChapterTitles(); 
        fetchNovelInfo();
        fetchNovelGenders(); 
    }, [novelId]);

    function goToChapter(chapterNumber) {
        navigate(`/novel/${novelName}/chapter/${chapterNumber}`, { state: userAuth });
    }

    function goToChapterRegister() {
        navigate(`/chapterRegister/${novelId}`);
    }

    return(
        <div className={styles.container}> 
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={userAuthenticate}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.mainHead}> 
                    <div className={styles.containerImage}>
                        <img className={"img-fluid"} src={`${IMG_PATH}/${novelInfo.imageUri}` || imagePath} />
                    </div>
                    <div className={styles.containerInfo}>
                        <h1>{novelInfo.novelName || '---'}</h1>
                        <p><strong>Autor:</strong> {novelInfo.username || '---'}</p>
                        <p><strong>Gêneros:</strong> {genders.map((gender, index) => (
                            <span>{gender}{(index+1) < genders.length ? <>, </> : <>.</>} </span>
                        ))}</p>
                        {/* <p><strong>Gênros:</strong> Aventura, Ação, Drama, Medieval, Magia.</p> */}
                        <h4>Sinopse</h4>
                        <p className={styles.sinopse}>
                            {novelInfo.synopsis || '---'}
                        </p>
                    </div>
                </div>
                {token != null && authorId === novelInfo.authorId ? (
                    <>
                        <div className={styles.div_btn}>
                            <button 
                                onClick={goToChapterRegister}
                                type="button" 
                                className="btn btn-warning"
                            >Cadastrar Capítulo</button>
                        </div>
                    </>
                ): (
                    <></>
                )}
                <hr />
                <div className={styles.capitulos}>
                    <h1>Capítulos</h1>
                    {chapterTiles.length === 0 ? (
                        <>
                            <br/>
                            <p>Nenhum capítulo foi registrado.</p>
                        </>
                    ) : (
                        <ul>
                            {chapterTiles.map((data, index) => (
                                <li onClick={() => goToChapter(index+1)}>{index+1}º {data.title}</li>
                            ))}
                        </ul>
                    ) }
                    
                </div>
            </div>
            <Footer />
        </div>
    );
}