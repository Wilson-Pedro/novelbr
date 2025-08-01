import React from 'react';

import userIcon from '../../assets/user-icon_2.png';

import styles from './Comment.module.css';

import { BackendCommentsI } from '../../screens/Novel/Novel';
import CommentForm from '../CommentForm/CommentForm';

interface CommentProps {
    comment: {
        id: number
        authorId: number
        username: string
        comentByCode: number
        entityId: number
        parentId: number | null
        bodyText: string
        createdAt: string
    }
    replies: BackendCommentsI[]
    currentUserId: number
    deleteComment:any
    updateComment:any
    activeComment:any
    setActiveComment:any
    parentId:any | null
    addComment:any
    replyComment?:any
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
    addComment,
    replyComment
}) => {
    const fiveMinutes = 300000;
    const timePassed = new Date().getTime() - new Date(comment.createdAt).getTime() > fiveMinutes;
    const canReply = Boolean(currentUserId);
    const canEdit = currentUserId === comment.authorId && !timePassed;
    const canDelete = currentUserId === comment.authorId && !timePassed;
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
                    <div>{createdAt}</div>
                </div>
                {!isEditing && <div className={styles.commentText}>{comment.bodyText}</div>}
                {isEditing && (
                    <CommentForm 
                        submitLabel="Update"
                        hasCancelButton
                        initialText={comment.bodyText}
                        handleSubmit={(text:string) => updateComment(text, comment.id)}
                        handleCancel={() => setActiveComment(null)}
                    />
                )}
                <div className={styles.commentActions}>
                    {canReply && (
                        <div className={styles.commentAction} 
                            onClick={() => setActiveComment({ id: comment.id, type: 'replying' })}>
                            Reply
                        </div> 
                    )}
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
                        handleSubmit={(text:string) => replyComment(text, replyId)}
                        hasCancelButton
                        handleCancel={() => {setActiveComment(null)}}
                        initialText={""}
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
                                replyComment={replyComment}
                            />
                        ))}
                    </div>
                )}
                
            </div>
        </div>
    );
}

export default Comment;