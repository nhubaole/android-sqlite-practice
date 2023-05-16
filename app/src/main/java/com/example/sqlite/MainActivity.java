package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ListView listviewClass;
    private List<Class> classes = new ArrayList<>();
    ClassAdapter classAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewClass = (ListView) findViewById(R.id.lvClass);
        if(classRepo.loadAllClass().isEmpty()){
            classRepo.addClass(new Class("Class001", "KTPM2021"));
            classRepo.addClass(new Class("Class002", "KTPM2022"));
            classRepo.addClass(new Class("Class003", "KTPM2020"));
        }
        classRepo.addStudent(new Student("Student001", "Nguyen Van A", "12/10/2003", "Class001"));
        classRepo.addStudent(new Student("Student002", "Nguyen Van B", "12/10/2003", "Class002"));
        classRepo.addStudent(new Student("Student003", "Nguyen Van C", "12/10/2003", "Class003"));
        classRepo.addStudent(new Student("Student004", "Nguyen Van D", "12/10/2003", "Class001"));
        classRepo.addStudent(new Student("Student005", "Nguyen Van E", "12/10/2003", "Class001"));
        classRepo.addStudent(new Student("Student006", "Nguyen Van F", "12/10/2003", "Class002"));
        classRepo.addStudent(new Student("Student007", "Nguyen Van G", "12/10/2003", "Class001"));
        classRepo.addStudent(new Student("Student008", "Nguyen Van H", "12/10/2003", "Class003"));
        classRepo.addStudent(new Student("Student009", "Nguyen Van I", "12/10/2003", "Class003"));
        classRepo.addStudent(new Student("Student010", "Nguyen Van J", "12/10/2003", "Class003"));

        classAdapter = new ClassAdapter(this, R.layout.list_item_class, classRepo.loadAllClass(), classRepo);
        listviewClass.setAdapter(classAdapter);

        listviewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Class selectedClass = classRepo.loadAllClass().get(i);
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("selectedClass", selectedClass);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        classAdapter.notifyDataSetChanged();
    }
}