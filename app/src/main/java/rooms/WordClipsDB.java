package rooms;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import bases.BaseAppciation;
import commonparms.Commons;
import dao.WordClipDao;
import entirys.WordClips;

/**
 * 作者：zzx on 2020/10/5 18:28
 * <p>
 * 作用： xxxx
 */
@Database(entities = WordClips.class,version = 1,exportSchema = false)
public abstract class WordClipsDB  extends RoomDatabase {
    public abstract WordClipDao getClipsDao();

    private static WordClipsDB sDB;

    public static WordClipsDB getDB() {
        if (sDB==null){
            synchronized (WordClipsDB.class){
                if (sDB==null){
                    sDB= Room.databaseBuilder(BaseAppciation.getContext(),WordClipsDB.class, Commons.DB_TYPE_WORDSCLIPS)
                            .build();
                }
            }
        }
        return sDB;
    }
}
