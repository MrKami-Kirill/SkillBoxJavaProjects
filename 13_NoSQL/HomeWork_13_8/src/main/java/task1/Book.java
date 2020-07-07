package task1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book
{
    private String name;

    private String author;

    private Integer year;
}
