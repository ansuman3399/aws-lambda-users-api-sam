package datatransformation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import java.io.InputStream;
import java.io.OutputStream;

public class LambdaRequestHandler implements RequestHandler<String, String> {
    public String handleRequest(String input, Context context) {
        context.getLogger().log("Input: " + input);
        return "Hello World - " + input;
    }

//    public class LambdaRequestStreamHandler
//            implements RequestStreamHandler {
//        public void handleRequest(InputStream inputStream,
//                                  OutputStream outputStream, Context context) {
//            String input = IOUtils.toString(inputStream, "UTF-8");
//            outputStream.write(("Hello World - " + input).getBytes());
//        }
//    }
}
