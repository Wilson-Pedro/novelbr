import { api, apiAuth } from "./api"

interface CommentCreate {
    authorId: number,
    commentByCode: number,
    entityId: number,
    parentId: number | null,
    bodyText: string
}

export const commentService = {

    async create(commentCreate: CommentCreate) {

        const response = await apiAuth.post(`/comments/`, {
                authorId: commentCreate.authorId,
                commentByCode: commentCreate.commentByCode,
                entityId: commentCreate.entityId,
                parentId: commentCreate?.parentId || null,
                bodyText: commentCreate.bodyText
            })

        return response;
    },

    async updateComment(bodyText: string, commentId: number) {

        const response = await apiAuth.put(`/comments/${commentId}`, {
            bodyText
        });
        return response;

    },

    async deleteComment(commentId: number) {
        await apiAuth.delete(`/comments/${commentId}`);
    },

    async fetchCommentsByChapter(chapterId: number) {
        const response = await api.get(`/comments/chapters/${chapterId}`);
        return response;
    },

    async fetchCommentsByNovel(novelId: number) {
        const response = await api.get(`/comments/novels/${novelId}`);
        return response;
    }
}