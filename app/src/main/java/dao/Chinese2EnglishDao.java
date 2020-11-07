package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entirys.Chinese2English;

/**
 * 作者：zzx on 2020/10/17 19:10
 * <p>
 * 作用： xxxx
 */
@Dao
public interface Chinese2EnglishDao {
    @Insert
    void insertChinese(Chinese2English...chinese);

    @Update
    void updateChinese(Chinese2English... chinese);

    @Delete
    void deleteChinese(Chinese2English ...chinese);

    //查询当前日期下 --> 完成的情况 --> 有无数据
//    @Query("SELECT * FROM Chinese2English WHERE is_finish=:isFinish AND finish_date=:date AND book_num=:bookPos")
//    int queryFinishByDate(boolean isFinish,String date,int bookPos);


    //获取当天的数据
    @Query("SELECT * FROM Chinese2English WHERE is_finish=:isFinish AND finish_date=:date AND book_num=:bookPos")
    List<Chinese2English> queryFinishByDate(boolean isFinish, String date, int bookPos);


    //获取一段范围内的数据 --> 今天之前的数据
    @Query("SELECT * FROM Chinese2English WHERE is_finish=:isFinish AND book_num=:bookPos  AND finish_date BETWEEN:fromDate and :toDate")
    List<Chinese2English> queryFinishByDate(boolean isFinish, String fromDate, String toDate,int bookPos);
}
