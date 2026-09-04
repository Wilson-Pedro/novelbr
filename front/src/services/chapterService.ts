import { api, apiAuth } from "./api";

export interface ChapterCreate {
    title: string;
    chapterText: string;
    novelId: number;
}

export const chapterService = {

    async create(data:ChapterCreate) {

        const response = await apiAuth.post(
            '/chapters/',
            data
        );

        return response.data;

    },

    async getNovelIdByName(novelName:string) {
        const response = await api.get(
            `/novels/${novelName}`
        );

        return response.data.id;
    }
}