import React from 'react';

import userIcon from '../../assets/user-icon_2.png';

import styles from './Comment.module.css';

import { BackendCommentsI } from '../Comments/Comments';
import CommentForm from '../CommentForm/CommentForm';
import { updateComment } from '../apiComments/getComments';

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
    updateComment:any
    activeComment:any
    setActiveComment:any
    parentId:any | null
    addComment:any
}

const Comment: React.FC<CommentProps> = 
({ comment, 
    replies, 
    currentUserId, 
    deleteComment,
    updateComment,
    activeComment, 
    setActiveComment, 
    parentId,
    addComment
}) => {
    const fiveMinutes = 300000;
    const timePassed = new Date().getTime() - new Date(comment.createdAt).getTime() > fiveMinutes;
    const canReply = Boolean(currentUserId);
    const canEdit = currentUserId === comment.userId && !timePassed;
    const canDelete = currentUserId === comment.userId && !timePassed;
    const createdAt = new Date(comment.createdAt).toLocaleDateString();

    const isReplying = 
        activeComment &&
        activeComment.type === "replying" && 
        activeComment.id === comment.id;

    const isEditing = 
        activeComment &&
        activeComment.type === "editing" && 
        activeComment.id === comment.id;

    const replyId = parentId ? parentId : comment.id;
    
    return(
        <div className={styles.comment}>
            <div className={styles.commentImageContainer}>
                <img src={userIcon} />
            </div>
            <div className={styles.commentRightPart}>
                <div className={styles.commentContent}>
                    <div className={styles.commentAuthor}>{comment.username}</div>
                    <div>{comment.createdAt}</div>
                </div>
                {!isEditing && <div className={styles.commentText}>{comment.body}</div>}
                {isEditing && (
                    <CommentForm 
                        submitLabel="Update"
                        hasCancelButton
                        initialText={comment.body}
                        handleSubmit={(text:string) => updateComment(text, comment.id)}
                        handleCancel={() => setActiveComment(null)}
                    />
                )}
                <div className={styles.commentActions}>
                    {canReply && 
                        <div className={styles.commentAction} 
                            onClick={() => setActiveComment({ id: comment.id, type: 'replying' })}>
                            Reply
                        </div> 
                    }
                    {canEdit && 
                        <div className={styles.commentAction}
                            onClick={() => setActiveComment({ id: comment.id, type: 'editing' })}>
                                Edit
                        </div> 
                    }
                    {canDelete && 
                        <div className={styles.commentAction} 
                            onClick={() => deleteComment(comment.id)}>
                                Delete
                        </div> 
                    }
                </div>
                {isReplying && (
                    <CommentForm 
                        submitLabel="Reply"
                        handleSubmit={(text:string) => addComment(text, replyId)}
                        hasCancelButton
                        handleCancel={() => {setActiveComment(null)}}
                        initialText={comment.body}
                    />
                )}
                {replies.length > 0 && (
                    <div className={styles.replies}>
                        {replies.map(reply => (
                            <Comment 
                                key={reply.id}
                                comment={reply} 
                                replies={[]} 
                                currentUserId={currentUserId}
                                deleteComment={deleteComment}
                                updateComment={updateComment}
                                activeComment={activeComment}
                                setActiveComment={setActiveComment}
                                parentId={comment.id}
                                addComment={addComment}
                            />
                        ))}
                    </div>
                )}
                
            </div>
        </div>
    );
}

export default Comment;