import React, { useEffect, useState } from 'react';
import styles from './Novels.module.css';
import Navbar from '../../layout/navbar/Navbar';
import Card from '../../component/cards/Card';
import Footer from '../../layout/footer/Rodape';
import { Link, Navigate, useParams, useNavigate } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

import { NovelCard } from '../../interfaces/NovelInterfaces';
import { Page } from '../../interfaces/ChapterInterfaces';
import { FaSearch as FaSearchIcon } from "react-icons/fa";

import { GendersBackend } from '../../interfaces/NovelInterfaces';

import { Tabs, Tab } from 'react-bootstrap';

import axios from 'axios';

const SearchIcon = FaSearchIcon as React.FC<{ className?: string }>;

const API_URL = process.env.REACT_APP_API;

const Novels: React.FC = () => {

    const params = useParams();
    const navigate = useNavigate();

    const novelName = params.novelName || '';

    const [novelNameSearch, setNovelNameSearch] = useState<string>('');
    const [page, setPage] = useState<number>(0);
    const [pageSeacrh, setPageSeacrh] = useState<number>(0);
    const [totalPages, setTolalPages] = useState<number>(0);
    const size = 18;
    const [cards, setCards] = useState<NovelCard[]>([]);
    const [cardsPages, setCardsPages] = useState<NovelCard[]>([]);
    const[gendersBackend, setGendersBackend] = useState<GendersBackend[]>([]);
    const [genders, setGenders] = useState<string[]>([]);

    useEffect(() => {

        const findNovelCards = async () => {

            try {
                const response = await axios.get(`${API_URL}/novels/search/${novelName}?page=${page}&size=${size}`);
                const pageData: Page<NovelCard> = response.data;
                setCards(pageData.content);
                setTolalPages(pageData.totalPages);
            } catch(error) {
                console.log("Error ao buscar Card por Username: ", error);
            }

        }

        const novelCardsPages = async () => {

            try {
                const response = await axios.get(`${API_URL}/novels/pages?page=${page}&size=${size}`);
                const pageData: Page<NovelCard> = response.data;
                setCardsPages(pageData.content);
                setTolalPages(pageData.totalPages);
            } catch(error) {
                console.log("Error ao buscar Card por Username: ", error);
            }

        }
        
        const fetchGenders = async () => {
            try {
                const response = await axios.get(`${API_URL}/genders`, {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                })
                setGendersBackend(response.data);
            } catch(error) {
                console.log("Error ao buscar gêneros: ", error)
            }
        };

        const novelByGenders = async () => {

            try {
                const response = await axios.get(
                    `${API_URL}/novels/genders?genders=${genders.join(",")}&page=${page}&size=${size}`
                );
                const pageData: Page<NovelCard> = response.data;
                setCardsPages(pageData.content);
                setTolalPages(pageData.totalPages);
            } catch(error) {
                console.log("Error ao buscar Card por Username: ", error);
            }
        }

        fetchGenders();
        novelCardsPages();
        findNovelCards();
        novelByGenders();
    }, [novelName, page, genders]);

    useEffect(() => {

        const findNovelCardsByNovelName = setTimeout(() =>{
            axios.get(`${API_URL}/novels/search/${novelNameSearch}?page=${page}&size=${size}`)
            .then(response => {
                const pageData: Page<NovelCard> = response.data;
                setCards(pageData.content);
            }).catch(error => console.log("Error ao buscar Card por Username: ", error));

        }, 500); 
        return () => clearTimeout(findNovelCardsByNovelName);
    }, [novelNameSearch]);

    function pageSeacrValid(pag:number, max:number) {
        if(pag >= 0 && pag <= max) {
            setPage(pag);
        } else {
            setPage(page);
        }
    }

    function addGenders(genderSelected:GendersBackend) {
        const gender = genderSelected.gender;
        if(!genders.includes(gender)) {
            setGenders([...genders, gender]);
        } else {
            setGenders(genders.filter(item => item !== gender));
        }
    }

    const token = localStorage.getItem('token');
    if(!token) return <Navigate to="/login"/>

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={true}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.divMain}>
                    <h1>Novels 📖</h1>
                </div>
                <Tabs defaultActiveKey="secao1" id="tabs-example" className="mb-3">
                    <Tab eventKey="secao1" title="Buscar por Nome">
                        <br />
                        <div className={styles.searchNovel}>
                            <div className="input-group input-group-lg">
                                <span className="input-group-text" id="inputGroup-sizing-lg">Buscar Novel</span>
                                <input 
                                    type="text" 
                                    className="form-control" 
                                    aria-label="Sizing example input" 
                                    aria-describedby="inputGroup-sizing-lg"
                                    value={novelNameSearch}
                                    onChange={(e) => setNovelNameSearch(e.target.value)}
                                />
                            </div>
                        </div>

                        
                        <div className={styles.divMain}>
                            {cards.length === 0 ? (
                                <>
                                    <br/>
                                    <p className={styles.pCenter}>Nenhuma Foi Encontrada.</p>
                                </>
                            ) : (
                                <div className={styles.novelsContainer}>
                                    <div className={styles.cardContainer}>
                                        {cards.map((card, index) => (
                                            <Card
                                                index={index}
                                                authorId={card.authorId}
                                                novelId={card.novelId}
                                                imagePath={card.imageUri}
                                                novelName={card.novelName}
                                                author={card.username}
                                                userAuthenticate={true}
                                            />
                                        ))}
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
                                    
                                </div>
                            )}
                        </div>
                    </Tab>
                    <Tab eventKey="secao2" title="Buscar por Gêneros">
                        <div className={styles.divGenders}>
                            {gendersBackend.map((gender, index) => (
                                <div className="form-check" key={index}>
                                        <input 
                                        className="form-check-input" 
                                        type="checkbox" 
                                        value={gender.gender} onChange={() => addGenders(gender)} />
                                        <label>
                                            {gender.genderType}
                                        </label>
                                </div>
                            ))}
                        </div>

                        <div className={styles.divMain}>
                            {cardsPages.length === 0 ? (
                                <>
                                    <br/>
                                    <p className={styles.pCenter}>Nenhuma Foi Encontrada.</p>
                                </>
                            ) : (
                                <div className={styles.novelsContainer}>
                                    <div className={styles.cardContainer}>
                                        {cardsPages.map((card, index) => (
                                            <Card
                                                index={index}
                                                authorId={card.authorId}
                                                novelId={card.novelId}
                                                imagePath={card.imageUri}
                                                novelName={card.novelName}
                                                author={card.username}
                                                userAuthenticate={true}
                                            />
                                        ))}
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
                                    
                                </div>
                            )}
                        </div>
                    </Tab>
                </Tabs>
            </div>
            <Footer />
        </div>
    );
}

export default Novels;