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

    @Query("SELECT MAX(recite_id) FROM LearnTasks WHERE book_pos=:booNum")
    int getReMaxID(int booNum);//获取最大的ID --> 即为最新的

    @Query("SELECT recite_id FROM LearnTasks WHERE book_pos=:booNum")
    List<Integer> getReAllID(int booNum);//获取最大的ID --> 即为最新的
}
