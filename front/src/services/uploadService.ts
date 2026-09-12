import { apiAuth } from "./api";

export const uploadService = {
    
    async uploadImage(formData: FormData) {
        await apiAuth.post(`/upload/image`, formData);
    }

}