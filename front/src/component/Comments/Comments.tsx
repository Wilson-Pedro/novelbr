import React, { useState } from 'react';
import Comment from '../Comment/Comment';
import CommentForm from '../CommentForm/CommentForm';
import styles from './Comments.module.css';

import { CommentsProps } from '../../interfaces/CommentInterfaces';

const API_URL = process.env.REACT_APP_API;

const Comments: React.FC<CommentsProps> = ({ 
    currentUserId, 
    comments,
    postComment,
    putComment,
    deleteComment,
    token
}) => {

    const [activeComment, setActiveComment] = useState(null);

    const rootComments = comments.filter(
        (backendComment) => backendComment.parentId === null
    );

    const getReplies = (commentId:number) => {
        return comments
        .filter((backendComment) => backendComment.parentId === commentId)
        .sort(
            (a, b) =>
                new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
        );
    };

    const addComment = async (bodyText:string, parentId:number) => {
        postComment(bodyText, parentId);
    };

    const replyComment = async (bodyText:string, parentId:number) => {
        addComment(bodyText, parentId);
        setActiveComment(null);
    };

    const updateComment = async (bodyText:string, commentId:number) => {
        putComment(bodyText, commentId)
        setActiveComment(null);
    };

    const deleteThisComment = (commentId:number) => {
        deleteComment(commentId)
    }

    return(
        <div className={styles.comments}>
            <h3 className={styles.commentsTitle}>Commentários</h3>
            {token && (
                <>
                    <div className={styles.commentsFormTitle}>Escreva um comentário</div>
                    <CommentForm 
                        submitLabel="Comentar" 
                        handleSubmit={addComment}
                        handleCancel
                    />
                </>
            )}
            <div className={styles.commentsContainer}>
                {rootComments.map((rootComment) => (
                    <>
                        <Comment 
                            key={rootComment.id}
                            comment={rootComment}
                            replies={getReplies(rootComment.id)}
                            currentUserId={currentUserId}
                            deleteComment={deleteThisComment}
                            updateComment={updateComment}
                            activeComment={activeComment}
                            setActiveComment={setActiveComment}
                            parentId={rootComment.id}
                            addComment={addComment}
                            replyComment={replyComment}
                            token={token}
                        />
                    </>
                ))}
            </div>
        </div>
    );
}

export default Comments;