package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/5 18:27
 * <p>
 * 作用： xxxx
 */

@Entity(indices = {@Index(value = {"title"},unique = true)})
public class WordClips {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "words_pos")
    private int wordsPos;//点击的哪个单词表

    @ColumnInfo(name = "title")
    private String title;//单词夹的标题

    @ColumnInfo(name = "pic")
    private String pic;//图片

    @ColumnInfo(name = "words_num")
    private int wordsNum;//夹中的单词数量

    @ColumnInfo(name = "words_collection")
    private String wordsCollection;//收藏的单词

    @ColumnInfo(name = "collection")
    private boolean collection;//是否收藏这个列表

    public WordClips(int wordsPos, String title, String pic, int wordsNum,String wordsCollection, boolean collection) {
        this.wordsPos = wordsPos;
        this.title = title;
        this.pic = pic;
        this.wordsNum = wordsNum;
        this.wordsCollection=wordsCollection;
        this.collection = collection;
    }

    public String getWordsCollection() {
        return wordsCollection;
    }

    public void setWordsCollection(String wordsCollection) {
        this.wordsCollection = wordsCollection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWordsPos() {
        return wordsPos;
    }

    public void setWordsPos(int wordsPos) {
        this.wordsPos = wordsPos;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getWordsNum() {
        return wordsNum;
    }

    public void setWordsNum(int wordsNum) {
        this.wordsNum = wordsNum;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }
}
