# interviewTask

#### to ron application with default database you have to do:
>add environment variable DB_USER and DB_PASSWORD

#### next let's execute this commands in project directory
```shell script
mvn build
mvn spring-boot:run
```

#### Info
this application default use in memory H2 database,
to change this please edit application.properties file.

#### How to use
> api gives endpoints:
>>  **GET  /api/posts** *to get all posts* \
>>  **PUT  /api/posts** *to edit post (body parameter is required)* \
>>  **DELETE  /api/posts/{id}** *to delete post (id in url is required)* \
>>  **POST  /api/posts/reload** *to refresh posts from remote api*  
###### data will be refresh automatically every day