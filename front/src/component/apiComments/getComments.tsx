export const getComments = async () => {
    return [
        {
            id: "1",
            body: "First Commet",
            username: "Jac",
            userId: "1",
            parentId: null,
            createdAt: "2025-07-18T8:20:00.010+03",
        },
        {
            id: "2",
            body: "Second Commet",
            username: "John",
            userId: "2",
            parentId: null,
            createdAt: "2025-07-18T8:20:05.010+03",
        },
        {
            id: "3",
            body: "Third Commet",
            username: "John",
            userId: "2",
            parentId: "1",
            createdAt: "2025-07-18T8:20:06.010+03",
        },
    ]
}

export const createComment = async (text:string, parentId:string | null = null) => {
    return {
        id: Math.random().toString(36).substring(2, 9),
        body: text,
        parentId,
        userId: "1",
        username: "John",
        createdAt: new Date().toISOString(),
    };
};

export const updateComment = async (text:string) => {
  return { text };
};

export const deleteComment = async (commentId:string) => {
  return {};
};