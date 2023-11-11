/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
                            `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                            `menu_name` varchar,
                            `path` varchar,
                            `component` varchar,
                            `visible` INTEGER DEFAULT 0,
                            `status` INTEGER DEFAULT 0,
                            `perms` varchar ,
                            `icon` varchar ,
                            `create_by` INTEGER DEFAULT NULL,
                            `create_time` varchar DEFAULT (datetime('now', 'localtime')),
                            `update_by` varchar DEFAULT (datetime('now', 'localtime')),
                            `update_time` varchar DEFAULT (datetime('now', 'localtime')),
                            `del_flag` INTEGER DEFAULT 0,
                            `remark` varchar
);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
                            `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                            `name` varchar,
                            `role_key` varchar,
                            `status` INTEGER DEFAULT 0,
                            `del_flag` INTEGER DEFAULT 0,
                            `create_by` INTEGER
                                `create_time` varchar DEFAULT (datetime('now', 'localtime')),
                            `update_by` INTEGER,
                            `update_time` varchar DEFAULT (datetime('now', 'localtime')),
                            `remark` varchar
);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;


CREATE TABLE "user" (
                        "id" INTEGER PRIMARY KEY AUTOINCREMENT,
                        "username" varchar NOT NULL,
                        "email" varchar,
                        "password" varchar NOT NULL,
                        "login_time" varchar DEFAULT (datetime('now', 'localtime')),
                        "create_time" varchar DEFAULT (datetime('now', 'localtime')),
                        "update_time" varchar DEFAULT (datetime('now', 'localtime')),
                        UNIQUE ("username" ASC),
                        UNIQUE ("email" ASC)
);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
                                 `role_id` INTEGER NOT NULL,
                                 `menu_id` INTEGER NOT NULL DEFAULT 0,
                                 PRIMARY KEY (`role_id`,`menu_id`)
);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
                            `id` INTEGER PRIMARY KEY AUTOINCREMENT,
                            `user_name` varchar,
                            `nick_name` varchar,
                            `password` varchar,
                            `status` INTEGER DEFAULT 0,
                            `email` varchar,
                            `phonenumber` varchar,
                            `sex` INTEGER,
                            `avatar` varchar,
                            `user_type` INTEGER NOT NULL DEFAULT 0,
                            `create_by` INTEGER,
                            `create_time` varchar DEFAULT (datetime('now', 'localtime')),
                            `update_by` INTEGER,
                            `update_time` varchar DEFAULT (datetime('now', 'localtime')),
                            `del_flag` INTEGER DEFAULT 0
);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
                                 `user_id` INTEGER NOT NULL,
                                 `role_id` INTEGER NOT NULL DEFAULT 0,
                                 PRIMARY KEY (`user_id`,`role_id`)
);