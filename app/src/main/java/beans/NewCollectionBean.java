package beans;

/**
 * 作者：zzx on 2020/9/27 16:26
 * <p>
 * 作用： 创建新的文件夹 --> 必须要填的
 */
public class NewCollectionBean {
    private String collectionName;
    private String collectionRemanks;

    public NewCollectionBean() {
    }

    public NewCollectionBean(String collectionName, String collectionRemanks) {
        this.collectionName = collectionName;
        this.collectionRemanks = collectionRemanks;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionRemanks() {
        return collectionRemanks;
    }

    public void setCollectionRemanks(String collectionRemanks) {
        this.collectionRemanks = collectionRemanks;
    }
}

