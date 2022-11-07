CREATE TABLE `arch_component`
(
    `arch_system_id` VARCHAR(36),
    `id`             VARCHAR(36) NOT NULL PRIMARY KEY,
    `name`           VARCHAR(50),
    `type`           VARCHAR(50)
);
