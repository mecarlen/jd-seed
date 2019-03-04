package com.jd.seed.dictionary.city.rpc;

import java.util.List;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * <pre>
 * RocketMQ监听
 * 
 * </pre>
 * 
 * @author mecarlen 2018年12月26日 上午10:57:11
 */
public class RocketmqListener implements MessageListenerConcurrently {

	@Override
	public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
		for(MessageExt msg:msgs) {
			System.out.println("---msg body----"+new String(msg.getBody()));
		}
		return ConsumeConcurrentlyStatus.RECONSUME_LATER;
	}

}
