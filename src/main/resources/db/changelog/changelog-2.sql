--liquibase formatted SQL

--changeset cj:2
INSERT INTO t_role (f_id, f_create_by, f_name)
VALUES
  (
    '8f2a9f6e-e245-4274-8477-cbb033aad5f0',
    'SYSTEM',
    '普通用户'
  );

INSERT INTO t_role (f_id, f_create_by, f_name)
VALUES
  (
    'adfdbf7e-13a5-4d34-939a-2dd44dca16d8',
    'SYSTEM',
    '管理员'
  );

INSERT INTO t_user (
  f_id,
  f_login_name,
  f_password,
  f_display_name,
  f_email,
  f_cell_phone_number,
  f_status
)
VALUES
  (
    '7691860a-cfb4-4c4a-a8bd-01347c3286be',
    'shufork',
    '$2a$10$rc70Qb.rkeCvmNIs5yHfg.ZWwk7ELma0hBBwe9trWQHmN4p5krH2O',
    'my password is "shufork"',
    'shufork@outlook.com',
    '18012345678',
    'REGULAR'
  );

INSERT INTO t_role_authority (f_id, f_role_id, f_user_id)
VALUES
  (
    '0b6dccd7-9b10-4765-8db7-c6c1af452d04',
    'adfdbf7e-13a5-4d34-939a-2dd44dca16d8',
    '7691860a-cfb4-4c4a-a8bd-01347c3286be'
  )