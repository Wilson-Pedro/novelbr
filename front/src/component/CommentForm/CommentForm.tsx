import React,{ useState, useEffect } from 'react';

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
                className='comment-form-textarea' 
                value={text}
                onChange={(e) => setText(e.target.value)}
            />
            <button className='comment-form-button' disabled={isTextAreaDisabled}>{submitLabel}</button>
        </form>
    );
}

export default CommentForm;