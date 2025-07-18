import React from 'react';

import userIcon from '../../assets/user-icon.png';

import styles from './Comment.module.css';

import { BackendCommentsI } from '../Comments/Comments';

interface CommentProps {
    comment: {
        id: string,
        body: string,
        username:string,
        userId:string,
        parentId:string | null,
        createdAt: string,
    },
    replies: BackendCommentsI[];
}

const Comment: React.FC<CommentProps> = ({ comment, replies }) => {
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
                <div className='comment-text'>{comment.body}
                    {replies.length > 0 && (
                        <div className={styles.replies}>
                            {replies.map(reply => (
                                <Comment 
                                    key={reply.id}
                                    comment={reply} 
                                    replies={[]} 
                                />
                            ))}
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
}

export default Comment;