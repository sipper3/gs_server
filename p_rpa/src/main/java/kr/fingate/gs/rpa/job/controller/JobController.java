package kr.fingate.gs.rpa.job.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.comon.dto.file.FileDto;
import kr.fingate.gs.rpa.job.dto.JobDto;
import kr.fingate.gs.rpa.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import kr.fingate.gs.comon.util.FileUtil;

import java.util.List;
import java.util.Map;

@RequestMapping("/${project.name}/api/job")
@RestController
@RequiredArgsConstructor
public class JobController {

    static final Logger logger = LoggerFactory.getLogger(JobController.class);

    final JobService jobService;
    final FileUtil fileUtil;

    @Value("${project.name}")
    private String SYSTEM_DIR;


    @RequestMapping(value="/nextjob", method = {RequestMethod.GET, RequestMethod.POST})
    public JobDto getNextJob() throws Exception {
        logger.info(SYSTEM_DIR);
        return jobService.getNextJob();
    }

    @PostMapping(value = "/upload")

    public List<FileDto> excelUpload(MultipartHttpServletRequest request,
                                           @ModelAttribute JobDto regJob,
                                           HttpServletResponse response) throws Exception {

        String destPath = SYSTEM_DIR;
        return fileUtil.uploadFiles(request, destPath);
    }

}
