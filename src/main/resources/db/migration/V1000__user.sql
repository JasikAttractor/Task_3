use `queen_db`;
CREATE TABLE `users`
(
    `id`             bigint auto_increment not null,
    `name`           varchar(128) not null,
    `email` varchar(128)  not null,
    `address` varchar(128) not null,
    PRIMARY KEY (`id`)
);