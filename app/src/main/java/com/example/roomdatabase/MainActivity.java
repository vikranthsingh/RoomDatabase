package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertSingleTodo(View view) {
        Todo todo = new Todo("Lets Rolls", false);
        InsertAsyncTask insertAsyncTask = new InsertAsyncTask();
        insertAsyncTask.execute(todo);

    }

    public void getAllTodos(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Todo> todoList = TodoRoomDatabase.getInstance(MainActivity.this).todoDao().getAllTodos();
                Log.i(TAG, String.valueOf(todoList));
            }
        });
        thread.start();
    }

    public void deleteATodo(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Todo todo = TodoRoomDatabase.getInstance(MainActivity.this)
                        .todoDao()
                        .findTodoById(3);

                Log.i(TAG, "run: " + todo.toString());

                TodoRoomDatabase.getInstance(MainActivity.this)
                        .todoDao()
                        .deleteTodo(todo);
                Log.i(TAG, "run: todo is deleted");
            }
        });
        thread.start();
    }

    public void updateATodo(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Todo todo = TodoRoomDatabase.getInstance(MainActivity.this)
                        .todoDao()
                        .findTodoById(1);
                todo.setCompleted(true);
                if (todo != null) {
                    TodoRoomDatabase.getInstance(MainActivity.this)
                            .todoDao()
                            .UpdateTodo(todo);
                    Log.i(TAG, "run: todo has been updated");
                }
            }
        });
        thread.start();
    }

    public void insertMultipleTodos(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Todo> todoList = new ArrayList<>();
                todoList.add(new Todo("Make a video on Angular", false));
                todoList.add(new Todo("Make a video on Android", false));
                todoList.add(new Todo("Make a video on ReactJS", false));
                TodoRoomDatabase.getInstance(MainActivity.this)
                        .todoDao()
                        .InsertMultipleTodo(todoList);
                Log.i(TAG, "run: multiple todos added ");
            }
        });
        thread.start();
    }

    public void findCompletedTodos(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Todo> todoList =
                        TodoRoomDatabase.getInstance(MainActivity.this).todoDao().getAllcompletedTodo();
                Log.i(TAG, "run: todo completed " + todoList.toString());
            }
        });
        thread.start();
    }

    class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {

        @Override
        protected Void doInBackground(Todo... todos) {
            TodoRoomDatabase.getInstance(getApplicationContext()).todoDao().insertDao(todos[0]);
            return null;
        }
    }
}