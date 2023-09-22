package kr.fingate.gs.core.beans.mq.bind;

import lombok.Data;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

@Data
public class MQServer {
    private String addresses;

    public static MQServer init(Environment env, String prefix) {
        return Binder.get(env).bind(prefix, MQServer.class).get();
    }
}
