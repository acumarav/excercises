package org.alext.encryption;

import com.amazonaws.AmazonWebServiceRequest;
import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.kms.AWSKMS;
import com.amazonaws.services.kms.AWSKMSClient;
import com.amazonaws.services.kms.AWSKMSClientBuilder;
import com.amazonaws.services.kms.model.*;
import com.amazonaws.services.s3.AmazonS3EncryptionClient;
import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;

/**
 * Created by alex on 11/13/2016.
 */
public class AWSKeyDecryptor {

    private static AmazonS3EncryptionClient encryptionClient;

    public static void sampleOne() throws Exception {
        String bucketName = "***bucket name***";
        String objectKey = "ExampleKMSEncryptedObject";
        String kms_cmk_id = "***AWS KMS customer master key ID***";

        KMSEncryptionMaterialsProvider materialProvider = new KMSEncryptionMaterialsProvider(kms_cmk_id);

        encryptionClient = new AmazonS3EncryptionClient(new ProfileCredentialsProvider(), materialProvider,
                new CryptoConfiguration().withKmsRegion(Regions.EU_CENTRAL_1))
                .withRegion(Region.getRegion(Regions.EU_CENTRAL_1));

        // Upload object using the encryption client.
        byte[] plaintext = "Hello World, S3 Client-side Encryption Using Asymmetric Master Key!"
                .getBytes();
        System.out.println("plaintext's length: " + plaintext.length);
        encryptionClient.putObject(new PutObjectRequest(bucketName, objectKey, new ByteArrayInputStream(plaintext), new ObjectMetadata()));

        // Download the object.
        S3Object downloadedObject = encryptionClient.getObject(bucketName,
                objectKey);
        byte[] decrypted = IOUtils.toByteArray(downloadedObject
                .getObjectContent());
        // Verify same data.
        //Assert.assertTrue(Arrays.equals(plaintext, decrypted));
    }

    public void describeKey(){
        AWSKMS kms2 = AWSKMSClientBuilder.standard().withRegion(Regions.EU_CENTRAL_1).withCredentials(new ProfileCredentialsProvider()).build();
        String keyId  = "arn:aws:kms:eu-central-1:636713281216:key/0b0c3ae9-f3dc-47c3-8980-5721097eb4c4";

        DescribeKeyRequest req = new DescribeKeyRequest().withKeyId(keyId);
        DescribeKeyResult result = kms2.describeKey(req);

        System.out.println(result.getKeyMetadata());
        System.out.println(result.getKeyMetadata().getDescription());
        System.out.println(result);
    }
}