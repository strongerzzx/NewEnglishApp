package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import beans.DateTemps;

public
/**
 * 作者：zzx on 2020/11/21 20:56
 *  作用： xxxx
 */
@Dao
interface TjDateDao {
    @Insert
    void insertDate(DateTemps... dateTemps);

    @Delete
    void delteDate(DateTemps... dateTemps);

    @Update
    void updateDate(DateTemps... dateTemps);

    @Query("SELECT tongji_date_temp FROM DATETEMPS WHERE book_pos=:bookPos AND account=:account ")
    List<String> queryDate(int bookPos, String account);
}
