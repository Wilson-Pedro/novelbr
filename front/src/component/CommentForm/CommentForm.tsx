import React,{ useState, useEffect } from 'react';
import styles from './CommentForm.module.css';

interface CommentFormProp {
    handleSubmit: any
    submitLabel: any
}

const CommentForm: React.FC<CommentFormProp> = ({ handleSubmit, submitLabel }) => {
    const [text, setText] = useState("");
    const isTextAreaDisabled = text.length === 0;
    const onSubmit = (e:any) => {
        e.preventDefault();
        handleSubmit(text);
        setText("");
    }
    return(
        <form onSubmit={onSubmit}>
            <textarea 
                className={styles.commentFormTextarea} 
                value={text}
                onChange={(e) => setText(e.target.value)}
            />
            <button className={styles.commentFormButton} disabled={isTextAreaDisabled}>{submitLabel}</button>
        </form>
    );
}

export default CommentForm;