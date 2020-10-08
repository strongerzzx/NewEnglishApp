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

    @ColumnInfo(name = "tran_other")
    private String tranOther;//单词的英语长句近义词

    @ColumnInfo(name = "syn_word_1")
    private String synWord1;//单词的近义词1

    @ColumnInfo(name = "p_content")
    private String pcontent;//短语英文

    @ColumnInfo(name = "p_cn")
    private String pcn;//短语中文

    @ColumnInfo(name = "collection_pos")
    private int collectionpos;//收藏夹的ID

    @ColumnInfo(name = "is_collection")
    private boolean iscollection;//是否收藏


    public Words(String headWord,int bookpos, String ukphone, String usphone, String picture, String content
            , String cnContent, String tran, String simpleTran,String tranOther,String synWord1
            ,String pcontent,String pcn,int collectionpos,boolean iscollection) {
        this.collectionpos=collectionpos;
        this.synWord1=synWord1;
        this.bookpos=bookpos;
        this.headWord = headWord;
        this.ukphone = ukphone;
        this.usphone = usphone;
        this.picture = picture;
        this.content = content;
        this.cnContent = cnContent;
        this.tran = tran;
        this.simpleTran = simpleTran;
        this.tranOther=tranOther;
        this.pcontent=pcontent;
        this.pcn=pcn;
        this.iscollection=iscollection;
    }

    public int getCollectionpos() {
        return collectionpos;
    }

    public void setCollectionpos(int collectionpos) {
        this.collectionpos = collectionpos;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public String getPcn() {
        return pcn;
    }

    public void setPcn(String pcn) {
        this.pcn = pcn;
    }

    public String getSynWord1() {
        return synWord1;
    }

    public void setSynWord1(String synWord1) {
        this.synWord1 = synWord1;
    }

    public String getTranOther() {
        return tranOther;
    }

    public void setTranOther(String tranOther) {
        this.tranOther = tranOther;
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
