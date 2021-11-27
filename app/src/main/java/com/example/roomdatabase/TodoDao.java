package com.example.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insertDao(Todo todo);

    @Query("SELECT * FROM todo_table")
    List<Todo> getAllTodos();

    @Query("Select * From todo_table WHERE todo_uid LIKE :uid")
    Todo findTodoById(int uid);

    @Delete
    void deleteTodo(Todo todo);

    @Update
    void UpdateTodo(Todo todo);

    @Insert
    void InsertMultipleTodo(List<Todo> todoList);

    @Query("SELECT * FROM todo_table WHERE todo_completed LIKE 1")      // 1 = true  0 = false
    List<Todo> getAllcompletedTodo();
}
