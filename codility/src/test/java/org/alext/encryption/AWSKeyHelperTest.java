package org.alext.encryption;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.CharSet;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.Charset;
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

}