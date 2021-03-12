import com.fasterxml.jackson.databind.ObjectMapper;
import dto.CategoryDto;
import dto.ResponseDto;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static HttpURLservice urLservice = new HttpURLservice();
    static FileService fileService = new FileService();
    static ObjectMapper objectMapper = new ObjectMapper();
    static Scanner scanner = new Scanner(System.in);

    static String address = "http://localhost:8080/";

    public static void main(String[] args) throws IOException {

        while (true) {

            System.out.println("Что вы хотите сделать?");
            System.out.println("Загрузить - 1 / Скачать - 2");
            int flag = scanner.nextInt();

            if (flag == 1) {
                if (!uploadFile()) {
                    continue;
                }
            }

            if (flag == 2) {
                System.out.println("Список доступных категорий: ");
                ResponseDto responseDto = urLservice.getRequest(address + "category");

                if (urLservice.serverError(responseDto)) {
                    continue;
                }

                String categories = responseDto.getResponseString();
                CategoryDto[] categoryDto = objectMapper.readValue(categories, CategoryDto[].class);

                if (!noFiles(categoryDto)) {
                    continue;
                }

                Arrays.stream(categoryDto).forEach(System.out::println);

                int categoryId = scanner.nextInt();

                if (!categoryExists(categoryDto, categoryId)) {
                    continue;
                }

                fileService.getFile(address + "data/" + categoryId);

            }

        }
    }

    public static boolean uploadFile() throws IOException {
        System.out.print("Введите путь до файла: ");
        String pathToFile = scanner.nextLine();
        pathToFile = scanner.nextLine();
        ResponseDto responseDto = urLservice.postRequest(address + "/data/", pathToFile);

        if (urLservice.serverError(responseDto)) {
            return false;
        }

        System.out.println("Файл успешно загружен на сервер");

        return true;
    }

    public static boolean categoryExists(CategoryDto[] categoryDto, int categoryId) {
        if (Arrays.stream(categoryDto).noneMatch(category -> category.getId() == categoryId)) {
            System.out.println("В бд нет файла с заданной категорией!");
            return false;
        }
        return true;
    }

    public static boolean noFiles(CategoryDto[] categoryDto) {
        if (categoryDto.length == 0) {
            System.out.println("В данный момент, загруженные файлы отсутсввуют");
            return false;
        }
        return true;
    }

}
