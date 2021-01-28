package com.dov.gestiondemoyennes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.dov.gestiondemoyennes.R;
import com.dov.gestiondemoyennes.model.Subject;
import com.dov.gestiondemoyennes.repository.SubjectRepository;

public class SubjectListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SubjectsRecyclerViewAdapter subjectsRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Liste des matières");
        setViewItems();
    }

    private void setViewItems() {
        recyclerView = findViewById(R.id.user_RV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        subjectsRecyclerViewAdapter = new SubjectsRecyclerViewAdapter(SubjectRepository.getINSTANCE().getSubjects(), new SubjectsRecyclerViewAdapter.OnDeleteButtonClicked() {
            @Override
            public void delete(Subject subject) {
                SubjectRepository.getINSTANCE().getSubjects().remove(subject);
                subjectsRecyclerViewAdapter.setSubjects(SubjectRepository.getINSTANCE().getSubjects());
            }
        });

        recyclerView.setAdapter(subjectsRecyclerViewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}