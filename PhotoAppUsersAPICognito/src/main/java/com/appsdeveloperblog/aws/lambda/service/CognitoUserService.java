package com.appsdeveloperblog.aws.lambda.service;

import com.google.gson.JsonObject;
import software.amazon.awssdk.services.cognitoidentityprovider.model.SignUpRequest;

public class CognitoUserService {

    public JsonObject createUser(JsonObject user) {
        JsonObject storedUser = new JsonObject();

        String userName = user.get("email").getAsString();
        String password = user.get("password").getAsString();
        SignUpRequest signUpRequest = SignUpRequest.builder()
                .username(userName)
                .password(password)
                .build();
        return storedUser;
    }
}
