package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/11 20:48
 * <p>
 * 作用： xxxx
 */
@Entity(foreignKeys = @ForeignKey(entity = ReciteWords.class,
                                   parentColumns = "id",
                                   childColumns = "recite_id"))
public class LearnTasks {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "recite_id")
    private int ReciteID;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReciteID() {
        return ReciteID;
    }

    public void setReciteID(int reciteID) {
        ReciteID = reciteID;
    }
}
