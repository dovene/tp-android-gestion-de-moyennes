package com.dov.gestiondemoyennes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dov.gestiondemoyennes.R;
import com.dov.gestiondemoyennes.model.Subject;
import com.dov.gestiondemoyennes.model.SubjectGrade;
import com.dov.gestiondemoyennes.repository.SubjectGradeRepository;
import com.dov.gestiondemoyennes.repository.SubjectRepository;

public class GradeActivity extends AppCompatActivity {
    private AppCompatEditText subjectET;
    private AppCompatEditText gradeET;
    private AppCompatButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_grade);
        setTitle("Saisie des moyennes");
        setViewItems();
    }

    private void setViewItems() {
        subjectET = findViewById(R.id.subject_ET);
        gradeET = findViewById(R.id.grade_ET);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    private void save() {
        if(TextUtils.isEmpty(subjectET.getText().toString())|| TextUtils.isEmpty(gradeET.getText().toString()) ){
            Toast.makeText(this, "Vérifier votre saisie", Toast.LENGTH_LONG).show();
            return;
        }
        SubjectGrade subjectGrade = new SubjectGrade(subjectET.getText().toString(), Float.valueOf(gradeET.getText().toString()));
        SubjectGradeRepository.getINSTANCE().getSubjectGrades().add(subjectGrade);
        Toast.makeText(this, "Enregistrement effectué", Toast.LENGTH_LONG).show();
        startActivity(new Intent(GradeActivity.this, GradeListActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}