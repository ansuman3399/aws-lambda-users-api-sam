package com.aws.lambda.users;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handler for requests to Lambda function.
 */
public class PostUserHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    public APIGatewayProxyResponseEvent handleRequest(final APIGatewayProxyRequestEvent request, final Context context) {

        String body = request.getBody();

        Gson gson = new Gson();
        Map<String, String> userDetails = gson.fromJson(body, Map.class);
        userDetails.put("userId", UUID.randomUUID().toString());

        //preparing the response object
        Map response = new HashMap();
        response.put("firstName", userDetails.get("firstName"));
        response.put("lastName", userDetails.get("firstName"));
        response.put("userId", userDetails.get("userId"));

        //response headers
        Map responseHeaders = new HashMap();
        responseHeaders.put("Content-Type", "application/json");

        return new APIGatewayProxyResponseEvent()
                .withBody(gson.toJson(response, Map.class))
                .withHeaders(responseHeaders)
                .withStatusCode(200);
    }
}
