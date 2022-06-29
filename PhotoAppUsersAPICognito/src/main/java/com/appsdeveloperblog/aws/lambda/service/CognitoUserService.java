package com.appsdeveloperblog.aws.lambda.service;

import com.google.gson.JsonObject;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class CognitoUserService {

    private final CognitoIdentityProviderClient client;

    public CognitoUserService(CognitoIdentityProviderClient client) {
        this.client = client;
    }

    public JsonObject createUser(JsonObject user, String appClientId, String appClientSecret) {

        JsonObject storedUser = new JsonObject();

        String email = user.get("email").getAsString();
        String password = user.get("password").getAsString();
        String userId = UUID.randomUUID().toString();
        String firstName = user.get("firstName").getAsString();
        String lastName = user.get("lastName").getAsString();

        AttributeType emailAttribute = AttributeType.builder()
                .name("email")
                .value(email)
                .build();

        AttributeType nameAttribute = AttributeType.builder()
                .name("name")
                .value(firstName + " " + lastName)
                .build();

        AttributeType attributeUserId = AttributeType.builder()
                .name("custom:userId")
                .value(userId)
                .build();

        List<AttributeType> attributes = Arrays.asList(emailAttribute, nameAttribute, attributeUserId);

        String secretHash = calculateSecretHash(appClientId, appClientSecret, email);

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username(email)
                .password(password)
                .userAttributes(attributes)
                .clientId(appClientId)
                .secretHash(secretHash)
                .build();

        return storedUser;
    }

    public static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName) {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }

}
