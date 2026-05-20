import java.util.Date;

/**
 * 图书借阅流转记录实体
 * 记录借阅、归还、逾期、评价信息
 */
public class BorrowRecord {
    private Integer recordId;
    private Integer bookId;
    private Integer publishUser;
    private Integer borrowUser;
    private Date borrowTime;
    private Date returnTime;
    private String recordStatus;
    private String evaluate;
    private Integer starScore;

    public BorrowRecord(){}

    public Integer getRecordId() {
        return recordId;
    }

    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(Integer publishUser) {
        this.publishUser = publishUser;
    }

    public Integer getBorrowUser() {
        return borrowUser;
    }

    public void setBorrowUser(Integer borrowUser) {
        this.borrowUser = borrowUser;
    }

    public Date getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Date borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getEvaluate() {
        return evaluate;
    }

    public void setEvaluate(String evaluate) {
        this.evaluate = evaluate;
    }

    public Integer getStarScore() {
        return starScore;
    }

    public void setStarScore(Integer starScore) {
        this.starScore = starScore;
    }
}
