export interface ChapterInfo {
    title:string;
    novelId:number;
    novelName:string;
    chapterText:string;
}

export interface ChapterTiles {
    title: string;
    chapterNumber:number;
}

export interface Page<T> {
    content: T[];
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
}

export interface LastChapters {
    index?:number;
    novelName:string;
    chapterNumber:number;
    title:string;
    dateRegistration:string
}