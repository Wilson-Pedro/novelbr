import { api, apiAuth } from "./api"

interface CommentCreate {
    authorId: number,
    commentByCode: number,
    entityId: number,
    parentId: number | null,
    bodyText: string
}

const API_URL = process.env.REACT_APP_API;

export const commentService = {

    async create(commentCreate: CommentCreate) {

        const response = await apiAuth.post(`${API_URL}/comments/`, {
                authorId: commentCreate.authorId,
                commentByCode: commentCreate.commentByCode,
                entityId: commentCreate.entityId,
                parentId: commentCreate?.parentId || null,
                bodyText: commentCreate.bodyText
            })

        return response;
    },

    async updateComment(bodyText: string, commentId: number) {

        const response = await apiAuth.put(`${API_URL}/comments/${commentId}`, {
            bodyText
        });
        return response;

    },

    async deleteComment(commentId: number) {
        await apiAuth.delete(`${API_URL}/comments/${commentId}`);
    },

    async fetchCommentsByChapter(chapterId: number) {
        const response = await api.get(`${API_URL}/comments/chapters/${chapterId}`);
        return response;
    },

    async fetchCommentsByNovel(novelId: number) {
        const response = await api.get(`${API_URL}/comments/novels/${novelId}`);
        return response;
    }
}