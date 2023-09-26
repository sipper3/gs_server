package kr.fingate.gs.rpa.job.controller;

import jakarta.servlet.http.HttpServletResponse;

import kr.fingate.gs.core.dto.file.FileDto;
import kr.fingate.gs.core.util.FileUtil;
import kr.fingate.gs.rpa.job.dto.JobDto;
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

    @RequestMapping(value = "/download", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView excelDownload(int jobId) throws Exception {

        String destPath = SYSTEM_DIR;
        FileDto fileDto = new FileDto();
        fileDto.setFileName("edade2f2-5030-4e78-b5ca-e80e29f6d40a.xlsx");
        fileDto.setFilePath("C:\\home\\fingate\\files\\rpa");
        fileDto.setOriginFileName("testfile.xlsx");

        return new ModelAndView("downloadView", "downloadFile", fileDto);
    }

}
