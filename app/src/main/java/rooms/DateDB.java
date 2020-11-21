package rooms;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import bases.BaseAppciation;
import beans.DateTemps;
import commonparms.Commons;
import dao.TjDateDao;

public
/**
 * 作者：zzx on 2020/11/21 21:01
 *  作用： xxxx
 */
@Database(entities = DateTemps.class,version = 1,exportSchema = false)
abstract class  DateDB  extends RoomDatabase {
    public abstract TjDateDao getDateDao();

    private static DateDB sDateDB;

    public static DateDB getDateDB() {
        if (sDateDB==null){
            synchronized (DateDB.class){
                if (sDateDB==null){
                    sDateDB= Room.databaseBuilder(BaseAppciation.getContext(),DateDB.class, Commons.DB_TYPE_TjDates)
                            .build();
                }
            }
        }
        return sDateDB;
    }
}

