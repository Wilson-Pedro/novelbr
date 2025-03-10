import React from 'react';
import styles from './Navbar.module.css';
import Search from './../../component/search/Search';
import  { Link } from 'react-router-dom';

export default function Navbar({ perfil=false }) {

    return(
        <>
            <div className={styles.divTitle}>
                <h1>NOVELS BR</h1>
            </div>
            <div className={styles.search}>
                <Search />
            </div>
            <nav>
                {perfil !== false ? (
                    <>
                        <p>
                            <Link className={styles.linkNone} to="/profile">Usuário</Link>
                        </p>
                        <p>
                            <Link className={styles.linkNone} to="/">Sair</Link>
                        </p>   
                    </>
                ) : (
                    <>
                        <p>
                            <Link className={styles.linkNone} to="/register">Casdastrar</Link>
                        </p>
                        <p>
                            <Link className={styles.linkNone} to="/login">Login</Link>
                        </p>
                    </>
                )}
            </nav>
        </>
    );
}