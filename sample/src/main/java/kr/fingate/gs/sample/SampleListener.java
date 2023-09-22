package kr.fingate.gs.sample;

import com.rabbitmq.client.Channel;
import kr.fingate.gs.core.beans.mq.config.MQConfig;
import lombok.RequiredArgsConstructor;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SampleListener {

	private static final String OUT_CTRT_UPD_QUEUE_NAME = "q.contract.salsmgmt.updctrt.out";
	private static final String PREFIX_CONTAINERFACTORY = "contract";

	/*
	 * 전송 받을 queue name 설정
	 * 연결 vhost명으로 containerFactory 설정
	 */
	@RabbitListener(queues = OUT_CTRT_UPD_QUEUE_NAME, containerFactory = PREFIX_CONTAINERFACTORY + MQConfig.MQ_LISTENER_BEAN)
	public void receiveMessage(final JSONObject jsonObj, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception{

		try {


		} catch (Exception e) {
			// TODO Auto-generated catch block

		} finally {
			// 오류 발생시에도 응답처리

		}
	}
}
