package com.example.queue_management_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.queue_management_project.R;
import com.example.queue_management_project.databinding.FragmentPage2Binding;
import com.example.queue_management_project.dbmodel.firebaseDp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Page2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Page2Fragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @NonNull
    private FragmentPage2Binding binding;

    public Page2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Page2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Page2Fragment newInstance(String param1, String param2) {
        Page2Fragment fragment = new Page2Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentPage2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_page2, container, false);

        binding.buttonLoginBack.setOnClickListener(this);
        binding.buttonLoginSubmit.setOnClickListener(this);
        return binding.getRoot();

    }

    @Override
    public void onClick(View view) {
        if (view.getId()==binding.buttonLoginBack.getId()) {
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_page2Fragment2_to_page1Fragment2);;
        }
         else {
            firebaseDp firebase = firebaseDp.getInstance(getActivity());
            String email = binding.loginEmail.getText().toString();
            String password = binding.loginPassword.getText().toString();
            if (firebase.login(email, password)){
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_page2Fragment2_to_mainCalendarFragment);
            }


        }

    }
}