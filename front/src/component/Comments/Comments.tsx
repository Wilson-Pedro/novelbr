import React, { useState, useEffect } from 'react';
import { getComments, createComment, deleteComment as deleteCommentApi, updateComment as updateCommentApi } from '../apiComments/getComments';
import Comment from '../Comment/Comment';
import CommentForm from '../CommentForm/CommentForm';
import styles from './Comments.module.css';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

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

export interface ActiveCommentI {
    activeComment: {
        text: string
        parantId: any
    }
}

const Comments: React.FC<CommentsProps> = ({ currentUserId }) => {

    const [authorId, setAuthorId] = useState<number>(1);
    const [commentByCode, setCommentByCode] = useState<number>(1);
    const [entityId, setEntityId] = useState<number>(2);
    const [commentFatherId, setCommentFatherId] = useState<number | null>(null);
    const [textBody, setTextBody] = useState<string>("");

    const [backendComments, setBackendComments] = useState<BackendCommentsI[]>([]);
    const [activeComment, setActiveComment] = useState(null);
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
        try {
            axios.post(`${API_URL}/comments/`, {
                authorId,
                commentByCode,
                entityId,
                commentFatherId,
                text

            });
        } catch(error) {
            console.log("Error ao adicionar comentário", error);
        }
    };

    const updateComment = (text:string, commentId:string) => {
        updateCommentApi(text, commentId).then(() => {
            const updateBackendComments = backendComments.map((backendComment) => {
                if(backendComment.id === commentId) {
                    return { ...backendComment, body: text }
                }
                return backendComment
            })
            setBackendComments(updateBackendComments);
            setActiveComment(null);
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
            <CommentForm 
                submitLabel="Write" 
                handleSubmit={addComment}
                handleCancel
            />
            <div className={styles.commentsContainer}>
                {rootComments.map((rootComment) => (
                    <>
                        <Comment 
                            key={rootComment.id}
                            comment={rootComment}
                            replies={getReplies(rootComment.id)}
                            currentUserId={currentUserId}
                            deleteComment={deleteComment}
                            updateComment={updateComment}
                            activeComment={activeComment}
                            setActiveComment={setActiveComment}
                            parentId={rootComment.id}
                            addComment={addComment}
                        />
                    </>
                ))}
            </div>
        </div>
    );
}

export default Comments;