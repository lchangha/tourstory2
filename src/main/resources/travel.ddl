DROP TABLE IF EXISTS diary_images;
CREATE TABLE diary_images
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    diary_id INT          NOT NULL,
    url      VARCHAR(255) NULL
);

CREATE INDEX idx_diary_id ON diary_images (diary_id);

DROP TABLE IF EXISTS diary_like;
CREATE TABLE diary_like
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    VARCHAR(100) NOT NULL,
    diary_id   INT          NOT NULL,
    UNIQUE (user_id, diary_id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS diary_comment_like;
CREATE TABLE diary_comment_like
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    user_id          VARCHAR(100) NOT NULL,
    diary_comment_id INT          NOT NULL,
    UNIQUE (user_id, diary_comment_id),
    created_at       DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS diary_reply_like;
CREATE TABLE diary_reply_like
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    user_id        VARCHAR(100) NOT NULL,
    diary_reply_id INT          NOT NULL,
    UNIQUE (user_id, diary_reply_id),
    created_at     DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS diary_reply;
CREATE TABLE diary_reply
(
    id               INT PRIMARY KEY AUTO_INCREMENT,
    diary_comment_id INT          NOT NULL,
    user_id          VARCHAR(100) NOT NULL,
    content          TEXT         NULL,
    created_at       DATETIME   DEFAULT CURRENT_TIMESTAMP,
    updated_at       DATETIME     NULL,
    deleted_at       DATETIME     NULL,
    is_deleted       TINYINT(1) DEFAULT 0
);

CREATE INDEX idx_diary_reply_id ON diary_reply (diary_comment_id);
CREATE INDEX idx_diary_reply_deleted ON diary_reply (deleted_at);

DROP TABLE IF EXISTS diary_comment;
CREATE TABLE diary_comment
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    diary_id   INT          NOT NULL,
    user_id    VARCHAR(100) NOT NULL,
    content    TEXT         NULL,
    created_at DATETIME   DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NULL,
    deleted_at DATETIME     NULL,
    is_deleted TINYINT(1) DEFAULT 0
);

CREATE INDEX idx_diary_comment_id ON diary_comment (diary_id);
CREATE INDEX idx_diary_comment_deleted ON diary_comment (deleted_at);

DROP TABLE IF EXISTS diaries;
CREATE TABLE diaries
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    user_id       VARCHAR(100) NOT NULL,
    trips_id      INT          NOT NULL,
    title         VARCHAR(100) NULL,
    content       TEXT         NULL,
    thumbnail_url VARCHAR(255) NULL,
    created_at    DATETIME   DEFAULT CURRENT_TIMESTAMP,
    updated_at    DATETIME     NULL,
    deleted_at    DATETIME     NULL,
    is_deleted    TINYINT(1) DEFAULT 0
);

CREATE INDEX idx_diary_user ON diaries (user_id);
CREATE INDEX idx_diary_deleted ON diaries (deleted_at);

DROP TABLE IF EXISTS trips_city;
CREATE TABLE trips_city
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    trips_id INT NOT NULL,
    city_id  INT NOT NULL
);

DROP TABLE IF EXISTS trips_concept;
CREATE TABLE trips_concept
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    trips_id   INT NOT NULL,
    concept_id INT NOT NULL
);

DROP TABLE IF EXISTS chat_participants;
CREATE TABLE chat_participants
(
    id                   INT PRIMARY KEY AUTO_INCREMENT,
    trips_id             INT          NOT NULL,
    user_id              VARCHAR(100) NOT NULL,
    last_read_message_id INT          NULL,
    created_at           DATETIME DEFAULT CURRENT_TIMESTAMP,
    UNIQUE (trips_id, user_id)
);

DROP TABLE IF EXISTS invitations;
CREATE TABLE invitations
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    trips_id        INT          NOT NULL,
    user_id         VARCHAR(100) NOT NULL,
    invitation_date DATETIME     DEFAULT CURRENT_TIMESTAMP,
    response_date   DATETIME     NULL,
    status          VARCHAR(255) DEFAULT 'invited'
);

DROP TABLE IF EXISTS plan_location;
CREATE TABLE plan_location
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    trips_id      INT             NOT NULL,
    place_name    VARCHAR(255)    NULL,
    address_name  VARCHAR(255)    NULL,
    category_name VARCHAR(255)    NULL,
    y             DECIMAL(30, 20) NULL,
    x             DECIMAL(30, 20) NULL,
    order_number  INT             NULL,
    created_at    DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS trips;
CREATE TABLE trips
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    user_id    VARCHAR(100) NOT NULL,
    start_date DATE         NULL,
    end_date   DATE         NULL,
    trip_name  VARCHAR(255) NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE IF EXISTS trip_message;
CREATE TABLE trip_message
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    trips_id   INT          NOT NULL,
    user_id    VARCHAR(100) NOT NULL,
    message    TEXT         NULL,
    created_at DATETIME   DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME     NULL,
    deleted_at DATETIME     NULL,
    is_deleted TINYINT(1) DEFAULT 0
);

DROP TABLE IF EXISTS city;
CREATE TABLE city
(
    id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name        VARCHAR(255)                   NOT NULL,
    description VARCHAR(255)                   NOT NULL,
    image_path  VARCHAR(255)                   NOT NULL
);

DROP TABLE IF EXISTS concept;
CREATE TABLE concept
(
    id          INT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    title       VARCHAR(255)                   NOT NULL,
    description VARCHAR(255)                   NOT NULL
);

DROP TABLE IF EXISTS notification;
CREATE TABLE notification
(
    id              INT PRIMARY KEY AUTO_INCREMENT       NOT NULL,
    receiver_id     VARCHAR(100)                         NOT NULL,
    trigger_user_id VARCHAR(100)                         NOT NULL,
    target_id       INT                                  NOT NULL,
    `type`          VARCHAR(50)                          NOT NULL,
    content         TEXT                                 NOT NULL,
    url             VARCHAR(255)                         NULL,
    un_read         TINYINT(1) DEFAULT 0                 NOT NULL,
    created_at      DATETIME   DEFAULT CURRENT_TIMESTAMP NOT NULL
);

CREATE INDEX idx_notification_receiver ON notification (receiver_id);

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    email       VARCHAR(100) PRIMARY KEY               NOT NULL,
    name        VARCHAR(50)                            NOT NULL,
    nickname    VARCHAR(50)                            NOT NULL,
    password    VARCHAR(255)                           NOT NULL,
    birth       DATE                                   NULL,
    gender      VARCHAR(10)                            NULL,
    profile_url VARCHAR(255) DEFAULT '/profile.jpg'    NOT NULL,
    phone       VARCHAR(20)                            NULL,
    created_at  DATETIME     DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  DATETIME                               NULL,
    deleted_at  DATETIME                               NULL,
    is_deleted  TINYINT(1)   DEFAULT 0                 NOT NULL,
    UNIQUE (nickname)
);
