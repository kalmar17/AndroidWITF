package com.ilm.org.wift.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ilm.org.wift.MainActivity;
import com.ilm.org.wift.R;
import com.ilm.org.wift.dao.ProductDao;
import com.ilm.org.wift.database.AppDatabase;
import com.ilm.org.wift.model.Product;
import com.ilm.org.wift.model.ProductAdapter;

import java.util.List;

public class DashboardFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        TextView nameUser = root.findViewById(R.id.user_name);
        TextView allProduct = root.findViewById(R.id.all_product);
        TextView listSaleProduct = root.findViewById(R.id.list_sale_product);

        return root;
    }
}