package beans;

public class SuggestBeans {
    private String tranCn;
    private String descCn;
    private String ukphone;
    private String wordHead;

    private boolean collection;

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public SuggestBeans() {
    }

    public SuggestBeans(String tranCn, String descCn, String ukphone, String wordHead) {
        this.tranCn = tranCn;
        this.descCn = descCn;
        this.ukphone = ukphone;
        this.wordHead = wordHead;
    }

    public String getTranCn() {
        return tranCn;
    }

    public void setTranCn(String tranCn) {
        this.tranCn = tranCn;
    }

    public String getDescCn() {
        return descCn;
    }

    public void setDescCn(String descCn) {
        this.descCn = descCn;
    }

    public String getUkphone() {
        return ukphone;
    }

    public void setUkphone(String ukphone) {
        this.ukphone = ukphone;
    }

    public String getWordHead() {
        return wordHead;
    }

    public void setWordHead(String wordHead) {
        this.wordHead = wordHead;
    }
}
