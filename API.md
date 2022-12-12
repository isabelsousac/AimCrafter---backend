# Users

## sign up

Request:

```
POST /signup
{
    "firstName": <String>,
    "lastName": <String>,
    "username": <String>,
    "email": <String>,
    "password": <String>
}
```

Response:

```
201 CREATED
{
    "user": {
        firstName": <String>,
        "lastName": <String>,
        "username": <String>
    },
    "token": <String> 
}
```

## sign in

Request:

```
POST /signin
{
    "email": <String>,
    "password": <String>
}
```

Response:

```
200 OK
{
    "user": {
        firstName": <String>,
        "lastName": <String>,
        "username": <String>
    },
    "token": <String> 
}
```

## show user

Request:

```
GET /user/<id>
```

Response:

```
200 OK
{
    "username": <String>,
    "image": <String>,
    "crafts": [
        {
            "title": <String>,
            "image": <String>,
            "createdAt": <TimeStamp>
        }
    ] 
}
```

# Crafts

## new craft

Request:

```
POST /craft
{
    "title": <String>,
    "userId": <Int>,
    "tools": <String[]>,
    "description": <String?>,
    "timeToCreate": <Time?>,
    "difficultyLevel": <Int?>
}
```

Response:

```
201 CREATED
{
    "title": <String>,
    "tools": <String[]>,
    "description": <String?>,
    "timeToCreate": <Time?>,
    "difficultyLevel": <Int?>
}
```