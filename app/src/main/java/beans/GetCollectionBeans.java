package beans;

import java.util.List;

public class GetCollectionBeans {

    /**
     * flag : true
     * msg : 获取成功
     * data : [{"id":1,"descCn":"d1","headWord":"h1","tranCn":"t1"},{"id":2,"descCn":"d2","headWord":"h2","tranCn":"t2"},{"id":3,"descCn":"d3","headWord":"h3","tranCn":"t3"},{"id":4,"descCn":"ddd","headWord":"china","tranCn":"??"},{"id":6,"descCn":"ddd","headWord":"china2","tranCn":"??"}]
     */

    private boolean flag;
    private String msg;
    private List<DataBean> data;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * descCn : d1
         * headWord : h1
         * tranCn : t1
         */

        private int id;
        private String descCn;
        private String headWord;
        private String tranCn;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDescCn() {
            return descCn;
        }

        public void setDescCn(String descCn) {
            this.descCn = descCn;
        }

        public String getHeadWord() {
            return headWord;
        }

        public void setHeadWord(String headWord) {
            this.headWord = headWord;
        }

        public String getTranCn() {
            return tranCn;
        }

        public void setTranCn(String tranCn) {
            this.tranCn = tranCn;
        }
    }
}
