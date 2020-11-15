package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/18 18:55
 * <p>
 * 作用： xxxx
 */
@Entity
public class Listeners {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "book_pos")
    private int bookPos;

    @ColumnInfo(name = "finish_date")
    private String finishDate;

    @ColumnInfo(name = "is_finish")
    private boolean finish;

    @ColumnInfo(name = "account")
    private String account; //用户

    public Listeners(int bookPos, String finishDate,String account, boolean finish) {
        this.bookPos = bookPos;
        this.finishDate = finishDate;
        this.account= account;
        this.finish = finish;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookPos() {
        return bookPos;
    }

    public void setBookPos(int bookPos) {
        this.bookPos = bookPos;
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
}
