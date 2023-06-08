USE healthygym;

CREATE TABLE IF NOT EXISTS member
(
   memberseq INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
   email     VARCHAR(100) NOT NULL,
   pwd       VARCHAR(500)  NOT NULL,
   auth      TINYINT      NOT NULL,
   authority VARCHAR(10) NOT NULL,
   profileimg   VARCHAR(100)  NOT NULL,
   nickname  VARCHAR(50)  NOT NULL,
   provider  VARCHAR(10) NOT NULL,
   mailkey   VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS memberinfo
(
   memberseq INT NOT NULL,
   name      VARCHAR(50),
   age       INT,
   gender    VARCHAR(10),
   phone     VARCHAR(20),
   mbti      VARCHAR(4),
   FOREIGN KEY (memberseq) REFERENCES member (memberseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS follow
(
    followseq INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    memberseq INT NOT NULL,
    foltarget INT NOT NULL,
    FOREIGN KEY (memberseq) REFERENCES member (memberseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS inbody
(
    inbodyseq   INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    memberseq   INT NOT NULL,
    uploaddate  DATETIME DEFAULT CURRENT_TIMESTAMP,
    weight      DECIMAL(4, 1),
    musclemass  DECIMAL(3, 1),
    bodyfatmass DECIMAL(3, 1),
    imgpath     VARCHAR(100),
    issaved     TINYINT DEFAULT 0,
    FOREIGN KEY (memberseq) REFERENCES member (memberseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS refreshtoken
(
	email VARCHAR(100) NOT NULL,
    rvalue VARCHAR(500) NOT NULL
);

CREATE TABLE IF NOT EXISTS bbs
(
   bbsseq    INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
   memberseq INT           NOT NULL,
   bbstag    TINYINT       NOT NULL,
   content   VARCHAR(4000) NOT NULL,
   title     VARCHAR(200)  NOT NULL,
   wdate     DATETIME DEFAULT CURRENT_TIMESTAMP,
   rdate     DATETIME DEFAULT null,
   likecount INT      DEFAULT 0,
   readcount INT      DEFAULT 0,
   report    INT      DEFAULT 0,
   del       TINYINT  DEFAULT 0,
   thumbnail  VARCHAR(100),
   FOREIGN KEY (memberseq) REFERENCES member (memberseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS like_bbs
(
    memberseq INT NOT NULL,
    bbsseq    INT NOT NULL,
    FOREIGN KEY (memberseq) REFERENCES member (memberseq) ON DELETE CASCADE,
    FOREIGN KEY (bbsseq) REFERENCES bbs (bbsseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS bbs_comment
(
    commentseq INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    memberseq  INT          NOT NULL,
    bbsseq     INT          NOT NULL,
    cmtcontent VARCHAR(500) NOT NULL,
    regdate    DATETIME DEFAULT CURRENT_TIMESTAMP,
    cmtdel     TINYINT      NOT NULL,
    step       TINYINT,
    ref        TINYINT,
    FOREIGN KEY (memberseq) REFERENCES member (memberseq) ON DELETE CASCADE,
    FOREIGN KEY (bbsseq) REFERENCES bbs (bbsseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mate
(
    bbsseq   INT          NOT NULL,
    addressfirst  VARCHAR(10) NOT NULL,
    addresssecond VARCHAR(10) NOT NULL,
    center   VARCHAR(10),
    mdate    VARCHAR(10)  NOT NULL,
    mtime    VARCHAR(5)   NOT NULL,
    back     TINYINT,
    chest    TINYINT,
    shoulder TINYINT,
    arm      TINYINT,
    abs      TINYINT,
    leg      TINYINT,
    run      TINYINT,
    FOREIGN KEY (bbsseq) REFERENCES bbs (bbsseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS message
(
    msgseq    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    memberseq INT NOT NULL,
    target    INT NOT NULL,
    message   VARCHAR(500) NOT NULL,
    isread    TINYINT NOT NULL DEFAULT 0,
    wdate     DATETIME DEFAULT NOW(),
    FOREIGN KEY (memberseq) REFERENCES member (memberseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS meal_ingredients
(
    foodseq     INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    bbsseq      INT         NOT NULL,
    DESCKOR     VARCHAR(50) NOT NULL,
    SERVINGWT   INT,
    NUTRCONT1   VARCHAR(50),
    NUTRCONT2   VARCHAR(50),
    NUTRCONT3   VARCHAR(50),
    NUTRCONT4   VARCHAR(50),
    NUTRCONT5   VARCHAR(50),
    NUTRCONT6   VARCHAR(50),
    NUTRCONT7   VARCHAR(50),
    NUTRCONT8   VARCHAR(50),
    NUTRCONT9   VARCHAR(50),
    BGNYEAR     VARCHAR(50),
    ANIMALPLANT VARCHAR(50),
    FOREIGN KEY (bbsseq) REFERENCES bbs (bbsseq) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mealrecommend
(
    foodseq     INT         NOT NULL AUTO_INCREMENT PRIMARY KEY,
    msgseq      INT         NOT NULL,
    whenmeal    TINYINT,
    DESCKOR     VARCHAR(50) NOT NULL,
    SERVINGWT   INT,
    NUTRCONT1   VARCHAR(50),
    NUTRCONT2   VARCHAR(50),
    NUTRCONT3   VARCHAR(50),
    NUTRCONT4   VARCHAR(50),
    NUTRCONT5   VARCHAR(50),
    NUTRCONT6   VARCHAR(50),
    NUTRCONT7   VARCHAR(50),
    NUTRCONT8   VARCHAR(50),
    NUTRCONT9   VARCHAR(50),
    BGNYEAR     VARCHAR(50),
    ANIMALPLANT VARCHAR(50),
    FOREIGN KEY (msgseq) REFERENCES message (msgseq) ON DELETE CASCADE
);

INSERT INTO member (email, pwd, auth, authority, profile, nickname, provider)
VALUES ('123@naver.com', '123', 1, 'ROLE_USER','upload/default.png', '헬린123', 'own');

INSERT INTO memberinfo (memberseq, name, age, gender, phone, mbti)
VALUES (1, '홍길동', 30, 'male', '010-1234-5678', 'ENFP');

INSERT INTO bbs (memberseq, bbstag, content, title)
VALUES (1, 3, 'content 게시글1', 'test bbstag3 게시글1');