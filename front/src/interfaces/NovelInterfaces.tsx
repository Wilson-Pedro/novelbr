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
    genre:string;
    genreType:string;
}

export interface NovelsFinded {
    id:number;
    novelName:string;
}

export interface NovelConfigProp {
    chageNovelImage: any;
    changeNovelStatusSubmit: any;
    handleFileChange: any;
    setNovelStatusId:any;
    novelName:any;
    handleCloseConfig:any;
    handleCloseImage:any;
    handleShowImage:any;
    handleCloseNovelStatus:any;
    handleShowNovelStatus:any;
    showModalConfig:any;
    showModalNovelStatus:any;
    showModalImage:any;
}