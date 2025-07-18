import React from 'react';
import Comments from '../Comments/Comments';

export default function CommentArea() {
    return(
        <div>
            <Comments currentUserId="1" />
        </div>
    );
}