import React from 'react';
import styles from './Chapter.module.css';
import Navbar from './../../layout/navbar/Navbar';
import Footer from './../../layout/footer/Rodape';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useLocation } from 'react-router-dom';

export default function Chapter() {

    const location = useLocation();
    const { isAuth } = location.state || {};

    return(
        <div className={styles.container}>
            <nav className={styles.navbar}>
                <Navbar 
                    userAuthenticate={isAuth}
                />
            </nav>
            <div className={styles.main}>
                <div className={styles.divTitle}>
                    <h1>Isto é vida.</h1>
                </div>
                <div className={styles.divButtons}>
                    <button type="button" class="btn btn-warning">&#60; Anterior </button>
                    <button type="button" class="btn btn-warning">Próximo &#62;</button>
                </div>
                <div className={styles.divChapter}>
                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Incidunt eligendi fugiat 
                        obcaecati iure expedita qui quasi maiores animi placeat reprehenderit, officiis 
                        quos dicta harum sit modi delectus. Laborum, numquam voluptates. 
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa.
                    </p>

                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nulla placeat quos odit 
                        quisquam aliquam temporibus itaque dolor veritatis accusantium, voluptates, 
                        consectetur fugit iusto illo. Incidunt sequi optio ratione quisquam vel.
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor, sit amet consectetur adipisicing elit. Amet quisquam,
                          commodi dignissimos, ea quo ratione vitae, minima magnam iusto dolorem adipisci? Perferendis aperiam deleniti, a sequi veritatis ducimus impedit cumque.
                    </p>


                    <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Molestias voluptatibus 
                        culpa facere explicabo? Porro, delectus maxime deserunt odio animi, aliquam neque 
                        saepe illum modi vero doloremque, aut libero asperiores expedita.
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit, amet consectetur adipisicing elit. Itaque totam libero 
                         iusto ut, veritatis sed illo dignissimos atque nostrum quod nobis esse dolor 
                         doloremque a assumenda quas quam! Non, sunt.
                    </p>


                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Incidunt eligendi fugiat 
                        obcaecati iure expedita qui quasi maiores animi placeat reprehenderit, officiis 
                        quos dicta harum sit modi delectus. Laborum, numquam voluptates. 
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa.
                    </p>


                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis obcaecati 
                        necessitatibus magni eum similique iure voluptatibus, fugit vitae ipsum voluptate.
                         Cum corporis sunt culpa saepe nobis quos maiores libero voluptatem! 
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa.
                    </p>

                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Incidunt eligendi fugiat 
                        obcaecati iure expedita qui quasi maiores animi placeat reprehenderit, officiis 
                        quos dicta harum sit modi delectus. Laborum, numquam voluptates. 
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa.
                    </p>

                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Incidunt eligendi fugiat 
                        obcaecati iure expedita qui quasi maiores animi placeat reprehenderit, officiis 
                        quos dicta harum sit modi delectus. Laborum, numquam voluptates. 
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa.
                    </p>

                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Incidunt eligendi fugiat 
                        obcaecati iure expedita qui quasi maiores animi placeat reprehenderit, officiis 
                        quos dicta harum sit modi delectus. Laborum, numquam voluptates. 
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa.
                    </p>

                    <p>Lorem, ipsum dolor sit amet consectetur adipisicing elit. Incidunt eligendi fugiat 
                        obcaecati iure expedita qui quasi maiores animi placeat reprehenderit, officiis 
                        quos dicta harum sit modi delectus. Laborum, numquam voluptates. 
                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa. 
                         Lorem ipsum dolor sit amet consectetur adipisicing elit. Deleniti earum natus quis
                         ex dolores eos adipisci nihil nam quia provident, iure consequuntur quae 
                         blanditiis! Molestias iste sit voluptatum rem culpa.
                    </p>
                </div> <br /><br />
                <div className={styles.divButtons}>
                    <button type="button" class="btn btn-warning">&#60; Anterior </button>
                    <button type="button" class="btn btn-warning">Próximo &#62;</button>
                </div>
            </div>
            <Footer />
        </div>
    );
}