package kr.fingate.gs.rpa.job.service;

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
}
