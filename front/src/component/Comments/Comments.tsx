import React, { useState, useEffect } from 'react';
import Comment from '../Comment/Comment';
import CommentForm from '../CommentForm/CommentForm';
import styles from './Comments.module.css';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

interface CommentsProps {
    currentUserId:string;
}

export interface BackendCommentsI {
    id: number
    authorId: number
    username: string
    comentByCode: number
    entityId: number
    parentId: number | null
    bodyText: string
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

    const [backendComments, setBackendComments] = useState<BackendCommentsI[]>([]);
    const [activeComment, setActiveComment] = useState(null);

    const rootComments = backendComments.filter(
        (backendComment) => backendComment.parentId === null
    );

    const getReplies = (commentId:number) => {
        return backendComments
        .filter((backendComment) => backendComment.parentId === commentId)
        .sort(
            (a, b) =>
                new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime()
        );
    };

    const addComment = async (bodyText:string, parentId:number) => {
        try {
            const response = await axios.post(`${API_URL}/comments/`, {
                authorId,
                commentByCode,
                entityId,
                parentId: parentId ? parentId : null,
                bodyText

            });
            const newComment = response.data;
            setBackendComments(comments => [newComment, ...comments]);

        } catch(error) {
            console.log("Error ao adicionar comentário", error);
        }
    };

    const updateComment = async (bodyText:string, commentId:number) => {

        try {
            await axios.put(`${API_URL}/comments/${commentId}`, {
                    bodyText
            })
        } catch(error) {
            console.log(error)
        }

        const updateBackendComments = backendComments.map((backendComment) => {
            if(backendComment.id === commentId) {
                return { ...backendComment, bodyText: bodyText }
            }
            return backendComment
        })
        setBackendComments(updateBackendComments);
        setActiveComment(null);
    };

    const deleteComment = (commentId:number) => {
        try {
            if(window.confirm('Delete this comment?')) {
                axios.delete(`${API_URL}/comments/${commentId}`);
            }
        } catch(error) {
            console.log(error)
        }
        const updateBackendComments = backendComments.filter((backendComment) => 
            backendComment.id !== commentId
        );
        setBackendComments(updateBackendComments);
    }

    useEffect(() => {
        const fetchComments = async () => {
            try {
                const response = await axios.get(`${API_URL}/comments`);
                setBackendComments(response.data)
            } catch(error) {
                console.log(error)
            }
        }
        fetchComments();
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