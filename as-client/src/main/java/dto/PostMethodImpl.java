package dto;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.methods.PostMethod;

public class PostMethodImpl {

    public PostMethod create(String mapping, String token) {
        PostMethod postMethod = new PostMethod(mapping);
        postMethod.addRequestHeader(new Header("Autorization", token));
        return postMethod;
    }

}
