package dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import entirys.LearnTasks;

/**
 * 作者：zzx on 2020/10/11 20:54
 * <p>
 * 作用： xxxx
 */
@Dao
public interface AllTaskDao {

    @Insert
    void insertReciteTask(LearnTasks ...learnTasks);

    @Query("SELECT * FROM LearnTasks WHERE book_pos =:bookNum")
    List<LearnTasks> getAllRecite(int bookNum);

}
