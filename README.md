# _Бизнес требования_
1. [x] Пользователь должен иметь возможность зарегистрироваться;
2. [x] Публиковать фразы и теги;
3. [ ] Получать свои фразы;
4. [ ] Подписываться на других пользователей
5. [ ] Читать фразы других пользователей
6. [ ] Оценивать фразы других пользователей
7. [ ] Соверщать поиск по тегам 
8. [ ] Соверщать поиск по словам 
9. [ ] Соверщать поиск по нику пользователя

# _Модель данных_
### User 
_id, nickname, password, email, time_insert_;
### Phrase
_id, text, user_id, time_insert_;
### Tag
_id, tag_;
### Phrase_tag
_phrase_id, tag_id_;

# _Функиональные требования_
## _1.1. Метод registration_

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
возвращает код ошибки "NICKNAME_BUSY". Проверка на уникальность email, если такой email существует в БД, то возвращает код ошибки 
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
## 1.2 Метод login
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
## _2.1. Метод public_phrase_

**Входящие данные** <br>
GWT в cookie (twiwwer) аутентифицированного пользлвателя (**Все последующие пунккты 2.** )
```json
{
  "text": "first phrase",
  "tags": [
    "first",
    "phrase"
  ]
}
```

**Валидация** <br>
text >= 1 символа, <= 150 символов <br>
tags от 0 до 5, значение от >= 3 символов, <= 25, разрешенные символы a-zA-Z0-9а-яА-Я<br>

**Логика метода** <br>
сервис проверяет наличие GWT в cookie twiwwer аутентифирированого пользователя,
если не находит - возвращает код ошибки "AUTHORIZATION_ERROR",
иначе валидируется запрос, если не валидный возвращает код ошибки "REQUEST_VALIDATION_ERROR",
записывает фразу в таблицу phrase,
по каждому тегу проверяет есть ли в таблице tag такой тег, если нет - записывает,
получает id всех тегов, записывает id фразы и тегов в таблицу phrase_tag

**Исходящие данные в случае успеха** <br>
статус 200
```json
{}
```

**Исходящие данные в случае ошибки** <br>
статус 401
```json
{
  "error": {
    "code": "AUTHORIZATION_ERROR",
    "message": "Nick name is already token"
  }
}
```
статус 400
```json
{
  "error": {
    "code": "REQUEST_VALIDATION_ERROR",
    "message": "Request json is not valid, errors of validation ... "
  }
}
```