
-- data.sql

-- Article 테이블 생성
CREATE TABLE IF NOT EXISTS article (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       title VARCHAR(255) NOT NULL,
                                       content TEXT NOT NULL,
                                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Comment 테이블 생성
CREATE TABLE IF NOT EXISTS comment (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       article_id BIGINT NOT NULL,
                                       body TEXT NOT NULL,
                                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                                       FOREIGN KEY (article_id) REFERENCES article(id)
);

INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목1', '내용1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목2', '내용2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목3', '내용3', NOW(), NOW());

INSERT INTO comment (article_id, body, created_at) VALUES (1, '내용1', NOW());
INSERT INTO comment (article_id, body, created_at) VALUES (2, '내용2', NOW());
INSERT INTO comment (article_id, body, created_at) VALUES (1, '내용3', NOW());