package kr.fingate.gs.sample.base.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleDto {
    private long cnslNo;
    private long custNo;
}
