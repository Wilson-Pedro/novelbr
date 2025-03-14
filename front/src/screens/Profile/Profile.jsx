import React from 'react';
import styles from './Profile.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Card from './../../component/cards/Card';
import Footer from './../../layout/footer/Rodape';
import imagePath1 from '../../assets/A_casa_ao_lado.jpg';
import { Link } from 'react-router-dom';

import 'bootstrap/dist/css/bootstrap.min.css';

export default function Profile() {

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={true}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.divMain}>
                    <h1>Informações do Usuário ℹ️</h1>
                    <h4>Nome: <span className={styles.noWeight}>ROBERTO FIRMINO SILVA</span></h4>
                    <h4>Pseudônimo: <sapn className={styles.noWeight}>All Star</sapn></h4>
                    <h4>Email: <span className={styles.noWeight}>roberto@gmail.com</span></h4>
                </div>

                <hr />
                <div className={styles.divMain}>
                    <h1>Configurações ⚙️</h1>
                    <p className={styles.info}>Crie uma nova jornada.</p>
                    <button type="button" class="btn btn-success">
                        <Link className={styles.linkNone} to="/novelRegister">Criar História</Link>
                    </button>
                </div>

                <hr />
                <div className={styles.divMain}>
                    <h1>Minhas Obras 📖</h1>
                    <div className={styles.cardContainer}>
                        <Card
                            imagePath={imagePath1}
                            title="A casa ao Lado."
                            charpter={23}
                        />
                    </div>
                    <Link to="/homeUser"><button type="button" class="btn btn-danger">Voltar</button></Link>
                </div>
            </div>
            <Footer />
        </div>
    );
}