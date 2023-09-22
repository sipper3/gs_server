package kr.fingate.gs.ws.base.dao;

import kr.fingate.gs.ws.base.dto.WsDto;

public interface BaseDao {
    WsDto getSelectOne() throws Exception;
}
