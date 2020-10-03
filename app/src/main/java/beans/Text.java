package beans;

import java.util.List;

/**
 * 作者：zzx on 2020/10/2 10:27
 * <p>
 * 作用： xxxx
 */
public class Text {

    /**
     * wordRank : 1162
     * headWord : resolve
     * content : {"word":{"wordHead":"resolve","wordId":"CET4luan_1_1162","content":{"sentence":{"sentences":[{"sContent":"Barnet was desperate for money to resolve his financial problems.","sCn":"巴尼特急需钱来解决他的经济问题。"},{"sContent":"Mary resolved that she would stop smoking.","sCn":"玛丽决定戒烟。"}],"desc":"例句"},"realExamSentence":{"sentences":[{"sContent":"...whereas women may approach the same dispute from the perspective of what's the easiest and quickest way to resolve the problem-even if that means doing the boring work themselves...","sourceInfo":{"level":"CET4","year":"2017.12","type":"阅读理解"}},{"sContent":"...But chances are it won\u2019t have much impact on the human tendency to resolve to get to the gym more and avoid chocolate cake when the clock strikes midnight on December 31...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2012.12","type":"阅读理解"}}],"desc":"真题例句"},"usphone":"rɪ'zɑlv","ukspeech":"resolve&type=1","star":0,"usspeech":"resolve&type=2","picture":"http://ydschool-online.nos.netease.com/CET4luan_1_1162_resolve_1523505573585001163_resolve_MT.png","syno":{"synos":[{"pos":"vt","tran":"决定；溶解；使\u2026分解；决心要做\u2026","hwds":[{"w":"solve"},{"w":"condition"},{"w":"conclude"}]},{"pos":"vi","tran":"解决；决心；分解","hwds":[{"w":"settle"},{"w":"work out"},{"w":"figure out"}]},{"pos":"n","tran":"坚决；决定要做的事","hwds":[{"w":"determinateness"}]}],"desc":"同近"},"ukphone":"rɪ'zɒlv","phrase":{"phrases":[{"pContent":"resolve into","pCn":"使分解为\u2026；归结为"},{"pContent":"fail to resolve","pCn":"解决未果"}],"desc":"短语"},"phone":"ri'zɔlv","speech":"resolve","remMethod":{"val":"re ＋ solve(解决) → 解决","desc":"记忆"},"relWord":{"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"resolved","tran":"下定决心的；已解决的；断然的"},{"hwd":"resolvable","tran":"可分解的；可解决的；可溶解的"},{"hwd":"resolvent","tran":"分解的；有溶解力的；消散的"}]},{"pos":"adv","words":[{"hwd":"resolutely","tran":"坚决地；毅然地"}]},{"pos":"n","words":[{"hwd":"resolution","tran":"[物] 分辨率；决议；解决；决心"},{"hwd":"resolving","tran":"分解；解析"},{"hwd":"resolvent","tran":"溶剂；分解物；消肿药"}]},{"pos":"v","words":[{"hwd":"resolved","tran":"解决；决定；分解；转变（resolve的过去分词）"},{"hwd":"resolving","tran":"分辨（resolve的ing形式）；分解；决定；解决矛盾"}]}]},"trans":[{"tranCn":"解决；下决心","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to find a satisfactory way of dealing with a problem or difficulty"}]}}}
     * bookId : CET4luan_1
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
         * word : {"wordHead":"resolve","wordId":"CET4luan_1_1162","content":{"sentence":{"sentences":[{"sContent":"Barnet was desperate for money to resolve his financial problems.","sCn":"巴尼特急需钱来解决他的经济问题。"},{"sContent":"Mary resolved that she would stop smoking.","sCn":"玛丽决定戒烟。"}],"desc":"例句"},"realExamSentence":{"sentences":[{"sContent":"...whereas women may approach the same dispute from the perspective of what's the easiest and quickest way to resolve the problem-even if that means doing the boring work themselves...","sourceInfo":{"level":"CET4","year":"2017.12","type":"阅读理解"}},{"sContent":"...But chances are it won\u2019t have much impact on the human tendency to resolve to get to the gym more and avoid chocolate cake when the clock strikes midnight on December 31...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2012.12","type":"阅读理解"}}],"desc":"真题例句"},"usphone":"rɪ'zɑlv","ukspeech":"resolve&type=1","star":0,"usspeech":"resolve&type=2","picture":"http://ydschool-online.nos.netease.com/CET4luan_1_1162_resolve_1523505573585001163_resolve_MT.png","syno":{"synos":[{"pos":"vt","tran":"决定；溶解；使\u2026分解；决心要做\u2026","hwds":[{"w":"solve"},{"w":"condition"},{"w":"conclude"}]},{"pos":"vi","tran":"解决；决心；分解","hwds":[{"w":"settle"},{"w":"work out"},{"w":"figure out"}]},{"pos":"n","tran":"坚决；决定要做的事","hwds":[{"w":"determinateness"}]}],"desc":"同近"},"ukphone":"rɪ'zɒlv","phrase":{"phrases":[{"pContent":"resolve into","pCn":"使分解为\u2026；归结为"},{"pContent":"fail to resolve","pCn":"解决未果"}],"desc":"短语"},"phone":"ri'zɔlv","speech":"resolve","remMethod":{"val":"re ＋ solve(解决) → 解决","desc":"记忆"},"relWord":{"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"resolved","tran":"下定决心的；已解决的；断然的"},{"hwd":"resolvable","tran":"可分解的；可解决的；可溶解的"},{"hwd":"resolvent","tran":"分解的；有溶解力的；消散的"}]},{"pos":"adv","words":[{"hwd":"resolutely","tran":"坚决地；毅然地"}]},{"pos":"n","words":[{"hwd":"resolution","tran":"[物] 分辨率；决议；解决；决心"},{"hwd":"resolving","tran":"分解；解析"},{"hwd":"resolvent","tran":"溶剂；分解物；消肿药"}]},{"pos":"v","words":[{"hwd":"resolved","tran":"解决；决定；分解；转变（resolve的过去分词）"},{"hwd":"resolving","tran":"分辨（resolve的ing形式）；分解；决定；解决矛盾"}]}]},"trans":[{"tranCn":"解决；下决心","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to find a satisfactory way of dealing with a problem or difficulty"}]}}
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
             * wordHead : resolve
             * wordId : CET4luan_1_1162
             * content : {"sentence":{"sentences":[{"sContent":"Barnet was desperate for money to resolve his financial problems.","sCn":"巴尼特急需钱来解决他的经济问题。"},{"sContent":"Mary resolved that she would stop smoking.","sCn":"玛丽决定戒烟。"}],"desc":"例句"},"realExamSentence":{"sentences":[{"sContent":"...whereas women may approach the same dispute from the perspective of what's the easiest and quickest way to resolve the problem-even if that means doing the boring work themselves...","sourceInfo":{"level":"CET4","year":"2017.12","type":"阅读理解"}},{"sContent":"...But chances are it won\u2019t have much impact on the human tendency to resolve to get to the gym more and avoid chocolate cake when the clock strikes midnight on December 31...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2012.12","type":"阅读理解"}}],"desc":"真题例句"},"usphone":"rɪ'zɑlv","ukspeech":"resolve&type=1","star":0,"usspeech":"resolve&type=2","picture":"http://ydschool-online.nos.netease.com/CET4luan_1_1162_resolve_1523505573585001163_resolve_MT.png","syno":{"synos":[{"pos":"vt","tran":"决定；溶解；使\u2026分解；决心要做\u2026","hwds":[{"w":"solve"},{"w":"condition"},{"w":"conclude"}]},{"pos":"vi","tran":"解决；决心；分解","hwds":[{"w":"settle"},{"w":"work out"},{"w":"figure out"}]},{"pos":"n","tran":"坚决；决定要做的事","hwds":[{"w":"determinateness"}]}],"desc":"同近"},"ukphone":"rɪ'zɒlv","phrase":{"phrases":[{"pContent":"resolve into","pCn":"使分解为\u2026；归结为"},{"pContent":"fail to resolve","pCn":"解决未果"}],"desc":"短语"},"phone":"ri'zɔlv","speech":"resolve","remMethod":{"val":"re ＋ solve(解决) → 解决","desc":"记忆"},"relWord":{"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"resolved","tran":"下定决心的；已解决的；断然的"},{"hwd":"resolvable","tran":"可分解的；可解决的；可溶解的"},{"hwd":"resolvent","tran":"分解的；有溶解力的；消散的"}]},{"pos":"adv","words":[{"hwd":"resolutely","tran":"坚决地；毅然地"}]},{"pos":"n","words":[{"hwd":"resolution","tran":"[物] 分辨率；决议；解决；决心"},{"hwd":"resolving","tran":"分解；解析"},{"hwd":"resolvent","tran":"溶剂；分解物；消肿药"}]},{"pos":"v","words":[{"hwd":"resolved","tran":"解决；决定；分解；转变（resolve的过去分词）"},{"hwd":"resolving","tran":"分辨（resolve的ing形式）；分解；决定；解决矛盾"}]}]},"trans":[{"tranCn":"解决；下决心","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to find a satisfactory way of dealing with a problem or difficulty"}]}
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
                 * sentence : {"sentences":[{"sContent":"Barnet was desperate for money to resolve his financial problems.","sCn":"巴尼特急需钱来解决他的经济问题。"},{"sContent":"Mary resolved that she would stop smoking.","sCn":"玛丽决定戒烟。"}],"desc":"例句"}
                 * realExamSentence : {"sentences":[{"sContent":"...whereas women may approach the same dispute from the perspective of what's the easiest and quickest way to resolve the problem-even if that means doing the boring work themselves...","sourceInfo":{"level":"CET4","year":"2017.12","type":"阅读理解"}},{"sContent":"...But chances are it won\u2019t have much impact on the human tendency to resolve to get to the gym more and avoid chocolate cake when the clock strikes midnight on December 31...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2012.12","type":"阅读理解"}}],"desc":"真题例句"}
                 * usphone : rɪ'zɑlv
                 * ukspeech : resolve&type=1
                 * star : 0
                 * usspeech : resolve&type=2
                 * picture : http://ydschool-online.nos.netease.com/CET4luan_1_1162_resolve_1523505573585001163_resolve_MT.png
                 * syno : {"synos":[{"pos":"vt","tran":"决定；溶解；使\u2026分解；决心要做\u2026","hwds":[{"w":"solve"},{"w":"condition"},{"w":"conclude"}]},{"pos":"vi","tran":"解决；决心；分解","hwds":[{"w":"settle"},{"w":"work out"},{"w":"figure out"}]},{"pos":"n","tran":"坚决；决定要做的事","hwds":[{"w":"determinateness"}]}],"desc":"同近"}
                 * ukphone : rɪ'zɒlv
                 * phrase : {"phrases":[{"pContent":"resolve into","pCn":"使分解为\u2026；归结为"},{"pContent":"fail to resolve","pCn":"解决未果"}],"desc":"短语"}
                 * phone : ri'zɔlv
                 * speech : resolve
                 * remMethod : {"val":"re ＋ solve(解决) → 解决","desc":"记忆"}
                 * relWord : {"desc":"同根","rels":[{"pos":"adj","words":[{"hwd":"resolved","tran":"下定决心的；已解决的；断然的"},{"hwd":"resolvable","tran":"可分解的；可解决的；可溶解的"},{"hwd":"resolvent","tran":"分解的；有溶解力的；消散的"}]},{"pos":"adv","words":[{"hwd":"resolutely","tran":"坚决地；毅然地"}]},{"pos":"n","words":[{"hwd":"resolution","tran":"[物] 分辨率；决议；解决；决心"},{"hwd":"resolving","tran":"分解；解析"},{"hwd":"resolvent","tran":"溶剂；分解物；消肿药"}]},{"pos":"v","words":[{"hwd":"resolved","tran":"解决；决定；分解；转变（resolve的过去分词）"},{"hwd":"resolving","tran":"分辨（resolve的ing形式）；分解；决定；解决矛盾"}]}]}
                 * trans : [{"tranCn":"解决；下决心","descOther":"英释","pos":"v","descCn":"中释","tranOther":"to find a satisfactory way of dealing with a problem or difficulty"}]
                 */

