--liquibase formatted SQL

--changeset cj:1

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_api_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_api_permission`;
CREATE TABLE `t_api_permission`  (
  `f_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_api_ref` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_role_ref` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE INDEX idx_qyr_role_permission ON t_api_permission ( f_role_ref, f_api_ref, z_version);
-- ----------------------------
-- Table structure for t_api_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_api_resource`;
CREATE TABLE `t_api_resource`  (
  `f_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_enabled` bit(1) NOT NULL,
  `f_api_method` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_api_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE INDEX idx_qyr_name ON t_api_resource ( f_scope, f_name, z_version);
CREATE INDEX idx_qyr_api ON t_api_resource ( f_api_method, f_api_path, z_version);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `f_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_enabled` bit(1) NOT NULL,
  `f_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `UK_r4wjftdokfh60mn7j21sr5wtm`(`f_description`) USING BTREE,
  UNIQUE INDEX `UK_gjxbniq161jp4virlctq0c28l`(`f_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE INDEX idx_fast_fetch_1 ON t_role ( f_name,f_type,f_enabled,f_description,f_create_by,z_version);

-- ----------------------------
-- Table structure for t_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `f_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_role_ref` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_user_ref` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE KEY `UK_user_role`(`f_user_ref`,`f_role_ref`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE INDEX idx_qyr_role_permission ON t_role_permission ( f_user_ref,z_version);

-- ----------------------------
-- Table structure for t_ui_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_ui_menu_permission`;
CREATE TABLE `t_ui_menu_permission`  (
  `f_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_menu_ref` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_role_ref` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE INDEX idx_qyr_ui_menu_permission ON t_ui_menu_permission ( f_role_ref, f_menu_ref, z_version);

-- ----------------------------
-- Table structure for t_ui_menu_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_ui_menu_resource`;
CREATE TABLE `t_ui_menu_resource`  (
  `f_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_create_by` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_enabled` bit(1) NOT NULL,
  `f_ui_page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_ui_node` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_ui_parent_node` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `f_ui_text` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `f_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_mobile` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_display_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `f_login_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `f_status` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `z_created_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `z_modified_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `z_version` bigint(20) NULL DEFAULT 0,
  PRIMARY KEY (`f_id`) USING BTREE,
  UNIQUE INDEX `UK_8kb3t5b6himm96nxbhw7r38jj`(`f_login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

CREATE INDEX idx_qyr_user_1 ON t_user ( f_login_name,f_status);
CREATE INDEX idx_qyr_user_2 ON t_user ( f_email,f_status);
CREATE INDEX idx_qyr_user_3 ON t_user ( f_mobile,f_status);

SET FOREIGN_KEY_CHECKS = 1;
