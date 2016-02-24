package cooperate.infrastructure.security;

import cooperate.infrastructure.constant.CommonConstants;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;

public class DesEncrypter {
    static String INITIALIZATION_VECTOR = "AODVNUASDNVVAOVF";

    public static String encrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchProviderException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(CommonConstants.CyrptoKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(INITIALIZATION_VECTOR.getBytes("UTF-8")));
        //must be multiplier of 16
        int mode16 = text.length() % 16;
        for (int i = 0; i < 16 - mode16; i++) {
            text += "\0";
        }
        byte[] encrypted = cipher.doFinal(text.getBytes("UTF-8"));
        byte[] decoded = Base64.getEncoder().encode(encrypted);

        return new String(decoded, "UTF-8");

    }

    public static String decrypt(String text) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchProviderException, UnsupportedEncodingException, InvalidAlgorithmParameterException {

        byte[] decoded = Base64.getDecoder().decode(text.getBytes());
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding", "SunJCE");
        SecretKeySpec key = new SecretKeySpec(CommonConstants.CyrptoKey.getBytes("UTF-8"), "AES");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(INITIALIZATION_VECTOR.getBytes("UTF-8")));
        String result = new String(cipher.doFinal(decoded), "UTF-8");
        return result.trim();
    }
}
