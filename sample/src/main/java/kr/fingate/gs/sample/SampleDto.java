package kr.fingate.gs.sample;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SampleDto {
    private String id;
    private String name;
}
