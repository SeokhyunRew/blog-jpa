
/*create table article (
                         id BIGINT AUTO_INCREMENT primary key,
                         title varchar(255) not null,
                         content varchar(255) not null
);*/

INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목1', '내용1', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목2', '내용2', NOW(), NOW());
INSERT INTO article (title, content, created_at, updated_at) VALUES ('제목3', '내용3', NOW(), NOW());