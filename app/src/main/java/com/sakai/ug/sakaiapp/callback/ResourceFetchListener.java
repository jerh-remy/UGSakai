package com.sakai.ug.sakaiapp.callback;

import com.sakai.ug.sakaiapp.models.resources.ContentCollection;
import com.sakai.ug.sakaiapp.models.site.SiteCollection;

import java.util.List;

public interface ResourceFetchListener {
    void onDeliverAllResources(List<ContentCollection> contentCollectionList);

    void onDeliverResource(ContentCollection contentCollection);

    void onHideDialog();
}
