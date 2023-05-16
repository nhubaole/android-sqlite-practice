package com.example.sqlite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClassAdapter extends ArrayAdapter<Class> {
    private int resource;
    private ClassRepo classRepo;
    private List<Class> classes;

    public ClassAdapter(@NonNull Context context, int resource, @NonNull List<Class> objects, ClassRepo classRepo) {
        super(context, resource, objects);
        this.resource = resource;
        this.classes = objects;
        this.classRepo = classRepo;
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
        Class c = getItem(position);
        TextView tvClassId = (TextView) v.findViewById(R.id.classId);
        TextView tvClassName = (TextView) v.findViewById(R.id.className);
        TextView tvClassStudents = (TextView) v.findViewById(R.id.classStudents);
        TextView tvClassSeq = (TextView) v.findViewById(R.id.classSeq);

        if(tvClassId != null){
            tvClassId.setText(c.getId());
        }
        if(tvClassName != null){
            tvClassName.setText(c.getName());
        }
        if(tvClassStudents != null){
            tvClassStudents.setText(Integer.toString(classRepo.countStudentsByClassID(c.getId())));
        }
        if(tvClassSeq != null){
            tvClassSeq.setText("#" + Integer.toString(position + 1));
        }
        return v;
    }
}
