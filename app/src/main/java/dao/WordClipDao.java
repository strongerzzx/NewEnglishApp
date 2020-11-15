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

    @Query("SELECT * FROM WordClips WHERE words_pos =:bookNum AND account =:account")
    List<WordClips> getSameNumWords(int bookNum,String account);

    @Query("SELECT * FROM WordClips ORDER BY words_pos DESC")
    List<WordClips> getAllWords();


    //获取所有为true并且是本书的收藏夹
    @Query("SELECT * FROM WORDCLIPS WHERE words_pos =:bookNum AND collection =:iscollection")
    List<WordClips> getSameNumWords(int bookNum,boolean iscollection);


    //查询单词夹是否已经存在
    @Query("SELECT title FROM WORDCLIPS WHERE  words_pos =:bookNum AND collection =:iscollection AND account=:username")
    List<String> judgeTitleExists(int bookNum,boolean iscollection,String username);

}
