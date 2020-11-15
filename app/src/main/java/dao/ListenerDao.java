package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entirys.Listeners;

/**
 * 作者：zzx on 2020/10/18 18:58
 * <p>
 * 作用： xxxx
 */
@Dao
public interface ListenerDao {
    @Insert
    void insertListener(Listeners... listeners);

    @Update
    void updateListener(Listeners... listeners);

    @Delete
    void deleteListener(Listeners... listeners);

    @Query("SELECT * FROM Listeners WHERE book_pos=:bookPos")
    List<Listeners> getAllListeners(int bookPos);

    @Query("SELECT * FROM Listeners WHERE is_finish=:isFinish AND finish_date=:date AND book_pos=:bookPos")
    List<Listeners> queryFinishByDate(boolean isFinish,String date,int bookPos);

    @Query("SELECT * FROM Listeners WHERE is_finish=:isFinish AND finish_date=:date AND book_pos=:bookPos AND account=:account")
    List<Listeners> queryFinishByDate(boolean isFinish,String date,int bookPos,String account);

    //获取一段范围内的数据 --> 今天之前的数据
    @Query("SELECT * FROM Listeners WHERE is_finish=:isFinish AND book_pos=:bookPos  AND finish_date BETWEEN:fromDate and :toDate")
    List<Listeners> queryFinishByDate(boolean isFinish, String fromDate, String toDate, int bookPos);

    @Query("SELECT * FROM Listeners WHERE is_finish=:isFinish AND book_pos=:bookPos AND account=:account AND finish_date BETWEEN:fromDate and :toDate")
    List<Listeners> queryFinishByDate(boolean isFinish, String fromDate, String toDate, int bookPos,String account);
}
