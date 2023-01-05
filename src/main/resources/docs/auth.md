# _������ ����������_

1. ����������� � ����������� ������������;

# _������������� ����������_

## _1.1. ����������� ������������_

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
���������� ��� ������ "NICKNAME_BUSY". �������� �� ������������ email, ���� ����� email ���������� � ��, �� ����������
��� ������
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

## 1.2 �������������� ������������

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