package rooms;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import bases.BaseAppciation;
import beans.ContentUrl;
import dao.WordClipDao;
import dao.WordsDao;
import entirys.WordClips;
import entirys.Words;

/**
 * 作者：zzx on 2020/10/2 11:18
 * <p>
 * 作用： xxxx
 */

@Database(entities = {Words.class, WordClips.class},version = 8,exportSchema = false)
public abstract class WordsDB extends RoomDatabase {
    public abstract WordsDao getWordsDao();
    public abstract WordClipDao getWordClipDao();

    private static WordsDB sWordsDB;

    public static WordsDB getWordsDB() {
        if (sWordsDB==null){
            synchronized (WordsDB.class){
                if (sWordsDB==null){
                    sWordsDB= Room.databaseBuilder(BaseAppciation.getContext(), WordsDB.class, ContentUrl.DB_NAME)
                            .fallbackToDestructiveMigration()
                            //.addMigrations(MIGRATION_7_8)
                            .build();
                }
            }
        }
        return sWordsDB;
    }

    public static final Migration MIGRATION_7_8=new Migration(7,8) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Words ADD COLUMN collection_pos int");
        }
    };
}
