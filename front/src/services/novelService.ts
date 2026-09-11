import { api, apiAuth } from "./api";

interface NovelStatusForm {
    novelId: number;
    novelStatusId: number;
}

interface NovelForm {
    novelName: string;
    authorId: number;
    genders: string[];
    synopsis: string;
    imageUri: string;
    
}

export const novelService = {

    async registerNovel(novelForm: NovelForm) {
        await apiAuth.post(`/novels/`, novelForm);
    },

    async fetchNovelsPages(page:number, size:number) {
        const response = await api.get(`/novels/pages?page=${page}&size=${size}`);
        return response;
    },
    
    async searchNovel(novelName:string, page:number, size:number) {

        const response = await api.get(`/novels/search/${novelName}?page=${page}&size=${size}`);
        return response;
    },

    async fetchNovelCards() {
        const response = await api.get(`/novels/novelCards`);
        return response;
    },

    async changeNovelStatus(novelStatusForm: NovelStatusForm) {
        await apiAuth.patch(`/novels/changeNovelStatus`, novelStatusForm);
    },

    async chageNovelImageUri(formData: FormData) {
        await apiAuth.patch(`/novels/changeNovelImageUri`, formData)
    },

    async fetchNovelByNovelName(novelName: string) {
        const response = await apiAuth.get(`/novels/${novelName}`);
        return response
    },

    async fetchNovelInfoByNovelId(novelId: number) {
        const response = await api.get(`/novels/novelCards/${novelId}`);
        return response
    },

    async fetchNovelsByGenders(genders: string[], page: number, size: number) {
        const response = await api.get(`/novels/genders?genders=${genders.join(",")}&page=${page}&size=${size}`);
        return response
    }

}