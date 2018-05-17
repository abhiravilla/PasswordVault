package com.passvault.abhi.passwordvault.display;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.data.UserTuple;

import java.util.List;

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.ViewHolder> {
    List<String> entries;
    Context ctx;
    final private itemClickListener mOnClickListener;

    public SiteAdapter(itemClickListener clickhandler) {
        mOnClickListener = clickhandler;
    }

    public void setdataEntries(List<String> entries){
        this.entries=entries;
        notifyDataSetChanged();
    }

    public interface itemClickListener{
        void onItemClick(String sitename);
    }

    @Override
    public SiteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SiteAdapter.ViewHolder holder, int position) {

        holder.Sitename.setText(entries.get(position));

    }

    @Override
    public int getItemCount() {
        if (null == entries) return 0;
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView Sitename;
        public ViewHolder(View itemView) {
            super(itemView);
            Sitename = itemView.findViewById(R.id.sitename);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            String site=entries.get(adapterPosition);
            mOnClickListener.onItemClick(site);
        }
    }
}