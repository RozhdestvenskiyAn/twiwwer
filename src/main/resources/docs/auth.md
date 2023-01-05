# _Бизнес требования_

1. Регистрация и авторизация пользователя;

# _Функиональные требования_

## _1.1. Регистрация пользователя_

**Входящие данные** <br>

```json
{
  "nickname": "ivanov2000",
  "password": "ivan2000pass",
  "email": "ivan2000@pass.com"
}
```

**Валидация** <br>
nickname >= 4 символа, <= 15 символов, A-Za-z0-9._- <br>
password >= 8 символа, <= 100 символов, A-Za-z0-9.,:;_-?!+=/'\"*[]{}() <br>
email >= 4 символа, <= 100 символов, email pattern<br>

**Логика метода** <br>
Валидация входных данных, в случае нарушений, возвращает код ошибки "REQUEST_VALIDATION_ERROR".
Поиск пользователя в талице User по столбцу nickname, если такой nickname существует в таблице User -
возвращает код ошибки "NICKNAME_BUSY". Проверка на уникальность email, если такой email существует в БД, то возвращает
код ошибки
"EMAIL_BUSY", иначе шифрование password, сохрание пользователя в таблицу User и возвращается
ответ в виде сообщения "Registration (nickname) was successful".

**Исходящие данные в случае успеха** <br>
статус 200

```json
{
  "data": {
    "message": "Registration ivanov2000 was successful"
  }
}
```

**Исходящие данные в случае ошибки** <br>
статус 400

```json
{
  "error": {
    "code": "REQUEST_VALIDATION_ERROR",
    "message": "Request json is not valid, errors of validation ... "
  }
}
```

```json
{
  "error": {
    "code": "NICKNAME_BUSY",
    "message": "Nick name is already token"
  }
}
```

```json
{
  "error": {
    "code": "EMAIL_BUSY",
    "message": "Email is already token"
  }
}
```

## 1.2 Аутентификация пользователя

**входящие данные**

```json
{
  "nickname": "ivanov2000",
  "password": "ivan2000pass"
}
```

**валидация**<br/>
nickname >= 4 символа, <= 15 символов, A-Za-z0-9._- <br>
password >= 8 символа, <= 100 символов, A-Za-z0-9.,:;_-?!+=/'\"*[]{}() <br>

**логика метода**<br/>
Валидация входных данных, в случае нарушений, возвращает код ошибки "REQUEST_VALIDATION_ERROR".
Сервис шифрует password таким же образом что и в _1.1 Метод registration_ и совершает
поиск в таблице user по столбцам nickname и password, если пользователь не найден - возвращает код ошибки
"BAD_CREDENTIALS", иначе генерируется JWT на основе nickname, возвращается
ответ в виде сообщения "User: (nickname) authentication was successful" и записывается
cookie : twiwwer = зашифрованный jwt.

**исходящие данные в случае успеха** <br/>статус 200

```json
{
  "data": {
    "message": "User: (nickname) authentication was successful"
  }
}
```

**исходящие данные в случае ошибки**<br/>статус 400

```json
{
  "error": {
    "code": "REQUEST_VALIDATION_ERROR",
    "text": "Request json is not valid, errors of validation ..."
  }
}
```

```json
{
  "error": {
    "code": "BAD_CREDENTIALS",
    "text": "Bad credentials"
  }
}
```