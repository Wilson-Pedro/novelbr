import React, { useState, useEffect } from 'react';
import styles from './Navbar.module.css';
import Search from './../../component/search/Search';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import Dropdown from 'react-bootstrap/Dropdown';

import  { Link, useNavigate } from 'react-router-dom';

import axios from 'axios';
const API_URL = process.env.REACT_APP_API;

export default function Navbar({ userAuthenticate=false }) {

    const [username, setUsername] = useState('');
    const navigate = useNavigate();

    useEffect(() => {

        const token = localStorage.getItem('token');
        const userId = parseInt(localStorage.getItem('userId'));

        if(token) {
            const fetchGenders = async () => {
                try {
                    const response = await axios.get(`${API_URL}/authors/${userId}`, {
                        headers: {
                            Authorization: `Bearer ${token}`
                        }
                    })
                    setUsername(response.data.username);
                } catch(error) {
                    console.log("Error ao dados do author por id ", error)
                }
            }
            fetchGenders();
        }
    }, [navigate]); 
    
    function goToHome() {
        if(localStorage.getItem('token') != null) {
            navigate(`/homeUser`);
        } else {
            navigate("/");
        }
    }

    function goToProfile() {
        navigate(`/profile/${username}`)
    }

    function goToRegister() {
        navigate(`/register`)
    }

        function goToLogin() {
        navigate(`/login`)
    }

    return(
        <>
            <div className={styles.divTitle}>
                <h1 onClick={goToHome}>NOVELS BR</h1>
            </div>
            <div className={styles.search}>
                <Search />
            </div>
            <nav clasName="container-fluid">
                {userAuthenticate !== false ? (
                    <>
                        <Dropdown>
                            <Dropdown.Toggle variant='Warning'>
                                Usuário
                            </Dropdown.Toggle>

                            <Dropdown.Menu className={styles.dropdownMenu}>
                                <Dropdown.Item className={styles.dropdownItem} onClick={goToHome}>Home</Dropdown.Item>
                                <Dropdown.Item className={styles.dropdownItem} onClick={goToProfile}>Perfil</Dropdown.Item>
                                <Dropdown.Item className={styles.dropdownItem} href="/">Sair</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                    </>
                ) : (
                    <>
                        <Dropdown>
                            <Dropdown.Toggle variant='Warning'>
                                Mais
                            </Dropdown.Toggle>

                            <Dropdown.Menu className={styles.dropdownMenu}>
                                <Dropdown.Item className={styles.dropdownItem} onClick={goToRegister}>Cadastrar</Dropdown.Item>
                                <Dropdown.Item className={styles.dropdownItem} onClick={goToLogin}>Login</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                    </>
                )}
            </nav>
        </>
    );
}