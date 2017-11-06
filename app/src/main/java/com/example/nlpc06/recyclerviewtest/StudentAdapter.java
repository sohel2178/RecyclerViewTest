package com.example.nlpc06.recyclerviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nlpc06.recyclerviewtest.Model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NL PC 06 on 10/26/2017.
 */

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.NameHolder> {

    private List<Student> studentList;
    private LayoutInflater inflater;
    private Context context;

    public StudentAdapter(Context context) {
        this.context = context;
        this.studentList = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    @Override
    public NameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.single_item,parent,false);

        NameHolder holder = new NameHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(NameHolder holder, int position) {

        Student student = studentList.get(position);

        holder.tvName.setText(student.getName());
        holder.tvAge.setText(String.valueOf(student.getAge()));

        if(student.getGenderType()==1){
            holder.tvGender.setText("You are Male");
        }else if(student.getGenderType()==2){
            holder.tvGender.setText("You are Female");
        }

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void addStudent(Student student){
        studentList.add(student);
        //notifyDataSetChanged();
        int pos = studentList.indexOf(student);
        notifyItemInserted(pos);
    }

    public class NameHolder extends RecyclerView.ViewHolder{
        TextView tvName,tvAge,tvGender;

        public NameHolder(View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvGender = itemView.findViewById(R.id.tv_gender);
        }
    }
}
