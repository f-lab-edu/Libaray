-- library.book definition

CREATE TABLE `book` (
                        `id` bigint NOT NULL AUTO_INCREMENT,
                        `category` varchar(255) DEFAULT NULL,
                        `content` varchar(255) DEFAULT NULL,
                        `deleted_by` datetime(6) DEFAULT NULL,
                        `isbn` varchar(255) DEFAULT NULL,
                        `title` varchar(255) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- library.lib_user definition

CREATE TABLE `lib_user` (
                            `id` varchar(255) NOT NULL,
                            `active` bit(1) NOT NULL,
                            `name` varchar(255) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- library.rental definition

CREATE TABLE `rental` (
                          `id` bigint NOT NULL AUTO_INCREMENT,
                          `end_date` datetime(6) NOT NULL,
                          `renew` bit(1) NOT NULL,
                          `return_date` datetime(6) DEFAULT NULL,
                          `start_date` datetime(6) NOT NULL,
                          `book_id` bigint DEFAULT NULL,
                          `user_id` varchar(255) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          KEY `FK4gi44e6r300a8l6fnw0ss0be6` (`book_id`),
                          KEY `FK87eu3xjpls7e74ylmawypihy7` (`user_id`),
                          CONSTRAINT `FK4gi44e6r300a8l6fnw0ss0be6` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`),
                          CONSTRAINT `FK87eu3xjpls7e74ylmawypihy7` FOREIGN KEY (`user_id`) REFERENCES `lib_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;