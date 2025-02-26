import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import styles from './Table.module.css';

export default function Table() {
    return(
        <div className="container mt-4">
            {/* <h2 className="mb-3">Mais Populares</h2> */}
            <table className="table table-striped table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>Nome</th>
                        <th>Autor</th>
                        <th>Capítulos</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th>Renegade Immortal</th>
                        <th>耳根</th>
                        <th>2088</th>
                    </tr>
                    <tr>
                        <th>The Overlord of Blood and Iron</th>
                        <th>Dam Hwa Gong</th>
                        <th>355</th>
                    </tr>
                    <tr>
                        <th>Red Storm</th>
                        <th>노경찬</th>
                        <th>235</th>
                    </tr>
                </tbody>
            </table>
        </div>
    );
}