package kr.fingate.gs.core.beans.db.config;

import kr.fingate.gs.core.beans.db.bind.DataSourceProperty;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.core.env.Environment;

import java.util.ArrayList;

public class DataSourceList extends ArrayList<DataSourceProperty> {
    public static DataSourceList init(Environment environment, String prefix) {
        return Binder.get(environment).bind(prefix, DataSourceList.class).get();
    }
}
