package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

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


}
