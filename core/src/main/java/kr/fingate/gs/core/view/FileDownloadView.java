package kr.fingate.gs.core.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.fingate.gs.core.aop.exception.BizException;
import kr.fingate.gs.core.aop.response.ResponseCode;
import kr.fingate.gs.core.dto.file.FileDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * File download view
 */
@Slf4j
@Component("downloadView")
public class FileDownloadView extends AbstractView {
	final static Logger logger = LoggerFactory.getLogger(FileDownloadView.class);

	public static final String CONTENT_TYPE = "apllication/download; charset=UTF-8";

    public FileDownloadView() {
		setContentType(CONTENT_TYPE);
    }

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FileDto fileInfo = (FileDto)model.get("downloadFile");

		String saveFileName = (String) fileInfo.getFileName();
    	File file = new File(fileInfo.getFilePath()  + File.separator + saveFileName);
    	String originFileName = (String) fileInfo.getOriginFileName();

		response.setContentType(getContentType());
		response.setContentLength((int) file.length());
		String fileName = new String(originFileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName+"\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
        InputStream is = null;

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			throw new BizException(ResponseCode.FILE_NOT_FOUND);
		} finally {
			if(fis != null) {
				try {
					fis.close();
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		out.flush();
    }

}
