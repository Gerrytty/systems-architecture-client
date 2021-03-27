import dto.ResponseDto;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLservice {

    public boolean serverError(ResponseDto response) {
        if (response.getResponseCode() != HttpURLConnection.HTTP_OK) {
            System.out.println("Some exception: " + response.getResponseString());
            return true;
        }
        return false;
    }

    public HttpURLConnection getUrlConnection(String mapping, String method)
            throws MalformedURLException {

        URL url;
        try {
            url = new URL(mapping);
        } catch (MalformedURLException e) {
            throw new MalformedURLException();
        }
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
        } catch (IOException e) {
            throw new MalformedURLException();
        }

        return con;
    }

    public ResponseDto getResponse(HttpURLConnection con) throws MalformedURLException {
        try {
            int responseCode = con.getResponseCode();
            return new ResponseDto(responseCode, read(con));
        } catch (IOException e) {
            throw new MalformedURLException();
        }
    }

    public String read(HttpURLConnection con) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();

        return sb.toString();
    }

}
