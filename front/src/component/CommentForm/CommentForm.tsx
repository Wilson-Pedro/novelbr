import React,{ useState, useEffect } from 'react';
import styles from './CommentForm.module.css';

interface CommentFormProp {
    handleSubmit: any
    submitLabel: any
    hasCancelButton?:any
    initialText?: string
    handleCancel: any
}

const CommentForm: React.FC<CommentFormProp> = ({ 
    handleSubmit, 
    submitLabel, 
    hasCancelButton = false, 
    initialText = "",
    handleCancel
    }) => {
    const [text, setText] = useState(initialText);
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
            <div>
                <button className={styles.commentFormButton} disabled={isTextAreaDisabled}>{submitLabel}</button>
                {hasCancelButton && (
                    <button 
                        type="button"
                        className={[styles.commentFormButton, styles.commentFormCancelButton].join(' ')}
                        onClick={handleCancel}
                    > Cancel </button>
                )}
            </div>
        </form>
    );
}

export default CommentForm;