package com.ilm.org.wift.model;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ilm.org.wift.R;

import java.util.Calendar;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    List<Product> products;
    private final Listener onProductClickListener;

    public ProductAdapter(List<Product> products, Listener onProductClickListener) {
        this.products = products;
        this.onProductClickListener = onProductClickListener;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_product, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClickListener.onProductClick((Product) v.getTag());
            }
        });
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int i) {
        Product product = products.get(i);
        holder.bind(product);
        holder.itemView.setTag(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {

        TextView textClock;
        TextView textProduct;
        TextView textQuantity;
        ImageButton imageButton;

        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            textClock = itemView.findViewById(R.id.textClock1);
            textProduct = itemView.findViewById(R.id.product_name);
            textQuantity = itemView.findViewById(R.id.quantity);
            imageButton = itemView.findViewById(R.id.delete_icon);

        }

        void bind(final Product product) {
            if (product.getKg() > 1)
                textQuantity.setText(((double) product.getKg() + (double) product.getGram() / 1000) + " kg");
            else if (product.getKg() < 1 && product.getGram() > 0)
                textQuantity.setText(product.getGram() + " gram");
            else
                textQuantity.setText(product.getQuantity() + " th");
            textProduct.setText(product.getName() + "");
            String date = product.getCalendar().get(Calendar.DAY_OF_MONTH) + "." + (product.getCalendar().get(Calendar.MONTH)+1) + "." + product.getCalendar().get(Calendar.YEAR);
            textClock.setText("Shelf life: " + date);
            Calendar calendar = Calendar.getInstance();
            if(!calendar.before(product.calendar)){
                textClock.setTextColor(Color.RED);
                textClock.setText("Expired product");
            }
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onProductClickListener.onDeleteClick(product);

                    ProductAdapter.this.notifyDataSetChanged();
                }
            });
        }

    }

    public interface Listener {

        void onProductClick(Product product);

        void onDeleteClick(Product product);
    }
}
