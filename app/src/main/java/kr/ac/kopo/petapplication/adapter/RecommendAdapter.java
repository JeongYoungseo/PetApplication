package kr.ac.kopo.petapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import kr.ac.kopo.petapplication.R;
import kr.ac.kopo.petapplication.model.RecommendItem;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    ArrayList<RecommendItem> itemList;

    public RecommendAdapter(ArrayList<RecommendItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommend, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RecommendItem item = itemList.get(position);

        holder.name.setText(item.getName());
        holder.desc.setText(item.getDesc());
        holder.price.setText(item.getPrice());
        holder.category.setText(item.getCategory());

        holder.imgFood.setImageResource(
                item.getImageRes()
        );

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(v.getContext(),
                    item.getName(),
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgFood;
        TextView name, desc, category, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            name = itemView.findViewById(R.id.txt_name);
            desc = itemView.findViewById(R.id.txt_desc);
            category = itemView.findViewById(R.id.txt_category);
            price = itemView.findViewById(R.id.txt_price);
        }
    }
}
