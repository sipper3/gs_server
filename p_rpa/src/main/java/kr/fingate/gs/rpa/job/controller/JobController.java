package kr.fingate.gs.rpa.job.controller;

import kr.fingate.gs.rpa.job.dto.JobDto;
import kr.fingate.gs.rpa.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/${project.name}/api/job")
@RestController
@RequiredArgsConstructor
public class JobController {
    final JobService jobService;
    @RequestMapping(value="/nextjob", method = {RequestMethod.GET, RequestMethod.POST})
    public JobDto getNextJob() throws Exception {

        return jobService.getNextJob();
    }


}
