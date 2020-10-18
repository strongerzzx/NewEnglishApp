package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/18 23:32
 * <p>
 * 作用： xxxx
 */
@Entity
public class Spells {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "book_pos")
    private int bookPos;

    @ColumnInfo(name = "finish_date")
    private String finishDate;

    @ColumnInfo(name = "is_finish")
    private boolean finish;

    public Spells(int bookPos, String finishDate, boolean finish) {
        this.bookPos = bookPos;
        this.finishDate = finishDate;
        this.finish = finish;
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
