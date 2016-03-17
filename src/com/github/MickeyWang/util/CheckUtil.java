
package com.github.MickeyWang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**<p>Title: 校验signature</p>
 * <p>Description: 开发者通过检验signature对请求进行校验（下面有校验方式）。</p>
 * @author MickeyWang
 * @version 1.0
 * @since 2016年2月3日 上午12:03:46
 */
public class CheckUtil {
	
	private static final String token = "test";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		
		//加密/校验流程如下：
		String[] arr = new String[]{token,timestamp,nonce};
		
		//1. 将token、timestamp、nonce三个参数进行字典序排序
		Arrays.sort(arr);
		
		//2. 将三个参数字符串拼接成一个字符串进行sha1加密
		StringBuffer content = new StringBuffer();
		for (String string : arr) {
			content.append(string);
		}
		//sha1加密
		String temp = SHA1(content.toString());
		
        //3. 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		return temp.equals(signature);
	}
	
	/**
	 * <p>Title: sha1</p>
	 * <p>Description: sha1加密</p>
	 * @version 1.0
	 * @author MickeyWang
	 * @since 2016年2月3日 上午12:13:55
	 */
	public static String SHA1(String decript) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
 
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
