package kr.fingate.gs.core.beans.mq.bind;

import lombok.Data;

@Data
public class MQHost {
    private String vHost;
    private String user;
    private String pass;
    private Listener listener;
    private Publisher publisher;
}
