package kr.fingate.gs.comon.util;

import kr.fingate.gs.comon.consts.CommonConst;
import kr.fingate.gs.comon.dto.file.FileDto;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {
    static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    @Value("${file.baseDir}")
    private String BASE_DIR;

    @Value("${file.not_allowed_file}")
    private String NOT_ALLOWED_FILE;

    private FileDto saveFile(MultipartFile mpf, String destPath) throws Exception {
        String targetPath = FilenameUtils.normalize(BASE_DIR + CommonConst.FILE_SEPARATOR + destPath);
        String originalFilename = FilenameUtils.getName(mpf.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(originalFilename);

        FileDto fileDto = new FileDto();
        fileDto.setFileName(newFileName);
        fileDto.setFileFieldName(mpf.getName());
        fileDto.setFileSize(mpf.getSize());
        fileDto.setFileExt(FilenameUtils.getExtension(originalFilename).toLowerCase());
        try {
            File dest_dir = new File(targetPath);
            if(!dest_dir.exists()) {
                dest_dir.mkdirs();
            }

            Path path = Paths.get(targetPath + CommonConst.FILE_SEPARATOR + newFileName);
            byte[] bytes = mpf.getBytes();
            Files.write(path, bytes);

            fileDto.setFilePath(targetPath);
            fileDto.setOriginFileName(originalFilename);
            fileDto.setStatus(CommonConst.FILE_SUCCESS_UPLOAD);

        } catch (IOException e) {
            logger.error("saveFile failed \n" + e.getMessage());
        }

        return fileDto;
    }

    // 등록불가 파일 형식 체크
    private boolean isNotAllowed(MultipartFile mpf) throws Exception {
        return NOT_ALLOWED_FILE.contains(FilenameUtils.getExtension(mpf.getOriginalFilename()).toLowerCase());
    }

    /*
        destPath : BASE_DIR 하위에 각 시스템 별 UPLOAD 할 디렉토리를 전달 EX) rpa/public/clientNo
     */
    public List<FileDto> uploadFiles(MultipartHttpServletRequest request, String destPath) throws Exception {
        Iterator<String> itrFiles = request.getFileNames();
        List<FileDto> files = new LinkedList<>();

        while(itrFiles.hasNext()) {
            MultipartFile file = request.getFile(itrFiles.next());
            if(file != null && !file.isEmpty()) {
                FileDto fileDto = new FileDto();
                if(this.isNotAllowed(file)) {
                    fileDto.setStatus(CommonConst.FILE_NOT_ALLOWED);
                } else {
                    fileDto = this.saveFile(file, destPath);
                }
                files.add(fileDto);
            }
        }

        return files;
    }
}
