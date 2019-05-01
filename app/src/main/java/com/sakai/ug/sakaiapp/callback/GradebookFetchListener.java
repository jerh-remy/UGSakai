package com.sakai.ug.sakaiapp.callback;

import com.sakai.ug.sakaiapp.models.gradebook.Assignment;

import java.util.List;

public interface GradebookFetchListener {
    void onDeliverAllGrades(List<Assignment> assignmentList);

    void onDeliverGrade(Assignment assignment);

    void onHideDialog();
}
