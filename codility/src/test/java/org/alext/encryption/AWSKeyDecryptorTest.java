package org.alext.encryption;

import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.DescribeKeyResult;
import com.fasterxml.jackson.databind.deser.Deserializers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
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

        DecryptResult decryptResult = decryptor.decodeStringWithKey(encryptionKeyBytes, transcoderEncryptionContext);

        ByteBuffer clearKeyButes = decryptResult.getPlaintext();
        String key=Base64.getEncoder().encodeToString(clearKeyButes.array());

        System.out.println("Key (base64): " +key);

        Assert.assertNotNull(decryptResult);
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