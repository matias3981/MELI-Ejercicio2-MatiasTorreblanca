import java.util.Collection;


public interface AgencyService {
    public Collection<Agency> llamarApi(String site_id,String payment_method, String near_to,
                                        String limit, String offset, String criterio) throws CustomException;
}
