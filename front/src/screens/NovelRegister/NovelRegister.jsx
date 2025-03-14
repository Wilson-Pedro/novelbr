import React from 'react';
import styles from './NovelRegister.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import { Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

export default function NovelRegister() {
    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={true}
                />
            </nav>
            <div className={styles.main}>
                <form action="">
                    <div className={styles.formDiv}>
                        <label htmlFor="">Nome da Obra</label>
                        <input type="text" class="form-control" placeholder="Nome da Obra" aria-label="Nome da Obra" aria-describedby="basic-addon1" />
                    </div>

                    <div className={styles.formDiv}>
                        <label htmlFor="">Autor</label>
                        <input type="text" class="form-control" placeholder="Autor" aria-label="Autor" aria-describedby="basic-addon1" />
                    </div>

                    <div className={styles.formDiv}>
                        <label htmlFor="">Gênros</label>
                        <input type="text" class="form-control" placeholder="Gênros" aria-label="Gênros" aria-describedby="basic-addon1" />
                    </div>

                    <div className={styles.formDiv}>
                        <label htmlFor="">Sinopse</label>
                        <textarea className="form-control" placeholder="Sinopse" id="floatingTextarea2"></textarea>
                    </div>
                    
                    <div className={styles.divBtn}>
                        <button type="button" class="btn btn-primary">Criar</button>
                        <button type="button" class="btn btn-danger">
                            <Link className={styles.linkNone} to="/profile">Cancelar</Link>
                        </button>
                    </div>
                </form>
            </div>
            <Footer />
        </div>
    );
}