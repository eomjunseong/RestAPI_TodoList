package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {

    //REPO 연결
    private final TodoRepository todoRepository;

    //저장
    public TodoEntity add(TodoRequest request){
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setCompleted(request.getCompleted());
        return this.todoRepository.save(todoEntity); //save 저장하고 값을 반환도 함
    }
    //*** 예외찾아봐야함
    public TodoEntity serarchById(Long id){
        return this.todoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }
    public List<TodoEntity> serarchAll(){
       return this.todoRepository.findAll();
    }
    //영속성 컨텍스트 생각.....
    public TodoEntity updateById(Long id,TodoRequest request){
        TodoEntity todoEntity = this.serarchById(id);
        if(request.getTitle()!=null){
            todoEntity.setTitle(request.getTitle());
        }
        if(request.getOrder()!=null){
            todoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted()!=null){
            todoEntity.setCompleted(request.getCompleted());
        }
        return this.todoRepository.save(todoEntity);
    }
    public void deleteById(Long id){
        this.todoRepository.deleteById(id);
    }
    public void deleteAll(){
        this.todoRepository.deleteAll();
    }

}
