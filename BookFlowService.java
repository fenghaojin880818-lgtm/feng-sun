import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 图书漂流核心业务层
 * 功能：用户登录校验、图书发布、借阅申请、归还图书、防重复借阅、信用分奖惩、逾期判断、书籍筛选
 */
public class BookFlowService {
    private static List<User> userList = new ArrayList<>();
    private static List<Book> bookList = new ArrayList<>();
    private static List<BorrowRecord> recordList = new ArrayList<>();

    // 用户登录校验
    public User userLogin(String username,String pwd){
        for(User u : userList){
            if(u.getUserName().equals(username) && u.getUserPwd().equals(pwd)){
                if("封禁".equals(u.getAccountStatus())){
                    return null;
                }
                return u;
            }
        }
        return null;
    }

    // 发布漂流图书
    public int publishBook(Book book){
        if(book.getFlowDay() < 3 || book.getFlowDay() > 30){
            return 0;
        }
        book.setPublishTime(new Date());
        book.setFlowStatus("可漂流");
        bookList.add(book);
        return 1;
    }

    // 筛选图书（按分类+状态）
    public List<Book> findBookByType(String type,String status){
        List<Book> res = new ArrayList<>();
        for(Book b : bookList){
            if(b.getBookType().equals(type) && b.getFlowStatus().equals(status)){
                res.add(b);
            }
        }
        return res;
    }

    // 核心：借阅图书 + 多重校验
    public String applyBorrow(Integer uid,Integer bid){
        // 1.判断用户账号状态
        User user = getUserById(uid);
        if(user == null || "封禁".equals(user.getAccountStatus())){
            return "借阅失败：账号异常，无法申请漂流";
        }
        // 2.信用分不足拦截
        if(user.getCreditScore() < 30){
            return "借阅失败：信用积分过低，暂时禁止借阅";
        }
        // 3.判断图书状态
        Book book = getBookById(bid);
        if(book == null || !"可漂流".equals(book.getFlowStatus())){
            return "借阅失败：图书暂不可漂流";
        }
        // 4.30天内禁止重复借同一本书
        if(checkRepeatBorrow(uid,bid)){
            return "借阅失败：30天内已借阅本书，禁止重复申请";
        }
        // 5.生成借阅记录
        BorrowRecord record = new BorrowRecord();
        record.setBookId(bid);
        record.setPublishUser(book.getPublishUid());
        record.setBorrowUser(uid);
        record.setBorrowTime(new Date());
        record.setRecordStatus("借阅中");
        recordList.add(record);
        // 修改图书状态
        book.setFlowStatus("已借出");
        book.setBorrowUid(uid);
        return "借阅申请成功，请按时归还图书";
    }

    // 归还图书 + 奖励信用分
    public String returnBook(Integer recordId){
        BorrowRecord record = getRecordById(recordId);
        if(record == null || !"借阅中".equals(record.getRecordStatus())){
            return "归还失败：无有效借阅记录";
        }
        record.setReturnTime(new Date());
        record.setRecordStatus("已完成");
        // 按时归还加分
        User borrowUser = getUserById(record.getBorrowUser());
        borrowUser.setCreditScore(borrowUser.getCreditScore()+15);
        // 恢复图书状态
        Book book = getBookById(record.getBookId());
        book.setFlowStatus("可漂流");
        book.setBorrowUid(null);
        return "归还成功，奖励15点信用积分";
    }

    // 逾期自动扣信用分
    public void autoOverdueDeduct(){
        Date now = new Date();
        for(BorrowRecord r : recordList){
            if("借阅中".equals(r.getRecordStatus())){
                long diff = now.getTime() - r.getBorrowTime().getTime();
                long day = diff / (1000*60*60*24);
                Book b = getBookById(r.getBookId());
                if(day > b.getFlowDay()){
                    User user = getUserById(r.getBorrowUser());
                    user.setCreditScore(user.getCreditScore()-20);
                    if(user.getCreditScore() <= 0){
                        user.setAccountStatus("封禁");
                    }
                }
            }
        }
    }

    // 校验重复借阅
    private boolean checkRepeatBorrow(Integer uid,Integer bid){
        Date now = new Date();
        for(BorrowRecord r : recordList){
            if(r.getBorrowUser().equals(uid) && r.getBookId().equals(bid)){
                long time = now.getTime() - r.getBorrowTime().getTime();
                long days = time / (1000*60*60*24);
                if(days <= 30){
                    return true;
                }
            }
        }
        return false;
    }

    private User getUserById(Integer uid){
        for(User u : userList){
            if(u.getUid().equals(uid)) return u;
        }
        return null;
    }
    private Book getBookById(Integer bid){
        for(Book b : bookList){
            if(b.getBookId().equals(bid)) return b;
        }
        return null;
    }
    private BorrowRecord getRecordById(Integer rid){
        for(BorrowRecord r : recordList){
            if(r.getRecordId().equals(rid)) return r;
        }
        return null;
    }
}
