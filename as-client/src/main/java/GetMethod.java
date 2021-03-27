import dto.ResponseDto;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class GetMethod implements HttpMethodFactory {

    private final HttpURLservice urLservice;

    public GetMethod(HttpURLservice urLservice) {
        this.urLservice = urLservice;
    }

    @Override
    public ResponseDto execute(String mapping, String token, String s) {
        HttpURLConnection con = null;
        try {
            con = urLservice.getUrlConnection(mapping, "GET");
            return urLservice.getResponse(con);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ResponseDto.builder().responseCode(HttpURLConnection.HTTP_BAD_GATEWAY).build();
        }
    }

}
