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
//@Entity(foreignKeys = @ForeignKey(entity = ReciteWords.class,
//                                   parentColumns = "id",
//                                   childColumns = "recite_id"),indices = {@Index(value = "id",unique = true),@Index(value = "recite_id")})


@Entity(foreignKeys = {@ForeignKey(
        entity = ReciteWords.class,
        parentColumns = "id",
        childColumns = "recite_id"),
        @ForeignKey(
                entity = Chinese2English.class,
                parentColumns = "id",
                childColumns = "chinese_id"
        )
},indices = {@Index(value = "id",unique = true),@Index(value = "recite_id"),@Index("chinese_id")})
public class LearnTasks {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "recite_id")
    private int reciteID;//背单词任务

    @ColumnInfo(name = "chinese_id")
    private int chineseID;//中选英任务

//    @ColumnInfo(name = "listener_id")
//    private int listenerID;//听音选意

    @ColumnInfo(name = "book_pos")
    private int bookPos;//选择的哪本书

    @ColumnInfo(name = "finish_task")
    private boolean finishtask;


    public LearnTasks() {
    }

    public LearnTasks(int reciteID, int c2eID,int bookPos, boolean finishtask) {
        this.reciteID = reciteID;
        this.chineseID=c2eID;
        this.bookPos = bookPos;
        this.finishtask = finishtask;
    }

//    public LearnTasks(int listenerID, int bookPos, boolean finishtask) {
//        this.listenerID = listenerID;
//        this.bookPos = bookPos;
//        this.finishtask = finishtask;
//    }



//    public int getListenerID() {
//        return listenerID;
//    }
//
//    public void setListenerID(int listenerID) {
//        this.listenerID = listenerID;
//    }

    public int getChineseID() {
        return chineseID;
    }

    public void setChineseID(int chineseID) {
        this.chineseID = chineseID;
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
