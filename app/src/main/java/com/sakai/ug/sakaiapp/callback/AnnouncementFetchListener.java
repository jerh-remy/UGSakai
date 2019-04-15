package com.sakai.ug.sakaiapp.callback;

import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;

import java.util.List;

public interface AnnouncementFetchListener {
    void onDeliverAllAnnouncements(List<AnnouncementCollection> announcementCollectionList);

    void onDeliverAnnouncement(AnnouncementCollection announcementCollection);

    void onHideDialog();
}
