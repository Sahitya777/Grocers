package com.example.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.model.Category;

import java.util.List;

import static com.example.project.R.layout.category_row_items;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{


    Context context;
    List<Category> categoryList;

    public CategoryAdapter(Context context,List<Category> categoryList){
        this.context=context;
        this.categoryList=categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

        holder.categoryImage.setImageResource(categoryList.get(position).getImageurl());

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.categoryImage);
        }
    }


}
