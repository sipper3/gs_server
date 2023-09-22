package kr.fingate.gs.core.beans.mq.recover;

import org.springframework.amqp.core.Message;

public interface MQRecoverService {
	public void doRecover(Message message, Throwable cause);
}
