import React, { useState, useEffect } from 'react';
import styles from './Search.module.css';
import { FaSearch as FaSearchIcon } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';

import axios from 'axios';

import  { NovelsFinded } from '../../interfaces/NovelInterfaces';
import { Page } from '../../interfaces/ChapterInterfaces';

const API_URL = process.env.REACT_APP_API;

const SearchIcon = FaSearchIcon as React.FC<{ className?: string }>;

const Search: React.FC = () => {

    const [novelName, setNovelName] = useState<string>('');
    const [novelsFinded, setNovelsFinded] = useState<NovelsFinded[]>([]);
    const page = 0;
    const size = 20;

    const navigate = useNavigate();

    useEffect(() => {
        const search = async () => {
            if(novelName.length < 2) {
                setNovelsFinded([]);
                return;
            }

            try {
                const response = await axios.get(`${API_URL}/novels/search/${novelName}?page=${page}&size=${size}`);
                const pageData: Page<NovelsFinded> = response.data;
                setNovelsFinded(pageData.content);
            } catch(error) {
                console.log('Error ao tentar buscar novels ', error);
            }
        };

        const delay = setTimeout(search, 100);

        return () => clearTimeout(delay);
    }, [novelName]);

    function goToNovel(novelName:string) {
        setNovelName('');
        setNovelsFinded([]);
        navigate(`/novel/${novelName}`);
    }

    function goToNovels() {
        if(novelName.replace(/\s+/g, '') !== '') {
            setNovelName('');
            setNovelsFinded([]);
            navigate(`/novels/${novelName}`);
        }
    }

    return (
        <div className={styles.searchContainer} >
            <input 
                type="text" 
                placeholder='Buscar...'
                onChange={(e) => setNovelName(e.target.value)}
            />
            <span onClick={() => goToNovels()}>
                <SearchIcon className={styles.searchIncon} />
            </span>
            {novelsFinded.length > 0 && (
                <ul className={styles.ul}>
                    {novelsFinded.map((novel) => (
                        <li 
                        className={styles.li} 
                        onClick={() => goToNovel(novel.novelName)} 
                        key={novel.id}>{novel.novelName}</li>
                    ))}
                </ul>
            )

            }
        </div>
    );
}

export default Search;