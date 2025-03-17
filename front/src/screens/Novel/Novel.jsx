import React, { useState } from 'react';
import styles from './Novel.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import imagePath from '../../assets/Jornada para o Além.jpg';

import { useParams, useLocation } from 'react-router-dom';

export default function Novel() {

    const params = useParams();
    const novelName =  params.novelName;

    const location = useLocation();
    const { isAuth } = location.state || {};

    const[userAuthenticate, setUserAuthenticate] = useState(isAuth);

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={userAuthenticate}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.mainHead}>
                    <div className={styles.containerImage}>
                        <img className={"img-fluid"} src={imagePath} />
                    </div>
                    <div className={styles.containerInfo}>
                        <h1>{novelName}</h1>
                        <p><strong>Autor:</strong> S. Elppa</p>
                        <p><strong>Gênros:</strong> Aventura, Ação, Drama, Medieval, Magia.</p>
                        <h4>Sinopse</h4>
                        <p className={styles.sinopse}>
                            Em um mundo medieval repleto de magia, criaturas ancestrais e civilizações esquecidas, a profecia do Grande Véu finalmente se concretiza: a barreira entre o mundo dos vivos e o Além está se rompendo. Espíritos errantes vagam pela terra, e monstros há muito adormecidos despertam de seu torpor. Apenas uma chave pode selar a fenda entre os reinos, mas ela foi perdida há séculos, enterrada nos confins do desconhecido.
                            <br/><br/>
                            Elarys, uma jovem ladina meio-elfa, se vê envolvida no caos ao descobrir que carrega em seu sangue a marca dos Guardiões do Véu, uma linhagem há muito extinta. Ao lado de um grupo improvável — um mago caído em desgraça, um guerreiro orc renegado e uma draconiana com segredos sombrios — ela embarca em uma jornada perigosa pelas terras selvagens de Eldoria, enfrentando seres místicos, deuses esquecidos e seus próprios demônios internos.
                            <br/><br/>
                            A cada passo, o tempo se esgota, e a sombra do Além se alastra. O destino do mundo está nas mãos de quem nunca quis ser heroína. Será Elarys capaz de impedir o fim da realidade antes que tudo sucumba ao vazio eterno?
                        </p>
                    </div>
                </div>
                <hr />
                <div className={styles.capitulos}>
                    <h1>Capítulos</h1>
                    <ul>
                        <li>1º capítulo.</li>
                        <li>2º capítulo.</li>
                        <li>3º capítulo.</li>
                        <li>4º capítulo.</li>
                        <li>5º capítulo.</li>
                        <li>6º capítulo.</li>
                        <li>7º capítulo.</li>
                        <li>8º capítulo.</li>
                    </ul>
                    
                </div>
            </div>
            <Footer />
        </div>
    );
}