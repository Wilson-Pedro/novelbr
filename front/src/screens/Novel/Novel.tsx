import React, { useState, useEffect } from 'react';
import styles from './Novel.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import imagePath from '../../assets/Jornada para o Além.jpg';
import axios from 'axios';
import Comments from '../../component/Comments/Comments';
import { Tabs, Tab } from 'react-bootstrap';

import 'bootstrap/dist/css/bootstrap.min.css';

import { FaSearch as FaSearchIcon } from "react-icons/fa";

import { useParams, useLocation, useNavigate } from 'react-router-dom';

import { BackendCommentsI } from '../../interfaces/CommentInterfaces';
import { NovelInfo } from '../../interfaces/NovelInterfaces';
import { ChapterTiles, Page } from '../../interfaces/ChapterInterfaces';

import NovelConfig from '../../component/NovelConfig/NovelConfig';

const SearchIcon = FaSearchIcon as React.FC<{ className?: string }>;

const API_URL = process.env.REACT_APP_API;
const IMG_PATH = process.env.REACT_APP_IMG_PATH;

interface Genre {
    id: number;
    genre: string;
    genreType: string;
}

const Novel: React.FC = () => {

    const params = useParams();
    const novelName = params.novelName || '';

    const location = useLocation();
    const { isAuth } = location.state || {};
    const userAuth = { isAuth: isAuth }
    const [novelInfo, setNovelInfo] = useState<NovelInfo>({} as NovelInfo);
    const [genrers, setGenrers] = useState<Genre[]>([]);
    const [chapterTiles, setChapterTitles] = useState<ChapterTiles[]>([]);
    const [backendComments, setBackendComments] = useState<BackendCommentsI[]>([]);
    const [novelId, setNovelId] = useState<number>(0);
    const [authorId, setAuthorId] = useState<number>(0);
    const [commentByCode, setCommentByCode] = useState<number>(1);
    const [novelStatusId, setNovelStatusId] = useState<number>(1);
    const [page, setPage] = useState<number>(0);
    const [pageSeacrh, setPageSeacrh] = useState<number>(0);
    const [totalPages, setTolalPages] = useState<number>(0);
    const [imageUri, setImageUri] = useState('')
    const [selectFile, setSelectFile] = useState<any>(null);

    const navigate = useNavigate();

    const token = localStorage.getItem('token');
    const [userAuthenticate, setUserAuthenticate] = useState(isAuth);

    const [showModalConfig, setShowModalConfig] = useState<boolean>(false);
    const [showModalImage, setShowModalImage] = useState<boolean>(false);
    const [showModalNovelStatus, setShowModalNovelStatus] = useState<boolean>(false);

    const changeNovelStatusSubmit = async () => {
        try {
            await axios.patch(`${API_URL}/novels/changeNovelStatus`, {
                novelId,
                novelStatusId,
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })
            handleCloseConfig();
        } catch (error) {
            console.log(error)
        }
    }

    const chageNovelImageUri = async (e:any) => {
        e.preventDefault();
        const formaData = new FormData();
        formaData.append("file", selectFile);
        formaData.append("novelId", novelId.toString());
        formaData.append("imageUri", imageUri);
        try {
            await axios.patch(`${API_URL}/novels/changeNovelImageUri`, formaData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'multipart/form-data'
                }
            })
            goToNovel();
            handleCloseImage();
        } catch (error) {
            console.log(error)
        }
    }

    const changeNovelStatus = (statusId: number) => {
        setNovelStatusId(statusId);
        changeNovelStatusSubmit();
    }

    useEffect(() => {
        const fecthNovelName = async () => {
            try {
                const response = await axios.get(`${API_URL}/novels/${novelName}`);
                setNovelId(response.data.id);
            } catch (error) {
                console.log(error)
            }
        }

        fecthNovelName();
    }, [novelId]);

    useEffect(() => {

        if (!novelId) return;
        const userId = parseInt(localStorage.getItem('userId') || '');
        setAuthorId(userId);

        const fecthData = async () => {
            try {
                const [infoRes, gendersRes, commentsRes, chapterPagesRes] =
                    await Promise.allSettled([
                        axios.get(`${API_URL}/novels/novelCards/${novelId}`),
                        axios.get(`${API_URL}/genres/novel/${novelId}`),
                        axios.get(`${API_URL}/comments/novels/${novelId}`),
                        axios.get(`${API_URL}/chapters/pages/novelsTitle/${novelId}?page=${page}&size=10`)
                    ]);

                if (infoRes.status === "fulfilled") {
                    setNovelInfo(infoRes.value.data);
                }

                if (gendersRes.status === "fulfilled") {
                    setGenrers(gendersRes.value.data);
                }

                if (commentsRes.status === "fulfilled") {
                    setBackendComments(commentsRes.value.data);
                }

                if (chapterPagesRes.status === "fulfilled") {
                    const pageData: Page<ChapterTiles> = chapterPagesRes.value.data;
                    setChapterTitles(pageData.content);
                    setTolalPages(pageData.totalPages);
                }

            } catch (error) {
                console.log(error);
            }
        }

        fecthData();

    }, [novelId, novelName, page, imageUri]);

    // COMMENTS

    const postComment = async (bodyText: string, parentId: number) => {
        try {
            const response = await axios.post(`${API_URL}/comments/`, {
                authorId: authorId,
                commentByCode,
                entityId: novelId,
                parentId: parentId ? parentId : null,
                bodyText

            },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
            const newComment = response.data;
            onAddComment(newComment);

        } catch (error) {
            console.log("Error ao adicionar comentário", error);
        }
    };

    const updateComment = async (bodyText: string, commentId: number) => {

        try {
            const response = await axios.put(`${API_URL}/comments/${commentId}`, {
                bodyText
            },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                })

            const commentUpdated = response.data;
            handleUpdateComment(commentUpdated, commentId);
        } catch (error) {
            console.log(error)
        }
    };

    const deleteComment = (commentId: number) => {
        try {
            if (window.confirm('Delete this comment?')) {
                axios.delete(`${API_URL}/comments/${commentId}`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
            }
        } catch (error) {
            console.log(error)
        }
        handleDeleteComment(commentId)
    }

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

    function goToChapter(chapterNumber: number) {
        navigate(`/novel/${novelName}/chapter/${chapterNumber}`, { state: userAuth });
    }

    function goToChapterRegister() {
        navigate(`/chapterRegister/${novelName}`);
    }

    function goToNovel() {
        navigate(`/novel/${novelId}`);
    }

        function goToAuthorNovels(username:string) {
        navigate(`/author/${username}/novels`);
    }

    function pageSeacrValid(pag:number, max:number) {
        if(pag >= 0 && pag <= max) {
            setPage(pag);
        } else {
            setPage(page);
        }
    }

    const handleCloseConfig = () => setShowModalConfig(false);
    const handleShowConfig = () => setShowModalConfig(true);

    const handleCloseImage = () => setShowModalImage(false);
    const handleShowImage = () => {
        setShowModalConfig(false);
        setShowModalImage(true);
    }

    const handleCloseNovelStatus = () => setShowModalNovelStatus(false);

    const handleShowNovelStatus= () => {
        setShowModalConfig(false);
        setShowModalNovelStatus(true);
    }
    
    const handleFileChange = async (e:any) => {
        if(e.target.files[0] != null) {
            setSelectFile(e.target.files[0]);
            setImageUri(e.target.files[0].name);
        }
    }

    return (
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
                        <p><strong>Autor:</strong> { novelInfo.username !== null ? 
                            <span className={styles.authorSpan} onClick={() => goToAuthorNovels(novelInfo.username)}>
                                {novelInfo.username}
                            </span>
                         : (<>'---'</>)}</p>
                        <p><strong>Gêneros:</strong> {genrers.map((genre, index) => (
                            <span>{genre.genreType}{(index + 1) < genrers.length ? <>, </> : <>.</>} </span>
                        ))}</p>
                        <p><strong>Ano:</strong> {novelInfo.year || '---'}</p>

                        <p><strong>Status:</strong> {novelInfo.novelStatus || '---'}</p>

                        <h4>Sinopse</h4>
                        <p className={styles.sinopse}>
                            {novelInfo.synopsis || '---'}
                        </p>
                    </div>
                </div>
                <NovelConfig 
                    chageNovelImage={chageNovelImageUri}
                    changeNovelStatusSubmit={changeNovelStatusSubmit}
                    handleFileChange={handleFileChange}
                    setNovelStatusId={setNovelStatusId}
                    novelName={novelName}
                    handleCloseConfig={handleCloseConfig}
                    handleCloseImage={handleCloseImage}
                    handleShowImage={handleShowImage}
                    handleCloseNovelStatus={handleCloseNovelStatus}
                    handleShowNovelStatus={handleShowNovelStatus}
                    showModalConfig={showModalConfig}
                    showModalNovelStatus={showModalNovelStatus}
                    showModalImage={showModalImage}
                />
                {token != null && authorId === novelInfo.authorId ? (

                    <div className={styles.div_btn}>
                        <button
                            onClick={handleShowConfig}
                            className="btn btn-warning">
                            Configurações
                        </button>
                    </div>

                ) : (
                    <></>
                )}
                <div className={styles.spa}>
                    <div className="container">
                        <Tabs defaultActiveKey="secao1" id="tabs-example" className="mb-3">
                            <Tab eventKey="secao1" title="Capítulos">
                                <div className={styles.capitulos}>
                                    <h1>Capítulos</h1>
                                    {chapterTiles.length === 0 ? (
                                        <>
                                            <br />
                                            <p>Nenhum capítulo foi registrado.</p>
                                        </>
                                    ) : (
                                        <>
                                            <div>
                                                <ul className={styles.cursorDefault}>
                                                    {chapterTiles.map((data, index) => (
                                                        <li >{data.chapterNumber}º 
                                                            <span 
                                                            className={styles.cursorPointer}
                                                            onClick={() => goToChapter(data.chapterNumber)}>
                                                                {data.title}
                                                            </span>
                                                        </li>
                                                    ))}
                                                </ul>
                                            </div>
                                            <div className={styles.div_btn}>
                                                <button
                                                    disabled={page === 0}
                                                    className="btn btn-warning"
                                                    onClick={() => setPage(0)}
                                                >&#60;&#60;&#60;</button>
                                                <button
                                                    disabled={page === 0}
                                                    className="btn btn-warning"
                                                    onClick={() => setPage(page - 1)}
                                                >Anterior</button>

                                                <button 
                                                    disabled={page === totalPages - 1}
                                                    className="btn btn-warning"
                                                    onClick={() => setPage(page + 1)}
                                                >Próxima</button>

                                                <button 
                                                    disabled={page === totalPages - 1}
                                                    className="btn btn-warning"
                                                    onClick={() => setPage(totalPages - 1)}
                                                >&#62;&#62;&#62;</button> <br/><br/>
                                            </div>
                                            <div>
                                                Buscar: 
                                                <input 
                                                    type="number" 
                                                    value={pageSeacrh}
                                                    onChange={(e) => setPageSeacrh(parseInt(e.target.value))}
                                                    min={1} 
                                                    max={totalPages} 
                                                />
                                                <button
                                                onClick={() => pageSeacrValid(pageSeacrh - 1, totalPages - 1)}
                                                ><SearchIcon className={styles.searchIncon} /></button> 
                                            </div>
                                            <div>
                                                <p>Página {page + 1} de {totalPages}</p>
                                            </div>
                                        </>
                                    )}
                                </div>
                            </Tab>
                            <Tab eventKey="secao2" title="Comentários">
                                <div className={styles.commentsDiv}>
                                    <Comments
                                        currentUserId={authorId}
                                        comments={backendComments}
                                        postComment={postComment}
                                        putComment={updateComment}
                                        deleteComment={deleteComment}
                                        token={token}
                                    />
                                </div>
                            </Tab>
                        </Tabs>
                    </div>

                </div>
            </div>
            <Footer />
        </div>
    );
}

export default Novel;