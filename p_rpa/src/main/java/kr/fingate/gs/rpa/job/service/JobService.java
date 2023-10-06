package kr.fingate.gs.rpa.job.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import kr.fingate.gs.core.dto.file.FileDto;
import kr.fingate.gs.core.util.FileUtil;
import kr.fingate.gs.rpa.job.controller.JobController;
import kr.fingate.gs.rpa.job.dao.JobDao;
import kr.fingate.gs.rpa.job.dto.JobDto;
import kr.fingate.gs.rpa.job.dto.ScenarioDto;
import kr.fingate.gs.rpa.job.dto.SearchJobDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {
    static final Logger logger = LoggerFactory.getLogger(JobService.class);
    final FileUtil fileUtil;

    @Value("${project.name}")
    private String SYSTEM_DIR;
    private final JobDao jobDao;

    public JobDto getJob(JobDto jobDto) throws Exception {
        return jobDao.getJob(jobDto);
    }

    public Page<JobDto> getJobList(SearchJobDto searchJob) throws Exception {

        PageHelper.startPage(1, 20);

        return jobDao.getJobList(searchJob);
    }


    public int insJob(MultipartHttpServletRequest request, JobDto jobDto) throws Exception {
        setUploadFile(request, jobDto);
        return jobDao.insJob(jobDto);
    }

    public int updJob(MultipartHttpServletRequest request, JobDto jobDto) throws Exception{
        setUploadFile(request, jobDto);
        return jobDao.updJob(jobDto);
    }

    private void setUploadFile(MultipartHttpServletRequest request, JobDto jobDto) throws Exception {
        List<FileDto> files = fileUtil.uploadFiles(request, SYSTEM_DIR);

        if(!files.isEmpty()) {
            FileDto file = files.get(0);
            jobDto.setUploadFile(file.getFileName());
            jobDto.setUploadPath(file.getFilePath());
            jobDto.setOrgFileName(file.getOriginFileName());

        }
    }

    public int delJob(long jobId) throws Exception{
        return jobDao.delJob(jobId);
    }

    public int terminateJob(MultipartHttpServletRequest request, JobDto jobDto) throws Exception {
        String destPath = SYSTEM_DIR;
        List<FileDto> files = fileUtil.uploadFiles(request, destPath);

        int updCnt = 0;
        if(!files.isEmpty()) {
            FileDto file = files.get(0);
            jobDto.setTUploadFile(file.getFileName());
            jobDto.setTUploadPath(file.getFilePath());
            jobDto.setTOrgFileName(file.getOriginFileName());
            updCnt = jobDao.updJobTerminate(jobDto);
        }
        return updCnt;
    }

    public int updJobStatus(JobDto jobDto) throws Exception {
        return jobDao.updJobStatus(jobDto);
    }
    public List<ScenarioDto> getScenarioList() throws Exception {

        return jobDao.getScenarioList();
    }

}
