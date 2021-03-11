import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreProtocolPNames;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        String address = "http://localhost:8888/";

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Что вы хотите сделать?");
            System.out.println("Загрузить - 1 / Скачать - 2");
            int flag = scanner.nextInt();

            if (flag == 1) {
                System.out.println("Введите путь до файла: ");
                String pathToFile = scanner.nextLine();
                ResponseDto responseDto = postRequest(address + "/data/", pathToFile);

                // todo
                if (responseDto.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    System.out.println("Some exception: " + responseDto.getResponseString());
                    continue;
                }
            }

            if (flag == 2) {
                System.out.println("Список доступных категорий: ");
                ResponseDto responseDto = getRequest("/category/");

                // todo
                if (responseDto.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    System.out.println("Some exception: " + responseDto.getResponseString());
                    continue;
                }

                String categories = responseDto.getResponseString();

                CategoryDto[] categoryDto =
                        objectMapper.readValue(categories, CategoryDto[].class);

                Arrays.stream(categoryDto).forEach(System.out::println);

                int categoryId = scanner.nextInt();

                if (Arrays.stream(categoryDto).anyMatch(category -> category.getId() == categoryId)) {
                    System.out.println("В бд нет файла с заданной категорией!");
                    continue;
                }

                ResponseDto response = getRequest("/data/" + categoryId);

                // todo
                if (response.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    System.out.println("Some exception: " + response.getResponseString());
                    continue;
                }

                getFile(address + "/data/" + categoryId);

            }

        }
    }

    // посылаем POST запрос
    public static ResponseDto postRequest(String mapping, String filePath)
            throws IOException {

        File f = new File(filePath);
        PostMethod filePost = new PostMethod(mapping);
        Part[] parts = { new FilePart("file", f) };
        filePost.setRequestEntity(new MultipartRequestEntity(parts,
                filePost.getParams()));
        HttpClient client = new HttpClient();
        int status = client.executeMethod(filePost);

        return ResponseDto.builder().responseCode(status).build();

    }

    // посылаем GET запрос
    public static ResponseDto getRequest(String mapping) throws MalformedURLException {
        HttpURLConnection con = getUrlConnection(mapping, "GET");
        assert con != null;
        return getResponse(con);
    }

    public static HttpURLConnection getUrlConnection(String mapping, String method)
            throws MalformedURLException {
        URL url;
        try {
            url = new URL(mapping);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MalformedURLException();
        }
        HttpURLConnection con;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(method);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MalformedURLException();
        }

        return con;
    }

    public static ResponseDto getResponse(HttpURLConnection con)
            throws MalformedURLException {

        try {
            int responseCode = con.getResponseCode();
            String responseMessage = con.getResponseMessage();
            return new ResponseDto(responseCode, responseMessage);
        } catch (IOException e) {
            e.printStackTrace();
            throw new MalformedURLException();
        }
    }

    @SneakyThrows
    public static void getFile(String path) throws MalformedURLException {
        HttpURLConnection conn = getUrlConnection(path, "GET");

        // always check HTTP response code first
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String fileName = "";
            String disposition = conn.getHeaderField("Content-Disposition");
            String contentType = conn.getContentType();
            int contentLength = conn.getContentLength();

            if (disposition != null) {
                // extracts file name from header field
                int index = disposition.indexOf("filename=");
                if (index > 0) {
                    fileName = disposition.substring(index + 10,
                            disposition.length() - 1);
                }
            } else {
                // extracts file name from URL
                fileName = "dowlonded";
            }

            System.out.println("Content-Type = " + contentType);
            System.out.println("Content-Disposition = " + disposition);
            System.out.println("Content-Length = " + contentLength);
            System.out.println("fileName = " + fileName);

            // opens input stream from the HTTP connection
            InputStream inputStream = conn.getInputStream();
            String saveFilePath = fileName;
            // String saveFilePath = saveDir + File.separator + fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            outputStream.close();
            inputStream.close();

            System.out.println("File downloaded");
        } else {
            System.out.println("No file to download. Server replied HTTP code: " + conn.getResponseCode());
        }
        conn.disconnect();
    }

}
