package kr.fingate.gs.sample.base.dao;

import kr.fingate.gs.sample.base.dto.SampleDto;

public interface BaseDao {
    SampleDto getSelectOne() throws Exception;
}
