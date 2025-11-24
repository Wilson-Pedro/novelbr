import React, { useState, useEffect } from 'react';
import styles from './Chapter.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useLocation, useParams, useNavigate } from 'react-router-dom';
import { ChapterInfo } from '../../interfaces/ChapterInterfaces';
import Comments from '../../component/Comments/Comments';
import { BackendCommentsI } from '../../interfaces/CommentInterfaces';
import { Tabs, Tab } from 'react-bootstrap';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function Chapter() {

    const [chapterInfo, setChapterInfo] = useState<ChapterInfo>({} as ChapterInfo);
    const [chapterId, setChapterId] = useState<number>();
    const [maxChapterNumber, setMaxChapterNumber] = useState<number>(1);
    const [novelId, setNovelId] = useState<number>(0);

    const [backendComments, setBackendComments] = useState<BackendCommentsI[]>([]);
    const [authorId, setAuthorId] = useState<number>(0);
    const [commentByCode, setCommentByCode] = useState<number>(2);

    const location = useLocation();
    const { isAuth } = location.state || {};

    const params = useParams();
    const novelName = params.novelName;
    const chapterNumber = parseInt(params.chapterNumber || '');

    const navigate = useNavigate();

    // COMMENTS
    const postComment = async (bodyText: string, parentId: number) => {
        const token = localStorage.getItem('token');
        try {
            const response = await axios.post(`${API_URL}/comments/`, {
                authorId,
                commentByCode,
                entityId: chapterId,
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
        const token = localStorage.getItem('token');
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
    }

    const deleteComment = (commentId: number) => {
        const token = localStorage.getItem('token');
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
    }

    const handleUpdateComment = (comment: BackendCommentsI, commentId: number) => {
        handleDeleteComment(commentId);
        onAddComment(comment);
    }

    const handleDeleteComment = (commentId: number) => {
        setBackendComments(c => c.filter(c => c.id !== commentId));
    }

    function goToNovel() {
        navigate(`/novel/${novelName}`);
    }

    useEffect(() => {
        const userId = parseInt(localStorage.getItem('userId') || '');
        setAuthorId(userId);

        const fetchChapter = async () => {
            try {
                const response = await axios.get(`${API_URL}/chapters/${novelName}/${chapterNumber}`);
                setChapterInfo(response.data);
                setNovelId(response.data.novelId);
                setChapterId(response.data.chapterId)
            } catch (error) {
                console.log("Error ao buscar capítulo: ", error)
            }
        }

        fetchChapter();
    }, [novelName, chapterNumber]);

    useEffect(() => {
        if (novelId !== 0) {
            const fetchMaxChapterNumber = async () => {
                try {
                    const response = await axios.get(`${API_URL}/chapters/chapterNumber/novel/${novelId}`);
                    setMaxChapterNumber(response.data.chapterNumber)
                    console.log(response)
                } catch (error) {
                    console.log('Error ao buscar o último capítulo da Novel ', error)
                }
            }
            fetchMaxChapterNumber();
        }
    }, [novelId]);

    useEffect(() => {
        if (novelId !== 0) {
            const fetchCommentsByChapters = async () => {
                try {
                    const response = await axios.get(`${API_URL}/comments/chapters/${chapterId}`);
                    setBackendComments(response.data);
                } catch (error) {
                    console.log('Error ao buscar o último capítulo da Novel ', error)
                }
            }
            fetchCommentsByChapters();
        }
    }, [chapterId]);

    function goToChapter(chapterNumber: number) {
        navigate(`/novel/${novelName}/chapter/${chapterNumber}`);
    }

    return (
        <>
            <div className={styles.container}>
                <nav className={styles.navbar}>
                    <Navbar
                    />
                </nav>
                <div className={styles.main}>
                    <div className={styles.divTitle}>
                        <h1 onClick={goToNovel}>{chapterInfo.novelName}</h1>
                    </div>
                    <div className={styles.divButtons}>
                        {chapterNumber - 1 === 0 ? (<></>) : (
                            <> <button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber - 1)}>&#60; Anterior </button> </>
                        )}
                        {chapterNumber + 1 > maxChapterNumber ? (
                            <></>
                        ) : (<button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber + 1)}>Próximo &#62;</button>)}
                    </div>
                    <div className={styles.divTitle}>
                        <h1>{chapterInfo.title}</h1>
                    </div>
                    <div className={styles.divChapter}
                        dangerouslySetInnerHTML={{ __html: chapterInfo.chapterText }}
                    >
                    </div> <br /><br />
                    <div className={styles.divButtons}>
                        {chapterNumber - 1 === 0 ? (<></>) : (
                            <> <button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber - 1)}>&#60; Anterior </button> </>
                        )}
                        {chapterNumber + 1 > maxChapterNumber ? (
                            <></>
                        ) : (<button type="button" className="btn btn-warning" onClick={() => goToChapter(chapterNumber + 1)}>Próximo &#62;</button>)}
                    </div>
                </div>
            </div>
            <div className={styles.spa}>
                <div className="container">
                    <Tabs defaultActiveKey="secao1" id="tabs-example" className="mb-3">
                        <Tab eventKey="secao2" title="Comentários">
                            <div className={styles.commentsDiv}>
                                <Comments
                                    currentUserId={authorId}
                                    comments={backendComments}
                                    postComment={postComment}
                                    putComment={updateComment}
                                    deleteComment={deleteComment}
                                    token={localStorage.getItem('token')}
                                />
                            </div>
                        </Tab>
                    </Tabs>
                </div>
            </div>
            <Footer />
        </>
    );
}