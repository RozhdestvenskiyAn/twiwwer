# _������ ����������_
1. [x] ������������ ������ ����� ����������� ������������������;
2. [x] ����������� ����� � ����;
3. [ ] �������� ���� �����;
4. [ ] ������������� �� ������ �������������
5. [ ] ������ ����� ������ �������������
6. [ ] ��������� ����� ������ �������������
7. [ ] ��������� ����� �� ����� 
8. [ ] ��������� ����� �� ������ 
9. [ ] ��������� ����� �� ���� ������������

# _������ ������_
### User 
_id, nickname, password, email, time_insert_;
### Phrase
_id, text, user_id, time_insert_;
### Tag
_id, tag_;
### Phrase_tag
_phrase_id, tag_id_;

# _������������� ����������_
## _1.1. ����� registration_

**�������� ������** <br>

```json
{
  "nickname": "ivanov2000",
  "password": "ivan2000pass",
  "email": "ivan2000@pass.com"
}
```

**���������** <br>
nickname >= 4 �������, <= 15 ��������, A-Za-z0-9._- <br>
password >= 8 �������, <= 100 ��������, A-Za-z0-9.,:;_-?!+=/'\"*[]{}() <br>
email >= 4 �������, <= 100 ��������, email pattern<br>

**������ ������** <br>
��������� ������� ������, � ������ ���������, ���������� ��� ������ "REQUEST_VALIDATION_ERROR". 
����� ������������ � ������ User �� ������� nickname, ���� ����� nickname ���������� � ������� User - 
���������� ��� ������ "NICKNAME_BUSY". �������� �� ������������ email, ���� ����� email ���������� � ��, �� ���������� ��� ������ 
"EMAIL_BUSY", ����� ���������� password, �������� ������������ � ������� User � ������������ 
����� � ���� ��������� "Registration (nickname) was successful".

**��������� ������ � ������ ������** <br>
������ 200
```json
{
  "data": {
    "message": "Registration ivanov2000 was successful"
  }
}
```

**��������� ������ � ������ ������** <br>
������ 400
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
## 1.2 ����� login
**�������� ������**
```json
{
  "nickname": "ivanov2000",
  "password": "ivan2000pass"
}
```
**���������**<br/>
nickname >= 4 �������, <= 15 ��������, A-Za-z0-9._- <br>
password >= 8 �������, <= 100 ��������, A-Za-z0-9.,:;_-?!+=/'\"*[]{}() <br>

**������ ������**<br/>
��������� ������� ������, � ������ ���������, ���������� ��� ������ "REQUEST_VALIDATION_ERROR".
������ ������� password ����� �� ������� ��� � � _1.1 ����� registration_ � ��������� 
����� � ������� user �� �������� nickname � password, ���� ������������ �� ������ - ���������� ��� ������
"BAD_CREDENTIALS", ����� ������������ JWT �� ������ nickname, ������������
����� � ���� ��������� "User: (nickname) authentication was successful" � ������������ 
cookie : twiwwer = ������������� jwt.

**��������� ������ � ������ ������** <br/>������ 200
```json
{
  "data": {
    "message": "User: (nickname) authentication was successful"
  }
}
```
**��������� ������ � ������ ������**<br/>������ 400
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
## _2.1. ����� public_phrase_

**�������� ������** <br>
GWT � cookie (twiwwer) �������������������� ������������ (**��� ����������� ������� 2.** )
```json
{
  "text": "first phrase",
  "tags": [
    "first",
    "phrase"
  ]
}
```

**���������** <br>
text >= 1 �������, <= 150 �������� <br>
tags �� 0 �� 5, �������� �� >= 3 ��������, <= 25, ����������� ������� a-zA-Z0-9�-��-�<br>

**������ ������** <br>
������ ��������� ������� GWT � cookie twiwwer ������������������� ������������,
���� �� ������� - ���������� ��� ������ "AUTHORIZATION_ERROR",
����� ������������ ������, ���� �� �������� ���������� ��� ������ "REQUEST_VALIDATION_ERROR",
���������� ����� � ������� phrase,
�� ������� ���� ��������� ���� �� � ������� tag ����� ���, ���� ��� - ����������,
�������� id ���� �����, ���������� id ����� � ����� � ������� phrase_tag

**��������� ������ � ������ ������** <br>
������ 200
```json
{}
```

**��������� ������ � ������ ������** <br>
������ 401
```json
{
  "error": {
    "code": "AUTHORIZATION_ERROR",
    "message": "Nick name is already token"
  }
}
```
������ 400
```json
{
  "error": {
    "code": "REQUEST_VALIDATION_ERROR",
    "message": "Request json is not valid, errors of validation ... "
  }
}
```