/*
 Navicat MySQL Data Transfer

 Source Server         : 阿里数据库✪私人数据库
 Source Server Type    : MySQL
 Source Server Version : 100137
 Source Host           : 47.104.87.159:3306
 Source Schema         : study

 Target Server Type    : MySQL
 Target Server Version : 100137
 File Encoding         : 65001

 Date: 03/01/2019 17:26:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_operational_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_operational_menu`;
CREATE TABLE `base_operational_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父级id',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单编码 唯一标识',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单的类型，标志是菜单还是按钮',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  `icon` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for base_operational_operation
-- ----------------------------
DROP TABLE IF EXISTS `base_operational_operation`;
CREATE TABLE `base_operational_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作名',
  `code` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作码 唯一标识',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构操作表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_operational_operation
-- ----------------------------
INSERT INTO `base_operational_operation` VALUES (1, '测试操作', 'operation', 1, '2019-01-03 16:40:57', NULL);

-- ----------------------------
-- Table structure for base_operational_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_operational_permission`;
CREATE TABLE `base_operational_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限编码 唯一标识',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构权限表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_operational_permission
-- ----------------------------
INSERT INTO `base_operational_permission` VALUES (1, '测试权限', 'permission', 1, '2019-01-03 16:40:36', NULL);

-- ----------------------------
-- Table structure for base_operational_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `base_operational_permission_menu`;
CREATE TABLE `base_operational_permission_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operational_permission_id` int(11) NOT NULL,
  `operational_menu_id` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for base_operational_permission_operation
-- ----------------------------
DROP TABLE IF EXISTS `base_operational_permission_operation`;
CREATE TABLE `base_operational_permission_operation`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operational_permission_id` int(11) NOT NULL,
  `operational_operation_id` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限操作关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_operational_permission_operation
-- ----------------------------
INSERT INTO `base_operational_permission_operation` VALUES (1, 1, 1, 1);

-- ----------------------------
-- Table structure for base_organization_info
-- ----------------------------
DROP TABLE IF EXISTS `base_organization_info`;
CREATE TABLE `base_organization_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '机构名',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '机构编码 唯一',
  `create_date` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '机构信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_organization_info
-- ----------------------------
INSERT INTO `base_organization_info` VALUES (1, '易迅通', 'YXT', '2019-01-03 16:38:57');

-- ----------------------------
-- Table structure for base_role
-- ----------------------------
DROP TABLE IF EXISTS `base_role`;
CREATE TABLE `base_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码 唯一键',
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `code_UNIQUE`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_role
-- ----------------------------
INSERT INTO `base_role` VALUES (1, '测试角色', 'role', 1, '2019-01-03 16:39:47', NULL);

-- ----------------------------
-- Table structure for base_role_operational_permission
-- ----------------------------
DROP TABLE IF EXISTS `base_role_operational_permission`;
CREATE TABLE `base_role_operational_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `operational_permission_id` int(11) NOT NULL,
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_role_operational_permission
-- ----------------------------
INSERT INTO `base_role_operational_permission` VALUES (1, 1, 1, 1);

-- ----------------------------
-- Table structure for base_user_info
-- ----------------------------
DROP TABLE IF EXISTS `base_user_info`;
CREATE TABLE `base_user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '电话号码',
  `id_card` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证',
  `birthday` date NULL DEFAULT NULL COMMENT '生日',
  `address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `id_card_UNIQUE`(`id_card`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_user_info
-- ----------------------------
INSERT INTO `base_user_info` VALUES (1, '刘岗强', '17628288315', '511623199203152696', '1992-03-15', '新怡花园A区', '123456', '2019-01-03 16:33:20');

-- ----------------------------
-- Table structure for base_user_organization_role
-- ----------------------------
DROP TABLE IF EXISTS `base_user_organization_role`;
CREATE TABLE `base_user_organization_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_info_id` int(11) NOT NULL,
  `organization_info_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户机构权限关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of base_user_organization_role
-- ----------------------------
INSERT INTO `base_user_organization_role` VALUES (1, 1, 1, 1, 1);

SET FOREIGN_KEY_CHECKS = 1;
