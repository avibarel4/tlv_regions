package com.avi.tlvregions.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.avi.tlvregions.App;
import com.avi.tlvregions.R;

/**
 * Created by avibarel on 21/10/2017.
 */

public class RegionsAdapter extends RecyclerView.Adapter<RegionsAdapter.ViewHolder> implements View.OnClickListener {

    private String[] mRegions;

    private OnRegionClickedCallback mListener;

    public RegionsAdapter() {
        mRegions = App.getContext().getResources().getStringArray(R.array.regions);
    }

    public void setListener(OnRegionClickedCallback listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_region, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mTextRegion.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mTextRegion.setTag(position);

        String region = mRegions[position];

        holder.mTextRegion.setText(region);
    }

    @Override
    public int getItemCount() {
        return mRegions.length;
    }

    @Override
    public void onClick(View v) {
        String region = mRegions[(Integer) v.getTag()];
        if (mListener != null) {
            mListener.onRegionClicked(region);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextRegion;

        public ViewHolder(View itemView) {
            super(itemView);

            mTextRegion = (TextView) itemView.findViewById(R.id.text_region);
        }
    }

    public interface OnRegionClickedCallback {
        void onRegionClicked(String region);
    }

}
