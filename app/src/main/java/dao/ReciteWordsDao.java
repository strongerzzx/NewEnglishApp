package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entirys.ReciteWords;

/**
 * 作者：zzx on 2020/10/11 20:26
 * <p>
 * 作用： xxxx
 */
@Dao
public interface ReciteWordsDao {
    @Insert
    void insertRecite(ReciteWords ...reciteWords);

    @Update
    void updateRecite(ReciteWords... reciteWords);

    @Delete
    void deleteRecite(ReciteWords ...reciteWords);

    @Query("SELECT * FROM RECITEWORDS WHERE book_pos =:bookNum")
    List<ReciteWords> getAllRecite(int bookNum);


    @Query("SELECT finish_date FROM RECITEWORDS WHERE book_pos=:bookNum AND id=:reId")
    String getNewDate(int bookNum,int reId);

    @Query("SELECT * FROM ReciteWords WHERE is_finish=:isFinish AND finish_date=:date AND book_pos=:bookNum")
    List<ReciteWords> queryFinishByDate(boolean isFinish,String date,int bookNum);

    //获取一段范围内的数据 --> 今天之前的数据
    @Query("SELECT * FROM ReciteWords WHERE is_finish=:isFinish AND book_pos=:bookPos  AND finish_date BETWEEN:fromDate and :toDate")
    List<ReciteWords> queryFinishByDate(boolean isFinish, String fromDate, String toDate, int bookPos);
}
