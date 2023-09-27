package kr.fingate.gs.rpa.job.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.rpa.job.dto.JobDto;

public interface JobDao {
    public JobDto getNextJob() throws Exception;

    public Page<JobDto> getJobList() throws Exception;
}
