package com.sakai.ug.sakaiapp.callback;

import com.sakai.ug.sakaiapp.models.syllabus.Item;

import java.util.List;

public interface SyllabusFetchListener {

    void onDeliverAllSyllabus(List<Item> syllabusList);

    void onDeliverSyllabus(Item syllabusItem);

    void onHideDialog();
}


