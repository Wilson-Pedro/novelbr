import React, { useState, useEffect } from 'react';
import styles from './Novel.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import imagePath from '../../assets/Jornada para o Além.jpg';
import axios from 'axios';

import { useParams, useLocation, useNavigate } from 'react-router-dom';

const API = "http://localhost:8080";

export default function Novel() {

    const params = useParams();
    const novelId =  parseInt(params.novelId);

    const location = useLocation();
    const { isAuth } = location.state || {};
    const userAuth = { isAuth: isAuth }
    const [novelInfo, setNovelInfo] = useState({});
    const [genders, setGenders] = useState([]);
    const [novelName, setNovelName] = useState('');

    const navigate = useNavigate();

    const[userAuthenticate, setUserAuthenticate] = useState(isAuth);

    useEffect(() => {

        const fetchNovelInfo = async () => {
            try {
                const response = await axios.get(`${API}/novels/novelCards/${novelId}`);
                setNovelInfo(response.data);

            } catch(error) {
                console.log(error.errorMessage)
            }
        }

        const fetchNovelGenders = async () => {
            try {
                const response = await axios.get(`${API}/genders/novel/${novelId}`);
                setGenders(response.data);
            } catch(error) {
                console.log(error.errorMessage)
            }
        }

        fetchNovelInfo();
        fetchNovelGenders();
    }, []);

    function goToChapter(chapterNumber) {
        navigate(`/novel/${novelName}/chapter/${chapterNumber}`, { state: userAuth });
    }

    function goToChapterRegister() {
        navigate('/chapterRegister');
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
                        <img className={"img-fluid"} src={novelInfo.imageUri || imagePath} />
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
                {userAuthenticate !== false ? (
                    <>
                        <div className={styles.div_btn}>
                            <button 
                                onClick={goToChapterRegister}
                                type="button" 
                                class="btn btn-warning"
                            >Cadastrar Capítulo</button>
                        </div>
                    </>
                ): (
                    <></>
                )}
                <hr />
                <div className={styles.capitulos}>
                    <h1>Capítulos</h1>
                    <ul>
                        <li onClick={() => goToChapter(1)}>1º capítulo.</li>
                        <li onClick={() => goToChapter(2)}>2º capítulo.</li>
                        <li onClick={() => goToChapter(3)}>3º capítulo.</li>
                        <li onClick={() => goToChapter(4)}>4º capítulo.</li>
                        <li onClick={() => goToChapter(5)}>5º capítulo.</li>
                        <li onClick={() => goToChapter(6)}>6º capítulo.</li>
                        <li onClick={() => goToChapter(7)}>7º capítulo.</li>
                        <li onClick={() => goToChapter(8)}>8º capítulo.</li>
                    </ul>
                    
                </div>
            </div>
            <Footer />
        </div>
    );
}