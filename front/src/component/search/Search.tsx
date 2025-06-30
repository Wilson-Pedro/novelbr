import React, { useState, useEffect } from 'react';
import styles from './Search.module.css';
import { FaSearch } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

interface NovelsFinded {
    id:number;
    novelName:string;
}

const Search: React.FC = () => {

    const [novelName, setNovelName] = useState<string>('');
    const [novelId, setNovelId] = useState<number>(0);
    const [novelsFinded, setNovelsFinded] = useState<NovelsFinded[]>([]);

    const navigate = useNavigate();

    useEffect(() => {
        const search = async () => {
            if(novelName.length < 2) {
                setNovelsFinded([]);
                return;
            }

            try {
                const response = await axios.get(`${API_URL}/novels/search/${novelName}`);
                setNovelsFinded(response.data);
            } catch(error) {
                console.log('Error ao tentar buscar novels ', error);
            }
        };

        const delay = setTimeout(search, 100);

        return () => clearTimeout(delay);
    }, [novelName]);

    function goToNovel(novelId:number) {
        setNovelName('');
        setNovelsFinded([]);
        navigate(`/novel/${novelId}`);
    }

    return (
        <div className={styles.searchContainer} >
            <input 
                type="text" 
                placeholder='Buscar...'
                onChange={(e) => setNovelName(e.target.value)}
            />
            <FaSearch className={styles.searchIncon} />
            {novelsFinded.length > 0 && (
                <ul className={styles.ul}>
                    {novelsFinded.map((novel) => (
                        <li 
                        className={styles.li} 
                        onClick={() => goToNovel(novel.id)} 
                        key={novel.id}>{novel.novelName}</li>
                    ))}
                </ul>
            )

            }
        </div>
    );
}

export default Search;