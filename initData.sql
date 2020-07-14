#insertTableApp
INSERT INTO `oit_app` VALUES (1, '壹站平台端', 'd0a911d882c94afdb6dc7d90dc8c538a', '1a1351068f72474995354c18348eb87d', '1', NULL, NULL, NULL, NULL);
INSERT INTO `oit_app` VALUES (2, '壹站三方端', 'cebf5835e76e4540b841f65713539775', '5a6866318a57497880cc7e5551b6576d', '1', NULL, NULL, NULL, NULL);
INSERT INTO `oit_app` VALUES (3, '壹站服务商端', 'f63013e13bd146bb90e69fa44e826037', '2bbbdc93209e446ca9e4cdb820e46bc1', '1', NULL, NULL, NULL, NULL);
INSERT INTO `oit_app` VALUES (4, '壹站承运商端', '03f3719d7e27438c8df59585d524d978', 'b51f9fb796094e2f8a294246acf1ca0e', '1', NULL, NULL, NULL, NULL);
#insertTableRole
INSERT INTO `oit_role` VALUES (1, '平台端管理员', 'PLATFORM_ADMIN', '1', 1, '壹站（上海）供应链管理有限公司', '1', NULL, NULL, NULL, NULL);
#insertTableRoleResource
INSERT INTO `oit_role_resource` VALUES (1, 1, 'menu_pt_010101,menu_pt_010102,menu_pt_010103,menu_pt_010104', '1', NULL, NULL, NULL, NULL);
INSERT INTO `oit_role_resource` VALUES (2, 1, 'button_pt_010101,button_pt_010102,button_pt_010103,button_pt_010104,button_pt_010105,button_pt_010106,button_pt_010107', '2', NULL, NULL, NULL, NULL);
#insertTableUser
INSERT INTO `oit_user` VALUES (1, 'admin', '$2a$10$GQkoRtVsyq84iGdpLxT/w.6ryUcZN51QNmbSeCZHHgI0.BrisUsDC', 'guff', NULL, NULL, NULL, NULL, NULL, NULL, 1, '壹站（上海）供应链管理有限公司', '1', '1', NULL, NULL, NULL, NULL);
#insertTableUserResource
INSERT INTO `oit_user_resource` VALUES (1, 1, '1,2,3,4,5', '1', NULL, NULL, NULL, NULL);
#insertTableUserRoleRel
INSERT INTO `oit_user_role_rel` VALUES (1, 1, 1, NULL, NULL, NULL, NULL);
#insertTableResource
#INSERT INTO `oit_resource` VALUES (1, 1, '数字订单', 'menu_010101', 1, NULL, 0, 1, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (2, 1, '数字运单', 'menu_010102', 1, NULL, 0, 2, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (3, 1, '运单列表', 'menu_010103', 1, NULL, 2, 1, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (4, 1, '回单管理', 'menu_010104', 1, NULL, 2, 2, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (5, 1, '运单导出', 'menu_010105', 1, NULL, 2, 3, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (6, 1, '数字调度', 'menu_010106', 1, NULL, 0, 3, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (7, 1, '数字运营', 'menu_010107', 1, NULL, 0, 4, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (8, 1, '导出', 'button_010101', 2, NULL, 3, 1, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (9, 1, '打印', 'button_010102', 2, NULL, 3, 2, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (10, 1, '回单', 'button_010103', 2, NULL, 3, 3, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (11, 1, '导出', 'button_010104', 2, NULL, 4, 1, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (12, 1, '打印', 'button_010105', 2, NULL, 4, 2, 1, NULL, NULL, NULL, NULL);
#INSERT INTO `oit_resource` VALUES (13, 1, '回单', 'button_010106', 2, NULL, 4, 3, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (1, 1, '系统管理', 'menu_pt_010101', 1, NULL, 0, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (2, 1, '组织架构', 'menu_pt_010102', 1, '/orgStructure', 1, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (3, 1, '角色管理', 'menu_pt_010103', 1, '/roleMgt', 1, 3, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (4, 1, '用户管理', 'menu_pt_010104', 1, '/userList', 1, 4, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (5, 1, '新增', 'button_pt_010101', 2, NULL, 3, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (6, 1, '修改', 'button_pt_010102', 2, NULL, 3, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (7, 1, '分配用户', 'button_pt_010103', 2, NULL, 3, 3, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (8, 1, '新增', 'button_pt_010104', 2, NULL, 4, 4, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (9, 1, '修改', 'button_pt_010105', 2, NULL, 4, 5, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (10, 1, '启用/停用', 'button_pt_010106', 2, NULL, 4, 6, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (11, 1, '重置密码', 'button_pt_010107', 2, NULL, 4, 7, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (12, 2, '系统管理', 'menu_sf_010101', 1, NULL, 0, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (13, 2, '角色管理', 'menu_sf_010102', 1, NULL, 12, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (14, 2, '用户管理', 'menu_sf_010103', 1, NULL, 12, 3, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (15, 2, '新增', 'button_sf_010101', 2, NULL, 13, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (16, 2, '修改', 'button_sf_010102', 2, NULL, 13, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (17, 2, '分配用户', 'button_sf_010103', 2, NULL, 13, 3, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (18, 2, '新增', 'button_sf_010104', 2, NULL, 14, 4, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (19, 2, '修改', 'button_sf_010105', 2, NULL, 14, 5, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (20, 2, '启用/停用', 'button_sf_010106', 2, NULL, 14, 6, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (21, 2, '重置密码', 'button_sf_010107', 2, NULL, 14, 7, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (22, 4, '系统管理', 'menu_yl_010101', 1, NULL, 0, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (23, 4, '角色管理', 'menu_yl_010102', 1, NULL, 22, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (24, 4, '用户管理', 'menu_yl_010103', 1, NULL, 22, 3, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (25, 4, '新增', 'button_yl_010101', 2, NULL, 23, 1, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (26, 4, '修改', 'button_yl_010102', 2, NULL, 23, 2, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (27, 4, '分配用户', 'button_yl_010103', 2, NULL, 23, 3, 1, NULL, NULL, NULL, NULL);

INSERT INTO `oit_resource` VALUES (28, 4, '新增', 'button_yl_010104', 2, NULL, 24, 4, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (29, 4, '修改', 'button_yl_010105', 2, NULL, 24, 5, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (30, 4, '启用/停用', 'button_yl_010106', 2, NULL, 24, 6, 1, NULL, NULL, NULL, NULL);
INSERT INTO `oit_resource` VALUES (31, 4, '重置密码', 'button_yl_010107', 2, NULL, 24, 7, 1, NULL, NULL, NULL, NULL);