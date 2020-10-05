package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import entirys.WordClips;

/**
 * 作者：zzx on 2020/10/5 18:27
 * <p>
 * 作用： xxxx
 */
@Dao
public interface WordClipDao {

    @Insert
    void insertWords(WordClips... clips);

    @Update
    void updateWords(WordClips... clips);

    @Delete
    void deleteWords(WordClips... clips);

    @Query("SELECT * FROM WordClips WHERE words_pos =:bookNum")
    List<WordClips> getSameNumWords(int bookNum);


    @Query("SELECT * FROM WordClips ORDER BY words_pos DESC")
    List<WordClips> getAllWords();
}
