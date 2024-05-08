package com.congo.backend.service;

import com.congo.backend.entity.Todo;
import com.congo.backend.repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{

    @Autowired
    private TodoRepo todoRepo;

    @Override
    public List<Todo> getAllTodo() {
        return todoRepo.findAll();
    }

    @Override
    public void saveTodo(Todo todo) {
        this.todoRepo.save(todo);

    }

    @Override
    public Todo getTodoById(Long id) {
        Optional<Todo> optional = todoRepo.findById(id);
        Todo todo;
        if(optional.isPresent()){
            todo =optional.get();
        }
        else{
            throw new RuntimeException("Todo for the id " + id + "is not found");
        }

        return todo;
    }

    @Override
    public void deleteTodo(Long id) {
        this.todoRepo.deleteById(id);
    }
    @Override
    public void updateTodo(Long id, Todo todo) {
        Todo todoFromDb =todoRepo.findById(id).get();
        todoFromDb.setTaskName(todo.getTaskName());
        todoFromDb.setDesc(todo.getDesc());
        todoRepo.save(todoFromDb);

    }
}
