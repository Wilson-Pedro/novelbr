const getComments = async () => {
    return [
        {
            id: "1",
            body: "First Commet",
            username: "Jac",
            userId: "1",
            parentId: null,
            createdAt: "17/07/2025 20:05",
        },
        {
            id: "2",
            body: "Second Commet",
            username: "John",
            userId: "2",
            parentId: null,
            createdAt: "17/07/2025 20:05",
        },
        {
            id: "3",
            body: "Third Commet",
            username: "John",
            userId: "2",
            parentId: "1",
            createdAt: "=17/07/2025 20:10",
        },
    ]
}

export default getComments;