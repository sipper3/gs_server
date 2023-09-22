package kr.fingate.gs.core.beans.mq.bind;

import lombok.Data;

@Data
public class Listener {
	private int concurrentConsumers;
	private String acknowledgeMode;
	private Retry retry;

}
