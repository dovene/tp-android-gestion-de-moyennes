package com.dov.gestiondemoyennes.repository;

import com.dov.gestiondemoyennes.model.Subject;
import com.dov.gestiondemoyennes.model.SubjectGrade;

import java.util.ArrayList;

public class SubjectGradeRepository {
    private ArrayList<SubjectGrade> subjectGrades = new ArrayList<>();

    private SubjectGradeRepository(){ }
    private static SubjectGradeRepository INSTANCE = null;

    public static synchronized SubjectGradeRepository getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE = new SubjectGradeRepository();
        }
        return INSTANCE;
    }

    public ArrayList<SubjectGrade> getSubjectGrades() {
        return subjectGrades;
    }

    public void setSubjectGrades(ArrayList<SubjectGrade> subjects) {
        this.subjectGrades = subjects;
    }
}
