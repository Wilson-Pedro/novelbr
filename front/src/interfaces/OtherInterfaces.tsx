export interface NavbarProp {
    userAuthenticate?:boolean;
}

export interface CardProps {
    index?:number;
    imagePath?:string;
    novelName:string;
    author?:string;
    userAuthenticate?:boolean;
    authorId?:number;
    novelId?:number;
}

export interface PaginationProp {
    page:number;
    totalPages:number;
    setPage:any;
    pageSeacrh:number;
    setPageSeacrh:any;
    pageSearchValid:any;
}
