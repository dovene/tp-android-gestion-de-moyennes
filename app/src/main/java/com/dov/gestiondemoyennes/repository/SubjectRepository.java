package com.dov.gestiondemoyennes.repository;

import com.dov.gestiondemoyennes.model.Subject;

import java.util.ArrayList;

public class SubjectRepository {
    private ArrayList<Subject> subjects = new ArrayList<>();

    private SubjectRepository(){ }
    private static SubjectRepository INSTANCE = null;

    public static synchronized SubjectRepository getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new SubjectRepository();
        }
        return INSTANCE;
    }

    public ArrayList<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }
}
