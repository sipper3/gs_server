package kr.fingate.gs.core.beans.mq.bind;

import lombok.Data;

@Data
public class Retry {
	private boolean enabled;
	private int initialInterval;
	private int maxAttempts;
	private int maxInterval;
	private int multiplier;
}