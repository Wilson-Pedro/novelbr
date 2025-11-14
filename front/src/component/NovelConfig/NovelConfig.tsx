import React from 'react';
import styles from './NovelConfig.module.css';
import 'bootstrap/dist/css/bootstrap.min.css';

import { useNavigate } from 'react-router-dom';

import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';

import { NovelConfigProp } from '../../interfaces/CommentInterfaces';

const NovelConfig: React.FC<NovelConfigProp> =({
    chageNovelImage,
    changeNovelStatusSubmit,
    handleFileChange,
    setNovelStatusId,
    novelName,
    handleCloseConfig,
    handleCloseImage,
    handleShowImage,
    handleCloseNovelStatus,
    handleShowNovelStatus,
    showModalConfig,
    showModalNovelStatus,
    showModalImage,
}) => {

    const navigate = useNavigate();


    const changeNovelStatus = (statusId: number) => {
        setNovelStatusId(statusId);
        changeNovelStatusSubmit();
    }

    function goToChapterRegister() {
        navigate(`/chapterRegister/${novelName}`);
    }

    return (
        <>
            {showModalConfig && (
                <>
                    <Modal show={showModalConfig} onHide={handleCloseConfig}>
                        <Modal.Header closeButton>
                            <Modal.Title>Configurações</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <div className={styles.divModalBtn}>
                                <button
                                    onClick={goToChapterRegister}
                                    className="btn btn-warning"
                                >Cadastrar Capítulo</button>

                                <button
                                    onClick={handleShowNovelStatus}
                                    className="btn btn-warning">
                                    Status da Novel
                                </button>

                                <button
                                    onClick={handleShowImage}
                                    className="btn btn-warning">
                                    Mudar Capa da Novel
                                </button>
                            </div>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={handleCloseConfig}>
                                Fechar
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </>
            )}
            {showModalNovelStatus && (
                <>
                    <Modal show={showModalNovelStatus} onHide={handleCloseNovelStatus}>
                        <Modal.Header closeButton>
                            <Modal.Title>Mudar Status da Novel</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <form onSubmit={changeNovelStatusSubmit} className={styles.formModal}>
                                <button
                                    className="btn btn-primary"
                                    onClick={() => changeNovelStatus(1)}>Em Curso
                                </button>
                                <button
                                    className="btn btn-primary"
                                    onClick={() => changeNovelStatus(2)}>Finalizado
                                </button>
                                <button
                                    className="btn btn-primary"
                                    onClick={() => changeNovelStatus(3)}>Hiato
                                </button>
                            </form>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={handleCloseNovelStatus}>
                                Fechar
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </>
            )}
            {showModalImage && (
                <>
                    <Modal show={showModalImage} onHide={handleCloseImage}>
                        <Modal.Header closeButton>
                            <Modal.Title>Mudar Capa da Novel</Modal.Title>
                        </Modal.Header>
                        <Modal.Body>
                            <form onSubmit={chageNovelImage} className={styles.formModal}>
                                <div className="input-group">
                                    <input
                                        type="file"
                                        className="form-control"
                                        id="inputGroupFile04"
                                        aria-describedby="inputGroupFileAddon04"
                                        aria-label="Upload"
                                        onChange={handleFileChange}
                                        required
                                    />
                                    <button
                                        className="btn btn-outline-secondary"
                                        type="submit"
                                        id="inputGroupFileAddon04">Enviar</button>
                                </div>
                            </form>
                        </Modal.Body>
                        <Modal.Footer>
                            <Button variant="secondary" onClick={handleCloseImage}>
                                Fechar
                            </Button>
                        </Modal.Footer>
                    </Modal>
                </>
            )}
        </>
    )
}

export default NovelConfig;