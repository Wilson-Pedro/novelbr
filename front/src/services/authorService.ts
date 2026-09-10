import { api, apiAuth } from "./api";

interface AuthorRegisterForm {
    name: string;
    username: string;
    email: string;
    password: string;
}

export const authorService = {

    async registerAuthor(registerForm: AuthorRegisterForm) {

        await api.post('/authors/', registerForm);

    },
    
    async fetchNovelCardByUsername(username: string) {

        const response = await api.get(`/novels/novelCards/author/${username}`);
        return response;

    },
    
    async fetchAuthorInfoByUsername(username: string) {

        const response = await apiAuth.get(`/authors/username/${username}`);
        return response;

    },
    
    async fetchAuthorInfoById(userId: number) {

        const response = await apiAuth.get(`/authors/${userId}`);
        return response;

    }
}