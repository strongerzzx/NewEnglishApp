package beans;

import java.util.List;

public class SelectBookBeans {

    /**
     * reason : succ
     * code : 200
     * cates : [{"cateName":"高等教育","bookList":[{"cover":"https://nos.netease.com/ydschool-online/1496632727200CET4luan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":788457,"wordNum":1162,"id":"CET4luan_1","title":"四级真题核心词（图片记忆）","offlinedata":"http://ydschool-online.nos.netease.com/1523620217431_CET4luan_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1496655382926CET6luan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":591417,"wordNum":1228,"id":"CET6luan_1","title":"六级真题核心词（图片记忆）","offlinedata":"http://ydschool-online.nos.netease.com/1521164660466_CET6luan_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1496632762670KaoYanluan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":771889,"wordNum":1341,"id":"KaoYanluan_1","title":"考研必考词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164661106_KaoYanluan_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1496632776935Level4luan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":269844,"wordNum":595,"id":"Level4luan_1","title":"专四真题高频词","offlinedata":"http://ydschool-online.nos.netease.com/1521164630387_Level4luan_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/1491037703359Level8_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":242255,"wordNum":684,"id":"Level8_1","title":"专八真题高频词","offlinedata":"http://ydschool-online.nos.netease.com/1521164635290_Level8_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_CET4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2723074,"wordNum":3739,"id":"CET4luan_2","title":"四级英语词汇","offlinedata":"http://ydschool-online.nos.netease.com/1524052539052_CET4luan_2.zip","cateName":"高等教育","version":"5"},{"cover":"https://nos.netease.com/ydschool-online/youdao_CET6_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1111633,"wordNum":2078,"id":"CET6_2","title":"六级英语词汇","offlinedata":"http://ydschool-online.nos.netease.com/1524052554766_CET6_2.zip","cateName":"高等教育","version":"4"},{"cover":"https://nos.netease.com/ydschool-online/youdao_KaoYan_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2431155,"wordNum":4533,"id":"KaoYan_2","title":"考研英语词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164654696_KaoYan_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1783295,"wordNum":4025,"id":"Level4luan_2","title":"专四核心词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164625401_Level4luan_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level8_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":5320395,"wordNum":12197,"id":"Level8luan_2","title":"专八核心词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164650006_Level8luan_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/1491037568440CET4_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":764018,"wordNum":1162,"id":"CET4_1","title":"四级真题核心词（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164649209_CET4_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1491037677590CET6_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":753196,"wordNum":1228,"id":"CET6_1","title":"六级真题核心词（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164668667_CET6_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1491037662208KaoYan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":685507,"wordNum":1341,"id":"KaoYan_1","title":"考研必考词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164669833_KaoYan_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/1491037690141Level4_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":267335,"wordNum":595,"id":"Level4_1","title":"专四真题高频词（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164647417_Level4_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_CET4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2542778,"wordNum":3739,"id":"CET4_2","title":"四级英语词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164635506_CET4_2.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1706442,"wordNum":4025,"id":"Level4_2","title":"专四核心词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164653685_Level4_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level8_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":4763514,"wordNum":12197,"id":"Level8_2","title":"专八核心词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164663794_Level8_2.zip","cateName":"高等教育","version":"2"}]},{"cateName":"出国考试","bookList":[{"cover":"https://nos.netease.com/ydschool-online/youdao_IELTS_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1597545,"wordNum":3427,"id":"IELTSluan_2","title":"雅思词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164624473_IELTSluan_2.zip","cateName":"出国考试","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_TOEFL_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":4327321,"wordNum":9213,"id":"TOEFL_2","title":"TOEFL词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164640451_TOEFL_2.zip","cateName":"出国考试","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_GRE_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2470882,"wordNum":7199,"id":"GRE_2","title":"GRE词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164637271_GRE_2.zip","cateName":"出国考试","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_SAT_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1784650,"wordNum":4423,"id":"SAT_2","title":"SAT词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164670910_SAT_2.zip","cateName":"出国考试","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_GMAT_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1509190,"wordNum":3254,"id":"GMATluan_2","title":"GMAT词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164629611_GMATluan_2.zip","cateName":"出国考试","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_IELTS_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1525677,"wordNum":3427,"id":"IELTS_2","title":"雅思词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164657744_IELTS_2.zip","cateName":"出国考试","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_GMAT_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1422468,"wordNum":3254,"id":"GMAT_2","title":"GMAT词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164662073_GMAT_2.zip","cateName":"出国考试","version":"2"}]},{"cateName":"基础教育","bookList":[{"cover":"https://nos.netease.com/ydschool-online/youdao_ChuZhong_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":940509,"wordNum":1420,"id":"ChuZhongluan_2","title":"中考必备词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164669076_ChuZhongluan_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_GaoZhong_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2120036,"wordNum":3668,"id":"GaoZhongluan_2","title":"高考必备词汇（图片记忆）","offlinedata":"http://ydschool-online.nos.netease.com/1521164673602_GaoZhongluan_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue3_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":42478,"wordNum":64,"id":"PEPXiaoXue3_1","title":"人教版小学英语-三年级上册","offlinedata":"http://ydschool-online.nos.netease.com/1521164661774_PEPXiaoXue3_1.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue3_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":40675,"wordNum":72,"id":"PEPXiaoXue3_2","title":"人教版小学英语-三年级下册","offlinedata":"http://ydschool-online.nos.netease.com/1521164656604_PEPXiaoXue3_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue4_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":47587,"wordNum":84,"id":"PEPXiaoXue4_1","title":"人教版小学英语-四年级上册","offlinedata":"http://ydschool-online.nos.netease.com/1521164677447_PEPXiaoXue4_1.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":53505,"wordNum":104,"id":"PEPXiaoXue4_2","title":"人教版小学英语-四年级下册","offlinedata":"http://ydschool-online.nos.netease.com/1521164663086_PEPXiaoXue4_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue5_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":71424,"wordNum":131,"id":"PEPXiaoXue5_1","title":"人教版小学英语-五年级上册","offlinedata":"http://ydschool-online.nos.netease.com/1530101080610_PEPXiaoXue5_1.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue5_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":87211,"wordNum":156,"id":"PEPXiaoXue5_2","title":"人教版小学英语-五年级下册","offlinedata":"http://ydschool-online.nos.netease.com/1530101073491_PEPXiaoXue5_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue6_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":76633,"wordNum":130,"id":"PEPXiaoXue6_1","title":"人教版小学英语-六年级上册","offlinedata":"http://ydschool-online.nos.netease.com/1530101075331_PEPXiaoXue6_1.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/2_youdao_PEPXiaoXue6_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":64879,"wordNum":108,"id":"PEPXiaoXue6_2","title":"人教版小学英语-六年级下册","offlinedata":"http://ydschool-online.nos.netease.com/1521164632445_PEPXiaoXue6_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPChuZhong7_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":203341,"wordNum":392,"id":"PEPChuZhong7_1","title":"人教版初中英语-七年级上册","offlinedata":"http://ydschool-online.nos.netease.com/1530101067588_PEPChuZhong7_1.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPChuZhong7_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":266712,"wordNum":492,"id":"PEPChuZhong7_2","title":"人教版初中英语-七年级下册","offlinedata":"http://ydschool-online.nos.netease.com/1521164677043_PEPChuZhong7_2.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPChuZhong8_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":222704,"wordNum":419,"id":"PEPChuZhong8_1","title":"人教版初中英语-八年级上册","offlinedata":"http://ydschool-online.nos.netease.com/1530101070747_PEPChuZhong8_1.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPChuZhong8_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":244325,"wordNum":466,"id":"PEPChuZhong8_2","title":"人教版初中英语-八年级下册","offlinedata":"http://ydschool-online.nos.netease.com/1521164666522_PEPChuZhong8_2.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPChuZhong9_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":278981,"wordNum":551,"id":"PEPChuZhong9_1","title":"人教版初中英语-九年级全册","offlinedata":"http://ydschool-online.nos.netease.com/1530101078234_PEPChuZhong9_1.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":145874,"wordNum":311,"id":"PEPGaoZhong_1","title":"人教版高中英语-必修1","offlinedata":"http://ydschool-online.nos.netease.com/1521164674793_PEPGaoZhong_1.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":148700,"wordNum":319,"id":"PEPGaoZhong_2","title":"人教版高中英语-必修2","offlinedata":"http://ydschool-online.nos.netease.com/1521164678610_PEPGaoZhong_2.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_3.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":162383,"wordNum":366,"id":"PEPGaoZhong_3","title":"人教版高中英语-必修3","offlinedata":"http://ydschool-online.nos.netease.com/1521164676690_PEPGaoZhong_3.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_4.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":129777,"wordNum":307,"id":"PEPGaoZhong_4","title":"人教版高中英语-必修4","offlinedata":"http://ydschool-online.nos.netease.com/1521164657462_PEPGaoZhong_4.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_5.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":162923,"wordNum":357,"id":"PEPGaoZhong_5","title":"人教版高中英语-必修5","offlinedata":"http://ydschool-online.nos.netease.com/1521164657147_PEPGaoZhong_5.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_6.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":172125,"wordNum":391,"id":"PEPGaoZhong_6","title":"人教版高中英语-选修6","offlinedata":"http://ydschool-online.nos.netease.com/1521164629184_PEPGaoZhong_6.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_7.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":174623,"wordNum":384,"id":"PEPGaoZhong_7","title":"人教版高中英语-选修7","offlinedata":"http://ydschool-online.nos.netease.com/1521164648940_PEPGaoZhong_7.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_8.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":187484,"wordNum":420,"id":"PEPGaoZhong_8","title":"人教版高中英语-选修8","offlinedata":"http://ydschool-online.nos.netease.com/1521164666266_PEPGaoZhong_8.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_9.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":145854,"wordNum":352,"id":"PEPGaoZhong_9","title":"人教版高中英语-选修9","offlinedata":"http://ydschool-online.nos.netease.com/1521164670293_PEPGaoZhong_9.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_10.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":140211,"wordNum":361,"id":"PEPGaoZhong_10","title":"人教版高中英语-选修10","offlinedata":"http://ydschool-online.nos.netease.com/1521164634796_PEPGaoZhong_10.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/3_youdao_PEPGaoZhong_11.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"人教版教材"},"size":120512,"wordNum":309,"id":"PEPGaoZhong_11","title":"人教版高中英语-选修11","offlinedata":"http://ydschool-online.nos.netease.com/1521164639915_PEPGaoZhong_11.zip","cateName":"基础教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/reciteWord_1545032535025_WaiYanSheChuZhong_1.png","bookOrigin":{"originUrl":"","desc":"来源于","originName":"外研社初中教材"},"size":351737,"wordNum":629,"id":"WaiYanSheChuZhong_1","title":"外研社版初中英语-七年级上册","offlinedata":"http://ydschool-online.nos.netease.com/reciteWord_1545032533243_WaiYanSheChuZhong_1.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/reciteWord_1545032535434_WaiYanSheChuZhong_2.png","bookOrigin":{"originUrl":"","desc":"来源于","originName":"外研社初中教材"},"size":247606,"wordNum":438,"id":"WaiYanSheChuZhong_2","title":"外研社版初中英语-七年级下册","offlinedata":"http://ydschool-online.nos.netease.com/reciteWord_1545032493536_WaiYanSheChuZhong_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/reciteWord_1545032535509_WaiYanSheChuZhong_3.png","bookOrigin":{"originUrl":"","desc":"来源于","originName":"外研社初中教材"},"size":184306,"wordNum":320,"id":"WaiYanSheChuZhong_3","title":"外研社版初中英语-八年级上册","offlinedata":"http://ydschool-online.nos.netease.com/reciteWord_1545032532744_WaiYanSheChuZhong_3.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/reciteWord_1545032534561_WaiYanSheChuZhong_4.png","bookOrigin":{"originUrl":"","desc":"来源于","originName":"外研社初中教材"},"size":147166,"wordNum":266,"id":"WaiYanSheChuZhong_4","title":"外研社版初中英语-八年级下册","offlinedata":"http://ydschool-online.nos.netease.com/reciteWord_1545032533455_WaiYanSheChuZhong_4.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/reciteWord_1545032534804_WaiYanSheChuZhong_5.png","bookOrigin":{"originUrl":"","desc":"来源于","originName":"外研社初中教材"},"size":196315,"wordNum":381,"id":"WaiYanSheChuZhong_5","title":"外研社版初中英语-九年级上册","offlinedata":"http://ydschool-online.nos.netease.com/reciteWord_1545032533808_WaiYanSheChuZhong_5.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/reciteWord_1545032534322_WaiYanSheChuZhong_6.png","bookOrigin":{"originUrl":"","desc":"来源于","originName":"外研社初中教材"},"size":72801,"wordNum":128,"id":"WaiYanSheChuZhong_6","title":"外研社版初中英语-九年级下册","offlinedata":"http://ydschool-online.nos.netease.com/reciteWord_1545032534071_WaiYanSheChuZhong_6.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_ChuZhong_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":912718,"wordNum":1420,"id":"ChuZhong_2","title":"初中英语词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164647926_ChuZhong_2.zip","cateName":"基础教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_GaoZhong_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1999328,"wordNum":3668,"id":"GaoZhong_2","title":"高中英语词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164675301_GaoZhong_2.zip","cateName":"基础教育","version":"2"}]},{"cateName":"其他词汇","bookList":[{"cover":"https://nos.netease.com/ydschool-online/youdao_BEC_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1494016,"wordNum":2753,"id":"BEC_2","title":"商务英语词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164626760_BEC_2.zip","cateName":"其他词汇","version":"2"}]}]
     */

