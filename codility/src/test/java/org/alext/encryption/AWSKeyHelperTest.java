package org.alext.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharSet;
import org.junit.Assert;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.ByteChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

import static org.junit.Assert.*;

public class AWSKeyHelperTest {
    private AWSKeyHelper helper=new AWSKeyHelper();

    @Test
    public void testBuildKeyAndHash() throws Exception {
        AWSKeyHelper.KeyData keyData = helper.buildKeyAndHash("01234567890abcdef");

        byte[] keyBytes = Base64.getDecoder().decode(keyData.getKeyBase64());
        byte[] keyHash = DigestUtils.md5(keyBytes);

        byte[] expectedHash = Base64.getDecoder().decode(keyData.getMd5hashBase64());

        boolean isEqual = Arrays.equals(keyHash, expectedHash);
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testBuildVector(){
        AWSKeyHelper.KeyData keyData = helper.buildKeyAndHash("1234123412341234");

        System.out.println("Result :"+keyData.getKeyBase64());
    }


    @Test
    public void testCheckAWSKeys(){
        final String keyBase64="AQEDAHhnMi87XE+oCQ/4BecsOaWcfyngBRCdG9vQbgxBfceL+AAAAG4wbAYJKoZIhvcNAQcGoF8wXQIBADBYBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDNRgbIPZWIV8McQYgQIBEIAro0f3zjeqPDxALmzJH8zPNZb/an/7/kEimb+zGSbuHv8qBJQZ7GhxoFU/tA==";
        byte[] keyBytes = Base64.getDecoder().decode(keyBase64);

        AWSKeyHelper.KeyData keyData = helper.buildKeyAndHash(keyBytes);

        assertEquals("4J710jXfo6uAeLd+JIuV2Q==",keyData.getMd5hashBase64());
    }

    @Test
    public void  testDecrypt() {
        final String keyBase64="AQEDAHhnMi87XE+oCQ/4BecsOaWcfyngBRCdG9vQbgxBfceL+AAAAG4wbAYJKoZIhvcNAQcGoF8wXQIBADBYBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDNRgbIPZWIV8McQYgQIBEIAro0f3zjeqPDxALmzJH8zPNZb/an/7/kEimb+zGSbuHv8qBJQZ7GhxoFU/tA==";
        String initVectorBase64="3hYf9TJa6rFx04T+oNqXXg==";


        try {
            IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(initVectorBase64));
            SecretKeySpec skeySpec = new SecretKeySpec(Base64.getDecoder().decode(keyBase64), "AES");

            //Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            //pkcs7
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            //Path sampleFile = Paths.get("c:\\liniusFiles\\drm\\aws_transcoder\\enc_autokey_demo.mp4");


            FileInputStream in=new FileInputStream(new File("c:\\liniusFiles\\drm\\aws_transcoder\\enc_autokey_demo.mp4"));
            byte[] encryptedBytes = IOUtils.toByteArray(in);
            byte[] original = cipher.doFinal(encryptedBytes);

            String out =new String(original);
            System.out.println(out.substring(0,100));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}