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

    @Query("select last_insert_rowid() from RECITEWORDS " )
    int getNewID();


}
