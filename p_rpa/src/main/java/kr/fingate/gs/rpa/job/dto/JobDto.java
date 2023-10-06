package kr.fingate.gs.rpa.job.dto;

import lombok.Data;

@Data
public class JobDto {
    private int jobId;
    private String jobName;
    private int scenId;
    private String scenScript;
    private String uploadFile;      // 업로드된 변환된 파일명
    private String uploadPath;      // 업로드된 Full path
    private String orgFileName;     // 업로드 시의 실제 파일명

    private String tUploadFile;      // 작업 완료된 업로드된 변환된 파일명
    private String tUploadPath;      // 작업 완료된 업로드된 Full path
    private String tOrgFileName;     // 작업 완료된 업로드 시의 실제 파일명

    private int pRate;              // 0 ~ 100
    private int jobCount;
    private int jobState;           // 100: 등록, 101: 대기, 102: 작업중, 103: 작업완료, 201: 다운로드 전, 202: 다운로드 완료, 301:오류발생 후 정지
    private String regId;
    private String startDate;
    private String endDate;
    private String regDate;
    private String updDate;

    // Login
    private String loginScript;
}
