package com.sakai.ug.sakaiapp.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sakai.ug.sakaiapp.R;
import com.sakai.ug.sakaiapp.models.ResourcesModel;

import java.util.List;

public class ResourcesAdapter extends RecyclerView.Adapter<ResourcesAdapter.ResourcesViewHolder> {

    private List<ResourcesModel> resourcesList;
    private Context context;

    public ResourcesAdapter(List<ResourcesModel> resourcesList, Context context) {
        this.resourcesList = resourcesList;
        this.context = context;
    }

    @NonNull
    @Override
    public ResourcesAdapter.ResourcesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_resource, null);
        return new ResourcesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ResourcesAdapter.ResourcesViewHolder resourcesViewHolder, int i) {

        ResourcesModel Resources = resourcesList.get(i);
        resourcesViewHolder.textViewResource.setText(Resources.getName());
        resourcesViewHolder.image.setImageDrawable(context.getResources().getDrawable(Resources.getImage()));

    }

    @Override
    public int getItemCount() {
        return resourcesList.size();
    }

    class ResourcesViewHolder extends RecyclerView.ViewHolder{

        TextView textViewResource;
        ImageView image;

        public ResourcesViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewResource = itemView.findViewById(R.id.textViewResource);
            image = itemView.findViewById(R.id.image);
        }
    }
}