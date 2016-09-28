package test.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TestConsumer {
	public static void main(String[] args) throws Exception {
		// 构造ConnectionFactory实例对象
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://192.168.110.110:61616");
		// 构造从工厂得到连接对象
		Connection connection = connectionFactory.createConnection();
		//connection.setClientID("TestID");//持久化订阅
		// 启动连接
		connection.start();
		// 获取连接会话
		Session session = connection.createSession(Boolean.FALSE,
				Session.AUTO_ACKNOWLEDGE);
		// 获取发送目的地,参数为broker的一个队列queue或者topic名称
		Destination destination = session.createQueue("TestQueues");
		//Destination destination = session.createTopic("TestTopics");
		MessageConsumer consumer = session.createConsumer(destination);
		//MessageConsumer consumer = session.createDurableSubscriber((Topic)destination, "TestID");
		while (true) {
			//设置接收者接收消息的时间为10s
			//TextMessage message = (TextMessage) consumer.receiveNoWait();
			TextMessage message = (TextMessage) consumer.receive(10000);
			if (null != message) {
				System.out.println("收到消息" + message.getText());
				message.acknowledge();//确认消息
			} /*else {
				break;
			}*/
			System.out.println("接收消息中...");
		}
		//System.out.println("接收消息完毕!");
		/*consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				try {
					System.out.println("收到消息" + ((TextMessage) message).getText());
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		});
		}*/
		//session.close();//关闭会话
		//connection.close();//关闭连接
	}
}
