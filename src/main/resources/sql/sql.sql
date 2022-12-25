CREATE TABLE User
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname    VARCHAR(15)  NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE ,
    time_insert TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
) COLLATE utf8_bin;