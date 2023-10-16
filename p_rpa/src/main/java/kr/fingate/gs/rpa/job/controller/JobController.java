package kr.fingate.gs.rpa.job.controller;

import jakarta.servlet.http.HttpServletResponse;

import kr.fingate.gs.common.dto.PageInfoDto;
import kr.fingate.gs.core.aop.exception.BizException;
import kr.fingate.gs.core.aop.response.ResponseCode;
import kr.fingate.gs.core.dto.file.FileDto;
import kr.fingate.gs.core.util.FileUtil;
import kr.fingate.gs.rpa.job.dto.JobDto;
import kr.fingate.gs.rpa.job.dto.ScenarioDto;
import kr.fingate.gs.rpa.job.dto.SearchJobDto;
import kr.fingate.gs.rpa.job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    public JobDto getNextJob(@RequestParam int jobState) throws Exception {
        JobDto jobDto = new JobDto();
        jobDto.setJobState(jobState);
//        throw new BizException(ResponseCode.FILE_NOT_ALLOW, "파일은 내 멋데로");
        return jobService.getJob(jobDto);
    }

    @RequestMapping(value="/list", method = {RequestMethod.GET, RequestMethod.POST})
    public PageInfoDto<JobDto> getJobList(@RequestBody SearchJobDto searchJob) throws Exception {
        return new PageInfoDto<>(jobService.getJobList(searchJob));
    }

    @RequestMapping(value="/scenlist", method = {RequestMethod.GET, RequestMethod.POST})
    public List<ScenarioDto> getScenarioList() throws Exception {
        return jobService.getScenarioList();
    }

    @PostMapping(value = "/ins")
    public int insJob(MultipartHttpServletRequest request,
                                     @ModelAttribute JobDto regJob,
                                     HttpServletResponse response) throws Exception {
        return jobService.insJob(request, regJob);

    }

    @PostMapping(value = "/terminate")
    public int terminateJob(MultipartHttpServletRequest request,
                      @ModelAttribute JobDto regJob,
                      HttpServletResponse response) throws Exception {
        return jobService.terminateJob(request, regJob);

    }

    @RequestMapping(value="/upd/state", method = {RequestMethod.GET, RequestMethod.POST})
    public int updJobState(@RequestBody JobDto jobDto) throws Exception {
        return jobService.updJobStatus(jobDto);
    }

    @RequestMapping(value="/upd", method = {RequestMethod.GET, RequestMethod.POST})
    public int updJob(MultipartHttpServletRequest request,
                      @ModelAttribute JobDto jobDto,
                      HttpServletResponse response) throws Exception {
        return jobService.updJob(request, jobDto);
    }

    @RequestMapping(value="/del", method = {RequestMethod.GET, RequestMethod.POST})
    public int delJob(@RequestBody JobDto jobDto) throws Exception {
        return jobService.delJob(jobDto.getJobId());
    }
    @PostMapping(value = "/upload")
    public List<FileDto> excelUpload(MultipartHttpServletRequest request,
                                     @ModelAttribute JobDto regJob,
                                     HttpServletResponse response) throws Exception {

        String destPath = SYSTEM_DIR;
        return fileUtil.uploadFiles(request, destPath);
    }

    @RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView excelDownload(int jobId) throws Exception {

        JobDto jobDto = new JobDto();
        jobDto.setJobId(jobId);
        jobDto = jobService.getJob(jobDto);
        FileDto fileDto = new FileDto();
        if (jobDto != null) {
            fileDto.setFileName(jobDto.getUploadFile());
            fileDto.setFilePath(jobDto.getUploadPath());
            fileDto.setOriginFileName(jobDto.getOrgFileName());
        }
        return new ModelAndView("downloadView", "downloadFile", fileDto);
    }

    @RequestMapping(value = "/terminate/download", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView excelTerminateDownload(int jobId) throws Exception {

        JobDto jobDto = new JobDto();
        jobDto.setJobId(jobId);
        jobDto = jobService.getJob(jobDto);
        FileDto fileDto = new FileDto();
        if (jobDto != null) {
            fileDto.setFileName(jobDto.getTUploadFile());
            fileDto.setFilePath(jobDto.getTUploadPath());
            fileDto.setOriginFileName(jobDto.getOrgFileName());
        }
        return new ModelAndView("downloadView", "downloadFile", fileDto);
    }

}
