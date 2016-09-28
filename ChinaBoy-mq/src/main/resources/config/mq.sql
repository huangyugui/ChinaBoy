--activemq_acks：用于存储持久化订阅关系DurableTopic。
CREATE TABLE `activemq_acks` (
  `CONTAINER` varchar(250) NOT NULL,--消息的Destination
  `SUB_DEST` varchar(250) DEFAULT NULL,--如果是使用Static集群，这个字段会有集群其他系统的信息
  `CLIENT_ID` varchar(250) NOT NULL,--每个订阅者都必须有一个唯一的客户端ID用以区分
  `SUB_NAME` varchar(250) NOT NULL,--订阅者名称
  `SELECTOR` varchar(250) DEFAULT NULL,--选择器，可以选择只消费满足条件的消息。条件可以用自定义属性实现，可支持多属性AND和OR操作
  `LAST_ACKED_ID` bigint(20) DEFAULT NULL,--记录消费过的消息的ID
  `PRIORITY` bigint(20) NOT NULL DEFAULT '5',--消息优先级别
  PRIMARY KEY (`CONTAINER`,`CLIENT_ID`,`SUB_NAME`,`PRIORITY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--在集群环境中才有用，只有一个Broker可以获得消息，称为Master Broker，其他的只能作为备份等待Master Broker不可用，
--才可能成为下一个Master Broker。这个表用于记录哪个Broker是当前的Master Broker
CREATE TABLE `activemq_lock` (
  `ID` bigint(20) NOT NULL,
  `TIME` bigint(20) DEFAULT NULL,
  `BROKER_NAME` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--activemq_msgs：用于存储消息，Queue和Topic都存储在这个表中。
CREATE TABLE `activemq_msgs` (
  `ID` bigint(20) NOT NULL,--自增的数据库主键
  `CONTAINER` varchar(250) DEFAULT NULL,--消息的Destination
  `MSGID_PROD` varchar(250) DEFAULT NULL,--消息发送者客户端的主键
  `MSGID_SEQ` bigint(20) DEFAULT NULL,--是发送消息的顺序，MSGID_PROD+MSG_SEQ可以组成JMS的MessageID 
  `EXPIRATION` bigint(20) DEFAULT NULL,--消息的过期时间，存储的是从1970-01-01到现在的毫秒数
  `MSG` blob,--消息本体的Java序列化对象的二进制数据
  `PRIORITY` bigint(20) DEFAULT NULL,--优先级，从0-9，数值越大优先级越高
  PRIMARY KEY (`ID`),
  KEY `ACTIVEMQ_MSGS_MIDX` (`MSGID_PROD`,`MSGID_SEQ`),
  KEY `ACTIVEMQ_MSGS_CIDX` (`CONTAINER`),
  KEY `ACTIVEMQ_MSGS_EIDX` (`EXPIRATION`),
  KEY `ACTIVEMQ_MSGS_PIDX` (`PRIORITY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;