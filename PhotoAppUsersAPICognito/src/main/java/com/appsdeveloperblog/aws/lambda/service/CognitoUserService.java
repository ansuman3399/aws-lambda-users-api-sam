package com.appsdeveloperblog.aws.lambda.service;

import com.google.gson.JsonObject;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CognitoUserService {

    public JsonObject createUser(JsonObject user , String appClientId) {

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

        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username(email)
                .password(password)
                .userAttributes(attributes)
                .clientId(appClientId)
                .secretHash("")
                .build();

        return storedUser;
    }
}
