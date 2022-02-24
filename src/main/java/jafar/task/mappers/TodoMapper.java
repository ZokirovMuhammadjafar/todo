package jafar.task.mappers;

import jafar.task.dto.TodoCreateDto;
import jafar.task.dto.TodoDto;
import jafar.task.dto.TodoUpdateDto;
import jafar.task.entity.Todo;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@Mapper(componentModel = "spring")
public interface TodoMapper {
    TodoDto toDto(Todo todo);

    List<TodoDto> toDto(List<Todo> todo);
    Todo fromUpdateDto(TodoUpdateDto dto);

    Todo fromCreateDto(TodoCreateDto dto);
}
