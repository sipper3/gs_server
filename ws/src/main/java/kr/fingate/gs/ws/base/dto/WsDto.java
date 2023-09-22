package kr.fingate.gs.ws.base.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WsDto {
    private long cnslNo;
    private long custNo;
}
