package kr.fingate.gs.core.beans.mq.config;

import kr.fingate.gs.core.beans.mq.bind.MQHost;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

import java.util.ArrayList;

public class MQHostList extends ArrayList<MQHost> {

    public static MQHostList init(Environment env, String prefix) {
        return Binder.get(env).bind(prefix, MQHostList.class).get();
    }
}
