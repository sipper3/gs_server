package kr.fingate.gs.common.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageSerializable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PageInfoDto<T> extends PageSerializable<T> {
    public static final int DEFAULT_NAVIGATE_PAGES = 8;
    public static final PageInfoDto EMPTY = new PageInfoDto(Collections.emptyList(), 0);
    /**
     * 현재 페이지
     */
    private int pageNum;
    /**
     * 페이지당 수량
     */
    private              int      pageSize;
    /**
     * 현재 페이지 수
     */
    private              int      size;

    /**
     * startRow와 endRow는 일반적으로 사용되지 않으므로 구체적인 사용법은 다음과 같습니다.
     * 페이지에서 "startRow부터 endRow까지의 전체 크기 데이터를 표시"할 수 있습니다.
     * 데이터베이스에 있는 현재 페이지의 첫 번째 요소의 행 번호
     */
    @JsonIgnore
    private long startRow;
    /**
     * 데이터베이스에 있는 현재 페이지의 마지막 요소의 행 번호
     */
    @JsonIgnore
    private long endRow;
    /**
     * 총 페이지
     */
    @JsonIgnore
    private int pages;
    /**
     * 이전 페이지
     */
    @JsonIgnore
    private int prePage;
    /**
     * 다음 페이지
     */
    @JsonIgnore
    private int nextPage;

    /**
     * 첫 페이지인지 여부
     */
    @JsonIgnore
    private boolean isFirstPage = false;
    /**
     * 마지막 페이지인지 여부
     */
    @JsonIgnore
    private boolean isLastPage = false;
    /**
     * 이전 페이지가 있는지 여부
     */
    @JsonIgnore
    private boolean hasPreviousPage = false;
    /**
     * 다음 페이지가 있는지 여부
     */
    @JsonIgnore
    private boolean hasNextPage = false;

    /**
     * 탐색 페이지 수
     */
    @JsonIgnore
    private int navigatePages;
    /**
     * 모든 탐색 페이지 번호
     */
    @JsonIgnore
    private int[] navigatepageNums;
    /**
     * 네비게이션 바의 첫 번째 페이지
     */
    @JsonIgnore
    private int navigateFirstPage;
    /**
     * 네비게이션 바의 마지막 페이지
     */
    @JsonIgnore
    private int navigateLastPage;

    public PageInfoDto() {
    }

    /**
     * 패키지 Page 개체
     *
     * @param list
     */
    public PageInfoDto(List<? extends T> list) {
        this(list, DEFAULT_NAVIGATE_PAGES);
    }

    /**
     * 패키지 Page 개체
     *
     * @param list          page 결과
     * @param navigatePages 페이지 수
     */
    public PageInfoDto(List<? extends T> list, int navigatePages) {
        super(list);
        if (list instanceof Page) {
            Page page = (Page) list;
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();

            this.pages = page.getPages();
            this.size = page.size();
            //由于结果是>startRow的，所以实际的需要+1
            if (this.size == 0) {
                this.startRow = 0;
                this.endRow = 0;
            } else {
                this.startRow = page.getStartRow() + 1;
                //计算实际的endRow（最后一页的时候特殊）
                this.endRow = this.startRow - 1 + this.size;
            }
        } else if (list instanceof Collection) {
            this.pageNum = 1;
            this.pageSize = list.size();

            this.pages = this.pageSize > 0 ? 1 : 0;
            this.size = list.size();
            this.startRow = 0;
            this.endRow = list.size() > 0 ? list.size() - 1 : 0;
        }
        if (list instanceof Collection) {
            calcByNavigatePages(navigatePages);
        }
    }

    public static <T> PageInfoDto<T> of(List<? extends T> list) {
        return new PageInfoDto<T>(list);
    }

    public static <T> PageInfoDto<T> of(List<? extends T> list, int navigatePages) {
        return new PageInfoDto<T>(list, navigatePages);
    }

    /**
     * 빈 Pageinfo 개체를 반환합니다.
     *
     * @return
     */
    public static <T> PageInfoDto<T> emptyPageInfo() {
        return EMPTY;
    }

    public void calcByNavigatePages(int navigatePages) {
        setNavigatePages(navigatePages);
        //计算导航页
        calcNavigatepageNums();
        //计算前后页，第一页，最后一页
        calcPage();
        //判断页面边界
        judgePageBoudary();
    }

    /**
     * 네비게이션 페이지 계산하기
     */
    private void calcNavigatepageNums() {
        //총 페이지 수가 탐색 페이지 수보다 작거나 같을 때
        if (pages <= navigatePages) {
            navigatepageNums = new int[pages];
            for (int i = 0; i < pages; i++) {
                navigatepageNums[i] = i + 1;
            }
        } else { //총 페이지 수가 탐색 페이지 수보다 많을 때
            navigatepageNums = new int[navigatePages];
            int startNum = pageNum - navigatePages / 2;
            int endNum = pageNum + navigatePages / 2;

            if (startNum < 1) {
                startNum = 1;
                //(이전 navigatePages 페이지
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            } else if (endNum > pages) {
                endNum = pages;
                //마지막 navigatePages 페이지
                for (int i = navigatePages - 1; i >= 0; i--) {
                    navigatepageNums[i] = endNum--;
                }
            } else {
                //모든 중간 페이지
                for (int i = 0; i < navigatePages; i++) {
                    navigatepageNums[i] = startNum++;
                }
            }
        }
    }

    /**
     * 계산전후페이지,첫페이지,마지막페이지
     */
    private void calcPage() {
        if (navigatepageNums != null && navigatepageNums.length > 0) {
            navigateFirstPage = navigatepageNums[0];
            navigateLastPage = navigatepageNums[navigatepageNums.length - 1];
            if (pageNum > 1) {
                prePage = pageNum - 1;
            }
            if (pageNum < pages) {
                nextPage = pageNum + 1;
            }
        }
    }

    /**
     * 페이지 경계 판정
     */
    private void judgePageBoudary() {
        isFirstPage = pageNum == 1;
        isLastPage = pageNum == pages || pages == 0;
        hasPreviousPage = pageNum > 1;
        hasNextPage = pageNum < pages;
    }

    /**
     * 내용 포함 여부
     */
    public boolean hasContent() {
        return this.size > 0;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public long getStartRow() {
        return startRow;
    }

    public void setStartRow(long startRow) {
        this.startRow = startRow;
    }

    public long getEndRow() {
        return endRow;
    }

    public void setEndRow(long endRow) {
        this.endRow = endRow;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPrePage() {
        return prePage;
    }

    public void setPrePage(int prePage) {
        this.prePage = prePage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isIsFirstPage() {
        return isFirstPage;
    }

    public void setIsFirstPage(boolean isFirstPage) {
        this.isFirstPage = isFirstPage;
    }

    public boolean isIsLastPage() {
        return isLastPage;
    }

    public void setIsLastPage(boolean isLastPage) {
        this.isLastPage = isLastPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }

    public int getNavigateFirstPage() {
        return navigateFirstPage;
    }

    public int getNavigateLastPage() {
        return navigateLastPage;
    }

    public void setNavigateFirstPage(int navigateFirstPage) {
        this.navigateFirstPage = navigateFirstPage;
    }

    public void setNavigateLastPage(int navigateLastPage) {
        this.navigateLastPage = navigateLastPage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PageInfo{");
        sb.append("pageNum=").append(pageNum);
        sb.append(", pageSize=").append(pageSize);
        sb.append(", size=").append(size);
        sb.append(", startRow=").append(startRow);
        sb.append(", endRow=").append(endRow);
        sb.append(", total=").append(total);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append(", prePage=").append(prePage);
        sb.append(", nextPage=").append(nextPage);
        sb.append(", isFirstPage=").append(isFirstPage);
        sb.append(", isLastPage=").append(isLastPage);
        sb.append(", hasPreviousPage=").append(hasPreviousPage);
        sb.append(", hasNextPage=").append(hasNextPage);
        sb.append(", navigatePages=").append(navigatePages);
        sb.append(", navigateFirstPage=").append(navigateFirstPage);
        sb.append(", navigateLastPage=").append(navigateLastPage);
        sb.append(", navigatepageNums=");
        if (navigatepageNums == null) {
            sb.append("null");
        } else {
            sb.append('[');
            for (int i = 0; i < navigatepageNums.length; ++i) {
                sb.append(i == 0 ? "" : ", ").append(navigatepageNums[i]);
            }
            sb.append(']');
        }
        sb.append('}');
        return sb.toString();
    }
}
