--liquibase formatted SQL

--changeset cj:2
INSERT INTO t_user ( f_id, f_login_name, f_password, f_display_name, f_email, f_mobile, f_status )
VALUES
	( '7691860a-cfb4-4c4a-a8bd-01347c3286be', 'shufork', '$2a$10$rc70Qb.rkeCvmNIs5yHfg.ZWwk7ELma0hBBwe9trWQHmN4p5krH2O', 'my password is shufork', 'shufork@outlook.com', '18012345678', 'ACTIVE' );
--changeset cj:3

-- roles
INSERT INTO t_role ( f_id,f_type,f_enabled, f_create_by, f_name, f_description )
VALUES ( '8f2a9f6e-e245-4274-8477-cbb033aad5f0', 'SYSTEM', 1, 'SYSTEM', 'ROLE_USER', '普通用户' );

INSERT INTO t_role ( f_id,f_type,f_enabled, f_create_by, f_name, f_description )
VALUES ( 'adfdbf7e-13a5-4d34-939a-2dd44dca16d8','SYSTEM', 1, 'SYSTEM', 'ROLE_ADMIN', '管理员' );

-- api entries
INSERT INTO t_api_resource ( f_id,f_scope,f_name, f_create_by, f_enabled, f_api_method, f_api_path )
VALUES ( '4fb2fa53-854e-41cb-9c8c-2609f8b6eb55','USER','ANY', 'SYSTEM', 1, 'GET', '/user/**' );

INSERT INTO t_api_resource ( f_id,f_scope,f_name, f_create_by, f_enabled, f_api_method, f_api_path )
VALUES ( '3762029e-8413-43b8-b9b3-a7ba99876b2d','USER','ANY', 'SYSTEM', 1, 'POST', '/user/**' );
-- api to role
INSERT INTO t_api_permission ( f_id, f_api_ref, f_role_ref )
VALUES ( '6c66a195-62e5-4279-bda7-22e771053b1e', '4fb2fa53-854e-41cb-9c8c-2609f8b6eb55', '8f2a9f6e-e245-4274-8477-cbb033aad5f0' );

INSERT INTO t_api_permission ( f_id, f_api_ref, f_role_ref )
VALUES ( 'e1f73aeb-7160-47a1-bc19-87af7acb5873', '3762029e-8413-43b8-b9b3-a7ba99876b2d', '8f2a9f6e-e245-4274-8477-cbb033aad5f0' );

-- menus
INSERT INTO t_ui_menu_resource ( f_id, f_create_by, f_enabled, f_ui_page, f_ui_node, f_ui_parent_node, f_ui_text )
VALUES ( 'c78467fe-26c3-459b-b392-3a9038df122c', 'SYSTEM', 1,'/admin/user', 'menu-1', NULL, 'menu-1' );

INSERT INTO t_ui_menu_resource ( f_id, f_create_by, f_enabled, f_ui_page, f_ui_node, f_ui_parent_node, f_ui_text )
VALUES ( '5750f009-8460-4ee2-8154-dda918316401', 'SYSTEM', 1,'/user/list1', 'menu-1-1', 'menu-1', 'menu-1-1' );

INSERT INTO t_ui_menu_resource ( f_id, f_create_by, f_enabled, f_ui_page, f_ui_node, f_ui_parent_node, f_ui_text )
VALUES ( 'c6274051-c30d-48cb-bcc1-354f337251f7', 'SYSTEM', 1,'/user/list2', 'menu-1-2', 'menu-1', 'menu-1-2' );

INSERT INTO t_ui_menu_resource ( f_id, f_create_by, f_enabled, f_ui_page, f_ui_node, f_ui_parent_node, f_ui_text )
VALUES ( '718c1070-9b47-4d2e-a047-9f5612bd1673', 'SYSTEM', 1,'/admin/user', 'menu-2', NULL, 'menu-2' );

INSERT INTO t_ui_menu_resource ( f_id, f_create_by, f_enabled, f_ui_page, f_ui_node, f_ui_parent_node, f_ui_text )
VALUES ( 'de3ae610-57f8-4efc-a9ec-37d5750c7658', 'SYSTEM', 1,'/user/list1', 'menu-2-1', 'menu-2', 'menu-2-1' );
-- menu to role
INSERT INTO t_ui_menu_permission(f_id,f_menu_ref,f_role_ref)
VALUES('b5a1407a-dec4-4d46-9835-37b66461f23f','c78467fe-26c3-459b-b392-3a9038df122c','8f2a9f6e-e245-4274-8477-cbb033aad5f0');

INSERT INTO t_ui_menu_permission(f_id,f_menu_ref,f_role_ref)
VALUES('94e928f3-2af5-4895-be8c-8bdb13e63314','5750f009-8460-4ee2-8154-dda918316401','8f2a9f6e-e245-4274-8477-cbb033aad5f0');

INSERT INTO t_ui_menu_permission(f_id,f_menu_ref,f_role_ref)
VALUES('e8e0f1c6-607c-4395-bb94-329d55a77dd8','c6274051-c30d-48cb-bcc1-354f337251f7','8f2a9f6e-e245-4274-8477-cbb033aad5f0');

INSERT INTO t_ui_menu_permission(f_id,f_menu_ref,f_role_ref)
VALUES('fa77ccde-ec3b-4ff9-8541-b274dd86a135','718c1070-9b47-4d2e-a047-9f5612bd1673','8f2a9f6e-e245-4274-8477-cbb033aad5f0');

INSERT INTO t_ui_menu_permission(f_id,f_menu_ref,f_role_ref)
VALUES('ff76dd6d-06be-4dec-8dfd-80e4e1326ff0','de3ae610-57f8-4efc-a9ec-37d5750c7658','8f2a9f6e-e245-4274-8477-cbb033aad5f0');
-- role to user
INSERT INTO t_role_permission ( f_id, f_role_ref, f_user_ref )
VALUES ( 'dbe9e0bf-0840-4177-9a43-c5f0b35c445a', '8f2a9f6e-e245-4274-8477-cbb033aad5f0', '7691860a-cfb4-4c4a-a8bd-01347c3286be' );

INSERT INTO t_role_permission ( f_id, f_role_ref, f_user_ref )
VALUES ( 'b39f628d-7b2b-42d1-a2df-485265a91402', 'adfdbf7e-13a5-4d34-939a-2dd44dca16d8', '7691860a-cfb4-4c4a-a8bd-01347c3286be' );

