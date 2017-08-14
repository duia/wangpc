/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50529
Source Host           : localhost:3306
Source Database       : wangpc

Target Server Type    : MYSQL
Target Server Version : 50529
File Encoding         : 65001

Date: 2017-08-14 17:32:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `parent_id` bigint(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `sort` int(10) NOT NULL COMMENT '排序',
  `code` varchar(100) DEFAULT NULL COMMENT '区域编码',
  `type` char(1) NOT NULL COMMENT '机构类型',
  `grade` char(1) NOT NULL COMMENT '机构等级',
  `address` varchar(255) DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(100) DEFAULT NULL COMMENT '邮政编码',
  `master` varchar(100) DEFAULT NULL COMMENT '负责人',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `fax` varchar(200) DEFAULT NULL COMMENT '传真',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `useable` varchar(64) DEFAULT NULL COMMENT '是否启用',
  `primary_person` varchar(64) DEFAULT NULL COMMENT '主负责人',
  `deputy_person` varchar(64) DEFAULT NULL COMMENT '副负责人',
  `create_by` bigint(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='机构表';

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '0', '0,', '山东省总公司', '10', '100000', '1', '1', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('2', '1', '0,1,', '公司领导', '10', '100001', '2', '1', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('3', '1', '0,1,', '综合部', '20', '100002', '2', '1', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('4', '1', '0,1,', '市场部', '30', '100003', '2', '1', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('5', '1', '0,1,', '技术部', '40', '100004', '2', '1', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('6', '1', '0,1,', '研发部', '50', '100005', '2', '1', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('7', '1', '0,1,', '济南市分公司', '20', '200000', '1', '2', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('8', '7', '0,1,7,', '公司领导', '10', '200001', '2', '2', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('9', '7', '0,1,7,', '综合部', '20', '200002', '2', '2', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('10', '7', '0,1,7,', '市场部', '30', '200003', '2', '2', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('11', '7', '0,1,7,', '技术部', '40', '200004', '2', '2', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('12', '7', '0,1,7,', '历城区分公司', '0', '201000', '1', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('13', '12', '0,1,7,12,', '公司领导', '10', '201001', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('14', '12', '0,1,7,12,', '综合部', '20', '201002', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('15', '12', '0,1,7,12,', '市场部', '30', '201003', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('16', '12', '0,1,7,12,', '技术部', '40', '201004', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('17', '7', '0,1,7,', '历下区分公司', '40', '201010', '1', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('18', '17', '0,1,7,17,', '公司领导', '10', '201011', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('19', '17', '0,1,7,17,', '综合部', '20', '201012', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('20', '17', '0,1,7,17,', '市场部', '30', '201013', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('21', '17', '0,1,7,17,', '技术部', '40', '201014', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('22', '7', '0,1,7,', '高新区分公司', '50', '201010', '1', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('23', '22', '0,1,7,22,', '公司领导', '10', '201011', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('24', '22', '0,1,7,22,', '综合部', '20', '201012', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('25', '22', '0,1,7,22,', '市场部', '30', '201013', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_department` VALUES ('26', '22', '0,1,7,22,', '技术部', '40', '201014', '2', '3', null, null, null, null, null, null, '1', null, null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

-- ----------------------------
-- Table structure for `sys_dict`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `value` varchar(100) NOT NULL COMMENT '数据值',
  `label` varchar(100) NOT NULL COMMENT '标签名',
  `type` varchar(100) NOT NULL COMMENT '类型',
  `description` varchar(100) NOT NULL COMMENT '描述',
  `sort` int(10) NOT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` bigint(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '0', '正常', 'del_flag', '删除标记', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('2', '1', '删除', 'del_flag', '删除标记', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('3', '1', '显示', 'show_hide', '显示/隐藏', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('4', '0', '隐藏', 'show_hide', '显示/隐藏', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('5', '1', '是', 'yes_no', '是/否', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('6', '0', '否', 'yes_no', '是/否', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('7', 'red', '红色', 'color', '颜色值', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('8', 'green', '绿色', 'color', '颜色值', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('9', 'blue', '蓝色', 'color', '颜色值', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('10', 'yellow', '黄色', 'color', '颜色值', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('11', 'orange', '橙色', 'color', '颜色值', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('12', 'default', '默认主题', 'theme', '主题方案', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('13', 'cerulean', '天蓝主题', 'theme', '主题方案', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('14', 'readable', '橙色主题', 'theme', '主题方案', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('15', 'united', '红色主题', 'theme', '主题方案', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'flat', 'Flat主题', 'theme', '主题方案', '60', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('17', '1', '国家', 'sys_area_type', '区域类型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('18', '2', '省份、直辖市', 'sys_area_type', '区域类型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('19', '3', '地市', 'sys_area_type', '区域类型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('20', '4', '区县', 'sys_area_type', '区域类型', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('21', '1', '公司', 'sys_office_type', '机构类型', '60', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('22', '2', '部门', 'sys_office_type', '机构类型', '70', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('23', '3', '小组', 'sys_office_type', '机构类型', '80', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('24', '4', '其它', 'sys_office_type', '机构类型', '90', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('25', '1', '综合部', 'sys_office_common', '快捷通用部门', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('26', '2', '开发部', 'sys_office_common', '快捷通用部门', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('27', '3', '人力部', 'sys_office_common', '快捷通用部门', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('28', '1', '一级', 'sys_office_grade', '机构等级', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('29', '2', '二级', 'sys_office_grade', '机构等级', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('30', '3', '三级', 'sys_office_grade', '机构等级', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('31', '4', '四级', 'sys_office_grade', '机构等级', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('32', '1', '所有数据', 'sys_data_scope', '数据范围', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('33', '2', '所在公司及以下数据', 'sys_data_scope', '数据范围', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('34', '3', '所在公司数据', 'sys_data_scope', '数据范围', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('35', '4', '所在部门及以下数据', 'sys_data_scope', '数据范围', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('36', '5', '所在部门数据', 'sys_data_scope', '数据范围', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('37', '8', '仅本人数据', 'sys_data_scope', '数据范围', '90', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('38', '9', '按明细设置', 'sys_data_scope', '数据范围', '100', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('39', '1', '系统管理', 'sys_user_type', '用户类型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('40', '2', '部门经理', 'sys_user_type', '用户类型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('41', '3', '普通用户', 'sys_user_type', '用户类型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('42', 'basic', '基础主题', 'cms_theme', '站点主题', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('43', 'blue', '蓝色主题', 'cms_theme', '站点主题', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('44', 'red', '红色主题', 'cms_theme', '站点主题', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('45', 'article', '文章模型', 'cms_module', '栏目模型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('46', 'picture', '图片模型', 'cms_module', '栏目模型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('47', 'download', '下载模型', 'cms_module', '栏目模型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('48', 'link', '链接模型', 'cms_module', '栏目模型', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('49', 'special', '专题模型', 'cms_module', '栏目模型', '50', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('50', '0', '默认展现方式', 'cms_show_modes', '展现方式', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('51', '1', '首栏目内容列表', 'cms_show_modes', '展现方式', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('52', '2', '栏目第一条内容', 'cms_show_modes', '展现方式', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('53', '0', '发布', 'cms_del_flag', '内容状态', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('54', '1', '删除', 'cms_del_flag', '内容状态', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('55', '2', '审核', 'cms_del_flag', '内容状态', '15', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('56', '1', '首页焦点图', 'cms_posid', '推荐位', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('57', '2', '栏目页文章推荐', 'cms_posid', '推荐位', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('58', '1', '咨询', 'cms_guestbook', '留言板分类', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('59', '2', '建议', 'cms_guestbook', '留言板分类', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('60', '3', '投诉', 'cms_guestbook', '留言板分类', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('61', '4', '其它', 'cms_guestbook', '留言板分类', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('62', '1', '公休', 'oa_leave_type', '请假类型', '10', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('63', '2', '病假', 'oa_leave_type', '请假类型', '20', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('64', '3', '事假', 'oa_leave_type', '请假类型', '30', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('65', '4', '调休', 'oa_leave_type', '请假类型', '40', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('66', '5', '婚假', 'oa_leave_type', '请假类型', '60', '0', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('67', '1', '接入日志', 'sys_log_type', '日志类型', '30', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('68', '2', '异常日志', 'sys_log_type', '日志类型', '40', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('69', 'leave', '请假流程', 'act_type', '流程类型', '10', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('70', 'test_audit', '审批测试流程', 'act_type', '流程类型', '20', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('71', '1', '分类1', 'act_category', '流程分类', '10', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('72', '2', '分类2', 'act_category', '流程分类', '20', '0', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('73', 'crud', '增删改查', 'gen_category', '代码生成分类', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('74', 'crud_many', '增删改查（包含从表）', 'gen_category', '代码生成分类', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('75', 'tree', '树结构', 'gen_category', '代码生成分类', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'between', 'Between', 'gen_query_type', '查询方式', '50', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'like', 'Like', 'gen_query_type', '查询方式', '60', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'left_like', 'Left Like', 'gen_query_type', '查询方式', '70', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'right_like', 'Right Like', 'gen_query_type', '查询方式', '80', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('84', 'input', '文本框', 'gen_show_type', '字段生成方案', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('85', 'textarea', '文本域', 'gen_show_type', '字段生成方案', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('86', 'select', '下拉框', 'gen_show_type', '字段生成方案', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('87', 'checkbox', '复选框', 'gen_show_type', '字段生成方案', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('88', 'radiobox', '单选框', 'gen_show_type', '字段生成方案', '50', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('89', 'dateselect', '日期选择', 'gen_show_type', '字段生成方案', '60', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('90', 'userselect', '人员选择', 'gen_show_type', '字段生成方案', '70', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('91', 'officeselect', '部门选择', 'gen_show_type', '字段生成方案', '80', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('92', 'areaselect', '区域选择', 'gen_show_type', '字段生成方案', '90', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('95', 'dao', '仅持久层', 'gen_category', '代码生成分类\0\0', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('96', '1', '男', 'sex', '性别', '10', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('97', '2', '女', 'sex', '性别', '20', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'java.util.Date', 'Date', 'gen_java_type', 'Java类型', '50', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('101', 'com.thinkgem.jeesite.modules.sys.entity.User', 'User', 'gen_java_type', 'Java类型', '60', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('102', 'com.thinkgem.jeesite.modules.sys.entity.Office', 'Office', 'gen_java_type', 'Java类型', '70', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('103', 'com.thinkgem.jeesite.modules.sys.entity.Area', 'Area', 'gen_java_type', 'Java类型', '80', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', '2013-10-28 08:00:00', '1', '2013-10-28 08:00:00', null, '1');
INSERT INTO `sys_dict` VALUES ('105', '1', '会议通告', 'oa_notify_type', '通知通告类型', '10', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('106', '2', '奖惩通告', 'oa_notify_type', '通知通告类型', '20', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('107', '3', '活动通告', 'oa_notify_type', '通知通告类型', '30', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('108', '0', '草稿', 'oa_notify_status', '通知通告状态', '10', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('109', '1', '发布', 'oa_notify_status', '通知通告状态', '20', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('110', '0', '未读', 'oa_notify_read', '通知通告状态', '10', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_dict` VALUES ('111', '1', '已读', 'oa_notify_read', '通知通告状态', '20', '0', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');

-- ----------------------------
-- Table structure for `sys_element`
-- ----------------------------
DROP TABLE IF EXISTS `sys_element`;
CREATE TABLE `sys_element` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) DEFAULT NULL COMMENT '所属菜单的id',
  `menu_ids` varchar(255) DEFAULT NULL COMMENT '所属菜单id的层级关系',
  `element_name` varchar(255) DEFAULT NULL,
  `element_code` varchar(255) DEFAULT NULL,
  `element_desc` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of sys_element
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_element_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_element_permission`;
CREATE TABLE `sys_element_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `element_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of sys_element_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_file`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) DEFAULT NULL,
  `file_code` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_file_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_file_permission`;
CREATE TABLE `sys_file_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `file_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of sys_file_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `parent_id` bigint(64) NOT NULL COMMENT '父级编号',
  `parent_ids` varchar(2000) NOT NULL COMMENT '所有父级编号',
  `menu_name` varchar(100) NOT NULL COMMENT '名称',
  `menu_code` varchar(200) DEFAULT NULL COMMENT '权限标识',
  `href` varchar(2000) DEFAULT NULL COMMENT '链接',
  `target` varchar(20) DEFAULT NULL COMMENT '目标',
  `icon` varchar(100) DEFAULT NULL COMMENT '图标',
  `is_active` char(1) NOT NULL COMMENT '是否在菜单中显示',
  `sort` int(10) NOT NULL COMMENT '排序',
  `create_by` bigint(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='菜单表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('2', '1', '0,1,', '系统设置', null, null, null, null, '1', '900', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('3', '2', '0,1,2,', '系统设置', null, null, null, null, '1', '980', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('4', '3', '0,1,2,3,', '菜单管理', null, '/sys/menu', null, 'fa fa-list-alt', '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('7', '3', '0,1,2,3,', '角色管理', null, '/sys/role/', null, 'fa fa-lock', '1', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('10', '3', '0,1,2,3,', '字典管理', null, '/sys/dict/', null, 'fa fa-th-list', '1', '60', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('13', '2', '0,1,2,', '机构用户', null, null, null, null, '1', '970', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('14', '13', '0,1,2,13,', '区域管理', null, '/sys/area/', null, 'fa fa-th', '1', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('17', '13', '0,1,2,13,', '机构管理', null, '/sys/office/', null, 'fa fa-th-large', '1', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('20', '13', '0,1,2,13,', '用户管理', null, '/sys/user', null, 'fa fa-user', '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('23', '2', '0,1,2,', '关于帮助', null, null, null, null, '0', '990', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('24', '23', '0,1,2,23', '官方首页', null, 'http://jeesite.com', '_blank', null, '0', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('25', '23', '0,1,2,23', '项目支持', null, 'http://jeesite.com/donation.html', '_blank', null, '0', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('26', '23', '0,1,2,23', '论坛交流', null, 'http://bbs.jeesite.com', '_blank', null, '0', '80', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('27', '1', '0,1,', '我的面板', null, null, null, null, '1', '100', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('28', '27', '0,1,27,', '个人信息', null, null, null, null, '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('29', '28', '0,1,27,28,', '个人信息', null, '/sys/user/info', null, 'fa fa-user', '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('30', '28', '0,1,27,28,', '修改密码', null, '/sys/user/modifyPwd', null, 'fa fa-lock', '1', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('31', '1', '0,1,', '内容管理', null, null, null, null, '1', '500', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('32', '31', '0,1,31,', '栏目设置', null, null, null, null, '1', '990', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('33', '32', '0,1,31,32', '栏目管理', null, '/cms/category/', null, 'fa fa-align-justify', '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('36', '32', '0,1,31,32', '站点设置', null, '/cms/site/', null, 'fa fa-certificate', '1', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('39', '32', '0,1,31,32', '切换站点', 'cms:site:select', '/cms/site/select', null, 'fa fa-retweet', '1', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('40', '31', '0,1,31,', '内容管理', 'cms:view', null, null, null, '1', '500', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('41', '40', '0,1,31,40,', '内容发布', null, '/cms/', null, 'fa fa-briefcase', '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('42', '41', '0,1,31,40,41,', '文章模型', null, '/cms/article/', null, 'fa fa-file', '0', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('46', '41', '0,1,31,40,41,', '链接模型', null, '/cms/link/', null, 'fa fa-random', '0', '60', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('50', '40', '0,1,31,40,', '评论管理', null, '/cms/comment/?status=2', null, 'fa fa-comment', '1', '40', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('53', '40', '0,1,31,40,', '公共留言', null, '/cms/guestbook/?status=2', null, 'fa fa-glass', '1', '80', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('56', '71', '0,1,27,71,', '文件管理', null, '/../static/ckfinder/ckfinder.html', null, 'fa fa-folder-open', '1', '90', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('60', '31', '0,1,31,', '统计分析', null, null, null, null, '1', '600', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('61', '60', '0,1,31,60,', '信息量统计', 'cms:stats:article', '/cms/stats/article', null, 'fa fa-tasks', '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('62', '1', '0,1,', '在线办公', null, null, null, null, '1', '200', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('63', '62', '0,1,62,', '个人办公', null, null, null, null, '1', '30', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('64', '63', '0,1,62,63,', '请假办理', null, '/oa/leave', null, 'fa fa-leaf', '0', '300', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('67', '2', '0,1,2,', '日志查询', null, null, null, null, '1', '985', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('68', '67', '0,1,2,67,', '日志查询', 'sys:log:view', '/sys/log', null, 'fa fa-pencil', '1', '30', '1', '2013-06-03 08:00:00', '1', '2013-06-03 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('69', '62', '0,1,62,', '流程管理', null, null, null, null, '1', '300', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('70', '69', '0,1,62,69,', '流程管理', 'act:process:edit', '/act/process', null, 'fa fa-road', '1', '50', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('71', '27', '0,1,27,', '文件管理', null, null, null, null, '1', '90', '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('72', '69', '0,1,62,69,', '模型管理', 'act:model:edit', '/act/model', null, 'fa fa-road', '1', '100', '1', '2013-09-20 08:00:00', '1', '2013-09-20 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('73', '63', '0,1,62,63,', '我的任务', null, '/act/task/todo/', null, 'fa fa-tasks', '1', '50', '1', '2013-09-24 08:00:00', '1', '2013-09-24 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('74', '63', '0,1,62,63,', '审批测试', 'oa:testAudit:view,oa:testAudit:edit', '/oa/testAudit', null, null, '1', '100', '1', '2013-09-24 08:00:00', '1', '2013-09-24 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('75', '1', '0,1,', '在线演示', null, null, null, null, '1', '3000', '1', '2013-10-08 08:00:00', '1', '2013-10-08 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('79', '1', '0,1,', '代码生成', null, null, null, null, '1', '5000', '1', '2013-10-16 08:00:00', '1', '2013-10-16 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('80', '79', '0,1,79,', '代码生成', null, null, null, null, '1', '50', '1', '2013-10-16 08:00:00', '1', '2013-10-16 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('81', '80', '0,1,79,80,', '生成方案配置', 'gen:genScheme:view,gen:genScheme:edit', '/gen/genScheme', null, null, '1', '30', '1', '2013-10-16 08:00:00', '1', '2013-10-16 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('82', '80', '0,1,79,80,', '业务表配置', 'gen:genTable:view,gen:genTable:edit,gen:genTableColumn:view,gen:genTableColumn:edit', '/gen/genTable', null, null, '1', '20', '1', '2013-10-16 08:00:00', '1', '2013-10-16 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('83', '80', '0,1,79,80,', '代码模板管理', 'gen:genTemplate:view,gen:genTemplate:edit', '/gen/genTemplate', null, null, '1', '90', '1', '2013-10-16 08:00:00', '1', '2013-10-16 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('84', '67', '0,1,2,67,', '连接池监视', null, '/../druid', null, null, '1', '40', '1', '2013-10-18 08:00:00', '1', '2013-10-18 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('86', '75', '0,1,75,', '组件演示', null, null, null, null, '1', '50', '1', '2013-10-22 08:00:00', '1', '2013-10-22 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('87', '86', '0,1,75,86,', '组件演示', 'test:test:view,test:test:edit', '/test/test/form', null, null, '1', '30', '1', '2013-10-22 08:00:00', '1', '2013-10-22 08:00:00', null, '1');
INSERT INTO `sys_menu` VALUES ('88', '62', '0,1,62,', '通知通告', '', '', '', '', '1', '20', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('89', '88', '0,1,62,88,', '我的通告', '', '/oa/oaNotify/self', '', '', '1', '30', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');
INSERT INTO `sys_menu` VALUES ('90', '88', '0,1,62,88,', '通告管理', 'oa:oaNotify:view,oa:oaNotify:edit', '/oa/oaNotify', '', '', '1', '50', '1', '2013-11-08 08:00:00', '1', '2013-11-08 08:00:00', null, '0');

-- ----------------------------
-- Table structure for `sys_menu_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_permission`;
CREATE TABLE `sys_menu_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `menu_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of sys_menu_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(255) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  `permission_name` varchar(255) DEFAULT NULL,
  `permission_code` varchar(255) DEFAULT NULL COMMENT '权限码',
  `permission_type` varchar(255) DEFAULT NULL COMMENT '权限类型',
  `sort` int(11) DEFAULT NULL,
  `create_by` bigint(20) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` bigint(20) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `remarks` varchar(255) DEFAULT NULL,
  `del_flag` char(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of sys_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(64) NOT NULL COMMENT '编号',
  `role_name` varchar(100) NOT NULL COMMENT '角色名称',
  `role_code` varchar(255) DEFAULT NULL COMMENT '权限码',
  `role_type` varchar(255) DEFAULT NULL COMMENT '角色类型',
  `data_scope` char(1) DEFAULT NULL COMMENT '数据范围',
  `is_sys` varchar(64) DEFAULT NULL COMMENT '是否系统数据',
  `useable` varchar(64) DEFAULT NULL COMMENT '是否可用',
  `sort` int(11) DEFAULT NULL,
  `create_by` bigint(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', 'dept', 'assignment', '1', null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_role` VALUES ('2', '公司管理员', 'hr', 'assignment', '2', null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_role` VALUES ('3', '本公司管理员', 'a', 'assignment', '3', null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_role` VALUES ('4', '部门管理员', 'b', 'assignment', '4', null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_role` VALUES ('5', '本部门管理员', 'c', 'assignment', '5', null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_role` VALUES ('6', '普通用户', 'd', 'assignment', '8', null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');
INSERT INTO `sys_role` VALUES ('7', '济南市管理员', 'e', 'assignment', '9', null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0');

-- ----------------------------
-- Table structure for `sys_role_department`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_department`;
CREATE TABLE `sys_role_department` (
  `role_id` bigint(64) NOT NULL COMMENT '角色编号',
  `department_id` bigint(64) NOT NULL COMMENT '机构编号',
  PRIMARY KEY (`role_id`,`department_id`)
) COMMENT='角色-机构';

-- ----------------------------
-- Records of sys_role_department
-- ----------------------------
INSERT INTO `sys_role_department` VALUES ('7', '7');
INSERT INTO `sys_role_department` VALUES ('7', '8');
INSERT INTO `sys_role_department` VALUES ('7', '9');
INSERT INTO `sys_role_department` VALUES ('7', '10');
INSERT INTO `sys_role_department` VALUES ('7', '11');
INSERT INTO `sys_role_department` VALUES ('7', '12');
INSERT INTO `sys_role_department` VALUES ('7', '13');
INSERT INTO `sys_role_department` VALUES ('7', '14');
INSERT INTO `sys_role_department` VALUES ('7', '15');
INSERT INTO `sys_role_department` VALUES ('7', '16');
INSERT INTO `sys_role_department` VALUES ('7', '17');
INSERT INTO `sys_role_department` VALUES ('7', '18');
INSERT INTO `sys_role_department` VALUES ('7', '19');
INSERT INTO `sys_role_department` VALUES ('7', '20');
INSERT INTO `sys_role_department` VALUES ('7', '21');
INSERT INTO `sys_role_department` VALUES ('7', '22');
INSERT INTO `sys_role_department` VALUES ('7', '23');
INSERT INTO `sys_role_department` VALUES ('7', '24');
INSERT INTO `sys_role_department` VALUES ('7', '25');
INSERT INTO `sys_role_department` VALUES ('7', '26');

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `login_name` varchar(100) NOT NULL COMMENT '登录名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `no` varchar(100) DEFAULT NULL COMMENT '工号',
  `username` varchar(100) NOT NULL COMMENT '姓名',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(200) DEFAULT NULL COMMENT '电话',
  `mobile` varchar(200) DEFAULT NULL COMMENT '手机',
  `user_type` char(1) DEFAULT NULL COMMENT '用户类型',
  `photo` varchar(1000) DEFAULT NULL COMMENT '用户头像',
  `login_ip` varchar(100) DEFAULT NULL COMMENT '最后登陆IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登陆时间',
  `login_flag` varchar(64) DEFAULT NULL COMMENT '是否可登录',
  `sort` int(11) DEFAULT NULL,
  `create_by` bigint(64) NOT NULL COMMENT '创建者',
  `create_date` datetime NOT NULL COMMENT '创建时间',
  `update_by` bigint(64) NOT NULL COMMENT '更新者',
  `update_date` datetime NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `company_id` bigint(64) NOT NULL COMMENT '归属公司',
  `department_id` bigint(64) NOT NULL COMMENT '归属部门',
  PRIMARY KEY (`id`)
) COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$shiro1$SHA-512$1$$x61Ey612Kl2gpFL56FT9weDnpSo4AV8j8+qx2AuTHdRyY036xxzTTrw10Wq3+4qQyB+XURPWx1ONxp3Y3pB37A==', '0001', '系统管理员', 'thinkgem@163.com', '8675', '8675', null, null, '127.0.0.1', '2017-08-05 14:30:29', '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', '最高管理员', '0', '1', '2');
INSERT INTO `sys_user` VALUES ('2', 'sd_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0002', '管理员', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '1', '2');
INSERT INTO `sys_user` VALUES ('3', 'sd_zhb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0003', '综合部', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '1', '3');
INSERT INTO `sys_user` VALUES ('4', 'sd_scb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0004', '市场部', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '1', '4');
INSERT INTO `sys_user` VALUES ('5', 'sd_jsb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0005', '技术部', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '1', '5');
INSERT INTO `sys_user` VALUES ('6', 'sd_yfb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0006', '研发部', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '1', '6');
INSERT INTO `sys_user` VALUES ('7', 'jn_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0007', '济南领导', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '7', '8');
INSERT INTO `sys_user` VALUES ('8', 'jn_zhb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0008', '济南综合部', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '7', '9');
INSERT INTO `sys_user` VALUES ('9', 'jn_scb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0009', '济南市场部', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '7', '10');
INSERT INTO `sys_user` VALUES ('10', 'jn_jsb', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0010', '济南技术部', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '7', '11');
INSERT INTO `sys_user` VALUES ('11', 'lc_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0011', '济南历城领导', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '12', '13');
INSERT INTO `sys_user` VALUES ('12', 'lx_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0012', '济南历下领导', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '12', '18');
INSERT INTO `sys_user` VALUES ('13', 'gx_admin', '02a3f0772fcca9f415adc990734b45c6f059c7d33ee28362c4852032', '0013', '济南高新领导', null, null, null, null, null, null, null, '1', null, '1', '2013-05-27 08:00:00', '1', '2013-05-27 08:00:00', null, '0', '22', '23');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(64) NOT NULL COMMENT '用户编号',
  `role_id` bigint(64) NOT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`)
) COMMENT='用户-角色';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '1', '2');
INSERT INTO `sys_user_role` VALUES ('3', '2', '1');
INSERT INTO `sys_user_role` VALUES ('4', '3', '2');
INSERT INTO `sys_user_role` VALUES ('5', '4', '3');
INSERT INTO `sys_user_role` VALUES ('6', '5', '4');
INSERT INTO `sys_user_role` VALUES ('7', '6', '5');
INSERT INTO `sys_user_role` VALUES ('8', '7', '2');
INSERT INTO `sys_user_role` VALUES ('9', '7', '7');
INSERT INTO `sys_user_role` VALUES ('10', '8', '2');
INSERT INTO `sys_user_role` VALUES ('11', '9', '1');
INSERT INTO `sys_user_role` VALUES ('12', '10', '2');
INSERT INTO `sys_user_role` VALUES ('13', '11', '3');
INSERT INTO `sys_user_role` VALUES ('14', '12', '4');
INSERT INTO `sys_user_role` VALUES ('15', '13', '5');
INSERT INTO `sys_user_role` VALUES ('16', '14', '6');
