package th.ac.kmutt.research.form;

import java.io.Serializable;

import th.ac.kmutt.research.xstream.common.Paging;

public class CommonForm implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -716798430048396259L;
    private String mode;
    private String command;
    private String keySearch;
    private String docsAssign;
    private Paging paging;

    private int pageCount;
    private int pageStart;
    private int pageEnd;
    private int pageNo = 1;
    private String tab;
    private String[] ids;
    private String filter;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }

    public CommonForm() {
        paging = new Paging();
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getCommand() {
        return command;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKeySearch() {
        return keySearch;
    }

    public void setKeySearch(String keySearch) {
        this.keySearch = keySearch;
    }

    public String getDocsAssign() {
        return docsAssign;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public void setDocsAssign(String docsAssign) {
        this.docsAssign = docsAssign;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

}
