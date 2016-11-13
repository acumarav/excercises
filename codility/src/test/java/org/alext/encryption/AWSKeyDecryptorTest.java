package org.alext.encryption;

import com.amazonaws.services.kms.model.DecryptResult;
import com.amazonaws.services.kms.model.DescribeKeyResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.util.Base64;

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
        ///String key="AQEDAHhnMi87XE+oCQ/4BecsOaWcfyngBRCdG9vQbgxBfceL+AAAAG4wbAYJKoZIhvcNAQcGoF8wXQIBADBYBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDNRgbIPZWIV8McQYgQIBEIAro0f3zjeqPDxALmzJH8zPNZb/an/7/kEimb+zGSbuHv8qBJQZ7GhxoFU/tA==";
        String newkey="AQEDAHhnMi87XE+oCQ/4BecsOaWcfyngBRCdG9vQbgxBfceL+AAAAG4wbAYJKoZIhvcNAQcGoF8wXQIBADBYBgkqhkiG9w0BBwEwHgYJYIZIAWUDBAEuMBEEDCV1zwV52WzlLsqyYwIBEIArYoqUQg5g1m1M/2BBx4CMaysdoque6ZAneA32bLrACSeWkAQ/XehnnagYYQ==";
        byte[] encryptionKeyBytes = Base64.getDecoder().decode(newkey);

        DecryptResult decryptResult = decryptor.decodeStringWithKey(encryptionKeyBytes);

        Assert.assertNotNull(decryptResult);
    }


    @Test
    public void testEncryptDecryptLoop(){
        final String msg ="Message to encrypt";

        ByteBuffer encrypted = decryptor.encrypt(msg);
        String message = decryptor.decrypt(encrypted);

        Assert.assertEquals(msg, message);
    }
}