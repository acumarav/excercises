package org.alext.encryption;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
        decryptor.describeKey();
    }
}