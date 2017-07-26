package com.luo.util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DecriptUtil {

    /** 
     * @author：罗国辉 
     * @date： 2015年12月17日 上午9:16:22 
     * @description： AES加密
     * @parameter： str：待加密字符串，secretKeyBase：用于生成密钥的基础字符串
     * @return：  加密字节数组
    **/
    public static byte[] encryptAES(String str, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = str.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * @author：罗国辉 
     * @date： 2015年12月17日 上午9:16:22 
     * @description： AES解密
     * @parameter： strByteArray：待解密字节数组，
     * @parameter： secretKeyBase：用于生成密钥的基础字符串， 需要注意的是EAS是对称加密，所以secretKeyBase在加密解密时要一样的
     * @return：  解密后字符串
    **/
    public static String decryptAES(byte[] strByteArray, String secretKeyBase) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(secretKeyBase.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            String result = new String(cipher.doFinal(strByteArray),"UTF-8");
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * @author：罗国辉 
     * @date： 2015年12月17日 上午9:24:43 
     * @description： SHA、SHA1加密
     * @parameter：   str：待加密字符串
     * @return：  加密串
    **/
    public static String SHA1(String str) {
        try {
            MessageDigest digest = java.security.MessageDigest
                    .getInstance("SHA-1"); //如果是SHA加密只需要将"SHA-1"改成"SHA"即可
            digest.update(str.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexStr = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexStr.append(0);
                }
                hexStr.append(shaHex);
            }
            return hexStr.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * @author：罗国辉 
     * @date： 2015年12月17日 上午9:24:43 
     * @description： MD5加密
     * @parameter：   str：待加密字符串
     * @return：  加密串
    **/
    public static String MD5(String str) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < md.length; i++) {
                String shaHex = Integer.toHexString(md[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * @author：罗国辉 
     * @date： 2015年12月17日 上午9:24:43 
     * @description： MD5加密 返回二进制
     * @parameter：   str：待加密字符串
     * @return：  加密二进制
    **/
    public static byte[] MD5ToByteArray(String str) {
        try {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(str.getBytes());
            // 获得密文
            byte[] md = mdInst.digest();
            return md;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        //创建一个Buffer字符串  
        byte[] buffer = new byte[1024];  
        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
        int len = 0;  
        //使用一个输入流从buffer里把数据读取出来  
        while( (len=inStream.read(buffer)) != -1 ){  
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
            outStream.write(buffer, 0, len);  
        }  
        //关闭输入流  
        inStream.close();  
        //把outStream里的数据写入内存  
        return outStream.toByteArray();  
    }  
    public String  CreateFile(InputStream inStream,String FilePath) throws Exception{
    	byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File(FilePath);  
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();  
        String filefull=FilePath.substring(0, FilePath.lastIndexOf(".")+1)+"tiff";
        return filefull;
    }

}