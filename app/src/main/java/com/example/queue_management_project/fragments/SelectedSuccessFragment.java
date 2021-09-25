package com.example.queue_management_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.queue_management_project.R;
import com.example.queue_management_project.databinding.FragmentPage2Binding;
import com.example.queue_management_project.databinding.FragmentSelectedSuccessBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectedSuccessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectedSuccessFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    @NonNull
    private FragmentSelectedSuccessBinding binding;

    public SelectedSuccessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectedSuccessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectedSuccessFragment newInstance(String param1, String param2) {
        SelectedSuccessFragment fragment = new SelectedSuccessFragment();

        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentSelectedSuccessBinding.inflate(getLayoutInflater());
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View view= inflater.inflate(R.layout.fragment_selected_success, container, false);
        //binding.textView.setOnClickListener(new );
        return binding.getRoot();
    }


}