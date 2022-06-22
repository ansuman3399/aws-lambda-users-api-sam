package datatransformation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Handler for requests to Lambda function.
 */
public class PostHandler implements RequestHandler<Map<String, String>, Map<String, String>> {

    public Map<String, String> handleRequest(final Map<String, String> input, final Context context) {

        //reading the value
        String firstName = input.get("firstName");
        String lastname = input.get("lastName");
        String email = input.get("email");
        String password = input.get("password");
        String repeatPassword = input.get("repeatPassword");

        LambdaLogger logger = context.getLogger();

        logger.log(firstName + "-" + lastname + "-" + email + "-" + password + "-" + repeatPassword);

        //setting the response
        Map<String, String> response = new HashMap<>();
        response.put("firstName",firstName);
        response.put("lastName",lastname);
        response.put("email",email);
        response.put("id", UUID.randomUUID().toString());

        //returning the response
        return response;
    }
}
