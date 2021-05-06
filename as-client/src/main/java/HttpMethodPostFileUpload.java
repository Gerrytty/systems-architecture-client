import dto.PostMethodImpl;
import dto.ResponseDto;
import lombok.SneakyThrows;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import java.io.File;

public class HttpMethodPostFileUpload implements HttpMethodFactory {

    private final PostMethodImpl postMethod = new PostMethodImpl();

    @SneakyThrows
    @Override
    public ResponseDto execute(String mapping, String token, String s) {

        PostMethod filePost = postMethod.create(mapping, token);

        File f = new File(s);
        Part[] parts = { new FilePart("file", f) };
        filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
        HttpClient client = new HttpClient();
        int status = client.executeMethod(filePost);

        return ResponseDto.builder().responseCode(status).build();
    }
}
