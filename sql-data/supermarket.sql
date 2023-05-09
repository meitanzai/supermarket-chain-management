/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1 -mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : supermarket

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/05/2023 23:26:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_show` tinyint(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 146 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (79, 'IKEA（宜家）', 1, '2023-05-06 21:41:28', '2023-05-06 21:43:39');
INSERT INTO `brand` VALUES (80, 'Ashley Furniture（阿什利家具）', 1, '2023-05-06 21:41:41', '2023-05-06 21:43:40');
INSERT INTO `brand` VALUES (81, 'La-Z-Boy（懒人沙发）', 1, '2023-05-06 21:41:49', '2023-05-06 21:43:40');
INSERT INTO `brand` VALUES (82, 'Herman Miller（赫曼·米勒）', 1, '2023-05-06 21:41:56', '2023-05-06 21:43:40');
INSERT INTO `brand` VALUES (83, 'Steelcase（斯蒂尔克斯）', 1, '2023-05-06 21:42:01', '2023-05-06 21:43:41');
INSERT INTO `brand` VALUES (84, 'MUJI（无印良品）', 1, '2023-05-06 21:42:07', '2023-05-06 21:43:41');
INSERT INTO `brand` VALUES (85, '苹果（Apple）', 1, '2023-05-06 21:42:13', '2023-05-06 22:07:28');
INSERT INTO `brand` VALUES (87, 'Bed Bath & Beyond（卧室和浴室之外）', 1, '2023-05-06 21:42:36', '2023-05-06 21:43:47');
INSERT INTO `brand` VALUES (88, 'Philips（飞利浦）', 1, '2023-05-06 21:42:43', '2023-05-06 21:43:48');
INSERT INTO `brand` VALUES (89, 'Flos（福洛斯）', 1, '2023-05-06 21:42:47', '2023-05-06 21:43:48');
INSERT INTO `brand` VALUES (90, 'Artemide（阿特米德）', 1, '2023-05-06 21:42:53', '2023-05-06 21:43:48');
INSERT INTO `brand` VALUES (91, 'Louis Poulsen（路易斯·普尔森）', 1, '2023-05-06 21:42:59', '2023-05-06 21:43:49');
INSERT INTO `brand` VALUES (92, 'ZWILLING J.A. Henckels（双立人）', 1, '2023-05-06 21:43:05', '2023-05-06 21:43:49');
INSERT INTO `brand` VALUES (93, 'WMF、Le Creuset（乐葡萄）', 1, '2023-05-06 21:43:10', '2023-05-06 21:43:52');
INSERT INTO `brand` VALUES (94, 'All-Clad（全合金）', 1, '2023-05-06 21:43:17', '2023-05-06 21:43:52');
INSERT INTO `brand` VALUES (95, 'Staub（斯陶布）', 1, '2023-05-06 21:43:23', '2023-05-06 21:43:53');
INSERT INTO `brand` VALUES (96, 'Zara Home', 1, '2023-05-06 21:43:30', '2023-05-06 21:43:53');
INSERT INTO `brand` VALUES (97, 'West Elm（西雅图）', 1, '2023-05-06 21:43:37', '2023-05-06 21:43:53');
INSERT INTO `brand` VALUES (98, '蒙牛（Mengniu）', 1, '2023-05-06 22:02:54', '2023-05-06 22:02:54');
INSERT INTO `brand` VALUES (99, '伊利（Yili）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (100, '安佳（Anchor）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (101, '爱他美（Aptamil）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (102, '卡乐比（Calbee）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (103, '雀巢（Nestle）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (104, '可口可乐（Coca-Cola）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (105, '伊利金典（Yili Gold）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (106, '美汁源（Mizone）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (107, '维他奶（Vitasoy）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (108, '养元六个核桃（Walnut milk）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (109, '三只松鼠（Three Squirrels）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (110, '雷克朗（Lay\'s）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (111, '维达（Vinda）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (112, '茶π（Chatime）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (113, '罗非鱼（Tilapia）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (114, '武汉香干（Wuhan dried beancurd）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (115, '辣条（Spicy stick）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (116, '金锣（Jinluo）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (117, '怡宝（Yibao）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (118, '康师傅（Kangshifu）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (119, '统一（Tongyi）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (120, '美味膳（Delicious meal）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (121, '光明（Bright dairy）', 1, '2023-05-06 22:03:27', '2023-05-06 22:03:27');
INSERT INTO `brand` VALUES (122, '卡宾（Cabbeen）', 1, '2023-05-06 22:04:27', '2023-05-06 22:04:27');
INSERT INTO `brand` VALUES (123, '七匹狼（SEPTWOLVES）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (124, '雷朋（Ray-Ban）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (125, '李宁（LI-NING）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (126, '安踏（ANTA）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (127, '耐克（Nike）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (128, '阿迪达斯（Adidas）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (129, '彪马（Puma）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (130, '新百伦（New Balance）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (131, '斯凯奇（Skechers）', 0, '2023-05-06 22:04:28', '2023-05-07 15:55:04');
INSERT INTO `brand` VALUES (132, '优衣库（UNIQLO）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (133, 'ZARA', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (134, 'H&M', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (135, 'ONLY', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (136, 'VERO MODA', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (137, 'JACK&JONES', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (138, 'GAP', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (139, 'LEVI\'S', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (140, 'CK', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (141, 'Bossini', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (142, '蓝色极限（GXG）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (143, '森马（Semir）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (144, '卡地亚（Cartier）（珠宝首饰）', 1, '2023-05-06 22:04:28', '2023-05-06 22:04:28');
INSERT INTO `brand` VALUES (145, '三全食品（Sanquan Food）', 1, '2023-05-07 15:54:56', '2023-05-07 15:54:56');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent_id` int(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 165 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (76, '食品', 0, '2023-05-06 21:02:38', '2023-05-06 21:02:38');
INSERT INTO `category` VALUES (77, '常温食品', 76, '2023-05-06 21:02:46', '2023-05-06 21:02:46');
INSERT INTO `category` VALUES (78, '糖果零食', 77, '2023-05-06 21:04:32', '2023-05-06 21:04:32');
INSERT INTO `category` VALUES (79, '巧克力', 78, '2023-05-06 21:04:37', '2023-05-06 21:04:37');
INSERT INTO `category` VALUES (80, '糖果', 78, '2023-05-06 21:04:43', '2023-05-06 21:04:43');
INSERT INTO `category` VALUES (81, '饼干', 78, '2023-05-06 21:04:48', '2023-05-06 21:04:48');
INSERT INTO `category` VALUES (82, '薯片', 78, '2023-05-06 21:04:55', '2023-05-06 21:04:55');
INSERT INTO `category` VALUES (83, '干货调料', 77, '2023-05-06 21:05:03', '2023-05-06 21:05:03');
INSERT INTO `category` VALUES (84, '米面杂粮', 83, '2023-05-06 21:05:10', '2023-05-06 21:05:10');
INSERT INTO `category` VALUES (85, '调味料', 83, '2023-05-06 21:05:22', '2023-05-06 21:05:22');
INSERT INTO `category` VALUES (86, '酱油醋', 83, '2023-05-06 21:05:29', '2023-05-06 21:05:29');
INSERT INTO `category` VALUES (87, '罐头食品', 83, '2023-05-06 21:05:36', '2023-05-06 21:05:36');
INSERT INTO `category` VALUES (88, '冷藏食品', 76, '2023-05-06 21:05:44', '2023-05-06 21:05:44');
INSERT INTO `category` VALUES (89, '奶制品', 88, '2023-05-06 21:05:51', '2023-05-06 21:05:51');
INSERT INTO `category` VALUES (90, '鲜奶', 89, '2023-05-06 21:05:57', '2023-05-06 21:05:57');
INSERT INTO `category` VALUES (91, '酸奶', 89, '2023-05-06 21:06:03', '2023-05-06 21:06:03');
INSERT INTO `category` VALUES (92, '奶酪', 89, '2023-05-06 21:06:08', '2023-05-06 21:06:08');
INSERT INTO `category` VALUES (93, '肉禽蛋类', 88, '2023-05-06 21:06:20', '2023-05-06 21:06:20');
INSERT INTO `category` VALUES (94, '猪肉', 93, '2023-05-06 21:06:27', '2023-05-06 21:06:27');
INSERT INTO `category` VALUES (95, '牛肉', 93, '2023-05-06 21:06:34', '2023-05-06 21:06:34');
INSERT INTO `category` VALUES (96, '鸡蛋', 93, '2023-05-06 21:06:39', '2023-05-06 21:06:39');
INSERT INTO `category` VALUES (97, '冷冻食品', 76, '2023-05-05 21:06:49', '2023-05-05 21:06:49');
INSERT INTO `category` VALUES (98, '肉类冷冻食品', 97, '2023-05-06 21:06:56', '2023-05-06 21:11:22');
INSERT INTO `category` VALUES (99, '猪肉冷冻食品', 98, '2023-05-06 21:13:22', '2023-05-06 21:13:22');
INSERT INTO `category` VALUES (100, '牛肉冷冻食品', 98, '2023-05-06 21:13:32', '2023-05-06 21:13:32');
INSERT INTO `category` VALUES (101, '羊肉冷冻食品', 98, '2023-05-06 21:17:48', '2023-05-06 21:17:48');
INSERT INTO `category` VALUES (102, '鸡肉冷冻食品', 98, '2023-05-06 21:19:19', '2023-05-06 21:19:19');
INSERT INTO `category` VALUES (103, '鸭肉冷冻食品', 98, '2023-05-06 21:19:25', '2023-05-06 21:19:25');
INSERT INTO `category` VALUES (104, '兔肉冷冻食品', 98, '2023-05-06 21:19:31', '2023-05-06 21:19:31');
INSERT INTO `category` VALUES (105, '鸽肉冷冻食品', 98, '2023-05-06 21:19:37', '2023-05-06 21:19:37');
INSERT INTO `category` VALUES (106, '海鲜冷冻食品', 97, '2023-05-06 21:19:45', '2023-05-06 21:19:45');
INSERT INTO `category` VALUES (107, '鱼类冷冻食品', 106, '2023-05-06 21:28:19', '2023-05-06 21:28:19');
INSERT INTO `category` VALUES (108, '虾类冷冻食品', 106, '2023-05-06 21:28:25', '2023-05-06 21:28:25');
INSERT INTO `category` VALUES (109, '蟹类冷冻食品', 106, '2023-05-06 21:28:30', '2023-05-06 21:28:30');
INSERT INTO `category` VALUES (110, '贝类冷冻食品', 106, '2023-05-06 21:28:34', '2023-05-06 21:28:34');
INSERT INTO `category` VALUES (111, '蔬菜水果冷冻食品', 97, '2023-05-06 21:28:41', '2023-05-06 21:28:41');
INSERT INTO `category` VALUES (112, '蔬菜冷冻食品', 111, '2023-05-06 21:28:48', '2023-05-06 21:28:48');
INSERT INTO `category` VALUES (113, '果类冷冻食品', 111, '2023-05-06 21:28:55', '2023-05-06 21:28:55');
INSERT INTO `category` VALUES (114, '混合冷冻食品', 111, '2023-05-06 21:29:00', '2023-05-06 21:29:00');
INSERT INTO `category` VALUES (115, '主食冷冻食品', 97, '2023-05-06 21:29:06', '2023-05-06 21:29:06');
INSERT INTO `category` VALUES (116, '面食冷冻食品', 115, '2023-05-06 21:29:12', '2023-05-06 21:29:12');
INSERT INTO `category` VALUES (117, '米饭冷冻食品', 115, '2023-05-06 21:29:18', '2023-05-06 21:29:18');
INSERT INTO `category` VALUES (118, '包子冷冻食品', 115, '2023-05-06 21:29:24', '2023-05-06 21:29:24');
INSERT INTO `category` VALUES (119, '馒头冷冻食品', 115, '2023-05-06 21:29:30', '2023-05-06 21:29:30');
INSERT INTO `category` VALUES (120, '饮品冷冻食品', 97, '2023-05-06 21:29:37', '2023-05-06 21:29:37');
INSERT INTO `category` VALUES (121, '果汁冷冻食品', 120, '2023-05-06 21:29:42', '2023-05-06 21:29:42');
INSERT INTO `category` VALUES (122, '奶制品冷冻食品', 120, '2023-05-06 21:29:48', '2023-05-06 21:29:48');
INSERT INTO `category` VALUES (123, '服装', 0, '2023-05-06 21:31:13', '2023-05-06 21:31:13');
INSERT INTO `category` VALUES (124, '男装', 123, '2023-05-06 21:31:20', '2023-05-06 21:31:20');
INSERT INTO `category` VALUES (125, '上装', 124, '2023-05-06 21:31:25', '2023-05-06 21:31:25');
INSERT INTO `category` VALUES (126, '衬衫', 125, '2023-05-06 21:31:30', '2023-05-06 21:31:30');
INSERT INTO `category` VALUES (127, 'T恤', 125, '2023-05-06 21:31:35', '2023-05-06 21:31:35');
INSERT INTO `category` VALUES (128, '毛衣', 125, '2023-05-06 21:31:41', '2023-05-06 21:31:41');
INSERT INTO `category` VALUES (129, '下装', 124, '2023-05-06 21:31:46', '2023-05-06 21:31:46');
INSERT INTO `category` VALUES (130, '裤子', 129, '2023-05-06 21:32:00', '2023-05-06 21:32:00');
INSERT INTO `category` VALUES (131, '牛仔裤', 129, '2023-05-06 21:32:06', '2023-05-06 21:32:06');
INSERT INTO `category` VALUES (132, '短裤', 129, '2023-05-06 21:32:11', '2023-05-06 21:32:11');
INSERT INTO `category` VALUES (133, '女装', 123, '2023-05-06 21:33:04', '2023-05-06 21:33:04');
INSERT INTO `category` VALUES (134, '上装', 133, '2023-05-06 21:33:11', '2023-05-06 21:33:11');
INSERT INTO `category` VALUES (135, '衬衫', 134, '2023-05-06 21:33:17', '2023-05-06 21:33:17');
INSERT INTO `category` VALUES (136, 'T恤', 134, '2023-05-06 21:33:24', '2023-05-06 21:33:24');
INSERT INTO `category` VALUES (137, '毛衣', 134, '2023-05-06 21:33:28', '2023-05-06 21:33:28');
INSERT INTO `category` VALUES (138, '下装', 133, '2023-05-06 21:33:35', '2023-05-06 21:33:35');
INSERT INTO `category` VALUES (139, '裙子', 138, '2023-05-06 21:33:42', '2023-05-06 21:33:42');
INSERT INTO `category` VALUES (140, '裤子', 138, '2023-05-06 21:33:52', '2023-05-06 21:33:52');
INSERT INTO `category` VALUES (141, '牛仔裤', 138, '2023-05-06 21:33:57', '2023-05-06 21:33:57');
INSERT INTO `category` VALUES (142, '童装', 123, '2023-05-06 21:34:18', '2023-05-06 21:34:18');
INSERT INTO `category` VALUES (143, '上装', 142, '2023-05-06 21:34:24', '2023-05-06 21:34:24');
INSERT INTO `category` VALUES (144, '卫衣', 143, '2023-05-06 21:34:28', '2023-05-06 21:34:28');
INSERT INTO `category` VALUES (145, '外套', 143, '2023-05-06 21:34:34', '2023-05-06 21:34:34');
INSERT INTO `category` VALUES (146, '羽绒服', 143, '2023-05-06 21:34:39', '2023-05-06 21:34:39');
INSERT INTO `category` VALUES (147, '下装', 142, '2023-05-06 21:34:44', '2023-05-06 21:34:44');
INSERT INTO `category` VALUES (148, '运动裤', 147, '2023-05-06 21:34:49', '2023-05-06 21:34:49');
INSERT INTO `category` VALUES (149, '牛仔裤', 147, '2023-05-06 21:34:54', '2023-05-06 21:34:54');
INSERT INTO `category` VALUES (150, '裙子', 147, '2023-05-06 21:34:58', '2023-05-06 21:34:58');
INSERT INTO `category` VALUES (151, '家居', 0, '2023-05-06 21:35:09', '2023-05-06 21:35:09');
INSERT INTO `category` VALUES (152, '家具', 151, '2023-05-06 21:35:16', '2023-05-06 21:35:16');
INSERT INTO `category` VALUES (153, '卧室家具', 152, '2023-05-06 21:35:21', '2023-05-06 21:35:21');
INSERT INTO `category` VALUES (154, '床', 153, '2023-05-06 21:35:27', '2023-05-06 21:35:27');
INSERT INTO `category` VALUES (155, '衣柜', 153, '2023-05-06 21:35:35', '2023-05-06 21:35:35');
INSERT INTO `category` VALUES (156, '床头柜', 153, '2023-05-06 21:35:40', '2023-05-06 21:35:40');
INSERT INTO `category` VALUES (157, '客厅家具', 152, '2023-05-06 21:35:50', '2023-05-06 21:35:50');
INSERT INTO `category` VALUES (158, '沙发', 157, '2023-05-06 21:35:57', '2023-05-06 21:35:57');
INSERT INTO `category` VALUES (159, '电视柜', 157, '2023-05-06 21:36:06', '2023-05-06 21:36:06');
INSERT INTO `category` VALUES (160, '茶几', 157, '2023-05-06 21:36:11', '2023-05-06 21:36:11');
INSERT INTO `category` VALUES (161, '餐厅家具', 152, '2023-05-06 21:36:19', '2023-05-06 21:36:19');
INSERT INTO `category` VALUES (162, '餐桌', 161, '2023-05-06 21:36:24', '2023-05-06 21:36:24');
INSERT INTO `category` VALUES (163, '餐椅', 161, '2023-05-06 21:36:29', '2023-05-06 21:36:29');
INSERT INTO `category` VALUES (164, '餐边柜', 161, '2023-05-06 21:36:36', '2023-05-06 21:36:36');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `work_number` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '员工姓名',
  `sex` tinyint(0) NOT NULL DEFAULT 1 COMMENT '性别1为男，0为女',
  `age` int(0) NOT NULL COMMENT '年龄',
  `position_id` int(0) NOT NULL DEFAULT 0 COMMENT '职务',
  `store_id` int(0) NOT NULL DEFAULT 0 COMMENT '所属部门',
  `warehouse_id` int(0) NOT NULL DEFAULT 0,
  `salary` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `status` tinyint(0) NOT NULL DEFAULT 1,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '员工表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (18, '10001', '张三', 1, 28, 18, 48, 0, 8000.00, 1, '2023-05-07 14:21:56', '2023-05-07 14:40:35');
INSERT INTO `employee` VALUES (19, '10002', '李四', 1, 30, 18, 44, 0, 8000.00, 1, '2023-05-07 14:22:18', '2023-05-07 15:06:37');
INSERT INTO `employee` VALUES (20, '10003', '王五', 0, 26, 18, 45, 0, 8000.00, 1, '2023-05-07 14:22:38', '2023-05-07 15:06:28');
INSERT INTO `employee` VALUES (21, '10004	', '赵六', 1, 33, 18, 46, 0, 8000.00, 1, '2023-05-07 14:23:16', '2023-05-07 15:06:12');
INSERT INTO `employee` VALUES (22, '10005', '刘七', 0, 24, 18, 47, 0, 8000.00, 1, '2023-05-07 14:23:35', '2023-05-07 15:05:53');
INSERT INTO `employee` VALUES (23, '10006', '钱八', 1, 27, 36, 0, 49, 7500.00, 1, '2023-05-07 14:23:49', '2023-05-07 14:28:58');
INSERT INTO `employee` VALUES (24, '10007	', '孙九', 0, 29, 36, 0, 48, 7500.00, 1, '2023-05-07 14:24:02', '2023-05-07 14:41:40');
INSERT INTO `employee` VALUES (25, '10008', '周十', 1, 32, 36, 0, 47, 7500.00, 1, '2023-05-07 14:24:16', '2023-05-07 14:42:05');
INSERT INTO `employee` VALUES (26, '10009	', '吴十一', 1, 25, 36, 0, 46, 7500.00, 1, '2023-05-07 14:24:28', '2023-05-07 14:41:52');
INSERT INTO `employee` VALUES (27, '10010', '朱十二', 0, 31, 36, 0, 45, 7500.00, 1, '2023-05-07 14:24:40', '2023-05-07 14:41:59');
INSERT INTO `employee` VALUES (28, '10050', '邱十三', 1, 22, 36, 0, 50, 9000.00, 1, '2023-05-07 14:33:08', '2023-05-07 14:40:28');
INSERT INTO `employee` VALUES (30, '10099', '刘莉', 0, 30, 18, 46, 0, 8000.00, 2, '2023-05-07 15:24:02', '2023-05-07 15:24:30');
INSERT INTO `employee` VALUES (31, '10088', '王浩', 1, 26, 0, 0, 0, 7500.00, 3, '2023-05-07 15:25:46', '2023-05-07 15:25:46');

-- ----------------------------
-- Table structure for income_expense
-- ----------------------------
DROP TABLE IF EXISTS `income_expense`;
CREATE TABLE `income_expense`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `amount` decimal(10, 2) NOT NULL,
  `type` tinyint(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of income_expense
-- ----------------------------
INSERT INTO `income_expense` VALUES (17, 1321500.00, 0, '2023-04-30 16:44:19', '2023-04-30 16:44:19');
INSERT INTO `income_expense` VALUES (18, 1421500.00, 0, '2023-03-31 16:44:19', '2023-03-31 16:44:19');
INSERT INTO `income_expense` VALUES (19, 1023500.00, 0, '2023-02-28 16:44:19', '2023-02-28 16:44:19');
INSERT INTO `income_expense` VALUES (20, 1136500.00, 0, '2023-01-31 16:44:19', '2023-01-31 16:44:19');
INSERT INTO `income_expense` VALUES (21, 2321500.00, 1, '2023-04-30 16:44:19', '2023-04-30 16:44:19');
INSERT INTO `income_expense` VALUES (22, 1451500.00, 1, '2023-03-31 16:44:19', '2023-03-31 16:44:19');
INSERT INTO `income_expense` VALUES (23, 1323500.00, 1, '2023-02-28 16:44:19', '2023-02-28 16:44:19');
INSERT INTO `income_expense` VALUES (24, 1736500.00, 1, '2023-01-31 16:44:19', '2023-01-31 16:44:19');

-- ----------------------------
-- Table structure for instock
-- ----------------------------
DROP TABLE IF EXISTS `instock`;
CREATE TABLE `instock`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `warehouse_id` int(0) NOT NULL DEFAULT 0,
  `product_id` int(0) NOT NULL DEFAULT 0,
  `instock_count` int(0) NOT NULL DEFAULT 0,
  `type` tinyint(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of instock
-- ----------------------------
INSERT INTO `instock` VALUES (28, 46, 36, 200, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (29, 47, 35, 10, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (30, 48, 37, 1000, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (31, 49, 34, 1000, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (32, 45, 33, 1000, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (33, 46, 38, 1000, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (34, 47, 40, 1000, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (35, 48, 42, 500, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (36, 49, 43, 1000, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (37, 45, 41, 1000, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `instock` VALUES (38, 47, 36, 10, 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `warehouse_id` int(0) NOT NULL DEFAULT 0,
  `product_id` int(0) NOT NULL DEFAULT 0,
  `instock_count` int(0) NOT NULL DEFAULT 0,
  `outstock_count` int(0) NOT NULL DEFAULT 0,
  `inventory_count` int(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of inventory
-- ----------------------------
INSERT INTO `inventory` VALUES (26, 46, 35, 0, 0, 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (27, 47, 35, 10, 1, 9, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (28, 48, 37, 1000, 2, 998, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (29, 49, 33, 0, 0, 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (30, 45, 33, 1000, 3, 997, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (31, 49, 34, 1000, 4, 996, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (32, 46, 38, 1000, 5, 995, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (33, 47, 40, 1000, 6, 994, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (34, 48, 42, 500, 7, 493, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (35, 49, 43, 1000, 8, 992, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (36, 45, 41, 1000, 8, 1026, '2023-05-08 16:23:14', '2023-05-08 09:43:09');
INSERT INTO `inventory` VALUES (37, 46, 36, 200, 20, 180, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `inventory` VALUES (38, 47, 36, 10, 0, 10, '2023-05-08 16:23:14', '2023-05-08 16:23:14');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `card_number` char(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sex` int(0) NOT NULL DEFAULT 1,
  `balance` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `status` int(0) NOT NULL DEFAULT 1,
  `point` int(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `card_number`(`card_number`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (12, '1234567890', '张伟', '13811112222', 1, 100.00, 1, 0, '2023-05-07 15:11:04', '2023-05-07 15:11:04');
INSERT INTO `member` VALUES (13, '2345678901', '王芳', '13922223333', 0, 50.00, 1, 0, '2023-05-07 15:11:30', '2023-05-07 15:11:30');
INSERT INTO `member` VALUES (14, '3456789012', '李娜', '13633334444', 0, 200.00, 1, 500, '2023-05-07 15:11:52', '2023-05-07 15:11:52');
INSERT INTO `member` VALUES (15, '4567890123', '赵强', '13744445555', 1, 300.00, 1, 129, '2023-05-07 15:12:18', '2023-05-07 15:12:18');
INSERT INTO `member` VALUES (16, '5678901234', '刘洋', '13855556666', 1, 150.00, 1, 500, '2023-05-07 15:12:58', '2023-05-07 15:12:58');
INSERT INTO `member` VALUES (17, ' 6789012345', '陈静', '13966667777', 0, 80.00, 1, 50, '2023-05-07 15:13:30', '2023-05-07 15:13:30');
INSERT INTO `member` VALUES (18, '9723947656', '王伟东', '17623380947', 1, 0.00, 2, 0, '2023-05-07 15:14:57', '2023-05-07 15:18:43');
INSERT INTO `member` VALUES (19, '1265378954', '张婷婷', '17623394098', 0, 0.00, 3, 0, '2023-05-07 15:15:27', '2023-05-07 15:18:02');

-- ----------------------------
-- Table structure for member_point
-- ----------------------------
DROP TABLE IF EXISTS `member_point`;
CREATE TABLE `member_point`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `member_id` int(0) NOT NULL DEFAULT 0,
  `point` int(0) NOT NULL,
  `remark` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member_point
-- ----------------------------
INSERT INTO `member_point` VALUES (21, 17, 100, '购买100元', '2023-05-07 15:20:18', '2023-05-07 15:21:06');
INSERT INTO `member_point` VALUES (22, 17, -50, '积分抵扣', '2023-05-07 15:20:38', '2023-05-07 15:20:38');
INSERT INTO `member_point` VALUES (23, 16, 200, '购买200元', '2023-05-07 15:21:00', '2023-05-07 15:21:00');
INSERT INTO `member_point` VALUES (24, 16, 300, '购买300元', '2023-05-07 15:21:20', '2023-05-07 15:21:20');
INSERT INTO `member_point` VALUES (25, 15, 50, '购买50元', '2023-05-07 15:21:35', '2023-05-07 15:21:35');
INSERT INTO `member_point` VALUES (26, 15, 79, '购买79元', '2023-05-07 15:21:52', '2023-05-07 15:21:52');
INSERT INTO `member_point` VALUES (27, 14, 1000, '购买1000元', '2023-05-07 15:22:19', '2023-05-07 15:22:19');
INSERT INTO `member_point` VALUES (28, 14, -500, '积分抵扣', '2023-05-07 15:22:32', '2023-05-07 15:22:32');

-- ----------------------------
-- Table structure for outstock
-- ----------------------------
DROP TABLE IF EXISTS `outstock`;
CREATE TABLE `outstock`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `warehouse_id` int(0) NOT NULL DEFAULT 0,
  `product_id` int(0) NOT NULL DEFAULT 0,
  `outstock_count` int(0) NOT NULL DEFAULT 0,
  `type` tinyint(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outstock
-- ----------------------------
INSERT INTO `outstock` VALUES (10, 46, 36, 10, 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (11, 46, 36, 10, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (12, 47, 35, 1, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (13, 45, 41, 8, 1, '2023-05-08 16:23:14', '2023-05-08 09:43:09');
INSERT INTO `outstock` VALUES (14, 49, 43, 8, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (15, 48, 42, 7, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (16, 47, 40, 6, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (17, 46, 38, 5, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (18, 49, 34, 4, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (19, 45, 33, 3, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `outstock` VALUES (20, 48, 37, 2, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `pid` int(0) NOT NULL DEFAULT 0,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `type` tinyint(0) NOT NULL DEFAULT 1,
  `permission_value` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `path` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, '商品模块', 1, NULL, '/product', 'Layout', 'el-icon-s-help', '2023-04-12 12:02:02', '2023-04-12 12:02:05');
INSERT INTO `permission` VALUES (2, 1, '分类管理', 1, 'product:category:index', 'category/tree', '/product/category/index', 'tree', '2023-04-12 12:04:09', '2023-04-12 12:04:11');
INSERT INTO `permission` VALUES (3, 1, '品牌管理', 1, 'product:brand:index', 'brand/table', '/product/brand/index', 'table', '2023-04-12 12:05:20', '2023-04-12 12:05:23');
INSERT INTO `permission` VALUES (4, 1, '商品管理', 1, 'product:product:index', 'product/table', '/product/product/index', 'table', '2023-04-12 12:06:25', '2023-04-12 12:06:27');
INSERT INTO `permission` VALUES (5, 0, '实体模块', 1, NULL, '/entity', 'Layout', 'el-icon-s-help', '2023-04-12 12:08:20', '2023-04-12 12:08:22');
INSERT INTO `permission` VALUES (6, 5, '地区管理', 1, 'entity:region:index', 'region/table', '/entity/region/index', 'table', '2023-04-12 12:09:14', '2023-04-12 12:09:16');
INSERT INTO `permission` VALUES (7, 5, '超市门店管理', 1, 'entity:store:index', 'store/table', '/entity/store/index', 'table', '2023-04-12 12:10:09', '2023-04-12 12:10:11');
INSERT INTO `permission` VALUES (8, 5, '仓库管理', 1, 'entity:warehouse:index', 'warehouse/table', '/entity/warehouse/index', 'table', '2023-04-12 12:10:38', '2023-04-12 12:10:40');
INSERT INTO `permission` VALUES (9, 0, '会员模块', 1, NULL, '/member', 'Layout', 'el-icon-s-help', '2023-04-12 12:11:26', '2023-04-12 12:11:29');
INSERT INTO `permission` VALUES (10, 9, '会员管理', 1, 'member:member:index', 'member/table', '/member/member/index', 'table', '2023-04-12 12:12:02', '2023-04-12 12:12:04');
INSERT INTO `permission` VALUES (11, 9, '会员积分管理', 1, 'member:memberpoint:index', 'memberpoint/table', '/member/memberpoint/index', 'table', '2023-04-12 12:12:30', '2023-04-12 12:12:32');
INSERT INTO `permission` VALUES (12, 0, '员工模块', 1, NULL, '/employee', 'Layout', 'el-icon-s-help', '2023-04-12 12:13:01', '2023-04-12 12:13:03');
INSERT INTO `permission` VALUES (13, 12, '员工管理', 1, 'employee:employee:index', 'employee/table', '/employee/employee/index', 'table', '2023-04-12 12:13:31', '2023-04-12 12:13:33');
INSERT INTO `permission` VALUES (14, 12, '职位管理', 1, 'employee:position:index', 'position/table', '/employee/position/index', 'table', '2023-04-12 12:13:56', '2023-04-12 12:13:58');
INSERT INTO `permission` VALUES (15, 0, '供应模块', 1, NULL, '/supply', 'Layout', 'el-icon-s-help', '2023-04-12 12:14:24', '2023-04-12 12:14:27');
INSERT INTO `permission` VALUES (16, 15, '供应商管理', 1, 'supply:supply:index', 'supply/table', '/supply/supply/index', 'table', '2023-04-12 12:14:51', '2023-04-12 12:14:54');
INSERT INTO `permission` VALUES (17, 15, '订单管理', 1, 'supply:order:index', 'order/table', '/supply/order/index', 'table', '2023-04-12 12:15:19', '2023-04-12 12:15:21');
INSERT INTO `permission` VALUES (18, 15, '进货管理', 1, 'supply:pruchase:index', 'purchase/table', '/supply/purchase/index', 'table', '2023-04-12 12:15:56', '2023-04-12 12:15:58');
INSERT INTO `permission` VALUES (19, 0, '库存模块', 1, NULL, '/inventory', 'Layout', 'el-icon-s-help', '2023-04-12 12:16:22', '2023-04-12 12:16:25');
INSERT INTO `permission` VALUES (20, 19, '库存管理', 1, 'inventory:inventory:index', 'inventory/table', '/inventory/inventory/index', 'table', '2023-04-12 12:16:53', '2023-04-12 12:16:55');
INSERT INTO `permission` VALUES (21, 19, '进库管理', 1, 'inventory:instock:index', 'instock/table', '/inventory/instock/index', 'table', '2023-04-12 12:17:18', '2023-04-12 12:17:20');
INSERT INTO `permission` VALUES (22, 19, '出库管理', 1, 'inventory:outstock:index', 'outstock/table', '/inventory/outstock/index', 'table', '2023-04-12 12:17:46', '2023-04-12 12:17:49');
INSERT INTO `permission` VALUES (23, 0, '权限模块', 1, NULL, '/permission', 'Layout', 'el-icon-s-help', '2023-04-12 18:48:40', '2023-04-12 18:48:42');
INSERT INTO `permission` VALUES (24, 23, '用户管理', 1, 'permission:user:index', 'user/table', '/permission/user/index', 'table', '2023-04-12 18:49:08', '2023-04-12 18:49:11');
INSERT INTO `permission` VALUES (25, 23, '角色管理', 1, 'permission:role:index', 'role/table', '/permission/role/index', 'table', '2023-04-12 18:50:55', '2023-04-12 18:50:57');
INSERT INTO `permission` VALUES (26, 23, '权限管理', 1, 'permission:permission:index', 'permission/table', '/permission/permission/index', 'table', '2023-04-12 18:51:45', '2023-04-12 18:51:46');
INSERT INTO `permission` VALUES (27, 0, '统计分析模块', 1, NULL, '/statistics', 'Layout', 'el-icon-s-help', '2023-04-18 12:56:35', '2023-04-18 12:56:37');
INSERT INTO `permission` VALUES (28, 27, '商品进价变化', 1, NULL, 'price_comparison/table', '/statistics/price_comparison/index', 'table', '2023-04-18 12:57:26', '2023-04-18 12:57:28');
INSERT INTO `permission` VALUES (29, 27, '供货商价格对比', 1, NULL, 'supplier_price_change/table', '/statistics/supplier_price_change/index', 'table', '2023-04-18 12:58:07', '2023-04-18 12:58:09');
INSERT INTO `permission` VALUES (30, 27, '上月支出占比', 1, NULL, 'proportion_of_expenditure/table', '/statistics/proportion_of_expenditure/index', 'table', '2023-04-18 12:58:57', '2023-04-18 12:58:59');
INSERT INTO `permission` VALUES (31, 27, '保质期与促销', 1, NULL, 'shelflife_promotion/table', '/statistics/shelflife_promotion/index', 'table', '2023-04-18 12:59:28', '2023-04-18 12:59:30');

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (18, '超市经理', '2023-05-06 20:42:27', '2023-05-06 20:45:05');
INSERT INTO `position` VALUES (19, '超市副经理', '2023-05-06 20:42:42', '2023-05-06 20:45:15');
INSERT INTO `position` VALUES (20, '采购员', '2023-05-06 20:42:53', '2023-05-06 20:42:53');
INSERT INTO `position` VALUES (21, '超市仓库管理员', '2023-05-06 20:43:02', '2023-05-06 20:43:02');
INSERT INTO `position` VALUES (22, '收银员', '2023-05-06 20:43:09', '2023-05-06 20:43:09');
INSERT INTO `position` VALUES (23, '促销员', '2023-05-06 20:43:15', '2023-05-06 20:43:15');
INSERT INTO `position` VALUES (24, '店员', '2023-05-06 20:43:20', '2023-05-06 20:43:20');
INSERT INTO `position` VALUES (25, '保安', '2023-05-06 20:43:25', '2023-05-06 20:43:25');
INSERT INTO `position` VALUES (26, '清洁员', '2023-05-06 20:43:31', '2023-05-06 20:43:31');
INSERT INTO `position` VALUES (27, '仓库管理员', '2023-05-06 20:43:39', '2023-05-06 20:43:39');
INSERT INTO `position` VALUES (28, '货物装卸工', '2023-05-06 20:43:48', '2023-05-06 20:43:48');
INSERT INTO `position` VALUES (29, '库房管理员', '2023-05-06 20:43:55', '2023-05-06 20:43:55');
INSERT INTO `position` VALUES (30, '库存管理员', '2023-05-06 20:44:02', '2023-05-06 20:44:02');
INSERT INTO `position` VALUES (31, '仓库文员', '2023-05-06 20:44:11', '2023-05-06 20:44:11');
INSERT INTO `position` VALUES (32, '配送员', '2023-05-06 20:44:17', '2023-05-06 20:44:17');
INSERT INTO `position` VALUES (33, '装箱员', '2023-05-06 20:44:22', '2023-05-06 20:44:22');
INSERT INTO `position` VALUES (34, '叉车司机', '2023-05-06 20:44:27', '2023-05-06 20:44:27');
INSERT INTO `position` VALUES (35, '安全员', '2023-05-06 20:44:32', '2023-05-06 20:44:32');
INSERT INTO `position` VALUES (36, '仓库经理', '2023-05-06 20:48:54', '2023-05-06 20:48:54');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `category_id` int(0) NOT NULL DEFAULT 0,
  `brand_id` int(0) NOT NULL DEFAULT 0,
  `sell_price` decimal(10, 2) NOT NULL DEFAULT 0.00 COMMENT '售价',
  `promotional_price` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `unit` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `is_show` tinyint(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (33, '香菇鸡肉罐头', 87, 118, 7.00, 7.00, '罐', 1, '2023-05-07 15:34:28', '2023-05-07 15:57:10');
INSERT INTO `product` VALUES (34, '菜花肉丸罐头', 87, 118, 9.00, 9.00, '罐', 1, '2023-05-07 15:37:36', '2023-05-07 15:57:11');
INSERT INTO `product` VALUES (35, 'HEMNES 床头柜', 156, 79, 599.00, 599.00, '个', 1, '2023-05-07 15:39:14', '2023-05-07 15:57:11');
INSERT INTO `product` VALUES (36, 'SELJE 床头柜', 156, 79, 199.00, 199.00, '个', 1, '2023-05-07 15:40:03', '2023-05-07 15:57:11');
INSERT INTO `product` VALUES (37, '脆脆鲨', 81, 103, 24.90, 24.90, '盒', 1, '2023-05-07 15:46:11', '2023-05-07 15:57:11');
INSERT INTO `product` VALUES (38, '夏季宽松运动休闲百搭舒适五分裤子', 130, 143, 49.90, 49.90, '件', 1, '2023-05-07 15:49:25', '2023-05-07 15:57:12');
INSERT INTO `product` VALUES (39, '双人款懒人沙发', 158, 81, 1200.00, 1200.00, '套', 0, '2023-05-07 15:51:09', '2023-05-07 15:57:26');
INSERT INTO `product` VALUES (40, '奶香馒头', 119, 145, 15.60, 15.60, '袋', 1, '2023-05-07 15:56:41', '2023-05-07 15:57:25');
INSERT INTO `product` VALUES (41, '上海灌汤小笼包', 118, 145, 29.50, 29.50, '袋', 1, '2023-05-07 15:59:39', '2023-05-07 15:59:43');
INSERT INTO `product` VALUES (42, '开叉装饰裙子', 139, 133, 299.00, 299.00, '件', 1, '2023-05-07 16:02:09', '2023-05-07 16:02:18');
INSERT INTO `product` VALUES (43, '高弹力运动裤(柔软束脚春装卫裤) ', 148, 132, 99.00, 99.00, '件', 1, '2023-05-07 16:04:40', '2023-05-07 16:21:48');

-- ----------------------------
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `purchase_number` bigint(0) NOT NULL,
  `product_id` int(0) NOT NULL DEFAULT 0,
  `supplier_id` int(0) NOT NULL DEFAULT 0,
  `quantity` int(0) NOT NULL,
  `purchase_price` decimal(10, 2) NOT NULL,
  `total_price` decimal(10, 2) NOT NULL,
  `shelf_life` datetime(0) NOT NULL,
  `type` tinyint(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 67 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES (53, 852623688602882048, 43, 18, 1000, 40.00, 35000.00, '2033-08-31 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (54, 852624389013901312, 42, 15, 500, 150.00, 70000.00, '2033-05-12 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (55, 852624769173032960, 41, 17, 1000, 15.00, 14000.00, '2023-05-10 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (56, 852625014317518848, 40, 17, 1000, 10.00, 9000.00, '2023-05-10 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (57, 852625587792121856, 38, 18, 1000, 30.00, 28000.00, '2032-05-19 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (58, 852625775319453696, 33, 17, 1000, 4.00, 4000.00, '2023-05-10 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (59, 852626553727750144, 34, 17, 1000, 5.00, 5000.00, '2023-05-10 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (60, 852627126761951232, 37, 17, 1000, 15.00, 14000.00, '2023-05-10 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (61, 852627298124435456, 35, 19, 10, 399.00, 3990.00, '2027-05-20 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (62, 852627460326559744, 36, 19, 100, 99.00, 9900.00, '2027-09-16 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (63, 852628000414502912, 36, 16, 9, 100.00, 900.00, '2027-09-23 00:00:00', 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (64, 852631043642101760, 36, 16, 100, 120.00, 10000.00, '2028-05-26 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (65, 852640441949097984, 36, 19, 1, 111.00, 111.00, '2027-05-14 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase` VALUES (66, 852640542457204736, 36, 19, 1, 107.00, 107.00, '2023-05-07 00:00:00', 0, '2023-05-08 16:23:14', '2023-05-08 16:23:14');

-- ----------------------------
-- Table structure for purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `order_number` bigint(0) NOT NULL,
  `purchase_id` int(0) NOT NULL DEFAULT 0,
  `supplier_id` int(0) NOT NULL DEFAULT 0,
  `total_price` decimal(10, 2) NOT NULL,
  `is_pay` tinyint(0) NOT NULL DEFAULT 2,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of purchase_order
-- ----------------------------
INSERT INTO `purchase_order` VALUES (19, 852623688602882048, 53, 18, 35000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (20, 852624389013901312, 54, 15, 70000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (21, 852624769173032960, 55, 17, 14000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (22, 852625014317518848, 56, 17, 9000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (23, 852625587792121856, 57, 18, 28000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (24, 852625775319453696, 58, 17, 4000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (25, 852626553727750144, 59, 17, 5000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (26, 852627126761951232, 60, 17, 14000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (27, 852627298124435456, 61, 19, 3990.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (28, 852627460326559744, 62, 19, 9900.00, 1, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (29, 852628000414502912, 63, 16, 900.00, 3, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (30, 852631043642101760, 64, 16, 10000.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (31, 852640441949097984, 65, 19, 111.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');
INSERT INTO `purchase_order` VALUES (32, 852640542457204736, 66, 19, 107.00, 2, '2023-05-08 16:23:14', '2023-05-08 16:23:14');

-- ----------------------------
-- Table structure for region
-- ----------------------------
DROP TABLE IF EXISTS `region`;
CREATE TABLE `region`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `parent_id` int(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 287 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of region
-- ----------------------------
INSERT INTO `region` VALUES (1, '河北省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (2, '山西省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (3, '辽宁省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (4, '吉林省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (5, '黑龙江省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (6, '江苏省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (7, '浙江省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (8, '安徽省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (9, '福建省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (10, '江西省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (11, '山东省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (12, '河南省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (13, '湖北省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (14, '湖南省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (15, '广东省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (16, '海南省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (17, '四川省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (18, '贵州省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (19, '云南省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (20, '陕西省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (21, '甘肃省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (22, '青海省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (23, '台湾省', 0, '2023-03-27 18:28:49', '2023-03-27 18:28:49');
INSERT INTO `region` VALUES (24, '内蒙古自治区', 0, '2023-03-27 18:30:25', '2023-03-27 18:30:25');
INSERT INTO `region` VALUES (25, '广西壮族自治区', 0, '2023-03-27 18:30:25', '2023-03-27 18:30:25');
INSERT INTO `region` VALUES (26, '西藏自治区', 0, '2023-03-27 18:30:25', '2023-03-27 18:30:25');
INSERT INTO `region` VALUES (27, '宁夏回族自治区', 0, '2023-03-27 18:30:25', '2023-03-27 18:30:25');
INSERT INTO `region` VALUES (28, '新疆维吾尔自治区', 0, '2023-03-27 18:30:25', '2023-03-27 18:30:25');
INSERT INTO `region` VALUES (29, '北京市', 0, '2023-03-27 18:30:52', '2023-03-27 18:30:52');
INSERT INTO `region` VALUES (30, '天津市', 0, '2023-03-27 18:30:52', '2023-03-27 18:30:52');
INSERT INTO `region` VALUES (31, '上海市', 0, '2023-03-27 18:30:52', '2023-03-27 18:30:52');
INSERT INTO `region` VALUES (32, '重庆市', 0, '2023-03-27 18:30:52', '2023-03-27 18:30:52');
INSERT INTO `region` VALUES (33, '香港特别行政区', 0, '2023-03-27 18:31:18', '2023-03-27 18:31:18');
INSERT INTO `region` VALUES (34, '澳门特别行政区', 0, '2023-03-27 18:31:18', '2023-03-27 18:31:18');
INSERT INTO `region` VALUES (35, '重庆市', 32, '2023-03-27 20:29:32', '2023-03-27 20:29:34');
INSERT INTO `region` VALUES (36, '渝中区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (37, '万州区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (38, '涪陵区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (39, '大渡口区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (40, '江北区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (41, '沙坪坝区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (42, '九龙坡区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (43, '南岸区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (44, '北碚区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (45, '綦江区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (46, '大足区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (47, '渝北区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (48, '巴南区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (49, '黔江区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (50, '长寿区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (51, '江津区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (52, '合川区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (53, '永川区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (54, '南川区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (55, '璧山区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (56, '铜梁区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (57, '潼南区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (58, '荣昌区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (59, '开州区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (60, '梁平区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (61, '武隆区', 35, '2023-03-27 18:51:57', '2023-03-27 18:51:57');
INSERT INTO `region` VALUES (62, '城口县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (63, '丰都县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (64, '垫江县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (65, '忠县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (66, '云阳县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (67, '奉节县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (68, '巫山县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (69, '巫溪县', 35, '2023-03-27 18:52:37', '2023-03-27 18:52:37');
INSERT INTO `region` VALUES (70, '石柱土家族自治县', 35, '2023-03-27 18:52:38', '2023-03-27 18:52:38');
INSERT INTO `region` VALUES (71, '秀山土家族苗族自治县', 35, '2023-03-27 18:52:38', '2023-03-27 18:52:38');
INSERT INTO `region` VALUES (72, '酉阳土家族苗族自治县', 35, '2023-03-27 18:52:38', '2023-03-27 18:52:38');
INSERT INTO `region` VALUES (73, '彭水苗族土家族自治县', 35, '2023-03-27 18:52:38', '2023-03-27 18:52:38');
INSERT INTO `region` VALUES (74, '南山街道', 43, '2023-03-27 20:33:46', '2023-03-27 20:33:48');
INSERT INTO `region` VALUES (75, '崇文路2号', 74, '2023-03-27 20:35:40', '2023-03-27 20:35:42');
INSERT INTO `region` VALUES (125, '朝天门街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (126, '南区街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (127, '解放碑街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (128, '临江门街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (129, '两路口街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (130, '大坪街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (131, '化龙桥街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (132, '龙湖街道', 36, '2023-05-07 13:48:44', '2023-05-07 13:48:44');
INSERT INTO `region` VALUES (133, '长江街道', 37, '2023-05-07 13:50:40', '2023-05-07 13:50:40');
INSERT INTO `region` VALUES (134, '滨江街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (135, '茶山童家溪街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (136, '云台街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (137, '长河街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (138, '童家溪街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (139, '官渡街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (140, '沙河街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (141, '竹山街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (142, '桥南街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (143, '万州街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (144, '沙坪街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (145, '太白街道', 37, '2023-05-07 13:50:41', '2023-05-07 13:50:41');
INSERT INTO `region` VALUES (146, '涪陵街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (147, '龙桥街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (148, '溪口街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (149, '龙潭街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (150, '白涛街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (151, '沙河街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (152, '柳荫街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (153, '三峡街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (154, '石家街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (155, '石坪街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (156, '官渡街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (157, '江南街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (158, '仙女山街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (159, '桂溪街道', 38, '2023-05-07 13:51:52', '2023-05-07 13:51:52');
INSERT INTO `region` VALUES (160, '新山村街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (161, '九宫庙街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (162, '鸿恩街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (163, '翠云街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (164, '茄子溪街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (165, '九龙坡街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (166, '锦华街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (167, '朝阳街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (168, '大渡口街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (169, '经济技术开发区街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (170, '社区服务中心街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (171, '陈家坪街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (172, '菜园坝街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (173, '内环以内街道', 39, '2023-05-07 13:53:01', '2023-05-07 13:53:01');
INSERT INTO `region` VALUES (174, '观音桥街道', 40, '2023-05-07 13:54:13', '2023-05-07 13:54:13');
INSERT INTO `region` VALUES (175, '大石坝街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (176, '北滨路街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (177, '十里河街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (178, '黄泥磅街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (179, '大竹林街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (180, '青年路街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (181, '五里店街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (182, '大兴村街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (183, '长生桥街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (184, '海尔路街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (185, '石马河街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (186, '寸滩街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (187, '农业园区街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (188, '龙头寺街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (189, '福利社街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (190, '鱼嘴镇街道', 40, '2023-05-07 13:54:14', '2023-05-07 13:54:14');
INSERT INTO `region` VALUES (191, '沙坪坝街道', 41, '2023-05-07 13:55:07', '2023-05-07 13:55:07');
INSERT INTO `region` VALUES (192, '陈家桥街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (193, '歌乐山街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (194, '井口街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (195, '石井坡街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (196, '石小路街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (197, '大学城街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (198, '沙龙街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (199, '申烨桥街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (200, '天星桥街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (201, '向阳路街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (202, '新桥街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (203, '西永街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (204, '修电院街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (205, '渝碚路街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (206, '曾家镇街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (207, '中梁山街道', 41, '2023-05-07 13:55:08', '2023-05-07 13:55:08');
INSERT INTO `region` VALUES (208, '陈家坪街道', 42, '2023-05-07 13:55:36', '2023-05-07 13:55:36');
INSERT INTO `region` VALUES (209, '石桥铺街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (210, '石坪桥街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (211, '黄桷坪街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (212, '二郎街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (213, '九龙镇街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (214, '南坪街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (215, '渝州路街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (216, '西彭街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (217, '金凤镇街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (218, '白市驿街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (219, '曾家镇街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (220, '龙泉镇街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (221, '杨家坪街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (222, '黄茅坪街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (223, '九龙坡街道', 42, '2023-05-07 13:55:37', '2023-05-07 13:55:37');
INSERT INTO `region` VALUES (224, '天生街道', 44, '2023-05-07 13:56:26', '2023-05-07 13:56:26');
INSERT INTO `region` VALUES (225, '东阳街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (226, '龙凤桥街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (227, '太极街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (228, '歇马街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (229, '永川街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (230, '朝阳街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (231, '北温泉街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (232, '清江街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (233, '大石坝街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (234, '金凤镇街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (235, '木耳镇街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (236, '蔡家岗街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (237, '石船镇街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (238, '谷城镇街道', 44, '2023-05-07 13:56:27', '2023-05-07 13:56:27');
INSERT INTO `region` VALUES (239, '工业园街道', 45, '2023-05-07 13:57:04', '2023-05-07 13:57:04');
INSERT INTO `region` VALUES (240, '工农街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (241, '江南街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (242, '向阳街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (243, '新华街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (244, '东泉街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (245, '河东街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (246, '范家街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (247, '龙门浩街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (248, '石角镇街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (249, '大塆镇街道', 45, '2023-05-07 13:57:05', '2023-05-07 13:57:05');
INSERT INTO `region` VALUES (250, '鸳鸯街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (251, '龙山街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (252, '红土地街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (253, '金童路街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (254, '花卉园街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (255, '回兴街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (256, '大竹林街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (257, '龙兴街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (258, '大湾街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (259, '两路街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (260, '空港街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (261, '双湖街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (262, '黄泥塝街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (263, '石船街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (264, '新牌坊街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (265, '紫荆路街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (266, '人和街道', 47, '2023-05-07 13:58:10', '2023-05-07 13:58:10');
INSERT INTO `region` VALUES (267, '巨轮街道', 46, '2023-05-07 13:58:49', '2023-05-07 13:58:49');
INSERT INTO `region` VALUES (268, '龙岗街道', 46, '2023-05-07 13:58:49', '2023-05-07 13:58:49');
INSERT INTO `region` VALUES (269, '西城街道', 46, '2023-05-07 13:58:49', '2023-05-07 13:58:49');
INSERT INTO `region` VALUES (270, '新市街道', 46, '2023-05-07 13:58:49', '2023-05-07 13:58:49');
INSERT INTO `region` VALUES (271, '茶山街道', 46, '2023-05-07 13:58:49', '2023-05-07 13:58:49');
INSERT INTO `region` VALUES (272, '长沙街道', 46, '2023-05-07 13:58:49', '2023-05-07 13:58:49');
INSERT INTO `region` VALUES (273, '沙坪街道', 46, '2023-05-07 13:58:49', '2023-05-07 13:58:49');
INSERT INTO `region` VALUES (274, '解放西路1号', 127, '2023-05-07 14:27:50', '2023-05-07 14:27:50');
INSERT INTO `region` VALUES (275, '建设路1号', 134, '2023-05-07 14:43:30', '2023-05-07 14:43:52');
INSERT INTO `region` VALUES (276, '人民西路1号', 146, '2023-05-07 14:44:53', '2023-05-07 15:25:00');
INSERT INTO `region` VALUES (277, '爱心路1号', 197, '2023-05-07 14:45:39', '2023-05-07 14:45:39');
INSERT INTO `region` VALUES (278, '兴科大道1号', 254, '2023-05-07 14:46:24', '2023-05-07 14:46:24');
INSERT INTO `region` VALUES (279, '人民西路1号', 60, '2023-05-07 15:15:54', '2023-05-07 15:19:11');
INSERT INTO `region` VALUES (280, '蔷薇路1号', 252, '2023-05-07 16:08:00', '2023-05-07 16:08:00');
INSERT INTO `region` VALUES (281, '龙山路1号', 251, '2023-05-07 16:08:42', '2023-05-07 16:08:42');
INSERT INTO `region` VALUES (282, '金龙路1号', 253, '2023-05-07 16:09:19', '2023-05-07 16:09:19');
INSERT INTO `region` VALUES (283, '启航路1号', 255, '2023-05-07 16:09:44', '2023-05-07 16:09:44');
INSERT INTO `region` VALUES (284, '龙虎路1号', 257, '2023-05-07 16:10:18', '2023-05-07 16:10:18');
INSERT INTO `region` VALUES (285, '启明路1号', 266, '2023-05-07 16:11:03', '2023-05-07 16:11:03');
INSERT INTO `region` VALUES (286, '长安路1号', 259, '2023-05-07 16:12:14', '2023-05-07 16:12:14');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '超级管理员', '2023-04-11 19:19:21', '2023-04-11 19:19:24');
INSERT INTO `role` VALUES (2, '仓库管理员', '2023-04-12 17:42:58', '2023-04-13 14:08:27');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `role_id` int(0) NOT NULL DEFAULT 0,
  `permission_id` int(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1, '2023-04-11 20:03:38', '2023-04-11 20:03:40');
INSERT INTO `role_permission` VALUES (2, 1, 2, '2023-04-12 15:00:22', '2023-04-12 15:00:24');
INSERT INTO `role_permission` VALUES (3, 1, 3, '2023-04-12 15:04:57', '2023-04-12 15:04:57');
INSERT INTO `role_permission` VALUES (4, 1, 4, '2023-04-12 15:04:57', '2023-04-12 15:04:57');
INSERT INTO `role_permission` VALUES (5, 1, 5, '2023-04-12 15:04:57', '2023-04-12 15:04:57');
INSERT INTO `role_permission` VALUES (6, 1, 6, '2023-04-12 15:04:57', '2023-04-12 15:04:57');
INSERT INTO `role_permission` VALUES (7, 1, 7, '2023-04-12 15:04:57', '2023-04-12 15:04:57');
INSERT INTO `role_permission` VALUES (8, 1, 8, '2023-04-12 15:04:57', '2023-04-12 15:04:57');
INSERT INTO `role_permission` VALUES (9, 1, 9, '2023-04-12 15:04:57', '2023-04-12 15:04:57');
INSERT INTO `role_permission` VALUES (10, 1, 10, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (11, 1, 11, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (12, 1, 12, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (13, 1, 13, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (14, 1, 14, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (15, 1, 15, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (16, 1, 16, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (17, 1, 17, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (18, 1, 18, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (19, 1, 19, '2023-04-12 15:05:44', '2023-04-12 15:05:44');
INSERT INTO `role_permission` VALUES (20, 1, 20, '2023-04-12 15:06:03', '2023-04-12 15:06:03');
INSERT INTO `role_permission` VALUES (21, 1, 21, '2023-04-12 15:06:03', '2023-04-12 15:06:03');
INSERT INTO `role_permission` VALUES (22, 1, 22, '2023-04-12 15:06:03', '2023-04-12 15:06:03');
INSERT INTO `role_permission` VALUES (27, 1, 23, '2023-04-12 18:54:14', '2023-04-12 18:54:15');
INSERT INTO `role_permission` VALUES (28, 1, 24, '2023-04-12 18:54:21', '2023-04-12 18:54:24');
INSERT INTO `role_permission` VALUES (29, 1, 25, '2023-04-12 18:54:38', '2023-04-12 18:54:40');
INSERT INTO `role_permission` VALUES (30, 1, 26, '2023-04-12 18:54:48', '2023-04-12 18:54:51');
INSERT INTO `role_permission` VALUES (42, 2, 19, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (43, 2, 20, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (44, 2, 21, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (45, 2, 22, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (46, 2, 18, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (47, 2, 17, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (48, 2, 15, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (49, 2, 16, '2023-04-13 14:08:27', '2023-04-13 14:08:27');
INSERT INTO `role_permission` VALUES (50, 1, 27, '2023-04-18 12:59:57', '2023-04-18 12:59:59');
INSERT INTO `role_permission` VALUES (51, 1, 28, '2023-04-18 12:59:57', '2023-04-18 12:59:59');
INSERT INTO `role_permission` VALUES (52, 1, 29, '2023-04-11 20:03:38', '2023-04-11 20:03:40');
INSERT INTO `role_permission` VALUES (53, 1, 30, '2023-04-11 20:03:38', '2023-04-11 20:03:40');
INSERT INTO `role_permission` VALUES (54, 1, 31, '2023-04-11 20:03:38', '2023-04-11 20:03:40');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `region_id` int(0) NOT NULL DEFAULT 0 COMMENT '门店地址',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '门店电话',
  `manager_id` int(0) NOT NULL DEFAULT 0,
  `area` double NOT NULL,
  `rent` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `status` tinyint(0) NOT NULL DEFAULT 3,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '门店表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES (44, 274, '021-12345678', 19, 1000, 100000.00, 1, '2023-05-07 14:50:05', '2023-05-07 14:50:05');
INSERT INTO `store` VALUES (45, 275, '010-87654321', 20, 800, 80000.00, 1, '2023-05-07 14:50:42', '2023-05-07 14:50:42');
INSERT INTO `store` VALUES (46, 276, '0755-11111111', 30, 1200, 150000.00, 1, '2023-05-07 14:51:09', '2023-05-07 14:51:09');
INSERT INTO `store` VALUES (47, 277, '027-22222222', 22, 500, 60000.00, 3, '2023-05-07 14:51:38', '2023-05-07 15:19:41');
INSERT INTO `store` VALUES (48, 278, '0512-33333333', 18, 900, 120000.00, 2, '2023-05-07 14:52:09', '2023-05-07 15:19:37');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `contact_person` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `tel` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `region_id` int(0) NOT NULL DEFAULT 0,
  `is_use` tinyint(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES (14, '重庆金百万食品公司', '张先生', '13823218888', 281, 0, '2023-05-07 16:13:49', '2023-05-07 16:17:21');
INSERT INTO `supplier` VALUES (15, '重庆美好时光服装厂', '李女士', '13625747777', 280, 1, '2023-05-07 16:14:20', '2023-05-07 16:17:01');
INSERT INTO `supplier` VALUES (16, '重庆阳光家居有限公司', '王先生', '13986356666', 282, 1, '2023-05-07 16:14:53', '2023-05-07 16:17:02');
INSERT INTO `supplier` VALUES (17, '重庆食品批发中心', '刘先生', '13723395555', 283, 1, '2023-05-07 16:15:21', '2023-05-07 16:17:02');
INSERT INTO `supplier` VALUES (18, '重庆时尚男装店', '赵女士', '13866664444', 284, 1, '2023-05-07 16:15:56', '2023-05-07 16:17:07');
INSERT INTO `supplier` VALUES (19, '重庆家具制造厂', '陈先生', '13617623333', 286, 1, '2023-05-07 16:16:23', '2023-05-07 16:17:07');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nick_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'SuperAdmin', '$2a$10$FuGb6TSAV1TzNuYQt6Qau.JPuWNQTXtsrbvPtW.n1JEXGlgVtZDdC', NULL, '2023-04-11 18:31:36', '2023-04-11 18:31:39');
INSERT INTO `user` VALUES (2, 'WarehouseAdmin', '$2a$10$FuGb6TSAV1TzNuYQt6Qau.JPuWNQTXtsrbvPtW.n1JEXGlgVtZDdC', NULL, '2023-04-12 17:41:24', '2023-04-12 17:41:26');

-- ----------------------------
-- Table structure for user_browsing_history
-- ----------------------------
DROP TABLE IF EXISTS `user_browsing_history`;
CREATE TABLE `user_browsing_history`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL,
  `type` tinyint(0) NOT NULL,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_browsing_history
-- ----------------------------
INSERT INTO `user_browsing_history` VALUES (30, 1, 1, '2023-05-07 17:52:51', '2023-05-07 17:52:51');
INSERT INTO `user_browsing_history` VALUES (31, 1, 1, '2023-05-08 10:25:18', '2023-05-08 10:25:18');
INSERT INTO `user_browsing_history` VALUES (32, 1, 2, '2023-05-08 10:25:38', '2023-05-08 10:25:38');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `user_id` int(0) NOT NULL DEFAULT 0,
  `role_id` int(0) NOT NULL DEFAULT 0,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (1, 1, 1, '2023-04-11 19:19:36', '2023-04-11 19:19:39');
INSERT INTO `user_role` VALUES (2, 2, 2, '2023-04-12 17:43:13', '2023-04-12 17:43:16');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `region_id` int(0) NOT NULL DEFAULT 0 COMMENT '仓库地址',
  `manager_id` int(0) NOT NULL DEFAULT 0 COMMENT '负责人',
  `tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '联系电话',
  `rent` decimal(10, 2) NOT NULL DEFAULT 0.00,
  `gmt_create` datetime(0) NOT NULL,
  `gmt_modified` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (45, 274, 27, '021-12345678', 100000.00, '2023-05-07 14:50:05', '2023-05-07 14:53:18');
INSERT INTO `warehouse` VALUES (46, 275, 26, '010-87654321', 80000.00, '2023-05-07 14:50:42', '2023-05-07 14:53:12');
INSERT INTO `warehouse` VALUES (47, 276, 25, '0755-11111111', 150000.00, '2023-05-07 14:51:09', '2023-05-07 14:53:09');
INSERT INTO `warehouse` VALUES (48, 277, 24, '027-22222222', 60000.00, '2023-05-07 14:51:38', '2023-05-07 15:19:41');
INSERT INTO `warehouse` VALUES (49, 278, 23, '0512-33333333', 120000.00, '2023-05-07 14:52:09', '2023-05-07 15:19:37');
INSERT INTO `warehouse` VALUES (50, 75, 28, '0731-44444444', 200000.00, '2023-05-07 14:53:49', '2023-05-07 14:53:49');

SET FOREIGN_KEY_CHECKS = 1;
