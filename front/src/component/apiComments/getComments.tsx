export const getComments = async () => {
    return [
        {
            id: 1,
            bodyText: "First Commet",
            username: "Jac",
            authorId: 1,
            comentByCode: 1,
            entityId: 1,
            parentId: null,
            createdAt: "2025-07-18T8:20:00.010+03",
        },
        {
            id: 2,
            bodyText: "Second Commet",
            username: "John",
            authorId: 2,
            comentByCode: 1,
            entityId: 1,
            parentId: null,
            createdAt: "2025-07-18T8:20:05.010+03",
        },
        {
            id: 3,
            bodyText: "Third Commet",
            username: "John",
            authorId: 2,
            comentByCode: 1,
            entityId: 1,
            parentId: 1,
            createdAt: "2025-07-18T8:20:06.010+03",
        },
    ]
}

export const createComment = async (text:string, parentId:string | null = null) => {
    return {
        id: Math.random().toString(36).substring(2, 9),
        bodyText: text,
        parentId,
        userId: "1",
        username: "John",
        createdAt: new Date().toISOString(),
    };
};

export const updateComment = async (text:string, commentId:any) => {
  return { text };
};

export const deleteComment = async (commentId:number) => {
  return {};
};