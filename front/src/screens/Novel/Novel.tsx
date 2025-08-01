import React, { useState, useEffect } from 'react';
import styles from './Novel.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import imagePath from '../../assets/Jornada para o Além.jpg';
import axios from 'axios';
import Comments from '../../component/Comments/Comments';

import 'bootstrap/dist/css/bootstrap.min.css';

import { useParams, useLocation, useNavigate } from 'react-router-dom';

const API_URL = process.env.REACT_APP_API;
const IMG_PATH = process.env.REACT_APP_IMG_PATH;

export interface BackendCommentsI {
    id: number
    authorId: number
    username: string
    comentByCode: number
    entityId: number
    parentId: number | null
    bodyText: string
    createdAt: string
}

interface NovelInfo {
    imageUri:string;
    novelName:string;
    novelStatus:string;
    username:string;
    year:number;
    synopsis:string;
    authorId:number;
}

interface ChapterTiles {
    title:String;
}

const Novel: React.FC = () => {

    const params = useParams();
    const novelId =  parseInt(params.novelId || '');

    const location = useLocation();
    const { isAuth } = location.state || {};
    const userAuth = { isAuth: isAuth }
    const [novelInfo, setNovelInfo] = useState<NovelInfo>({} as NovelInfo);
    const [genders, setGenders] = useState([]);
    const [chapterTiles, setChapterTitles] = useState<ChapterTiles[]>([]);
    const [backendComments, setBackendComments] = useState<BackendCommentsI[]>([]);
    const [novelName, setNovelName] = useState<string>('');
    const [authorId, setAuthorId] = useState<number>(0);

    const navigate = useNavigate();

    const token = localStorage.getItem('token');
    const[userAuthenticate, setUserAuthenticate] = useState(isAuth);

    useEffect(() => {

        const userId = parseInt(localStorage.getItem('userId') || '');
        setAuthorId(userId);

        const fetchNovelInfo = async () => {
            try {
                const response = await axios.get(`${API_URL}/novels/novelCards/${novelId}`);
                setNovelInfo(response.data);
                setNovelName(response.data.novelName);

            } catch(error) {
                console.log(error)
            }
        }

        const fetchNovelGenders = async () => {
            try {
                const response = await axios.get(`${API_URL}/genders/novel/${novelId}`);
                setGenders(response.data);
            } catch(error) {
                console.log(error)
            }
        }

        const fetchNovelsChapterTitles = async () => {
            try {
                const response = await axios.get(`${API_URL}/chapters/novelsTile/novel/${novelId}`);
                setChapterTitles(response.data)
            } catch(error) {
                console.log(error)
            }
        }

        const fetchComments = async () => {
            try {
                const response = await axios.get(`${API_URL}/comments/novels/${novelId}`);
                setBackendComments(response.data)
            } catch(error) {
                console.log(error)
            }
        }

        fetchComments();
        fetchNovelsChapterTitles(); 
        fetchNovelInfo();
        fetchNovelGenders(); 
    }, [novelId, novelName]);

    const onAddComment = (comment: BackendCommentsI) => {
        setBackendComments(c => [comment, ...c])
    };

    const handleUpdateComment = (comment: BackendCommentsI, commentId: number) => {
        handleDeleteComment(commentId);
        onAddComment(comment);
    }

    const handleDeleteComment = (commentId: number) => {
        setBackendComments(c => c.filter(c => c.id !== commentId));
    }

    function goToChapter(chapterNumber:number) {
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
                        <img className="img-fluid" src={`${IMG_PATH}/${novelInfo.imageUri}` || imagePath} />
                    </div>
                    <div className={styles.containerInfo}>
                        <h1>{novelInfo.novelName || '---'}</h1>
                        <p><strong>Autor:</strong> {novelInfo.username || '---'}</p>
                        <p><strong>Gêneros:</strong> {genders.map((gender, index) => (
                            <span>{gender}{(index+1) < genders.length ? <>, </> : <>.</>} </span>
                        ))}</p>
                        <p><strong>Ano:</strong> {novelInfo.year || '---'}</p>
                        
                        <p><strong>Status:</strong> {novelInfo.novelStatus || '---'}</p>

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
                <div className={styles.commentsDiv}>
                    {token && (
                        <Comments 
                            currentUserId={authorId}
                            comments={backendComments}
                            entityId={novelId}
                            onAddComment={onAddComment}
                            handleDeleteComment={handleDeleteComment}
                            handleUpdateComment={handleUpdateComment}
                        />
                    )}
                </div>
            </div>
            <Footer />
        </div>
    );
}

export default Novel;