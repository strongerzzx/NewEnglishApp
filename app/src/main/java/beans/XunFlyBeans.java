package beans;

import java.util.List;


/**
 * 作者：zzx on 2020/10/26 19:03
 *  作用： xxxx
 */
public class XunFlyBeans {

    /**
     * sn : 2
     * ls : true
     * bg : 0
     * ed : 0
     * ws : [{"bg":0,"cw":[{"sc":0,"w":""}]}]
     */

    private Integer sn;
    private Boolean ls;
    private Integer bg;
    private Integer ed;
    private List<WsBean> ws;

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public Boolean isLs() {
        return ls;
    }

    public void setLs(Boolean ls) {
        this.ls = ls;
    }

    public Integer getBg() {
        return bg;
    }

    public void setBg(Integer bg) {
        this.bg = bg;
    }

    public Integer getEd() {
        return ed;
    }

    public void setEd(Integer ed) {
        this.ed = ed;
    }

    public List<WsBean> getWs() {
        return ws;
    }

    public void setWs(List<WsBean> ws) {
        this.ws = ws;
    }

    public static class WsBean {
        /**
         * bg : 0
         * cw : [{"sc":0,"w":""}]
         */

        private Integer bg;
        private List<CwBean> cw;

        public Integer getBg() {
            return bg;
        }

        public void setBg(Integer bg) {
            this.bg = bg;
        }

        public List<CwBean> getCw() {
            return cw;
        }

        public void setCw(List<CwBean> cw) {
            this.cw = cw;
        }

        public static class CwBean {
            /**
             * sc : 0.0
             * w :
             */

            private Double sc;
            private String w;

            public Double getSc() {
                return sc;
            }

            public void setSc(Double sc) {
                this.sc = sc;
            }

            public String getW() {
                return w;
            }

            public void setW(String w) {
                this.w = w;
            }
        }
    }
}

