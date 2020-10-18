package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/11 20:22
 * <p>
 * 作用： 背单词数据库
 */
@Entity
public class ReciteWords {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "book_pos")
    private int bookPos;//选择的哪本书

    @ColumnInfo(name = "finish_num")
    private int finishNum;//完成的数量

    @ColumnInfo(name = "finish_date")
    private String finishDate;//任务完成时间

    @ColumnInfo(name = "finish_time")
    private int finishTime;//完成次数

    @ColumnInfo(name = "finish_most_times")
    private int finishMostTimes;//完成上限

    @ColumnInfo(name = "is_finish")
    private boolean finish;//是否完成任务

    @ColumnInfo(name = "finish_input")
    private String finishInput;//完成感想

    public ReciteWords(int bookPos,int finishNum ,String finishDate,int finishTime,int finishMostTimes, boolean finish, String finishInput) {
        this.bookPos = bookPos;
        this.finishNum=finishNum;
        this.finishDate = finishDate;
        this.finishTime=finishTime;
        this.finishMostTimes=finishMostTimes;
        this.finish = finish;
        this.finishInput = finishInput;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public int getFinishMostTimes() {
        return finishMostTimes;
    }

    public void setFinishMostTimes(int finishMostTimes) {
        this.finishMostTimes = finishMostTimes;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
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

    public String getFinishInput() {
        return finishInput;
    }

    public void setFinishInput(String finishInput) {
        this.finishInput = finishInput;
    }
}
