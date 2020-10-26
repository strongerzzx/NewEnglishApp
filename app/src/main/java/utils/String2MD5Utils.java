package utils;

import java.security.MessageDigest;

public class String2MD5Utils {
    public static String hashKeyForCache(String key){
        String cacheKey;
        try {
            MessageDigest digest=MessageDigest.getInstance("MD5");
            digest.update(key.getBytes());
            cacheKey=bytes2HexString(digest.digest());
        } catch (Exception e) {
            e.printStackTrace();
            cacheKey=String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private static String bytes2HexString(byte[] digest) {
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < digest.length; i++) {
            String hex=Integer.toHexString(0xFF & digest[i]);
            if (hex.length()==1){
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }
}
