package kr.fingate.gs.common.external.file;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
public class FileMeta extends BaseObject {

	private String fileName;
	private String fileFieldName;
	private String fileUrl;
	private long fileSize;
	private String fileType;
	private boolean isNewLine;

	private String filePath;
	private String orgnFileName;
	private String fileFomt;
	private File file;

	private boolean isAllowed = true;
	private boolean isMaxUploadSizeExceeded = false;

	// 처리상태 1: 정상, -8:최대 허용 사이즈 초과, -9:허용되지 않는 파일형식
	private int status = 0;
	private String message;

	public void setFileType(String fileType) {
		this.fileType = fileType;
		this.fileFomt = fileType;
	}

	public void setStatus(int status) {
		this.status = status;
		this.isAllowed = status != -9;
		this.isMaxUploadSizeExceeded = status == -8;
	}

}
