CREATE TABLE User
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname    VARCHAR(15)  NOT NULL UNIQUE,
    password    VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE ,
    time_insert TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
) COLLATE utf8_bin;

CREATE TABLE Phrase
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    text        VARCHAR(150) NOT NULL,
    user_id     BIGINT REFERENCES User (id),
    time_insert TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
) COLLATE utf8_bin;

CREATE TABLE Tag
(
    id  BIGINT AUTO_INCREMENT PRIMARY KEY,
    tag VARCHAR(150) NOT NULL UNIQUE
) COLLATE utf8_bin;

CREATE TABLE Phrase_tag
(
    phrase_id BIGINT REFERENCES Phrase (id),
    tag_id    BIGINT REFERENCES Tag (id),
    PRIMARY KEY (phrase_id, tag_id)
) COLLATE utf8_bin;