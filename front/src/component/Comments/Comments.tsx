import React, { useState, useEffect } from 'react';
import getComments from '../apiComments/getComments';
import Comment from '../Comment/Comment';

interface CommentsProps {
    currentUserId:string;
}

interface BackendCommentsI {
    id: string,
    body: string,
    username:string,
    userId:string,
    parentId:string | null
    createdAt: string;
}

const Comments: React.FC<CommentsProps> = ({ currentUserId }) => {

    const [backendComments, setBackendComments] = useState<BackendCommentsI[]>([]);
    const rootComments = backendComments.filter(
        (backendComment) => backendComment.parentId === null
    );

    useEffect(() => {
        getComments().then((data) => {
            setBackendComments(data);
        });
    }, []);

    return(
        <div className='comments'>
            <h3 className="comments-title">Comments</h3>
            <div className="comments-container">
                {rootComments.map((rootComment) => (
                    <>
                        <Comment comment={rootComment} />
                        <br />
                    </>
                ))}
            </div>
        </div>
    );
}

export default Comments;