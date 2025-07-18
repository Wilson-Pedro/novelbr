import React from 'react';

import userIcon from '../../assets/user-icon.png';

import styles from './Comment.module.css';

interface CommentProps {
    comment: {
        id: string,
        body: string,
        username:string,
        userId:string,
        parentId:string | null
        createdAt: string,
    };
}

const Comment: React.FC<CommentProps> = ({ comment }) => {
    return(
        <div className={styles.comment}>
            <div className={styles.commentImageContainer}>
                <img src={userIcon} />
            </div>
            <div className='comment-right-part'>
                <div className='comment-content'>
                    <div className='comment-author'>{comment.username}</div>
                    <div>{comment.createdAt}</div>
                </div>
                <div className='comment-text'>{comment.body}</div>
            </div>
        </div>
    );
}

export default Comment;