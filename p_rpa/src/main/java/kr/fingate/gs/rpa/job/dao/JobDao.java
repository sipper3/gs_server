package kr.fingate.gs.rpa.job.dao;

import kr.fingate.gs.rpa.job.dto.JobDto;

public interface JobDao {
    public JobDto getNextJob() throws Exception;
}
