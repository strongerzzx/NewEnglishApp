package beans;

public class LoginBeans {

    /**
     * flag : true
     * msg : 登录成功
     * data : {"id":1,"username":"admin","password":"admin","avator":"https://img-1255613699.cos.ap-guangzhou.myqcloud.com/portrait/201904/6a9488660d0d0fdc6cfea08ed92af0e1.jpg"}
     */

    private boolean flag;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * username : admin
         * password : admin
         * avator : https://img-1255613699.cos.ap-guangzhou.myqcloud.com/portrait/201904/6a9488660d0d0fdc6cfea08ed92af0e1.jpg
         */

        private int id;
        private String username;
        private String password;
        private String avator;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getAvator() {
            return avator;
        }

        public void setAvator(String avator) {
            this.avator = avator;
        }
    }
}
