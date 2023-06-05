USE healthygym;

CREATE TABLE IF NOT EXISTS member
(
   memberseq INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
   email     VARCHAR(100) NOT NULL,
   pwd       VARCHAR(500)  NOT NULL,
   auth      TINYINT      NOT NULL,
   authority VARCHAR(10) NOT NULL,
   profile   VARCHAR(100)  NOT NULL,
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

INSERT INTO member (email, pwd, auth, authority, profile, nickname, provider)
VALUES ('123@naver.com', '123', 1, 'ROLE_USER','upload/default.png', '헬린123', 'own');

INSERT INTO memberinfo (memberseq, name, age, gender, phone, mbti)
VALUES (1, '홍길동', 30, 'male', '010-1234-5678', 'ENFP');

INSERT INTO bbs (memberseq, bbstag, content, title)
VALUES (1, 3, 'content 게시글1', 'test bbstag3 게시글1');