import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class FileService {

    private static final int BUFFER_SIZE = 4096;

    HttpURLservice httpURLservice = new HttpURLservice();

    @SneakyThrows
    public void getFile(String path) throws MalformedURLException {
        HttpURLConnection conn = httpURLservice.getUrlConnection(path, "GET");

        // always check HTTP response code first
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            String fileName = "file";
            String disposition = conn.getHeaderField("Content-Disposition");
            String contentType = conn.getContentType();
            int contentLength = conn.getContentLength();

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

            System.out.println("Файл загружен");
        } else {
            System.out.println("Файл не загружен. Сервер вернул HTTP код: " + conn.getResponseCode());
        }
        conn.disconnect();
    }

}
