CREATE TABLE `arch_component_connection`
(
    `arch_system_id` VARCHAR(36),
    `id`             VARCHAR(36) NOT NULL PRIMARY KEY,
    `source`         VARCHAR(36),
    `target`         VARCHAR(50)
);
