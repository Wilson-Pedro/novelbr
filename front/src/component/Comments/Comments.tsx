import React, { useState } from 'react';
import Comment from '../Comment/Comment';
import CommentForm from '../CommentForm/CommentForm';
import styles from './Comments.module.css';

import { BackendCommentsI } from '../../screens/Novel/Novel';

import axios from 'axios';

const API_URL = process.env.REACT_APP_API;

interface CommentsProps {
    currentUserId:number;
    comments:BackendCommentsI[];
    entityId:number
    onAddComment:any;
    handleDeleteComment:any;
    handleUpdateComment:any;
}

const Comments: React.FC<CommentsProps> = ({ currentUserId, comments, entityId, onAddComment, handleDeleteComment, handleUpdateComment }) => {

    const [commentByCode, setCommentByCode] = useState<number>(1);
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
        try {
            const response = await axios.post(`${API_URL}/comments/`, {
                authorId: currentUserId,
                commentByCode,
                entityId,
                parentId: parentId ? parentId : null,
                bodyText

            });
            const newComment = response.data;
            onAddComment(newComment);

        } catch(error) {
            console.log("Error ao adicionar comentário", error);
        }
    };

    const replyComment = async (bodyText:string, parentId:number) => {
        addComment(bodyText, parentId);
        setActiveComment(null);
    };

    const updateComment = async (bodyText:string, commentId:number) => {

        try {
            const response = await axios.put(`${API_URL}/comments/${commentId}`, {
                    bodyText
            })

            const commentUpdated = response.data;
            handleUpdateComment(commentUpdated, commentId);
        } catch(error) {
            console.log(error)
        }
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
        handleDeleteComment(commentId)
    }

    // useEffect(() => {
    //     const fetchComments = async () => {
    //         try {
    //             const response = await axios.get(`${API_URL}/comments`);
    //             setBackendComments(response.data)
    //         } catch(error) {
    //             console.log(error)
    //         }
    //     }
    //     fetchComments();
    // }, []);

    return(
        <div className={styles.comments}>
            <h3 className={styles.commentsTitle}>Comments</h3>
            <div className={styles.commentsFormTitle}>Write Comment</div>
            <CommentForm 
                submitLabel="Comentar" 
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
                            replyComment={replyComment}
                        />
                    </>
                ))}
            </div>
        </div>
    );
}

export default Comments;