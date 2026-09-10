import { api } from "./api";

interface LoginForm {
    login: string,
    password: string
}

export const authService = {

    async login(loginForm: LoginForm) {

        const response = await api.post(`/auth/login`, loginForm, {
            withCredentials: true,
            headers: {
                'Content-Type': 'application/json'
            }
        })

        return response
        
    }
}