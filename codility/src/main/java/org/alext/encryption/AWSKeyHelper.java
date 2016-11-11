package org.alext.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.CharSet;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by tsumaraa on 20/09/2016.
 */
public class AWSKeyHelper {
    public final class KeyData {
        private Charset charset= Charset.forName("ASCII");
        private final byte[] key;
        private final byte[] md5hash;

        public KeyData(byte[] key, byte[] md5hash) {
            this.key = key;
            this.md5hash = md5hash;
        }

        public byte[] getKey() {
            return key;
        }

        public String getKeyBase64() {

            return Base64.getEncoder().encodeToString(key);
            //charset.de
        }

        public byte[] getMd5hash() {
            return md5hash;
        }

        public String getMd5hashBase64() {
            return Base64.getEncoder().encodeToString(md5hash);
        }
    }

    public KeyData buildKeyAndHash(String inputKey) {

        byte[] ascii = Charset.forName("ASCII").encode(inputKey).array();
        byte[] md5HashBytes = DigestUtils.md5(ascii);

        KeyData data=new KeyData(ascii, md5HashBytes);

        StringBuilder sb = new StringBuilder();
        System.out.println("Input Key: " + inputKey);
        System.out.println("Base64 Key: " + data.getKeyBase64());
        System.out.println("Base64 MD5 Hash: " + data.getMd5hashBase64());


        return data;
    }

    public KeyData buildKeyAndHash(byte[] inputKey) {

        byte[] bytes=inputKey;
        byte[] md5HashBytes = DigestUtils.md5(bytes);

        KeyData data=new KeyData(bytes, md5HashBytes);

        StringBuilder sb = new StringBuilder();
        System.out.println("Input Key: " + inputKey);
        System.out.println("Base64 Key: " + data.getKeyBase64());
        System.out.println("Base64 MD5 Hash: " + data.getMd5hashBase64());

        return data;
    }
}
