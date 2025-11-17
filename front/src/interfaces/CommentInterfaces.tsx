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

export interface CommentFormProp {
    handleSubmit: any;
    submitLabel: any;
    hasCancelButton?:any;
    initialText?: string;
    handleCancel: any;
}

export interface CommentsProps {
    currentUserId:number;
    comments:BackendCommentsI[];
    postComment:any;
    putComment:any;
    deleteComment:any;
    token:string | null;
}

export interface CommentProps {
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
    token:string | null
}