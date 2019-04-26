import com.google.gson.Gson;

import static spark.Spark.*;

public class RestServer {
    public static void main(String[] args) {
        final AgencyService agencyService = new AgencyServiceImpl();

        get("/agencias", (request, response) -> {
            request.headers("Access-Control-Allow-Origin");
            response.type("application/json");
            MyLogger.generateLogger(request.url()+"/"+request.queryString());
            try {
                return new Gson().toJson(agencyService.llamarApi(request.queryParams("site_id"),
                        request.queryParams("payment_method_id"), request.queryParams("near_to"),
                        request.queryParams("limit"), request.queryParams("offset"), request.queryParams("orderBy")));
            }catch (CustomException custom){
                return new Gson().toJson(new StandardResponse("ERROR", custom.getMessage()));
            }


        });
    }
}
