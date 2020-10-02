package beans;

import java.util.List;

public class ZipBeans {

    /**
     * wordRank : 1
     * headWord : handily
     * content : {"word":{"wordHead":"handily","wordId":"Level4luan_1_1","content":{"usphone":"'hændɪli","syno":{"synos":[{"pos":"adv","tran":"方便地；敏捷地；灵巧地","hwds":[{"w":"promptly"},{"w":"expediently"}]}],"desc":"同近"},"ukphone":"ˈhændɪli","ukspeech":"handily&type=1","star":0,"phone":"'hændili","speech":"handily","relWord":{"rels":[{"pos":"adj","words":[{"hwd":"handy","tran":" 便利的；手边的，就近的；容易取得的；敏捷的"}]},{"pos":"n","words":[{"hwd":"handiness","tran":" 轻便；灵巧；敏捷"}]}],"desc":"同根"},"usspeech":"handily&type=2","trans":[{"tranCn":"轻松地","descOther":"英释","descCn":"中释","pos":"adv","tranOther":"if you win something handily, you win easily"}]}}}
     * bookId : Level4luan_1
     */


    private int wordRank;
    private String headWord;
    private ContentBeanX content;
    private String bookId;


    public int getWordRank() {
        return wordRank;
    }

    public void setWordRank(int wordRank) {
        this.wordRank = wordRank;
    }

    public String getHeadWord() {
        return headWord;
    }

    public void setHeadWord(String headWord) {
        this.headWord = headWord;
    }

    public ContentBeanX getContent() {
        return content;
    }

