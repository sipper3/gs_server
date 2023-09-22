package kr.fingate.gs.rpa.job.dto;

import lombok.Data;

@Data
public class JobDto {
    private int jobId;
    private int scenId;
    private String scenScript;
    private String uploadFile;
    private int pRate;              // 0 ~ 100
    private int jobCount;
    private int jobState;           // 100: 등록, 101: 대기, 102: 작업중, 103: 작업완료, 201: 다운로드 전, 202: 다운로드 완료, 301:오류발생 후 정지
    private String startDate;
    private String endDate;
    private String regDate;
    private String updDate;
}
