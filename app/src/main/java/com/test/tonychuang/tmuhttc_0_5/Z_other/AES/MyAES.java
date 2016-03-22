package com.test.tonychuang.tmuhttc_0_5.Z_other.AES;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加解密字串
 * Created by TonyChuang on 2016/3/21.
 */
public class MyAES {
    private String IvAES = "TMU12345TUM12345";
    private String KeyAES = "TMU12345TUM12345TMU12345TUM12345";

    public MyAES() {
    }


    public MyAES(String ivAES, String keyAES) {
        IvAES = ivAES;
        KeyAES = keyAES;
    }

    //AES加密
    public String EncryptAES(String TextAES) {
        byte[] TextByte = new byte[0];
        try {
            TextByte = EncryptAES(IvAES.getBytes("UTF-8"), KeyAES.getBytes("UTF-8"), TextAES.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return Base64.encodeToString(TextByte, Base64.DEFAULT);
    }

    //AES解密
    public String DecryptAES(String TextAES) {
        byte[] TextByte;
        try {
            TextByte = DecryptAES(IvAES.getBytes("UTF-8"), KeyAES.getBytes("UTF-8"), Base64.decode(TextAES.getBytes("UTF-8"), Base64.DEFAULT));
            return new String(TextByte,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    //AES加密，帶入byte[]型態的16位英數組合文字、32位英數組合Key、需加密文字
    private static byte[] EncryptAES(byte[] iv, byte[] key, byte[] text) {
        try {
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(key, "AES");
            Cipher mCipher;
            mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            mCipher.init(Cipher.ENCRYPT_MODE, mSecretKeySpec, mAlgorithmParameterSpec);

            return mCipher.doFinal(text);
        } catch (Exception ex) {
            return null;
        }
    }

    //AES解密，帶入byte[]型態的16位英數組合文字、32位英數組合Key、需解密文字
    private static byte[] DecryptAES(byte[] iv, byte[] key, byte[] text) {
        try {
            AlgorithmParameterSpec mAlgorithmParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec mSecretKeySpec = new SecretKeySpec(key, "AES");
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            mCipher.init(Cipher.DECRYPT_MODE,
                    mSecretKeySpec,
                    mAlgorithmParameterSpec);

            return mCipher.doFinal(text);
        } catch (Exception ex) {
            return null;
        }
    }
}
