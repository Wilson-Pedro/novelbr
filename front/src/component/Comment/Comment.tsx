import React from 'react';

import userIcon from '../../assets/user-icon_2.png';

import styles from './Comment.module.css';

import { CommentProps } from '../../interfaces/CommentInterfaces';
import CommentForm from '../CommentForm/CommentForm';
import { useNavigate } from 'react-router-dom';

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
    replyComment,
    token
}) => {
    const fiveMinutes = 300000;
    const timePassed = new Date().getTime() - new Date(comment.createdAt).getTime() > fiveMinutes;
    const canReply = Boolean(currentUserId) && token !== null;
    const canEdit = currentUserId === comment.authorId && !timePassed && token !== null;
    const canDelete = currentUserId === comment.authorId && !timePassed && token !== null;
    const createdAt = new Date(comment.createdAt).toLocaleDateString();

    const isReplying = 
        activeComment &&
        activeComment.type === "replying" && 
        activeComment.id === comment.id  && 
        token !== null;

    const isEditing = 
        activeComment &&
        activeComment.type === "editing" && 
        activeComment.id === comment.id  && 
        token !== null;

    const replyId = parentId ? parentId : comment.id;

    const navigate = useNavigate();

    function goToAuthorNovels() {
        navigate(`/author/${comment.username}/novels`);
    }
    
    return(
        <div className={styles.comment}>
            <div className={styles.commentImageContainer}>
                <img src={userIcon} />
            </div>
            <div className={styles.commentRightPart}>
                <div className={styles.commentContent}>
                    <div className={styles.commentAuthor} onClick={goToAuthorNovels}>
                        {comment.username}
                    </div>
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
                                token={token}
                            />
                        ))}
                    </div>
                )}
                
            </div>
        </div>
    );
}

export default Comment;