package com.ilm.org.wift.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ilm.org.wift.MainActivity;
import com.ilm.org.wift.model.Product;
import com.ilm.org.wift.model.ProductAdapter;
import com.ilm.org.wift.R;
import com.ilm.org.wift.dao.ProductDao;
import com.ilm.org.wift.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view_product);

        FloatingActionButton floatingActionButton = root.findViewById(R.id.input_add);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        final List<Product> products = makeProduct();
        final ProductAdapter productAdapter = new ProductAdapter(products, new ProductAdapter.Listener() {
            @Override
            public void onProductClick(Product product) {
                final Intent intent = new Intent(getContext(), MainActivity.class);
                intent.putExtra("key", product);
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(Product product) {
                AppDatabase db = AppDatabase.getAppDatabase(getContext());
                ProductDao productDao = db.productDao();
                productDao.delete(product);
                products.clear();
                products.addAll(productDao.getAll());
            }
        });

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(productAdapter);

        return root;

    }

    List<Product> makeProduct() {
        final List<Product> products = new ArrayList<>();
        AppDatabase db = AppDatabase.getAppDatabase(getContext());
        ProductDao productDao = db.productDao();
        products.addAll(productDao.getAll());
        return products;
    }

}