    public void setContent(ContentBeanX content) {
        this.content = content;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public static class ContentBeanX {
        /**
         * word : {"wordHead":"handily","wordId":"Level4luan_1_1","content":{"usphone":"'hændɪli","syno":{"synos":[{"pos":"adv","tran":"方便地；敏捷地；灵巧地","hwds":[{"w":"promptly"},{"w":"expediently"}]}],"desc":"同近"},"ukphone":"ˈhændɪli","ukspeech":"handily&type=1","star":0,"phone":"'hændili","speech":"handily","relWord":{"rels":[{"pos":"adj","words":[{"hwd":"handy","tran":" 便利的；手边的，就近的；容易取得的；敏捷的"}]},{"pos":"n","words":[{"hwd":"handiness","tran":" 轻便；灵巧；敏捷"}]}],"desc":"同根"},"usspeech":"handily&type=2","trans":[{"tranCn":"轻松地","descOther":"英释","descCn":"中释","pos":"adv","tranOther":"if you win something handily, you win easily"}]}}
         */

        private WordBean word;

        public WordBean getWord() {
            return word;
        }

        public void setWord(WordBean word) {
            this.word = word;
        }

        public static class WordBean {
            /**
             * wordHead : handily
             * wordId : Level4luan_1_1
             * content : {"usphone":"'hændɪli","syno":{"synos":[{"pos":"adv","tran":"方便地；敏捷地；灵巧地","hwds":[{"w":"promptly"},{"w":"expediently"}]}],"desc":"同近"},"ukphone":"ˈhændɪli","ukspeech":"handily&type=1","star":0,"phone":"'hændili","speech":"handily","relWord":{"rels":[{"pos":"adj","words":[{"hwd":"handy","tran":" 便利的；手边的，就近的；容易取得的；敏捷的"}]},{"pos":"n","words":[{"hwd":"handiness","tran":" 轻便；灵巧；敏捷"}]}],"desc":"同根"},"usspeech":"handily&type=2","trans":[{"tranCn":"轻松地","descOther":"英释","descCn":"中释","pos":"adv","tranOther":"if you win something handily, you win easily"}]}
             */

            private String wordHead;
            private String wordId;
            private ContentBean content;

            public String getWordHead() {
                return wordHead;
            }

            public void setWordHead(String wordHead) {
                this.wordHead = wordHead;
            }

            public String getWordId() {
                return wordId;
            }

            public void setWordId(String wordId) {
                this.wordId = wordId;
            }

            public ContentBean getContent() {
                return content;
            }

            public void setContent(ContentBean content) {
                this.content = content;
            }

            public static class ContentBean {
                /**
                 * usphone : 'hændɪli
                 * syno : {"synos":[{"pos":"adv","tran":"方便地；敏捷地；灵巧地","hwds":[{"w":"promptly"},{"w":"expediently"}]}],"desc":"同近"}
                 * ukphone : ˈhændɪli
                 * ukspeech : handily&type=1
                 * star : 0
                 * phone : 'hændili
                 * speech : handily
                 * relWord : {"rels":[{"pos":"adj","words":[{"hwd":"handy","tran":" 便利的；手边的，就近的；容易取得的；敏捷的"}]},{"pos":"n","words":[{"hwd":"handiness","tran":" 轻便；灵巧；敏捷"}]}],"desc":"同根"}
                 * usspeech : handily&type=2
                 * trans : [{"tranCn":"轻松地","descOther":"英释","descCn":"中释","pos":"adv","tranOther":"if you win something handily, you win easily"}]
                 */

                private String usphone;
                private SynoBean syno;
                private String ukphone;
                private String ukspeech;
                private int star;
                private String phone;
                private String speech;
                private RelWordBean relWord;
                private String usspeech;
                private List<TransBean> trans;

                public String getUsphone() {
                    return usphone;
                }

                public void setUsphone(String usphone) {
                    this.usphone = usphone;
                }

                public SynoBean getSyno() {
                    return syno;
                }

                public void setSyno(SynoBean syno) {
                    this.syno = syno;
                }

                public String getUkphone() {
                    return ukphone;
                }

                public void setUkphone(String ukphone) {
                    this.ukphone = ukphone;
                }

                public String getUkspeech() {
                    return ukspeech;
                }

                public void setUkspeech(String ukspeech) {
                    this.ukspeech = ukspeech;
                }

                public int getStar() {
                    return star;
                }

                public void setStar(int star) {
                    this.star = star;
                }

                public String getPhone() {
                    return phone;
                }

                public void setPhone(String phone) {
                    this.phone = phone;
                }

                public String getSpeech() {
                    return speech;
                }

                public void setSpeech(String speech) {
                    this.speech = speech;
                }

                public RelWordBean getRelWord() {
                    return relWord;
                }

                public void setRelWord(RelWordBean relWord) {
                    this.relWord = relWord;
                }

                public String getUsspeech() {
                    return usspeech;
                }

                public void setUsspeech(String usspeech) {
                    this.usspeech = usspeech;
                }

                public List<TransBean> getTrans() {
                    return trans;
                }

                public void setTrans(List<TransBean> trans) {
                    this.trans = trans;
                }

                public static class SynoBean {
                    /**
                     * synos : [{"pos":"adv","tran":"方便地；敏捷地；灵巧地","hwds":[{"w":"promptly"},{"w":"expediently"}]}]
                     * desc : 同近
                     */

                    private String desc;
                    private List<SynosBean> synos;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SynosBean> getSynos() {
                        return synos;
                    }

                    public void setSynos(List<SynosBean> synos) {
                        this.synos = synos;
                    }

                    public static class SynosBean {
                        /**
                         * pos : adv
                         * tran : 方便地；敏捷地；灵巧地
                         * hwds : [{"w":"promptly"},{"w":"expediently"}]
                         */

                        private String pos;
                        private String tran;
                        private List<HwdsBean> hwds;

                        public String getPos() {
                            return pos;
                        }

                        public void setPos(String pos) {
                            this.pos = pos;
                        }

                        public String getTran() {
                            return tran;
                        }

                        public void setTran(String tran) {
                            this.tran = tran;
                        }

                        public List<HwdsBean> getHwds() {
                            return hwds;
                        }

                        public void setHwds(List<HwdsBean> hwds) {
                            this.hwds = hwds;
                        }

                        public static class HwdsBean {
                            /**
                             * w : promptly
                             */

                            private String w;

                            public String getW() {
                                return w;
                            }

                            public void setW(String w) {
                                this.w = w;
                            }
                        }
                    }
                }

                public static class RelWordBean {
                    /**
                     * rels : [{"pos":"adj","words":[{"hwd":"handy","tran":" 便利的；手边的，就近的；容易取得的；敏捷的"}]},{"pos":"n","words":[{"hwd":"handiness","tran":" 轻便；灵巧；敏捷"}]}]
                     * desc : 同根
                     */

                    private String desc;
                    private List<RelsBean> rels;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<RelsBean> getRels() {
                        return rels;
                    }

                    public void setRels(List<RelsBean> rels) {
                        this.rels = rels;
                    }

                    public static class RelsBean {
                        /**
                         * pos : adj
                         * words : [{"hwd":"handy","tran":" 便利的；手边的，就近的；容易取得的；敏捷的"}]
                         */

                        private String pos;
                        private List<WordsBean> words;

                        public String getPos() {
                            return pos;
                        }

                        public void setPos(String pos) {
                            this.pos = pos;
                        }

                        public List<WordsBean> getWords() {
                            return words;
                        }

                        public void setWords(List<WordsBean> words) {
                            this.words = words;
                        }

                        public static class WordsBean {
                            /**
                             * hwd : handy
                             * tran :  便利的；手边的，就近的；容易取得的；敏捷的
                             */

                            private String hwd;
                            private String tran;

                            public String getHwd() {
                                return hwd;
                            }

                            public void setHwd(String hwd) {
                                this.hwd = hwd;
                            }

                            public String getTran() {
                                return tran;
                            }

                            public void setTran(String tran) {
                                this.tran = tran;
                            }
                        }
                    }
                }

                public static class TransBean {
                    /**
                     * tranCn : 轻松地
                     * descOther : 英释
                     * descCn : 中释
                     * pos : adv
                     * tranOther : if you win something handily, you win easily
                     */

                    private String tranCn;
                    private String descOther;
                    private String descCn;
                    private String pos;
                    private String tranOther;

                    public String getTranCn() {
                        return tranCn;
                    }

                    public void setTranCn(String tranCn) {
                        this.tranCn = tranCn;
                    }

                    public String getDescOther() {
                        return descOther;
                    }

                    public void setDescOther(String descOther) {
                        this.descOther = descOther;
                    }

                    public String getDescCn() {
                        return descCn;
                    }

                    public void setDescCn(String descCn) {
                        this.descCn = descCn;
                    }

                    public String getPos() {
                        return pos;
                    }

                    public void setPos(String pos) {
                        this.pos = pos;
                    }

                    public String getTranOther() {
                        return tranOther;
                    }

                    public void setTranOther(String tranOther) {
                        this.tranOther = tranOther;
                    }
                }
            }
        }
    }
}
