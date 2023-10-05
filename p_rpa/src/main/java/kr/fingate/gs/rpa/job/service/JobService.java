package kr.fingate.gs.rpa.job.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.rpa.job.dao.JobDao;
import kr.fingate.gs.rpa.job.dto.JobDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobDao jobDao;

    public JobDto getNextJob() throws Exception {
        return jobDao.getNextJob();
    }

    public Page<JobDto> getJobList() throws Exception {

        PageHelper.startPage(1, 20);
        Page<JobDto> jobList = jobDao.getJobList();

        return jobList;
    }

}
