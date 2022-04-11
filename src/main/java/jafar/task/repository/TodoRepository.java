package jafar.task.repository;

import jafar.task.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {


    @Query(value = "select * from todo  t where t.user_id = :id order by completed ",nativeQuery = true)
    List<Todo> find(@Param("id")Long id);

    List<Todo> findAllByUserId(@Param("id")Long id);



}
