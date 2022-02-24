package jafar.task.service;


import jafar.task.config.security.UserDetails;
import jafar.task.dto.TodoCreateDto;
import jafar.task.dto.TodoDto;
import jafar.task.dto.TodoUpdateDto;
import jafar.task.entity.Todo;
import jafar.task.mappers.TodoMapper;
import jafar.task.repository.TodoRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final TodoMapper mapper;


    public TodoService(TodoRepository todoRepository, TodoMapper mapper) {
        this.todoRepository = todoRepository;
        this.mapper = mapper;
    }

    public List<TodoDto> getAll() {
        Long id = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<Todo> todoList = todoRepository.find(id);
        return mapper.toDto(todoList);
    }

    public void create(TodoCreateDto dto) {
        Todo todo = mapper.fromCreateDto(dto);
        Long id = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        todo.setUserId(id);
        todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

    // TODO: 2/21/2022 optionalni tekshir
    public TodoDto getTodo(Long id) {
        Optional<Todo> byId = todoRepository.findById(id);
        return mapper.toDto(byId.get());
    }

    public void update(TodoUpdateDto dto) {
        Todo found = todoRepository.findById(dto.getId()).orElseThrow(() -> {
            throw new UsernameNotFoundException("not found");
        });
        if(!found.getUserId().equals(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()))throw new UsernameNotFoundException("not found");
        found.setDescription(dto.getDescription());
        found.setTitle(dto.getTitle());
        todoRepository.save(found);

    }

    public void complete(Long id) {
        Todo found = todoRepository.findById(id).orElseThrow(() -> {
            throw new UsernameNotFoundException("not found");
        });
        if(!found.getUserId().equals(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()))throw new UsernameNotFoundException("not found");
        found.setCompleted(!found.isCompleted());
        todoRepository.save(found);
    }
}
