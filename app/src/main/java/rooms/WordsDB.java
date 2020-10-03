package rooms;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import dao.WordsDao;
import entirys.Words;

/**
 * 作者：zzx on 2020/10/2 11:18
 * <p>
 * 作用： xxxx
 */

@Database(entities = Words.class,version = 1,exportSchema = false)
public abstract class WordsDB extends RoomDatabase {
    public abstract WordsDao getWordsDao();

}
