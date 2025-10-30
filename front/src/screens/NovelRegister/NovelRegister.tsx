import React, { useState, useEffect } from 'react';
import styles from './NovelRegister.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Footer from '../../layout/footer/Rodape';
import { useNavigate } from 'react-router-dom';

import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';

import axios from 'axios';

import { GendersBackend } from '../../interfaces/NovelInterfaces';

import 'bootstrap/dist/css/bootstrap.min.css';

const API_URL = process.env.REACT_APP_API;

export default function NovelRegister() {

    const [novelName, setNovelName] = useState('');
    const [genders, setGenders] = useState<string[]>([]);
    const [synopsis, setSynopsis] = useState('');
    const [authorId, setAuthorId] = useState<number>(0);
    const [imageUri, setImageUri] = useState('')
    const [selectFile, setSelectFile] = useState<any>(null);

    const [modalMessage, setModalMessage] = useState("");
    const [showModal, setShowModal] = useState<boolean>(false);
    const [showModalError, setShowModalError] = useState<boolean>(false);

    const navigate = useNavigate();

    const[gendersBackend, setGendersBackend] = useState<GendersBackend[]>([]);


    useEffect(() => {

        const token = localStorage.getItem('token');
        const userId = localStorage.getItem('userId');

        if (!token) {
            navigate('/login');
            return;
        }

        setAuthorId(userId ? parseInt(userId) : 0);
        //setTokenUser(token)

        const fetchGenders = async () => {
            try {
                const response = await axios.get(`${API_URL}/genres`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                })
                setGendersBackend(response.data);
            } catch(error) {
                console.log("Error ao buscar gêneros: ", error)
            }
        };
        fetchGenders();
    }, [navigate]); 

    const submitNovel = async (e:any) => {
        e.preventDefault();
        console.log("Enviando novel...");

        const token = localStorage.getItem('token');
        try {
            await axios.post(`${API_URL}/novels/`, {
                novelName,
                authorId,
                genders,
                synopsis,
                imageUri
            },
            {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            })

            await uploadImage();

            setShowModal(true);
            setModalMessage("Novel cadastarda com Sucesso.");
        } catch (error) {
            console.log(error);
            setShowModalError(true);
            setModalMessage("Error ao cadastrar Novel");
        }
    }

    const uploadImage = async () => {
        const token = localStorage.getItem('token');
        const formData = new FormData();
        formData.append("file", selectFile);
        try {
            await axios.post(`${API_URL}/upload/image`, formData, {
                headers: {
                    Authorization: `Bearer ${token}`,
                    'Content-Type': 'multipart/form-data'
                }
            })
        } catch(error) {
            console.log("Error ao fazer upload da imagem: ", error);
        }
    }

    function addGenders(e:any) {
        const gender = e.target.value;
        const newGenders = [...genders, gender];
        if(!genders.includes(gender)) {
            setGenders(newGenders);
        } else {
            setGenders(newGenders.filter(item => item !== gender));
        }
    }

    function goToProfile() {
        const username = localStorage.getItem('username');
        navigate(`/profile/${username}`);
    }

    function closeModal() {
        setShowModalError(false);
        const username = localStorage.getItem('username');
        navigate(`/profile/${username}`);
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
                    userAuthenticate={true}
                />
            </nav>
            <div className={styles.main}>
                <form onSubmit={submitNovel} encType="multipart/form-data">
                    <div className={styles.formDiv}>
                        <label>Nome da Obra </label>
                        <input
                            type="text"
                            className="form-control"
                            value={novelName}
                            onChange={(e) => setNovelName(e.target.value)}
                            placeholder="Nome da Obra"
                            aria-label="Nome da Obra"
                            aria-describedby="basic-addon1"
                            required
                        />
                    </div>

                    <div className={styles.formDiv}>
                        <label>Gênros</label>
                    </div>
                    <div className={styles.divGenders}>
                        {gendersBackend.map((gender, index) => (
                            <div className="form-check" key={index}>
                                    <input className={`form-check-input ${styles.checkBox}`} type="checkbox" value={gender.genreType} onChange={(e) => addGenders(e)} />
                                    <label>
                                        {gender.genreType}
                                    </label>
                            </div>
                        ))}
                    </div>

                    <div className={styles.formDiv}>
                        <label>Sinopse</label>
                        <textarea
                            className="form-control"
                            value={synopsis}
                            onChange={(e) => setSynopsis(e.target.value)}
                            placeholder="Sinopse"
                            id="floatingTextarea2"
                            required
                        >
                        </textarea>
                    </div>

                    <div className={styles.formDiv}>
                        <label>Capa da Obra </label>
                        <input
                            type="file"
                            className="form-control"
                            name="file"
                            onChange={handleFileChange}
                            placeholder="Capa da Obra"
                            aria-label="Capa da Obra"
                            required
                        />
                    </div>

                    <div className={styles.divBtn}>
                        <button type="button" className="btn btn-primary" onClick={submitNovel}>Criar</button>
                        <button type="button" className="btn btn-danger" onClick={() => goToProfile()}>
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
            </div>
            <Footer />
        </div>
    );
}