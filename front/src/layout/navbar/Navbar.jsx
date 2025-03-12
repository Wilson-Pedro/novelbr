import React from 'react';
import styles from './Navbar.module.css';
import Search from './../../component/search/Search';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';

import Dropdown from 'react-bootstrap/Dropdown';

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
                        <Dropdown>
                            <Dropdown.Toggle variant='Warning'>
                                Usuário
                            </Dropdown.Toggle>

                            <Dropdown.Menu className={styles.dropdownMenu}>
                                <Dropdown.Item className={styles.dropdownItem} href="/profile">Perfil</Dropdown.Item>
                                <Dropdown.Item className={styles.dropdownItem} href="/">Sair</Dropdown.Item>
                            </Dropdown.Menu>
                        </Dropdown>
                        {/* <p>
                            <Link className={styles.linkNone} to="/profile">Usuário</Link>
                        </p>
                        <p>
                            <Link className={styles.linkNone} to="/">Sair</Link>
                        </p>    */}
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