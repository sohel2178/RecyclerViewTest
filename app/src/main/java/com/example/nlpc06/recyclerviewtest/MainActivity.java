package com.example.nlpc06.recyclerviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nlpc06.recyclerviewtest.Activities.StudentActivity;
import com.example.nlpc06.recyclerviewtest.Model.Student;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_CODE=50000;

    private RecyclerView rvStudents;

    private Button btnAdd;

    private StudentAdapter adapter;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        adapter = new StudentAdapter(getApplicationContext());

        initView();

        getData();


    }

    private void getData() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Student> studentList = realm.where(Student.class).findAll();

                for(Student x:studentList){
                    adapter.addStudent(x);
                }
            }
        });
    }

    private void initView() {
        rvStudents = (RecyclerView) findViewById(R.id.rv_student);
        btnAdd = (Button) findViewById(R.id.btn_add);

        btnAdd.setOnClickListener(this);

        rvStudents.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvStudents.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);

        startActivityForResult(intent,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK){

            Student student = (Student) data.getSerializableExtra("student");

            adapter.addStudent(student);
        }
    }
}
