package com.appsdeveloperblog.aws.lambda;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
        //download a file

    }
}
