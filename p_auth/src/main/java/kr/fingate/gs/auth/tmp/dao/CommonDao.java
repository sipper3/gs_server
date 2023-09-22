package kr.fingate.gs.auth.tmp.dao;

import kr.fingate.gs.auth.tmp.dto.SystemDto;

import java.util.List;

public interface CommonDao {


	/**
	 * 로그인한 사용자의 접속 가능한 시스템 목록을 조회
	 *
	 * @author 
	 * @since 2023.09.11.
	 */
	List<SystemDto> selAuthSystem(Long userNo) throws Exception;

}
