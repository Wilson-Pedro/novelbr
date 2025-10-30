import React, { useEffect, useState } from 'react';
import styles from './ChapterRegister.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useParams, useNavigate } from 'react-router-dom';

import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

import 'bootstrap/dist/css/bootstrap.min.css';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function ChapterRegister() {

    const navigate = useNavigate();
    const params = useParams();
    const novelName = params.novelName || '';
    const [novelId, setNovelId] = useState<number>(0);
    
    const [title, setTitle] = useState<string>('');
    const [chapterText, setChapterText] = useState<string>('');

    const [modalMessage, setModalMessage] = useState("");
    const [showModal, setShowModal] = useState<boolean>(false);
    const [showModalError, setShowModalError] = useState<boolean>(false);

    useEffect(() => {

        const token = localStorage.getItem('token');

        if (!token) {
            navigate('/login');
            return;
        }

        const fecthNovelName = async () => {
            try {
                const response = await axios.get(`${API_URL}/novels/${novelName}`);
                setNovelId(response.data.id);
            } catch(error) {
                console.log(error)
            }
        }
        fecthNovelName();
    }, [novelId, novelName]);

    const submitChapter = async (e:any) => { 
        e.preventDefault();
        const token = localStorage.getItem('token');
        try {
            await axios.post(`${API_URL}/chapters/`, {
                title,
                chapterText,
                novelId
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });

            
            setShowModal(true);
            setModalMessage("Capítulo cadastrado com sucesso.");
        } catch(error) {
            console.log(error);
            setShowModalError(true);
            setModalMessage("Error ao cadastrar capítulo.");
        }
    }

    function goToNovel() {
        navigate(`/novel/${novelName}`, { replace: true });
    }

    function closeModal() {
        setShowModalError(false);
        const username = localStorage.getItem('username');
        navigate(`/novel/${novelName}`, { replace: true });
    }

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={true}
                />
            </nav>
            <form onSubmit={submitChapter} className={styles.main}>
                <div className={styles.divTitle}>
                    <h1>Cadastrar Capítulo.</h1>
                </div>
                <div className={styles.formDiv}>
                    <label>Título do capítulo</label>
                    <input
                        type="text"
                        className="form-control"
                        value={title}
                        onChange={(e) => setTitle(e.target.value)}
                        placeholder="Título do capítulo"
                        aria-label="Título do capítulo"
                        aria-describedby="basic-addon1"
                        required
                    /> 
                </div> <br />
                <h3>Capítulo</h3> <br />
                <div className={styles.textEditor}>
                    <textarea
                        className="form-control"
                        value={chapterText}
                        onChange={(e) => setChapterText(e.target.value)}
                        placeholder="Capítulo"
                        id="floatingTextarea2"
                        required
                    >
                    </textarea>
                </div>
                <div className={styles.divBtn}>
                    <button type="submit" className="btn btn-primary">Cadastrar</button>
                    <button type="button" className="btn btn-danger" onClick={() => goToNovel()}>
                        Cancelar
                    </button>
                </div>
            </form>
                        {showModal && (
                            <>
                                <Modal show={showModal} onHide={() => setShowModal(false)}>
                                    <Modal.Header closeButton>
                                        <Modal.Title>Sucesso</Modal.Title>
                                    </Modal.Header>
                                    <Modal.Body>
                                        <div className={styles.divModalBtn}>
                                            <h1>{modalMessage}</h1>
                                        </div>
                                    </Modal.Body>
                                    <Modal.Footer>
                                        <Button variant="secondary" onClick={() => closeModal()}>
                                            Ok
                                        </Button>
                                    </Modal.Footer>
                                </Modal>
                            </>
                        )}
        
                        {showModalError && (
                            <>
                                <Modal show={showModalError} onHide={() => setShowModalError(false)}>
                                    <Modal.Header closeButton>
                                        <Modal.Title>Error</Modal.Title>
                                    </Modal.Header>
                                    <Modal.Body>
                                        <div className={styles.divModalBtn}>
                                            <h1>{modalMessage}</h1>
                                        </div>
                                    </Modal.Body>
                                    <Modal.Footer>
                                        <Button variant="secondary" onClick={() => setShowModalError(false)}>
                                            Ok
                                        </Button>
                                    </Modal.Footer>
                                </Modal>
                            </>
                        )}
            <Footer />
        </div>
    );
}