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
        const response = await api.get(`/novels/${novelName}`);
        return response.data.id;
    },

    async fetchLastChapters() {
        const response = await api.get('/chapters/lastChapters');
        return response.data;
    },

    async fetchChapter(novelName:string, chapterNumber:number ) {
        const response = await api.get(`/chapters/${novelName}/${chapterNumber}`);
        return response.data;
    },

    async fetchMaxChapterNumber(novelId:number) {
        const response = await api.get(`/chapters/chapterNumber/novel/${novelId}`);
        return response.data;
    },

    async fetchChapterTitles(novelId:number, page:number) {
        const response = await api.get(`/chapters/pages/novelsTitle/${novelId}?page=${page}&size=10`);
        return response;
    }
}