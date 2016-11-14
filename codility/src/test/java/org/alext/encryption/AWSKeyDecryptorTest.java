package org.alext.encryption;

import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.DescribeKeyResult;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 11/13/2016.
 */
public class AWSKeyDecryptorTest {

    private AWSKeyDecryptor decryptor;

    @Before
    public void setUp() throws Exception {
        decryptor=new AWSKeyDecryptor();
    }

    @Test
    public void testDescribeKey() throws Exception {
        DescribeKeyResult ffKey = decryptor.findFrankfutKeyKey();

        Assert.assertEquals("vidkey Frankfurt", ffKey.getKeyMetadata().getDescription());
    }

    @Test
    public void testDecodeEncryptionKey(){
        String newkey="AQEDAHhnMi87XE+oCQ/4BecsOaWcfyngBRCdG9vQbgxBfceL+AAAAG4wbAYJKoZIhvcNAQcGoF8wXQIBADBYBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDCV1zwV52WzlLsqyYwIBEIArYoqUQg5g1m1M/2BBx4CMaysdoque6ZAneA32bLrACSeWkAQ/XehnnagYYQ==";
        byte[] encryptionKeyBytes = Base64.getDecoder().decode(newkey);

        Map<String, String> transcoderEncryptionContext = new HashMap<String, String>();
        transcoderEncryptionContext.put("service", "elastictranscoder.amazonaws.com");

        DecryptResult decrKeyResults = decryptor.decodeStringWithKey(encryptionKeyBytes, transcoderEncryptionContext);

        ByteBuffer clearKeyButes = decrKeyResults.getPlaintext();
        String key=Base64.getEncoder().encodeToString(clearKeyButes.array());
        System.out.println("Key (base64): " +key);
        Assert.assertNotNull(decrKeyResults);

       /* String initVector="3hYf9TJa6rFx04T+oNqXXg==";
        DecryptResult decrVectorResults = decryptor.decodeStringWithKey(Base64.getDecoder().decode(initVector), transcoderEncryptionContext);
        String vector=Base64.getEncoder().encodeToString(decrVectorResults.getPlaintext().array());
        System.out.println("Vertor (base64): "+vector);*/
    }

    @Test
    public void testDecryptAES128CBCFile() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        String keyB64="LVfCLHKhkjceHYqnT7RfXw==";
        String initVector="3hYf9TJa6rFx04T+oNqXXg==";

        //Cipher aesCipher = Cipher.getInstance("AES");
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = Base64.getDecoder().decode(keyB64);
        IvParameterSpec ivspec = new IvParameterSpec(Base64.getDecoder().decode(initVector));
        SecretKeySpec key=new SecretKeySpec(keyBytes,"AES");
        aesCipher.init(Cipher.DECRYPT_MODE,key,ivspec);

        //ReadableByteChannel inputChannel = Channels.newChannel(FileUtils.openInputStream());
        byte[] inBytes = FileUtils.readFileToByteArray(new File("c:\\liniusFiles\\drm\\aws_transcoder\\enc_autokey_demo.mp4"));

        byte[] outBytes = aesCipher.doFinal(inBytes);
        File outFile = new File("c:\\liniusFiles\\drm\\aws_transcoder\\decrypted_autokey_demo.mp4");
        if(outFile.exists()){
            outFile.delete();
        }

        FileUtils.writeByteArrayToFile(outFile,outBytes);
    }

    @Test
    public void testSecondFileNov13() throws Exception {
        String encryptedKeyBase64="AQEDAHhnMi87XE+oCQ/4BecsOaWcfyngBRCdG9vQbgxBfceL+AAAAG4wbAYJKoZIhvcNAQcGoF8wXQIBADBYBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDCV1zwV52WzlLsqyYwIBEIArYoqUQg5g1m1M/2BBx4CMaysdoque6ZAneA32bLrACSeWkAQ/XehnnagYYQ==";
        String vector="t4aJsOMvBiKgqeNhh7fonA==";

        Map<String, String> transcoderEncryptionContext = new HashMap<String, String>();
        transcoderEncryptionContext.put("service", "elastictranscoder.amazonaws.com");

        DecryptResult decrKeyResults = decryptor.decodeStringWithKey(Base64.getDecoder().decode(encryptedKeyBase64), transcoderEncryptionContext);

        SecretKeySpec keySpec=new SecretKeySpec(decrKeyResults.getPlaintext().array(),"AES");
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivspec = new IvParameterSpec(Base64.getDecoder().decode(vector));
        aesCipher.init(Cipher.DECRYPT_MODE,keySpec, ivspec);

        byte[] inBytes = FileUtils.readFileToByteArray(new File("e:\\videos\\liniusFiles\\enc_web_frankey_sample.mp4"));
        byte[] outBytes = aesCipher.doFinal(inBytes);
        /*ByteBuffer clearKeyButes = decrKeyResults.getPlaintext();
        String key=Base64.getEncoder().encodeToString(clearKeyButes.array());
        System.out.println("Key (base64): " +key);
        Assert.assertNotNull(decrKeyResults);*/
    }


    @Test
    public void testEncryptDecryptLoop(){
        final String msg ="Message to encrypt";

        ByteBuffer encrypted = decryptor.encrypt(msg);
        String message = decryptor.decrypt(encrypted);

        Assert.assertEquals(msg, message);
    }

    @Test
    public void auxTest(){
        String newkey="AQEDAHhnMi87XE+oCQ/4BecsOaWcfyngBRCdG9vQbgxBfceL+AAAAG4wbAYJKoZIhvcNAQcGoF8wXQIBADBYBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDCV1zwV52WzlLsqyYwIBEIArYoqUQg5g1m1M/2BBx4CMaysdoque6ZAneA32bLrACSeWkAQ/XehnnagYYQ==";
        byte[] keyBytes = Base64.getDecoder().decode(newkey);

        String out=new String(keyBytes);

        System.out.println(out);
    }
}