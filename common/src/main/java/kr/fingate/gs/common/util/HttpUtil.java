package kr.fingate.gs.common.util;


import jakarta.servlet.http.HttpServletRequest;
import org.apache.http.HttpResponse;
import org.springframework.http.MediaType;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class HttpUtil {
	private HttpResponse response;
	private String headerName;
	private String headerValue;

	/* ajax */
	public boolean isAjaxRequest(final HttpServletRequest request) {
		String reqHeader = request.getHeader("x-requested-with");
		return reqHeader.equals("XMLHttpRequest");
	}

    /* json */
	public boolean isJsonRequest(final HttpServletRequest request) {
    	String contentType = Optional.ofNullable(request.getContentType()).orElse("");
    	if (contentType.isEmpty()) return false;
		return contentType.contains(MediaType.APPLICATION_JSON.toString());
	}

	/**
	 * 브라우저 종류에 따라 한글을 인코딩하여 리턴
	 * @param filename
	 * @param userAgent
	 * @return
	 * @throws Exception
	 */
	public static String getEncodingFilename(String filename, String userAgent) throws Exception {
		Browser browser = HttpUtil.getBrowser(userAgent);
		String encodedFilename = filename;
		if (browser == Browser.MSIE) {
			encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
		} else if (browser == Browser.EDGE) {
			encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
		} else if (browser == Browser.FIREFOX || browser == Browser.OPERA) {
			encodedFilename = String.format("\"%s\"", new String(filename.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1) );
	    } else if (browser == Browser.CHROME) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < filename.length(); i++) {
				char c = filename.charAt(i);
				if (c > '~') {
					sb.append(URLEncoder.encode("" + c, StandardCharsets.UTF_8));
				} else {
					sb.append(c);
				}

			}
			encodedFilename = sb.toString();
		}

		return encodedFilename;
	}

	/**
	 * 브라우저 종류를 리턴
	 * @param userAgent
	 * @return
	 */
	public static Browser getBrowser(String userAgent) {
		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			return Browser.MSIE;
		} else if (userAgent.contains("Edge")) {
			return Browser.EDGE;
		} else if (userAgent.contains("Chrome")) {
			return Browser.CHROME;
		} else if (userAgent.contains("Opera")) {
			return Browser.OPERA;
		} else if (userAgent.contains("Firefox")) {
			return Browser.FIREFOX;
		}
		return Browser.ETC;
	}

	enum Browser {
		MSIE, EDGE, CHROME, OPERA, FIREFOX, ETC
	}
}
