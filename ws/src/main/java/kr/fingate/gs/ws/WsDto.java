package kr.fingate.gs.ws;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WsDto {
    private String id;
    private String name;
}
