--数据库中的两个表users,authorites必须完全按照脚本所示来定义,也就是说表的名字不能修改
--users表必须包含username,password,enabled字段,这三个字段是绝对不能少的,也不能修改类型.另外enabled一定要为1才能登录
--authorities表必须包含username字段,这个字段引用users的username作为外键,authority字段就是角色的名字,
--角色名字必须满足ROLE_XXX的格式(例如:ROLE_ADMIN,ROLE_USER,ROLE_MAMAGER)
--如果一个用户有多个角色,不要将多个角色放在一起用逗号隔开.而是每个角色定义一条记录(例如:abu有ROLE_ADMIN,ROLE_USER两个角色,
--那么应该定义两条记录: 一条为abu, ROLE_USER,另一条为abu, ROLE_ADMIN.而不是只有一条:abu, ROLE_ADMIN,ROLE_USER)
--可以给authorities表添加一个id字段作为主键
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ix_auth_username` (`username`,`authority`),
  CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--spring security remember me token table
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` datetime NOT NULL,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;