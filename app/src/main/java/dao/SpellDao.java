package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entirys.Spells;

/**
 * 作者：zzx on 2020/10/18 23:33
 * <p>
 * 作用： xxxx
 */
@Dao
public interface SpellDao {
    @Insert
    void insertSpell(Spells... spells);

    @Update
    void updateSpell(Spells... spells);

    @Delete
    void deleteSpell(Spells... spells);

    @Query("SELECT * FROM Spells WHERE is_finish=:isFinish AND finish_date=:date AND book_pos=:bookNum")
    List<Spells> queryFinishByDate(boolean isFinish, String date, int bookNum);


    @Query("SELECT * FROM Spells WHERE is_finish=:isFinish AND finish_date=:date AND book_pos=:bookNum AND account=:account")
    List<Spells> queryFinishByDate(boolean isFinish, String date, int bookNum,String account);


    //获取一段范围内的数据 --> 今天之前的数据
    @Query("SELECT * FROM Spells WHERE is_finish=:isFinish AND book_pos=:bookPos  AND finish_date BETWEEN:fromDate and :toDate")
    List<Spells> queryFinishByDate(boolean isFinish, String fromDate, String toDate, int bookPos);

    @Query("SELECT * FROM Spells WHERE is_finish=:isFinish AND book_pos=:bookPos AND account=:account  AND finish_date BETWEEN:fromDate and :toDate")
    List<Spells> queryFinishByDate(boolean isFinish, String fromDate, String toDate, int bookPos,String account);

}
