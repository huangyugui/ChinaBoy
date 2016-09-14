package test.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestProducer {

	public static void main(String[] args) throws Exception {
		// 构造ConnectionFactory实例对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://192.168.110.110:61616");
		// 构造从工厂得到连接对象
		Connection connection = connectionFactory.createConnection();
		// 启动连接
		connection.start();
		// 获取连接会话
		Session session = connection.createSession(Boolean.TRUE,
				Session.AUTO_ACKNOWLEDGE);
		// 获取发送目的地,参数为broker的一个队列queue或者topic名称
		Destination destination = session.createQueue("TestQueues");
		//Destination destination = session.createTopic("TestTopics");
		// 得到消息生成者即发送者
		MessageProducer producer = session.createProducer(destination);
		// 消息默认持久化,这里设置不持久化
		producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		producer.setPriority(4);//优先级别0-9,9最大,默认4,但是不一定能够优先传递
		producer.setTimeToLive(5);//消息过期时间(毫秒),过期后自动销毁
		for (int i = 1; i <= 5; i++) {
			TextMessage message = session.createTextMessage("ActiveMq 发送的消息"
					+ i);
			// 发送消息到目的地方
			System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
			producer.send(message);
		}
		session.commit();// 事务开启时,只有commit消息才能发送到broker
		session.close();//关闭会话
		connection.close();//关闭连接
	}

}
