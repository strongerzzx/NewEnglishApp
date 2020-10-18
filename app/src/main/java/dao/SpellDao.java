package dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import entirys.Spells;

/**
 * 作者：zzx on 2020/10/18 23:33
 * <p>
 * 作用： xxxx
 */
@Dao
public interface SpellDao {
    @Insert
    void insertSpell(Spells... spells);

    @Update
    void updateSpell(Spells... spells);

    @Delete
    void deleteSpell(Spells... spells);
}
