export interface NovelCard {
    index?:number;
    authorId:number;
    novelId:number;
    imageUri:string;
    novelName:string;
    username:string;
}

export interface NovelInfo {
    imageUri: string;
    novelName: string;
    novelStatus: string;
    username: string;
    year: number;
    synopsis: string;
    authorId: number;
}

export interface GendersBackend {
    genderType:string;
}

export interface NovelsFinded {
    id:number;
    novelName:string;
}