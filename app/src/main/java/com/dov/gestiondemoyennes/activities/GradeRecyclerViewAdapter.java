package com.dov.gestiondemoyennes.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.dov.gestiondemoyennes.R;
import com.dov.gestiondemoyennes.model.SubjectGrade;

import java.util.ArrayList;

public class GradeRecyclerViewAdapter extends RecyclerView.Adapter<GradeRecyclerViewAdapter.GradeViewHolder> {

    public interface OnDeleteButtonClicked {
        void delete(SubjectGrade subjectGrade);
    }

    private ArrayList<SubjectGrade> subjectGrades;
    private OnDeleteButtonClicked onDeleteButtonClicked;

    public GradeRecyclerViewAdapter(ArrayList<SubjectGrade> subjectGrades, OnDeleteButtonClicked onDeleteButtonClicked) {
        this.subjectGrades = subjectGrades;
        this.onDeleteButtonClicked = onDeleteButtonClicked;
    }

    public void setgrades(ArrayList<SubjectGrade> Grade) {
        this.subjectGrades = subjectGrades;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.grade_item, parent, false);
        return new GradeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GradeViewHolder holder, int position) {
        holder.bind(subjectGrades.get(position), onDeleteButtonClicked);
    }

    @Override
    public int getItemCount() {
        return subjectGrades.size();
    }

    public class GradeViewHolder extends RecyclerView.ViewHolder {
        AppCompatTextView GradeTV;
        AppCompatTextView gradeTV;
        AppCompatButton deleteBT;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);
            GradeTV = itemView.findViewById(R.id.subject_TV);
            gradeTV = itemView.findViewById(R.id.grade_TV);
            deleteBT = itemView.findViewById(R.id.delete_BT);
        }

        public void bind(SubjectGrade subjectGrade, OnDeleteButtonClicked onDeleteButtonClicked) {
            GradeTV.setText(subjectGrade.getSubject());
            gradeTV.setText(String.valueOf(subjectGrade.getGrade()));
            deleteBT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onDeleteButtonClicked.delete(subjectGrade);
                }
            });
        }
    }
}
