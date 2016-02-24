package cooperate.infrastructure.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityHelper {
    private static final String salt = "ka>dkl9((şlkasd118*/39ıiaşd";

    public static byte[] Encrypt(String passwordToHash) {
        byte[] bytes = null;
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            bytes = md.digest(passwordToHash.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;
    }


}
