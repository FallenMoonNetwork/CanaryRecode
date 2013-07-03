package net.minecraft.server;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.crypto.BufferedBlockCipher;
import org.bouncycastle.crypto.engines.AESFastEngine;
import org.bouncycastle.crypto.io.CipherInputStream;
import org.bouncycastle.crypto.io.CipherOutputStream;
import org.bouncycastle.crypto.modes.CFBBlockCipher;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


public class CryptManager {

    public static KeyPair b() {
        try {
            KeyPairGenerator keypairgenerator = KeyPairGenerator.getInstance("RSA");

            keypairgenerator.initialize(1024);
            return keypairgenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
            nosuchalgorithmexception.printStackTrace();
            System.err.println("Key pair generation failed!");
            return null;
        }
    }

    public static byte[] a(String s0, PublicKey publickey, SecretKey secretkey) {
        try {
            return a("SHA-1", new byte[][]{ s0.getBytes("ISO_8859_1"), secretkey.getEncoded(), publickey.getEncoded() });
        } catch (UnsupportedEncodingException unsupportedencodingexception) {
            unsupportedencodingexception.printStackTrace();
            return null;
        }
    }

    private static byte[] a(String s0, byte[]... abyte) {
        try {
            MessageDigest messagedigest = MessageDigest.getInstance(s0);
            byte[][] abyte0 = abyte;
            int i0 = abyte.length;

            for (int i1 = 0; i1 < i0; ++i1) {
                byte[] abyte1 = abyte0[i1];

                messagedigest.update(abyte1);
            }

            return messagedigest.digest();
        } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
            nosuchalgorithmexception.printStackTrace();
            return null;
        }
    }

    public static PublicKey a(byte[] abyte) {
        try {
            X509EncodedKeySpec x509encodedkeyspec = new X509EncodedKeySpec(abyte);
            KeyFactory keyfactory = KeyFactory.getInstance("RSA");

            return keyfactory.generatePublic(x509encodedkeyspec);
        } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
            nosuchalgorithmexception.printStackTrace();
        } catch (InvalidKeySpecException invalidkeyspecexception) {
            invalidkeyspecexception.printStackTrace();
        }

        System.err.println("Public key reconstitute failed!");
        return null;
    }

    public static SecretKey a(PrivateKey privatekey, byte[] abyte) {
        return new SecretKeySpec(b(privatekey, abyte), "AES");
    }

    public static byte[] b(Key key, byte[] abyte) {
        return a(2, key, abyte);
    }

    private static byte[] a(int i0, Key key, byte[] abyte) {
        try {
            return a(i0, key.getAlgorithm(), key).doFinal(abyte);
        } catch (IllegalBlockSizeException illegalblocksizeexception) {
            illegalblocksizeexception.printStackTrace();
        } catch (BadPaddingException badpaddingexception) {
            badpaddingexception.printStackTrace();
        }

        System.err.println("Cipher data failed!");
        return null;
    }

    private static Cipher a(int i0, String s0, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(s0);

            cipher.init(i0, key);
            return cipher;
        } catch (InvalidKeyException invalidkeyexception) {
            invalidkeyexception.printStackTrace();
        } catch (NoSuchAlgorithmException nosuchalgorithmexception) {
            nosuchalgorithmexception.printStackTrace();
        } catch (NoSuchPaddingException nosuchpaddingexception) {
            nosuchpaddingexception.printStackTrace();
        }

        System.err.println("Cipher creation failed!");
        return null;
    }

    private static BufferedBlockCipher a(boolean flag0, Key key) {
        BufferedBlockCipher bufferedblockcipher = new BufferedBlockCipher(new CFBBlockCipher(new AESFastEngine(), 8));

        bufferedblockcipher.init(flag0, new ParametersWithIV(new KeyParameter(key.getEncoded()), key.getEncoded(), 0, 16));
        return bufferedblockcipher;
    }

    public static OutputStream a(SecretKey secretkey, OutputStream outputstream) {
        return new CipherOutputStream(outputstream, a(true, secretkey));
    }

    public static InputStream a(SecretKey secretkey, InputStream inputstream) {
        return new CipherInputStream(inputstream, a(false, secretkey));
    }

    static {
        Security.addProvider(new BouncyCastleProvider());
    }
}
