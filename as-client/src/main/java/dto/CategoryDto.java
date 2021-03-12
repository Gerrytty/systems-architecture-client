package dto;

import lombok.Data;

@Data
public class CategoryDto {
    private Long id;
    private String title;

    @Override
    public String toString() {
        return id + ") " + title;
    }
}
