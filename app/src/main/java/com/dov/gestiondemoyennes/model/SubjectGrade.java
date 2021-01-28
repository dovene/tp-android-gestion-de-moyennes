package com.dov.gestiondemoyennes.model;

public class SubjectGrade {
    private String subject;
    private Float grade;

    public SubjectGrade(String subject, Float grade) {
        this.subject = subject;
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Float getGrade() {
        return grade;
    }

    public void setGrade(Float grade) {
        this.grade = grade;
    }
}
