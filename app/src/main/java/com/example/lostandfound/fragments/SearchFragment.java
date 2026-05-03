package com.example.lostandfound.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lostandfound.R;
import com.example.lostandfound.activities.ItemDetailActivity;
import com.example.lostandfound.adapters.ItemAdapter;
import com.example.lostandfound.models.Item;
import com.example.lostandfound.utils.PreferencesManager;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText etSearch;
    private Spinner spinnerCategory, spinnerType;
    private ItemAdapter adapter;
    private PreferencesManager prefsManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        prefsManager    = new PreferencesManager(requireContext());
        etSearch        = view.findViewById(R.id.et_search);
        spinnerCategory = view.findViewById(R.id.spinner_category);
        spinnerType     = view.findViewById(R.id.spinner_type);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_search);

        String[] categories = {"All", "Electronics", "Bags", "Documents", "Jewelry", "Keys", "Clothing", "Other"};
        String[] types      = {"All", "Lost", "Found"};
        spinnerCategory.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, categories));
        spinnerType.setAdapter(new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_dropdown_item, types));

        adapter = new ItemAdapter(prefsManager.getAllItems(), item -> {
            Intent intent = new Intent(getActivity(), ItemDetailActivity.class);
            intent.putExtra("item", item);
            startActivity(intent);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        TextWatcher watcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int a, int b, int c) {}
            public void onTextChanged(CharSequence s, int a, int b, int c) { performSearch(); }
            public void afterTextChanged(Editable s) {}
        };
        etSearch.addTextChangedListener(watcher);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> p, View v, int pos, long id) { performSearch(); }
            public void onNothingSelected(AdapterView<?> p) {}
        };
        spinnerCategory.setOnItemSelectedListener(listener);
        spinnerType.setOnItemSelectedListener(listener);

        return view;
    }

    private void performSearch() {
        String query    = etSearch.getText().toString();
        String category = spinnerCategory.getSelectedItem().toString();
        String type     = spinnerType.getSelectedItem().toString();
        List<Item> results = prefsManager.searchItems(query, category, type);
        adapter.updateList(results);
    }
}
