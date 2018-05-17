package com.passvault.abhi.passwordvault.display;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.data.Entries;
import com.passvault.abhi.passwordvault.data.UserTuple;

import java.util.List;

class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {
    List<UserTuple> entries;
    Context ctx;
    final private itemClickListener mOnClickListener;
    public EntryAdapter(itemClickListener clickhandler) {
        mOnClickListener = clickhandler;
    }
    public void setdataEntries(List<UserTuple> entries){
        this.entries=entries;
        notifyDataSetChanged();
    }
    public interface itemClickListener{
        void onItemClick(UserTuple utuple);
    }
    @Override
    public EntryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleusertuple,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EntryAdapter.ViewHolder holder, int position) {

        holder.Username.setText(entries.get(position).getUsername());
        holder.Password.setText(entries.get(position).getPassword());

    }

    @Override
    public int getItemCount() {
        if (null == entries) return 0;
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView Username;
        private TextView Password;
        private CardView card;
        public ViewHolder(View itemView) {
            super(itemView);
            Username = itemView.findViewById(R.id.susername);
            Password=itemView.findViewById(R.id.pass);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            UserTuple ut=new UserTuple(entries.get(adapterPosition).getUsername(),entries.get(adapterPosition).getPassword());
            mOnClickListener.onItemClick(ut);
        }
    }
}
