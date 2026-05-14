package kr.ac.kopo.petapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.model.CareItem;

public class CareAdapter extends RecyclerView.Adapter<CareAdapter.CareViewHolder> {

    private List<CareItem> list;

    public CareAdapter(List<CareItem> list) {
        this.list = list;
    }

    public void updateList(List<CareItem> newList) {
        this.list = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CareViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_care, parent, false);

        return new CareViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CareViewHolder holder, int position) {

        CareItem item = list.get(position);

        // 기본 정보
        holder.tvTitle.setText(item.title);
        holder.tvDate.setText(item.date);
        holder.tvDetail.setText(item.detail);

        // 👉 산책 데이터
        if (item.time != null) {
            holder.tvTime.setText("시간: " + item.time);
            holder.tvTime.setVisibility(View.VISIBLE);
        } else {
            holder.tvTime.setVisibility(View.GONE);
        }

        if (item.location != null) {
            holder.tvLocation.setText("장소: " + item.location);
            holder.tvLocation.setVisibility(View.VISIBLE);
        } else {
            holder.tvLocation.setVisibility(View.GONE);
        }

        // 👉 예방접종 데이터
        if (item.nextVaccine != null) {
            holder.tvNextVaccine.setText("다음 접종: " + item.nextVaccine);
            holder.tvNextVaccine.setVisibility(View.VISIBLE);
        } else {
            holder.tvNextVaccine.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    // ViewHolder
    static class CareViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvDate;
        TextView tvDetail;

        TextView tvTime;
        TextView tvLocation;
        TextView tvNextVaccine;

        public CareViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDetail = itemView.findViewById(R.id.tvDetail);

            tvTime = itemView.findViewById(R.id.tvTime);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvNextVaccine = itemView.findViewById(R.id.tvNextVaccine);
        }
    }
}