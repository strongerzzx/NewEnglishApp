package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/2 11:14
 * <p>
 * 作用： xxxx
 */
@Dao
public interface WordsDao {

    @Insert
    void insertWords(Words... words);

    @Update
    void updateWords(Words... words);

    @Delete
    void deleteWords(Words... words);

    @Query("DELETE FROM WORDS")
    void deleteAllWords();

    @Query("SELECT * FROM WORDS ORDER BY bookpos DESC")
    List<Words> getAllWords();

    @Query("SELECT * FROM WORDS WHERE bookpos =:bookNum")
    List<Words> getSameNumWords(int bookNum);

    @Query("SELECT * FROM WORDS WHERE head_word LIKE '%' || :keyword || '%' AND bookpos=:booknum")
    List<Words> getSearchResultWords(int booknum,String keyword);

    @Query("SELECT count(*) FROM WORDS WHERE is_collection =:isCollection AND collection_pos =:collPos")
    int getTrueWords(boolean isCollection,int collPos);

}
