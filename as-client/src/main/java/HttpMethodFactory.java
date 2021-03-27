import dto.ResponseDto;

public interface HttpMethodFactory {

    ResponseDto execute(String mapping, String token, String s);

}
