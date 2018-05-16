--liquibase formatted SQL

--changeset cj:2

INSERT INTO t_role ( f_id, f_enabled, f_create_by, f_name, f_description )
VALUES
	( '8f2a9f6e-e245-4274-8477-cbb033aad5f0', 1, 'SYSTEM', 'ROLE_USER', '普通用户' );
INSERT INTO t_role ( f_id, f_enabled, f_create_by, f_name, f_description )
VALUES
	( 'adfdbf7e-13a5-4d34-939a-2dd44dca16d8', 1, 'SYSTEM', 'ROLE_ADMIN', '管理员' );
INSERT INTO t_user ( f_id, f_login_name, f_password, f_display_name, f_email, f_cell_phone_number, f_status )
VALUES
	( '7691860a-cfb4-4c4a-a8bd-01347c3286be', 'shufork', '$2a$10$rc70Qb.rkeCvmNIs5yHfg.ZWwk7ELma0hBBwe9trWQHmN4p5krH2O', 'my password is "shufork"', 'shufork@outlook.com', '18012345678', 'ACTIVATED' );
INSERT INTO t_api_resource ( f_id, f_create_by, f_enabled, f_api_method, f_api_path )
VALUES
	( '4fb2fa53-854e-41cb-9c8c-2609f8b6eb55', 'SYSTEM', 1, 'GET', '/user/**' );
INSERT INTO t_api_resource ( f_id, f_create_by, f_enabled, f_api_method, f_api_path )
VALUES
	( '3762029e-8413-43b8-b9b3-a7ba99876b2d', 'SYSTEM', 1, 'POST', '/user/**' );
INSERT INTO t_api_permission ( f_id, f_api_ref, f_role_ref )
VALUES
	( '6c66a195-62e5-4279-bda7-22e771053b1e', '4fb2fa53-854e-41cb-9c8c-2609f8b6eb55', '8f2a9f6e-e245-4274-8477-cbb033aad5f0' );
INSERT INTO t_api_permission ( f_id, f_api_ref, f_role_ref )
VALUES
	( 'e1f73aeb-7160-47a1-bc19-87af7acb5873', '3762029e-8413-43b8-b9b3-a7ba99876b2d', '8f2a9f6e-e245-4274-8477-cbb033aad5f0' );
INSERT INTO t_ui_menu_resource ( f_id, f_create_by, f_enabled, f_ui_icon, f_ui_node, f_ui_parent_node, f_ui_text )
VALUES
	( 'c78467fe-26c3-459b-b392-3a9038df122c', 'SYSTEM', 1,'el-menu', 'as-m-user-1', NULL, 'user management' );
INSERT INTO t_ui_menu_resource ( f_id, f_create_by, f_enabled, f_ui_icon, f_ui_node, f_ui_parent_node, f_ui_text )
VALUES
	( '5750f009-8460-4ee2-8154-dda918316401', 'SYSTEM', 1,'el-list', 'as-m-user-1-1', 'as-m-user-1', 'view users' );
INSERT INTO t_ui_menu_permission ( f_id, f_menu_ref, f_role_ref )
VALUES
	( '7b73843c-74f0-4fe8-9d59-8a38b7384cd8', 'c78467fe-26c3-459b-b392-3a9038df122c', '8f2a9f6e-e245-4274-8477-cbb033aad5f0' );
INSERT INTO t_role_permission ( f_id, f_role_ref, f_user_ref )
VALUES
	( 'dbe9e0bf-0840-4177-9a43-c5f0b35c445a', '8f2a9f6e-e245-4274-8477-cbb033aad5f0', '7691860a-cfb4-4c4a-a8bd-01347c3286be' );
INSERT INTO t_role_permission ( f_id, f_role_ref, f_user_ref )
VALUES
	( 'b39f628d-7b2b-42d1-a2df-485265a91402', 'adfdbf7e-13a5-4d34-939a-2dd44dca16d8', '7691860a-cfb4-4c4a-a8bd-01347c3286be' );