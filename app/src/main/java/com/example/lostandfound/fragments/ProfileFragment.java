package com.example.lostandfound.fragments;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lostandfound.R;
import com.example.lostandfound.adapters.ItemAdapter;
import com.example.lostandfound.utils.PreferencesManager;
import java.util.UUID;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        PreferencesManager prefsManager = new PreferencesManager(requireContext());

        EditText etName  = view.findViewById(R.id.et_name);
        EditText etPhone = view.findViewById(R.id.et_phone);

        etName.setText(prefsManager.getUserName());
        etPhone.setText(prefsManager.getUserPhone());

        view.findViewById(R.id.btn_save_profile).setOnClickListener(v -> {
            String name  = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();
            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(getContext(), "Fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }
            String userId = prefsManager.getUserId().isEmpty()
                    ? UUID.randomUUID().toString()
                    : prefsManager.getUserId();
            prefsManager.saveUserProfile(userId, name, phone);
            Toast.makeText(getContext(), "Profile saved!", Toast.LENGTH_SHORT).show();
        });

        RecyclerView myItems = view.findViewById(R.id.recycler_my_items);
        myItems.setLayoutManager(new LinearLayoutManager(getContext()));
        myItems.setAdapter(new ItemAdapter(
                prefsManager.getItemsByUser(prefsManager.getUserId()), item -> {}));

        return view;
    }
}