package kr.fingate.gs.rpa.job.dao;

import com.github.pagehelper.Page;
import kr.fingate.gs.rpa.job.dto.JobDto;
import kr.fingate.gs.rpa.job.dto.ScenarioDto;
import kr.fingate.gs.rpa.job.dto.SearchJobDto;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.util.List;

public interface JobDao {
    public JobDto getJob(JobDto jobDto) throws Exception;

    public Page<JobDto> getJobList(SearchJobDto searchJob) throws Exception;
    public List<ScenarioDto> getScenarioList() throws Exception;

    public int insJob(JobDto jobDto) throws Exception;
    public int updJob(JobDto jobDto) throws Exception;
    public int delJob(long jobId) throws Exception;

    public int updJobStatus(JobDto jobDto) throws Exception;

    public int updJobTerminate(JobDto jobDto) throws Exception;
}
