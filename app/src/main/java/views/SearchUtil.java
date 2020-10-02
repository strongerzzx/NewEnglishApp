package views;

public class SearchUtil {

    private static final String TAG = "SearchUtil";

    public static int bf(String source, String target){
        int slen = source.length();
        int tlen = target.length();
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        int i=0;
        int j=0;
        if (slen<tlen)
            return -1; //如果主串长度小于模式串，直接返回-1，匹配失败
        else{
            while (i<slen && j<tlen){
                if (s[i]==t[j]){//如果i,j位置上的字符匹配成功就继续向后匹配
                    ++i;
                    ++j;
                }else{
                    i=i-(j-1);  //i回溯到主串上一次开始匹配下一个位置的地方
                    j=0;  //j重置，模式串从开始再次进行匹配
                }
            }
            if (j==tlen){  //匹配成功
                return i-j;
            }else{
                return -1;
            }
        }
    }

}
