import React, { useState } from 'react';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

export default function UploadImage() {

    const [selectFile, setSelectFile] = useState(null);

    const uploadImage = async (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append("file", selectFile);
        try {
            await axios.post(`${API_URL}/upload/image`, formData, {
                headers: {
                    "Content-Type": "multipart/form-data"
                }
        });
        } catch(error) {
            console.log(error.getErrorMessage);
        }
    }

    const handleFileChange = async (e) => {
        if(e.target.files[0] != null) {
            setSelectFile(e.target.files[0]);
        }
    }

    return(
        <form onSubmit={uploadImage} enctype="multipart/form-data">
            <input 
                type="file"
                name="file"
                onChange={handleFileChange}
            />
            <input type="submit" value="enviar" />
        </form>
    )
}