package rooms;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import bases.BaseAppciation;
import beans.ContentUrl;
import dao.AllTaskDao;
import dao.Chinese2EnglishDao;
import dao.ListenerDao;
import dao.ReciteWordsDao;
import dao.SpellDao;
import entirys.Chinese2English;
import entirys.LearnTasks;
import entirys.Listeners;
import entirys.ReciteWords;
import entirys.Spells;

/**
 * 作者：zzx on 2020/10/11 20:32
 * <p>
 * 作用： xxxx
 */
@Database(entities = {ReciteWords.class, Chinese2English.class, Listeners.class, Spells.class,LearnTasks.class},version = 7,exportSchema = false)
public abstract class AllTaskDB extends RoomDatabase {

    public abstract AllTaskDao getLearnTaskDao();
    public abstract ReciteWordsDao getReciteWordsDao();
    public abstract Chinese2EnglishDao getChinese2EnglishDao();
    public abstract ListenerDao getListenerDao();
    public abstract SpellDao getSpellDao();

    private static AllTaskDB sAllTaskDB;

    public static AllTaskDB getAllTaskDB() {
        if (sAllTaskDB==null){
            synchronized (AllTaskDB.class){
                if (sAllTaskDB==null){
                    sAllTaskDB= Room.databaseBuilder(BaseAppciation.getContext(),AllTaskDB.class, ContentUrl.DB_NAME_TASK)
                            .fallbackToDestructiveMigration()
                            //.addMigrations(MIGRATION_2_3)
                            .build();
                }
            }
        }
        return sAllTaskDB;
    }

    public static final Migration MIGRATION_2_3 =new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE RECITEWORDS ADD finish_time int ");
            database.execSQL("ALTER TABLE RECITEWORDS ADD finishMostTimes int ");
        }
    };
}
