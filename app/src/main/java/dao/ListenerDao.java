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
}
