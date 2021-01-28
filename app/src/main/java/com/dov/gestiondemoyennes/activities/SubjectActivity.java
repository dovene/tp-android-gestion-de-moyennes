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
import com.dov.gestiondemoyennes.repository.SubjectRepository;

public class SubjectActivity extends AppCompatActivity {

    private AppCompatEditText titleET;
    private AppCompatEditText coeffET;
    private AppCompatButton saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_subject);
        setTitle("Saisie des matières");
        setViewItems();
    }

    private void setViewItems() {
        titleET = findViewById(R.id.title_ET);
        coeffET = findViewById(R.id.coefficient_ET);
        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save();
            }
        });

    }

    private void save() {
        if(TextUtils.isEmpty(titleET.getText().toString())|| TextUtils.isEmpty(coeffET.getText().toString()) ){
            Toast.makeText(this, "Vérifier votre saisie", Toast.LENGTH_LONG).show();
            return;
        }
        Subject subject = new Subject(titleET.getText().toString(), Integer.valueOf(coeffET.getText().toString()));
        SubjectRepository.getINSTANCE().getSubjects().add(subject);
        Toast.makeText(this, "Enregistrement effectué", Toast.LENGTH_LONG).show();
        startActivity(new Intent(SubjectActivity.this, SubjectListActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }
}