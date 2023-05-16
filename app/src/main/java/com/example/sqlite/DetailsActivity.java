package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class DetailsActivity extends BaseActivity {

    private ListView listView;
    TextView tvClassId;
    TextView tvClassName;
    TextView tvClassStudents;
    Button btnBack;
    private List<Student> students;

    StudentAdapter studentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        listView = (ListView) findViewById(R.id.lvStudents);

        tvClassId = (TextView) findViewById(R.id.classId);
        tvClassName = (TextView) findViewById(R.id.className);
        tvClassStudents = (TextView) findViewById(R.id.classStudents);

        Class _class = (Class) getIntent().getExtras().get("selectedClass");

        tvClassId.setText(_class.getId());
        tvClassName.setText(_class.getName());
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        students = classRepo.getStudentByClassID(_class.getId());
        if(students == null){
            return;
        }
        studentAdapter = new StudentAdapter(this, R.layout.list_item_student, students);
        studentAdapter.setOnDeleteButtonClickListener(new StudentAdapter.OnDeleteButtonClickListener() {
            @Override
            public void onBtnClick(Student student) {
                if(classRepo.deleteStudent(student.get_id())){
                    studentAdapter.remove(student);
                    studentAdapter.notifyDataSetChanged();
                }
            }
        });
        listView.setAdapter(studentAdapter);
    }
}