package com.example.lostandfound.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lostandfound.R;
import com.example.lostandfound.activities.ItemDetailActivity;
import com.example.lostandfound.adapters.ItemAdapter;
import com.example.lostandfound.models.Item;
import com.example.lostandfound.utils.PreferencesManager;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_items);

        PreferencesManager prefsManager = new PreferencesManager(requireContext());
        List<Item> items = prefsManager.getAllItems();

        ItemAdapter adapter = new ItemAdapter(items, item -> {
            Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }
}