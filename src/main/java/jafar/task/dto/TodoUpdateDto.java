package jafar.task.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoUpdateDto {
    private Long id;
    private String title;
    private String description;
}
