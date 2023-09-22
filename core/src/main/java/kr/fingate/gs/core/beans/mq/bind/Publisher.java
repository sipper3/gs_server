package kr.fingate.gs.core.beans.mq.bind;

import lombok.Data;

@Data
public class Publisher {
	private boolean enabled;
	private boolean confirms;
}
