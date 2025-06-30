import React from 'react';
import styles from './Rodape.module.css';

const Rodape: React.FC = () => {
    return(
        <footer className={styles.footer}>
            <h3 className="p-1">NovelsBR.</h3>
        </footer>
    );
}

export default Rodape;