    private String reason;
    private int code;
    private List<CatesBean> cates;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<CatesBean> getCates() {
        return cates;
    }

    public void setCates(List<CatesBean> cates) {
        this.cates = cates;
    }

    public static class CatesBean {
        /**
         * cateName : 高等教育
         * bookList : [{"cover":"https://nos.netease.com/ydschool-online/1496632727200CET4luan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":788457,"wordNum":1162,"id":"CET4luan_1","title":"四级真题核心词（图片记忆）","offlinedata":"http://ydschool-online.nos.netease.com/1523620217431_CET4luan_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1496655382926CET6luan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":591417,"wordNum":1228,"id":"CET6luan_1","title":"六级真题核心词（图片记忆）","offlinedata":"http://ydschool-online.nos.netease.com/1521164660466_CET6luan_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1496632762670KaoYanluan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":771889,"wordNum":1341,"id":"KaoYanluan_1","title":"考研必考词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164661106_KaoYanluan_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1496632776935Level4luan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":269844,"wordNum":595,"id":"Level4luan_1","title":"专四真题高频词","offlinedata":"http://ydschool-online.nos.netease.com/1521164630387_Level4luan_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/1491037703359Level8_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":242255,"wordNum":684,"id":"Level8_1","title":"专八真题高频词","offlinedata":"http://ydschool-online.nos.netease.com/1521164635290_Level8_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_CET4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2723074,"wordNum":3739,"id":"CET4luan_2","title":"四级英语词汇","offlinedata":"http://ydschool-online.nos.netease.com/1524052539052_CET4luan_2.zip","cateName":"高等教育","version":"5"},{"cover":"https://nos.netease.com/ydschool-online/youdao_CET6_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1111633,"wordNum":2078,"id":"CET6_2","title":"六级英语词汇","offlinedata":"http://ydschool-online.nos.netease.com/1524052554766_CET6_2.zip","cateName":"高等教育","version":"4"},{"cover":"https://nos.netease.com/ydschool-online/youdao_KaoYan_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2431155,"wordNum":4533,"id":"KaoYan_2","title":"考研英语词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164654696_KaoYan_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1783295,"wordNum":4025,"id":"Level4luan_2","title":"专四核心词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164625401_Level4luan_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level8_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":5320395,"wordNum":12197,"id":"Level8luan_2","title":"专八核心词汇","offlinedata":"http://ydschool-online.nos.netease.com/1521164650006_Level8luan_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/1491037568440CET4_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":764018,"wordNum":1162,"id":"CET4_1","title":"四级真题核心词（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164649209_CET4_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1491037677590CET6_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":753196,"wordNum":1228,"id":"CET6_1","title":"六级真题核心词（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164668667_CET6_1.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/1491037662208KaoYan_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":685507,"wordNum":1341,"id":"KaoYan_1","title":"考研必考词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164669833_KaoYan_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/1491037690141Level4_1.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道考神团队"},"size":267335,"wordNum":595,"id":"Level4_1","title":"专四真题高频词（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164647417_Level4_1.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_CET4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":2542778,"wordNum":3739,"id":"CET4_2","title":"四级英语词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164635506_CET4_2.zip","cateName":"高等教育","version":"3"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level4_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":1706442,"wordNum":4025,"id":"Level4_2","title":"专四核心词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164653685_Level4_2.zip","cateName":"高等教育","version":"2"},{"cover":"https://nos.netease.com/ydschool-online/youdao_Level8_2.jpg","bookOrigin":{"originUrl":"","desc":"来源于","originName":"有道词典"},"size":4763514,"wordNum":12197,"id":"Level8_2","title":"专八核心词汇（正序版）","offlinedata":"http://ydschool-online.nos.netease.com/1521164663794_Level8_2.zip","cateName":"高等教育","version":"2"}]
         */

