--liquibase formatted SQL

--changeset cj:1

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_create_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `UK_gjxbniq161jp4virlctq0c28l`(`f_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

DROP TABLE IF EXISTS `t_role_authority`;
CREATE TABLE `t_role_authority`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_role_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `f_user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`f_id`) USING BTREE,
  INDEX `FKp65hrbhifhu5waor32ni5yyv5`(`f_role_id`) USING BTREE,
  INDEX `FKh48lhqt14jg28hlyije1r4wda`(`f_user_id`) USING BTREE,
  CONSTRAINT `FKh48lhqt14jg28hlyije1r4wda` FOREIGN KEY (`f_user_id`) REFERENCES `t_user` (`f_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKp65hrbhifhu5waor32ni5yyv5` FOREIGN KEY (`f_role_id`) REFERENCES `t_role` (`f_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `f_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_cell_phone_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `f_display_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `f_login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `f_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `UK_8kb3t5b6himm96nxbhw7r38jj`(`f_login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
