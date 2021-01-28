package com.dov.gestiondemoyennes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.dov.gestiondemoyennes.R;
import com.dov.gestiondemoyennes.model.Subject;
import com.dov.gestiondemoyennes.model.SubjectGrade;
import com.dov.gestiondemoyennes.repository.SubjectGradeRepository;
import com.dov.gestiondemoyennes.repository.SubjectRepository;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;

public class GradeListActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private GradeRecyclerViewAdapter gradeRecyclerViewAdapter;
    private MaterialButton processButton;
    private AppCompatTextView resultTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_list);
        setTitle("Liste des moyennes");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setViewItems();
    }

    private void setViewItems() {
        recyclerView = findViewById(R.id.user_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        gradeRecyclerViewAdapter = new GradeRecyclerViewAdapter(SubjectGradeRepository.getINSTANCE().getSubjectGrades(), new GradeRecyclerViewAdapter.OnDeleteButtonClicked() {
            @Override
            public void delete(SubjectGrade subjectGrade) {
                SubjectGradeRepository.getINSTANCE().getSubjectGrades().remove(subjectGrade);
                gradeRecyclerViewAdapter.setgrades(SubjectGradeRepository.getINSTANCE().getSubjectGrades());
            }
        });

        recyclerView.setAdapter(gradeRecyclerViewAdapter);
        processButton = findViewById(R.id.process_BT);
        resultTV = findViewById(R.id.result_TV);
        processButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAverageGrade();
            }
        });
    }

    private void getAverageGrade() {
        float sumGrades = 0;
        for (SubjectGrade subjectGrade: SubjectGradeRepository.getINSTANCE().getSubjectGrades()){
            for (Subject subject: SubjectRepository.getINSTANCE().getSubjects()){
                if (subjectGrade.getSubject().equals(subject.getTitle())){
                    sumGrades = sumGrades + (subjectGrade.getGrade()*subject.getCoefficient());
                }
            }
        }
        float average = sumGrades / (float) getSumCoefficient();

        DecimalFormat twoDecimal = new DecimalFormat("#.##");
        resultTV.setText("La moyenne générale est : "+Float.valueOf(twoDecimal.format(average)));
    }

    private int getSumCoefficient(){
        int sumCoefficient = 0;
        for (Subject subject: SubjectRepository.getINSTANCE().getSubjects()){
            sumCoefficient = sumCoefficient + subject.getCoefficient();
        }
        return sumCoefficient;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}