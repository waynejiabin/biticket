package com.ruoyi.common.utils.security;

import org.apache.commons.codec.digest.DigestUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptUtils {
    /**
     * 传入文本内容，返回 SHA-256 串
     *
     * @param strText
     * @return
     */
    public String SHA256(final String strText)
    {
        return SHA(strText, "SHA-256");
    }

    /**
     * 传入文本内容，返回 SHA-512 串
     *
     * @param strText
     * @return
     */
    public String SHA512(final String strText)
    {
        return SHA(strText, "SHA-512");
    }

    /**
     * 字符串 SHA 加密
     *
     * @param strText
     * @param strType
     * @return
     */
    private String SHA(final String strText, final String strType)
    {
        // 返回值
        String strResult = null;

        // 是否是有效字符串
        if (strText != null && strText.length() > 0)
        {
            try
            {
                // SHA 加密开始
                // 创建加密对象 并傳入加密類型
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // 传入要加密的字符串
                messageDigest.update(strText.getBytes());
                // 得到 byte 類型结果
                byte byteBuffer[] = messageDigest.digest();

                // 將 byte 轉換爲 string
                StringBuffer strHexString = new StringBuffer();
                // 遍歷 byte buffer
                for (int i = 0; i < byteBuffer.length; i++)
                {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1)
                    {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // 得到返回結果
                strResult = strHexString.toString();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }
        }

        return strResult;
    }
    /**
     * SHA-512加密
     * @param key
     * @param salt
     * @return
     */
    public static String createSecretKey(String key, String salt){
        try{
            MessageDigest digest = DigestUtils.getSha512Digest();
            digest.update(salt.getBytes("UTF-8"));
            byte[] secretKey = digest.digest(key.getBytes("UTF-8"));
            /** 8 10 16 32 进制 java最大密钥128位 8&10&16都是128位  32是64位*/
            return byteHex(secretKey,32);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * byte转换字符串
     * @param data
     * @param radix
     * @return
     */
    private static String byteHex(byte[] data, int radix) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            buf.append(Integer.toString((data[i] & 0xff) + 0x100, radix).substring(1));
        }
        return buf.toString();
    }
}
