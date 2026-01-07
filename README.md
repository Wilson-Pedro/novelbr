<h1 align="center" style="font-weight: bold;">Novel BR 📖</h1>


<p align="center">
    <b>Our project offers an interactive platform where writers can create accounts and publish their own stories, similar to the novel format. 
      Readers can access the site, explore various genres, and follow their favorite stories. 
      It is a collaborative space that encourages creativity and the exchange of experiences between writers and readers, providing a rich and engaging experience for all 
      literature enthusiasts.</b>
</p>

![NOVELBR_SCREEN](https://github.com/Wilson-Pedro/images-for-readme/blob/main/novelbr/1_HOME_SCREEN.PNG)

<h2 id="technologies">💻 Technologies</h2>

- Java 17+
- H2 Database
- REACT

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Git](https://git-scm.com/downloads)
- [Nodejs](https://nodejs.org/pt)

<h3>Cloning</h3>

```bash
git clone git@github.com:Wilson-Pedro/novelbr.git
```

<h2 id="routes">📍 API Endpoints</h2>

<h3 id="routes">User:</h3>

​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /users</kbd>     | retrieves information for all users
| <kbd>GET /users/pages</kbd>     | pagination
| <kbd>GET /users/1</kbd>     | retrieves information of one user 
| <kbd>POST /users/</kbd>     | create user
| <kbd>PUT /users/1</kbd>     | update user 
| <kbd>DELETE /users/1</kbd>     | delete user 

<h3 id="get-auth-detail">GET /users</h3>

**RESPONSE**
```json
{
    "id": 1,
    "pseudonym": "little fox",
    "email": "littlefox@gmail.com",
    "password": "1234"
}
```

<h3 id="get-auth-detail">GET /users/1</h3>

**RESPONSE**
```json
{
    "id": 1,
    "pseudonym": "little fox",
    "email": "littlefox@gmail.com",
    "password": "1234"
}
```

<h3 id="post-auth-detail">POST /users/</h3>

**REQUEST**
```json
{
    "pseudonym": "little salamander",
    "email": "little_salamander@gmail.com",
    "password": "123"
}
```

**RESPONSE**
```json
{
    "id": 2,
    "pseudonym": "little salamander",
    "email": "little_salamander@gmail.com",
    "password": "123"
}
```

<h3 id="post-auth-detail">PUT /users/2</h3>

**REQUEST**
```json
{
    "pseudonym": "little cat",
    "email": "little_salamander@gmail.com",
    "password": "123"
}
```

**RESPONSE**
```json
{
    "id": 2
    "pseudonym": "little cat",
    "email": "little_salamander@gmail.com",
    "password": "123"
}
```
<h3 id="routes">Novel:</h3>

​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /novels</kbd>     | retrieves information for all novels
| <kbd>GET /novels/pages</kbd>     | pagination
| <kbd>GET /novels/1</kbd>     | retrieves information of one novel 
| <kbd>POST /novels/</kbd>     | create novels
| <kbd>PUT /novels/1</kbd>     | update novels 
| <kbd>DELETE /novels/1</kbd>     | delete novel 

<h3 id="get-auth-detail">GET /novels</h3>

**RESPONSE**
```json
{
    "id": 1,
    "title": "Agaings the Gods",
    "synopsis": "The Gods...",
    "authorId": 1
}
```

<h3 id="get-auth-detail">GET /novels/1</h3>

**RESPONSE**
```json
{
    "id": 1,
    "title": "Agaings the Gods",
    "synopsis": "The Gods...",
    "authorId": 1
}
```

<h3 id="post-auth-detail">POST /novels/</h3>

**REQUEST**
```json
{
    "title": "Immortal Renagede",
    "synopsis": "In Those times...",
    "authorId": 1
}
```

**RESPONSE**
```json
{
    "id": 2
    "title": "Immortal Renagede",
    "synopsis": "In Those times...",
    "authorId": 1
}
```

<h3 id="post-auth-detail">PUT /novels/2</h3>

**REQUEST**
```json
{
    "title": "I will seal the heavens",
    "synopsis": "In Those times...",
    "authorId": 1
}
```

**RESPONSE**
```json
{
    "id": 2
    "title": "I will seal the heavens",
    "synopsis": "In Those times...",
    "authorId": 1
}
```
<h3 id="routes">Chapter:</h3>

​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /chapters</kbd>     | retrieves information for all chapters
| <kbd>GET /chapters/pages</kbd>     | pagination
| <kbd>GET /chapters/1</kbd>     | retrieves information of one chapter 
| <kbd>POST /chapters/</kbd>     | create chapters
| <kbd>PUT /chapters/1</kbd>     | update chapters 
| <kbd>DELETE /chapters/1</kbd>     | delete chapter 

<h3 id="get-auth-detail">GET /chapters</h3>

**RESPONSE**
```json
{
    "id": 1,
    "chapterTilte": "Begins",
    "chapterNumber": 1,
    "text": "In Those Days, the Gods...",
    "novelId": 1
}
```

<h3 id="get-auth-detail">GET /chapters/1</h3>

**RESPONSE**
```json
{
    "id": 1,
    "chapterTilte": "Begins",
    "chapterNumber": 1,
    "text": "In Those Days, the Gods...",
    "novelId": 1
}
```

<h3 id="post-auth-detail">POST /chapters/</h3>

**REQUEST**
```json
{
    "chapterTilte": "Cultivation",
    "chapterNumber": 4,
    "text": "In Those Days, the Gods...",
    "novelId": 1
}
```

**RESPONSE**
```json
{
    "id": 4,
    "chapterTilte": "Cultivation",
    "chapterNumber": 4,
    "text": "In Those Days, the Gods...",
    "novelId": 1
}
```

<h3 id="post-auth-detail">PUT /chapters/4</h3>

**REQUEST**
```json
{
{
    "chapterTilte": "Begins the journey",
    "chapterNumber": 6,
    "text": "In Those Days, the Gods...",
    "novelId": 1
}
```

**RESPONSE**
```json
{
    "id": 4
    "chapterTilte": "Begins the journey",
    "chapterNumber": 6,
    "text": "In Those Days, the Gods...",
    "novelId": 1
}
```

<h2 id="contribute">📫 Status of Project</h2>
In Deveopment...
