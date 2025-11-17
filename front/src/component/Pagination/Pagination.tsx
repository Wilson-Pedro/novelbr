import React from 'react';
import styles from './Pagination.module.css';
import { FaSearch as FaSearchIcon } from "react-icons/fa";

import { PaginationProp } from './../../interfaces/OtherInterfaces';

const Pagination: React.FC<PaginationProp> = ({
    page,
    totalPages,
    setPage,
    pageSeacrh,
    setPageSeacrh,
    pageSearchValid,
}) => {

    const SearchIcon = FaSearchIcon as React.FC<{ className?: string }>;

    return (
        <>
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
                >&#62;&#62;&#62;</button> <br /><br />
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
                    onClick={() => pageSearchValid(pageSeacrh - 1, totalPages - 1)}
                ><SearchIcon className={styles.searchIncon} /></button>
            </div>
            <div>
                <p>Página {page + 1} de {totalPages}</p>
            </div>
        </>
    )
}

export default Pagination;