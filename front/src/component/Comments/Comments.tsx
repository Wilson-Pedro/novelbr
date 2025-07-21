import React, { useState, useEffect } from 'react';
import { getComments, createComment, deleteComment as deleteCommentApi } from '../apiComments/getComments';
import Comment from '../Comment/Comment';
import CommentForm from '../CommentForm/CommentForm';
import styles from './Comments.module.css';

interface CommentsProps {
    currentUserId:string;
}

export interface BackendCommentsI {
    id: string
    body: string
    username:string
    userId:string
    parentId:string | null
    createdAt: string
}

const Comments: React.FC<CommentsProps> = ({ currentUserId }) => {

    const [backendComments, setBackendComments] = useState<BackendCommentsI[]>([]);
    const rootComments = backendComments.filter(
        (backendComment) => backendComment.parentId === null
    );

    const getReplies = (commentId:string) => {
        return backendComments
        .filter((backendComment) => backendComment.parentId === commentId)
        .sort(
            (a, b) =>
                new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
        );
    };
    
    const addComment = (text:string, parentId:string) => {
        console.log("addComment", text, parentId);
        createComment(text, parentId).then(comment => {
            setBackendComments([comment, ...backendComments])
        })
    };

    const deleteComment = (commentId:string) => {
        if(window.confirm('Delete this comment?')) {
            deleteCommentApi(commentId).then(() => {
                const updateBackendComments = backendComments.filter(
                    (backendComment) => backendComment.id !== commentId
                );
                setBackendComments(updateBackendComments);
            });
        }
    }

    useEffect(() => {
        getComments().then((data) => {
            setBackendComments(data);
        });
    }, []);

    return(
        <div className={styles.comments}>
            <h3 className={styles.commentsTitle}>Comments</h3>
            <div className={styles.commentsFormTitle}>Write Comment</div>
            <CommentForm submitLabel="Write" handleSubmit={addComment}/>
            <div className={styles.commentsContainer}>
                {rootComments.map((rootComment) => (
                    <>
                        <Comment 
                            key={rootComment.id}
                            comment={rootComment}
                            replies={getReplies(rootComment.id)}
                            currentUserId={currentUserId}
                            deleteComment={deleteComment}
                        />
                    </>
                ))}
            </div>
        </div>
    );
}

export default Comments;