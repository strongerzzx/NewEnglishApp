package entirys;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

/**
 * 作者：zzx on 2020/10/2 11:10
 * <p>
 * 作用： xxxx
 */
@Entity(indices = {@Index(value = {"head_word"},unique = true)})  //单词设置为唯一 防止重复插入
public class Words {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "bookpos")
    private int bookpos;//根据这个来判断 --> 点击的是哪个词书

    @ColumnInfo(name = "head_word")
    private String headWord;//单词

    @ColumnInfo(name = "uk_phone")
    private String ukphone;//英式发音

    @ColumnInfo(name = "us_phone")
    private String usphone;//美式发音

    @ColumnInfo(name = "picture")
    private String picture;//图片

    @ColumnInfo(name = "content")
    private String content;//例句

    @ColumnInfo(name = "cn_content")
    private String cnContent;//例句翻译

    @ColumnInfo(name = "tran")
    private String tran;//正常翻译

    @ColumnInfo(name = "simple_tran")
    private String simpleTran;//简单翻译

    @ColumnInfo(name = "is_collection")
    private boolean iscollection;//简单翻译


    public Words(String headWord,int bookpos, String ukphone, String usphone, String picture, String content, String cnContent, String tran, String simpleTran,boolean iscollection) {
        this.bookpos=bookpos;
        this.headWord = headWord;
        this.ukphone = ukphone;
        this.usphone = usphone;
        this.picture = picture;
        this.content = content;
        this.cnContent = cnContent;
        this.tran = tran;
        this.simpleTran = simpleTran;
        this.iscollection=iscollection;
    }

    public boolean isIscollection() {
        return iscollection;
    }

    public void setIscollection(boolean iscollection) {
        this.iscollection = iscollection;
    }

    public int getBookpos() {
        return bookpos;
    }

    public void setBookpos(int bookpos) {
        this.bookpos = bookpos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeadWord() {
        return headWord;
    }

    public void setHeadWord(String headWord) {
        this.headWord = headWord;
    }

    public String getUkphone() {
        return ukphone;
    }

    public void setUkphone(String ukphone) {
        this.ukphone = ukphone;
    }

    public String getUsphone() {
        return usphone;
    }

    public void setUsphone(String usphone) {
        this.usphone = usphone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCnContent() {
        return cnContent;
    }

    public void setCnContent(String cnContent) {
        this.cnContent = cnContent;
    }

    public String getTran() {
        return tran;
    }

    public void setTran(String tran) {
        this.tran = tran;
    }

    public String getSimpleTran() {
        return simpleTran;
    }

    public void setSimpleTran(String simpleTran) {
        this.simpleTran = simpleTran;
    }
}
