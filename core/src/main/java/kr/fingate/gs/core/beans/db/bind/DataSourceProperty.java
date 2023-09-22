package kr.fingate.gs.core.beans.db.bind;

import kr.fingate.gs.core.beans.db.config.DBConfig;
import lombok.Data;

import java.util.Map;

@Data
public class DataSourceProperty {
    private String name = "";
    private String basePackage = "";
    private String baseMapper = "";
    private String aopExecution = "";
    private Map<String, String> dataSource;

    public String getName(DBConfig.BEAN_TYPE beanType) {
        return this.name.concat(beanType.getSuffix());
    }
}