                private SentenceBean sentence;
                private RealExamSentenceBean realExamSentence;
                private String usphone;
                private String ukspeech;
                private int star;
                private String usspeech;
                private String picture;
                private SynoBean syno;
                private String ukphone;
                private PhraseBean phrase;
                private String phone;
                private String speech;
                private RemMethodBean remMethod;
                private RelWordBean relWord;
                private List<TransBean> trans;

                public SentenceBean getSentence() {
                    return sentence;
                }

                public void setSentence(SentenceBean sentence) {
                    this.sentence = sentence;
                }

                public RealExamSentenceBean getRealExamSentence() {
                    return realExamSentence;
                }

                public void setRealExamSentence(RealExamSentenceBean realExamSentence) {
                    this.realExamSentence = realExamSentence;
                }

                public String getUsphone() {
                    return usphone;
                }

                public void setUsphone(String usphone) {
                    this.usphone = usphone;
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

                public String getUsspeech() {
                    return usspeech;
                }

                public void setUsspeech(String usspeech) {
                    this.usspeech = usspeech;
                }

                public String getPicture() {
                    return picture;
                }

                public void setPicture(String picture) {
                    this.picture = picture;
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

                public PhraseBean getPhrase() {
                    return phrase;
                }

                public void setPhrase(PhraseBean phrase) {
                    this.phrase = phrase;
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

                public RemMethodBean getRemMethod() {
                    return remMethod;
                }

                public void setRemMethod(RemMethodBean remMethod) {
                    this.remMethod = remMethod;
                }

                public RelWordBean getRelWord() {
                    return relWord;
                }

                public void setRelWord(RelWordBean relWord) {
                    this.relWord = relWord;
                }

                public List<TransBean> getTrans() {
                    return trans;
                }

                public void setTrans(List<TransBean> trans) {
                    this.trans = trans;
                }

                public static class SentenceBean {
                    /**
                     * sentences : [{"sContent":"Barnet was desperate for money to resolve his financial problems.","sCn":"巴尼特急需钱来解决他的经济问题。"},{"sContent":"Mary resolved that she would stop smoking.","sCn":"玛丽决定戒烟。"}]
                     * desc : 例句
                     */

                    private String desc;
                    private List<SentencesBean> sentences;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SentencesBean> getSentences() {
                        return sentences;
                    }

                    public void setSentences(List<SentencesBean> sentences) {
                        this.sentences = sentences;
                    }

                    public static class SentencesBean {
                        /**
                         * sContent : Barnet was desperate for money to resolve his financial problems.
                         * sCn : 巴尼特急需钱来解决他的经济问题。
                         */

                        private String sContent;
                        private String sCn;

                        public String getSContent() {
                            return sContent;
                        }

                        public void setSContent(String sContent) {
                            this.sContent = sContent;
                        }

                        public String getSCn() {
                            return sCn;
                        }

                        public void setSCn(String sCn) {
                            this.sCn = sCn;
                        }
                    }
                }

                public static class RealExamSentenceBean {
                    /**
                     * sentences : [{"sContent":"...whereas women may approach the same dispute from the perspective of what's the easiest and quickest way to resolve the problem-even if that means doing the boring work themselves...","sourceInfo":{"level":"CET4","year":"2017.12","type":"阅读理解"}},{"sContent":"...But chances are it won\u2019t have much impact on the human tendency to resolve to get to the gym more and avoid chocolate cake when the clock strikes midnight on December 31...","sourceInfo":{"paper":"第三套","level":"CET4","year":"2012.12","type":"阅读理解"}}]
                     * desc : 真题例句
                     */

                    private String desc;
                    private List<SentencesBeanX> sentences;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<SentencesBeanX> getSentences() {
                        return sentences;
                    }

                    public void setSentences(List<SentencesBeanX> sentences) {
                        this.sentences = sentences;
                    }

                    public static class SentencesBeanX {
                        /**
                         * sContent : ...whereas women may approach the same dispute from the perspective of what's the easiest and quickest way to resolve the problem-even if that means doing the boring work themselves...
                         * sourceInfo : {"level":"CET4","year":"2017.12","type":"阅读理解"}
                         */

                        private String sContent;
                        private SourceInfoBean sourceInfo;

                        public String getSContent() {
                            return sContent;
                        }

                        public void setSContent(String sContent) {
                            this.sContent = sContent;
                        }

                        public SourceInfoBean getSourceInfo() {
                            return sourceInfo;
                        }

                        public void setSourceInfo(SourceInfoBean sourceInfo) {
                            this.sourceInfo = sourceInfo;
                        }

                        public static class SourceInfoBean {
                            /**
                             * level : CET4
                             * year : 2017.12
                             * type : 阅读理解
                             */

                            private String level;
                            private String year;
                            private String type;

                            public String getLevel() {
                                return level;
                            }

                            public void setLevel(String level) {
                                this.level = level;
                            }

                            public String getYear() {
                                return year;
                            }

                            public void setYear(String year) {
                                this.year = year;
                            }

                            public String getType() {
                                return type;
                            }

                            public void setType(String type) {
                                this.type = type;
                            }
                        }
                    }
                }

                public static class SynoBean {
                    /**
                     * synos : [{"pos":"vt","tran":"决定；溶解；使\u2026分解；决心要做\u2026","hwds":[{"w":"solve"},{"w":"condition"},{"w":"conclude"}]},{"pos":"vi","tran":"解决；决心；分解","hwds":[{"w":"settle"},{"w":"work out"},{"w":"figure out"}]},{"pos":"n","tran":"坚决；决定要做的事","hwds":[{"w":"determinateness"}]}]
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
                         * pos : vt
                         * tran : 决定；溶解；使…分解；决心要做…
                         * hwds : [{"w":"solve"},{"w":"condition"},{"w":"conclude"}]
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
                             * w : solve
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

                public static class PhraseBean {
                    /**
                     * phrases : [{"pContent":"resolve into","pCn":"使分解为\u2026；归结为"},{"pContent":"fail to resolve","pCn":"解决未果"}]
                     * desc : 短语
                     */

                    private String desc;
                    private List<PhrasesBean> phrases;

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }

                    public List<PhrasesBean> getPhrases() {
                        return phrases;
                    }

                    public void setPhrases(List<PhrasesBean> phrases) {
                        this.phrases = phrases;
                    }

                    public static class PhrasesBean {
                        /**
                         * pContent : resolve into
                         * pCn : 使分解为…；归结为
                         */

                        private String pContent;
                        private String pCn;

                        public String getPContent() {
                            return pContent;
                        }

                        public void setPContent(String pContent) {
                            this.pContent = pContent;
                        }

                        public String getPCn() {
                            return pCn;
                        }

                        public void setPCn(String pCn) {
                            this.pCn = pCn;
                        }
                    }
                }

                public static class RemMethodBean {
                    /**
                     * val : re ＋ solve(解决) → 解决
                     * desc : 记忆
                     */

                    private String val;
                    private String desc;

                    public String getVal() {
                        return val;
                    }

                    public void setVal(String val) {
                        this.val = val;
                    }

                    public String getDesc() {
                        return desc;
                    }

                    public void setDesc(String desc) {
                        this.desc = desc;
                    }
                }

                public static class RelWordBean {
                    /**
                     * desc : 同根
                     * rels : [{"pos":"adj","words":[{"hwd":"resolved","tran":"下定决心的；已解决的；断然的"},{"hwd":"resolvable","tran":"可分解的；可解决的；可溶解的"},{"hwd":"resolvent","tran":"分解的；有溶解力的；消散的"}]},{"pos":"adv","words":[{"hwd":"resolutely","tran":"坚决地；毅然地"}]},{"pos":"n","words":[{"hwd":"resolution","tran":"[物] 分辨率；决议；解决；决心"},{"hwd":"resolving","tran":"分解；解析"},{"hwd":"resolvent","tran":"溶剂；分解物；消肿药"}]},{"pos":"v","words":[{"hwd":"resolved","tran":"解决；决定；分解；转变（resolve的过去分词）"},{"hwd":"resolving","tran":"分辨（resolve的ing形式）；分解；决定；解决矛盾"}]}]
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
                         * words : [{"hwd":"resolved","tran":"下定决心的；已解决的；断然的"},{"hwd":"resolvable","tran":"可分解的；可解决的；可溶解的"},{"hwd":"resolvent","tran":"分解的；有溶解力的；消散的"}]
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
                             * hwd : resolved
                             * tran : 下定决心的；已解决的；断然的
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
                     * tranCn : 解决；下决心
                     * descOther : 英释
                     * pos : v
                     * descCn : 中释
                     * tranOther : to find a satisfactory way of dealing with a problem or difficulty
                     */

                    private String tranCn;
                    private String descOther;
                    private String pos;
                    private String descCn;
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

                    public String getPos() {
                        return pos;
                    }

                    public void setPos(String pos) {
                        this.pos = pos;
                    }

                    public String getDescCn() {
                        return descCn;
                    }

                    public void setDescCn(String descCn) {
                        this.descCn = descCn;
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
