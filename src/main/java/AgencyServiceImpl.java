import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class AgencyServiceImpl implements AgencyService {

    private static HashMap<String, Agency> AgencyMap = new HashMap<String, Agency>();

    public Collection<Agency> llamarApi(String site_id, String payment_method, String near_to, String limit, String offset, String criterio)
            throws CustomException {

        try {
            if(limit == null) {
                limit = "50";
            }
            if(offset == null) {
                offset="0";
            }

            String data = readUrl("https://api.mercadolibre.com/sites/"+site_id+"/payment_methods/"+payment_method+"/agencies" +
                    "?near_to="+near_to+"&limit="+limit+"&offset="+offset);

            JsonObject jsonObject = new JsonParser().parse(data).getAsJsonObject();
            Agency[] agencies = new Gson().fromJson(jsonObject.get("results"), Agency[].class);

            if(criterio != null){
                switch (criterio){
                        case "distance":
                            Agency.criterio = Criterio.DISTANCE;
                            break;
                        case "agency_code":
                            Agency.criterio = Criterio.AGENCY_CODE;
                            break;
                        case "address_line":
                            Agency.criterio = Criterio.ADDRESS_LINE;
                            break;
                }

            agencies = Operador.ordenarElemento(agencies);
            }



            for (Agency agencia: agencies){
                AgencyMap.put(agencia.getId(), agencia);
            }

        } catch (IOException e) {
                throw new CustomException("Revise los paramatros enviados");

        }
            return AgencyMap.values();

    }

    private String readUrl(String urlString) throws IOException {

        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Accept", "application/json");
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuffer buffer = new StringBuffer();
            char[] chars = new char[1024];
            int read = 0;

            while((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0,read);
            }
            return buffer.toString();
        }finally {
            if(reader != null){
                reader.close();
            }
        }

    }


}
