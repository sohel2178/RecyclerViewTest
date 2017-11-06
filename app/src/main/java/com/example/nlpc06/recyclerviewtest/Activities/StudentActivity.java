package com.example.nlpc06.recyclerviewtest.Activities;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.nlpc06.recyclerviewtest.Model.Student;
import com.example.nlpc06.recyclerviewtest.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName,etAge;
    private Button btnOk,btnShowData;

    private RadioGroup radioGroup;
    private RadioButton radMale,radFemale;

    private int genderType =1;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        realm = Realm.getDefaultInstance();

        initView();
    }

    private void initView() {
       etName= (EditText) findViewById(R.id.name);
        etAge= (EditText) findViewById(R.id.age);
        btnOk= (Button) findViewById(R.id.btn_ok);
        btnShowData= (Button) findViewById(R.id.show_data);
        radioGroup= (RadioGroup) findViewById(R.id.group);
        radMale= (RadioButton) findViewById(R.id.rad_male);
        radFemale= (RadioButton) findViewById(R.id.rad_female);

        btnOk.setOnClickListener(this);
        btnShowData.setOnClickListener(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                if(checkedId==R.id.rad_male){
                    genderType=1;
                }else if(checkedId==R.id.rad_female){
                    genderType=2;
                }
            }
        });

    }


    @Override
    public void onClick(View v) {

        if(v.equals(btnOk)){
            String name = etName.getText().toString();
            String ageStr = etAge.getText().toString();

            int age = Integer.parseInt(ageStr);

            Student student = new Student(name,age,genderType);
            addStudent(student);

            Intent intent = new Intent();
            intent.putExtra("student",student);

            setResult(RESULT_OK,intent);
            finish();
        }else if(v.equals(btnShowData)){
            showData();
        }



        //Student student = new Student(name,age);

        /*Intent intent = new Intent();
        intent.putExtra("name",name);
        intent.putExtra("age",age);
        intent.putExtra("gender",genderType);

        setResult(RESULT_OK,intent);
        finish();*/
    }

    private void showData() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Student> studentList = realm.where(Student.class).findAll();

                for(Student x:studentList){
                    Log.d("NAME",x.getName());
                }
            }
        });
    }

    private void addStudent(final Student student) {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Number currentIdNum = realm.where(Student.class).max("id");

                int nextId;

                if(currentIdNum==null){
                    nextId =1;
                }else{
                    nextId = currentIdNum.intValue()+1;
                }

                student.setId(nextId);

                realm.insert(student);

                etName.setText("");
                etAge.setText("");
            }
        });
    }
}
