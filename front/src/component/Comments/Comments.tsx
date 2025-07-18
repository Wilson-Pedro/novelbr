import React, { useState, useEffect } from 'react';
import { getComments, createComment } from '../apiComments/getComments';
import Comment from '../Comment/Comment';
import CommentForm from '../CommentForm/CommentForm';

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

    useEffect(() => {
        getComments().then((data) => {
            setBackendComments(data);
        });
    }, []);

    const addComment = (text:string, parentId:string) => {
        console.log("addComment", text, parentId);
        createComment(text, parentId).then(comment => {
            setBackendComments([comment, ...backendComments])
        })
    };

    return(
        <div className='comments'>
            <h3 className="comments-title">Comments</h3>
            <div className="comment-form-title">Write Comment</div>
            <CommentForm submitLabel="Write" handleSubmit={addComment}/>
            <div className="comments-container">
                {rootComments.map((rootComment) => (
                    <>
                        <Comment 
                            key={rootComment.id}
                            comment={rootComment}
                            replies={getReplies(rootComment.id)}
                        />
                        <br />
                    </>
                ))}
            </div>
        </div>
    );
}

export default Comments;