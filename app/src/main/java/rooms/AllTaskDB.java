package rooms;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import bases.BaseAppciation;
import beans.ContentUrl;
import dao.ReciteWordsDao;
import entirys.LearnTasks;
import entirys.ReciteWords;

/**
 * 作者：zzx on 2020/10/11 20:32
 * <p>
 * 作用： xxxx
 */
@Database(entities = {ReciteWords.class, LearnTasks.class},version = 1,exportSchema = false)
public abstract class AllTaskDB extends RoomDatabase {

    public abstract ReciteWordsDao getReciteWordsDao();

    private static AllTaskDB sAllTaskDB;

    public static AllTaskDB getAllTaskDB() {
        if (sAllTaskDB==null){
            synchronized (AllTaskDB.class){
                if (sAllTaskDB==null){
                    sAllTaskDB= Room.databaseBuilder(BaseAppciation.getContext(),AllTaskDB.class, ContentUrl.DB_NAME_TASK)
                            .build();
                }
            }
        }
        return sAllTaskDB;
    }
}
