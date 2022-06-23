package datatransformation;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import datatransformation.pojo.MyLambdaRequest;

import java.util.Date;
import java.util.UUID;

//public class LambdaFunctionHandler implements RequestHandler<MyLambdaRequest, MyLambdaRequest> {
//
//    @Override
//    public MyLambdaRequest handleRequest(MyLambdaRequest input, Context context) {
//        context.getLogger().log("Input: " + input);
//        MyLambdaRequest lambdaResponse = new MyLambdaRequest();
//        try {
//            lambdaResponse.setResponseMessage("Hello " + input.getName() + " Response Time : " + new Date());
//            lambdaResponse.setTransactionID(UUID.randomUUID().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//            lambdaResponse.setResponseMessage(e.getMessage());
//        }
//        context.getLogger().log("Response : " + lambdaResponse);
//        return lambdaResponse;
//    }
//}