        private String cateName;
        private List<BookListBean> bookList;

        public String getCateName() {
            return cateName;
        }

        public void setCateName(String cateName) {
            this.cateName = cateName;
        }

        public List<BookListBean> getBookList() {
            return bookList;
        }

        public void setBookList(List<BookListBean> bookList) {
            this.bookList = bookList;
        }

        public static class BookListBean {
            /**
             * cover : https://nos.netease.com/ydschool-online/1496632727200CET4luan_1.jpg
             * bookOrigin : {"originUrl":"","desc":"来源于","originName":"有道考神团队"}
             * size : 788457
             * wordNum : 1162
             * id : CET4luan_1
             * title : 四级真题核心词（图片记忆）
             * offlinedata : http://ydschool-online.nos.netease.com/1523620217431_CET4luan_1.zip
             * cateName : 高等教育
             * version : 3
             */

            private String cover;
            private BookOriginBean bookOrigin;
            private int size;
            private int wordNum;
            private String id;
            private String title;
            private String offlinedata;
            private String cateName;
            private String version;

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }

            public BookOriginBean getBookOrigin() {
                return bookOrigin;
            }

            public void setBookOrigin(BookOriginBean bookOrigin) {
                this.bookOrigin = bookOrigin;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getWordNum() {
                return wordNum;
            }

            public void setWordNum(int wordNum) {
                this.wordNum = wordNum;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getOfflinedata() {
                return offlinedata;
            }

            public void setOfflinedata(String offlinedata) {
                this.offlinedata = offlinedata;
            }

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
            }

            public static class BookOriginBean {
                /**
                 * originUrl :
                 * desc : 来源于
                 * originName : 有道考神团队
                 */

                private String originUrl;
                private String desc;
                private String originName;

                public String getOriginUrl() {
                    return originUrl;
                }

                public void setOriginUrl(String originUrl) {
                    this.originUrl = originUrl;
                }

                public String getDesc() {
                    return desc;
                }

                public void setDesc(String desc) {
                    this.desc = desc;
                }

                public String getOriginName() {
                    return originName;
                }

                public void setOriginName(String originName) {
                    this.originName = originName;
                }
            }
        }
    }
}
