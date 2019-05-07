package com.sakai.ug.sakaiapp.callback;

import com.sakai.ug.sakaiapp.models.announcement.AnnouncementCollection;

public interface RecentAnnouncementFetchListener {
    void onDeliverRecentAnnouncement(AnnouncementCollection announcementCollection);

}
