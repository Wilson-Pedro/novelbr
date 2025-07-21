import React from 'react';

import userIcon from '../../assets/user-icon_2.png';

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
    }
    replies: BackendCommentsI[]
    currentUserId: string
    deleteComment:any
}

const Comment: React.FC<CommentProps> = ({ comment, replies, currentUserId, deleteComment }) => {
    const fiveMinutes = 300000;
    const timePassed = new Date().getTime() - new Date(comment.createdAt).getTime() > fiveMinutes;
    const canReply = Boolean(currentUserId);
    const canEdit = currentUserId === comment.userId && !timePassed;
    const canDelete = currentUserId === comment.userId && !timePassed;
    const createdAt = new Date(comment.createdAt).toLocaleDateString();
    return(
        <div className={styles.comment}>
            <div className={styles.commentImageContainer}>
                <img src={userIcon} />
            </div>
            <div className={styles.commentRightPart}>
                <div className='comment-content'>
                    <div className='comment-author'>{comment.username}</div>
                    <div>{comment.createdAt}</div>
                </div>
                <div className={styles.commentText}>{comment.body}</div>
                <div className={styles.commentActions}>
                    {canReply && 
                        <div className={styles.commentAction}>
                            Reply
                        </div> 
                    }
                    {canEdit && 
                        <div 
                            className={styles.commentAction}>
                                Edit
                        </div> 
                    }
                    {canDelete && 
                        <div 
                            className={styles.commentAction} 
                            onClick={() => deleteComment(comment.id)}>
                                Delete
                        </div> 
                    }
                </div>
                {replies.length > 0 && (
                    <div className={styles.replies}>
                        {replies.map(reply => (
                            <Comment 
                                key={reply.id}
                                comment={reply} 
                                replies={[]} 
                                currentUserId={currentUserId}
                                deleteComment={deleteComment}
                            />
                        ))}
                    </div>
                )}
                
            </div>
        </div>
    );
}

export default Comment;