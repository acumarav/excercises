package org.alext.encryption;

import com.amazonaws.services.kms.model.DescribeKeyResult;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
}