package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/17 19:08
 * <p>
 * 作用： xxxx
 */
@Entity
public class Chinese2English {
    @PrimaryKey( autoGenerate = true)
    private int id;

    @ColumnInfo(name = "book_num")
    private int bookNum;

    @ColumnInfo(name = "finish_date")
    private String finishDate;//任务完成时间

    @ColumnInfo(name = "account")
    private String account; //用户

    @ColumnInfo(name = "is_finish")
    private boolean finish;//是否完成任务

    @ColumnInfo(name = "finish_num")
    private int finishNum;//完成的数量

    public Chinese2English(int bookNum, String finishDate, String account,boolean finish, int finishNum) {
        this.bookNum = bookNum;
        this.finishDate = finishDate;
        this.account= account;
        this.finish = finish;
        this.finishNum = finishNum;
    }

    public String getAccount() {
        return account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }
}
