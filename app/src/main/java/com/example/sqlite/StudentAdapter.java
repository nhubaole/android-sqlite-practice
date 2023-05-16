package com.example.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {
    private int resource;
    private List<Student> students;

    public interface OnDeleteButtonClickListener {
        void onBtnClick(Student student);
    }

    private OnDeleteButtonClickListener onDeleteButtonClickListener;


    public StudentAdapter(@NonNull Context context, int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.students = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v==null){
            LayoutInflater vi;
            vi = LayoutInflater.from(this.getContext());
            v = vi.inflate(this.resource, null);
        }
        Student s = getItem(position);
        TextView tvStudentId = (TextView) v.findViewById(R.id.studentId);
        TextView tvStudentName = (TextView) v.findViewById(R.id.studentName);
        TextView tvStudentDob = (TextView) v.findViewById(R.id.studentDob);
        MaterialButton deleteBtn = (MaterialButton) v.findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onDeleteButtonClickListener != null){
                    onDeleteButtonClickListener.onBtnClick(s);
                }
            }
        });

        if(tvStudentId != null){
            tvStudentId.setText(s.get_id());
        }
        if(tvStudentName != null){
            tvStudentName.setText(s.get_name());
        }
        if(tvStudentDob != null){
            tvStudentDob.setText(s.get_dob());
        }
        return v;
    }

    public void setOnDeleteButtonClickListener(OnDeleteButtonClickListener listener) {
        this.onDeleteButtonClickListener = listener;
    }
}
