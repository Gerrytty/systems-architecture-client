package dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Long id;
    private String title;
    private List<DataDto> dataList;

    @Override
    public String toString() {
        return id + ") " + title;
    }
}
