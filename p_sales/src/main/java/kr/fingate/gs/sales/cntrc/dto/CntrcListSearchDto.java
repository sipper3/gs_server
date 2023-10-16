package kr.fingate.gs.sales.cntrc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.fingate.gs.common.annotation.Encrypt;
import kr.fingate.gs.common.annotation.Info;
import kr.fingate.gs.common.vo.PageVO;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Getter
@Setter
@Alias("CntrcListSearchDto")
public class CntrcListSearchDto extends PageVO {

    @Info(value="등록일 from")
    private String regStartDate;

    @Info(value="등록일 to")
    private String regEndDate;

    @Info(value="계약일 from")
    private String cntrcStartDate;

    @Info(value="계약일 to")
    private String cntrcEndDate;

    @Info(value="계약번호")
    private String cntrcNo;

    @Info(value="증권번호")
    private String policyNo;

    @Info(value="계약상태(환수계약상태)")
    private String finalRdmptCntrcState;

    @JsonIgnore
    @Info(value="계약상태(세부유형)")
    private List<String> ctomyClassDtl;

    @Info(value="청약상태")
    private String sbscrState;

    @Info(value="판매방식")
    private String saleMethod;

    @JsonIgnore
    @Info(value="조직")
    private List<String> orgnzNoList;

    @Info(value="담당자명")
    private String rspnsUserName;

    @JsonIgnore
    @Info(value = "상품구분")
    private List<String> prdctClass;

    @JsonIgnore
    @Info(value = "상품종류")
    private List<String> prdctKind;

    @JsonIgnore
    @Info(value = "보험사")
    private List<String> insrrCode;

    @Info(value="자필서명")
    private String hndwrState;

    /*
     @JsonIgnore
    @Info(value="담당자 조직번호(리스트)")
    private List<Long> rspnsOrgnzNoList;

    @Info(value="담당자")
    private long rspnsUserNo;

    @Info(value="조회 등급")
    private String searchGrade;
    */

    @Info(value="정렬대상")
    private String order;

    @Info(value="정렬방식")
    private String sort;


    @Info(value="빠른검색 사용여부")
    private String quickSearchYn;

    @Info(value="빠른검색 검색 구분" +
            "name : 보험대상자 전체" +
            "c0Name : 계약자" +
            "p1Name : 피보험자" +
            "etc : 증권번호/보험사설계번호/이메일 전체" +
            "policyNo : 증권번호" +
            "insrrDesignNo : 보험사설계번호" +
            "email : 이메일" +
            "idntn6Dgt : 주민번호 앞6자리" +
            "tlphnNo : 자택전화번호" +
            "clphnNo : 휴대전화" +
            "idntnNo : 주민번호" +
            "phoneNo4Dgt : 휴대전화/자택전화 4자리" +
            "cntrcNo : 계약번호")
    private String quickSearchKey;

    @Info(value="빠른검색 키워드")
    private String quickSearchValue;

    @Encrypt
    @Info(value="빠른검색 키워드 암호화")
    private String quickSearchValueEncrypt;













}
