import { api, apiAuth } from "./api";

export const genreService = {

    async searchGendersByNovelId(novelId: number) {
        const response = await api.get(`/genres/novel/${novelId}`);
        return response;
    },

    async searchGenres() {
        const response = await api.get(`/genres`);
        return response;
    }

}