package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/11 20:48
 * <p>
 * 作用： 全部任务的关系表
 */
@Entity(foreignKeys = @ForeignKey(entity = ReciteWords.class,
                                   parentColumns = "id",
                                   childColumns = "recite_id"),indices = {@Index(value = "id",unique = true),@Index(value = "recite_id")})
public class LearnTasks {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "recite_id")
    private int reciteID;

    @ColumnInfo(name = "book_pos")
    private int bookPos;//选择的哪本书

    @ColumnInfo(name = "finish_task")
    private boolean finishtask;


    public LearnTasks(int reciteID, int bookPos, boolean finishtask) {
        this.reciteID = reciteID;
        this.bookPos = bookPos;
        this.finishtask = finishtask;
    }

    public boolean isFinishtask() {
        return finishtask;
    }

    public void setFinishtask(boolean finishtask) {
        this.finishtask = finishtask;
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

    public int getReciteID() {
        return reciteID;
    }

    public void setReciteID(int reciteID) {
        this.reciteID = reciteID;
    }
}
