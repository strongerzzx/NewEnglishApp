package beans;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public
/**
 * 作者：zzx on 2020/11/20 15:34
 *  作用： xxxx
 */
@Entity
class DateTemps  {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "tongji_date_temp")
    private String date;

    @ColumnInfo(name = "account")
    private String account;

    @ColumnInfo(name = "book_pos")
    private int bookPos;

    public DateTemps(String date, String account, int bookPos) {
        this.date = date;
        this.account = account;
        this.bookPos = bookPos;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getBookPos() {
        return bookPos;
    }

    public void setBookPos(int bookPos) {
        this.bookPos = bookPos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

