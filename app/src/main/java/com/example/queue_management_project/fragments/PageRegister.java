package com.example.queue_management_project.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.queue_management_project.Model.User;
import com.example.queue_management_project.R;
import com.example.queue_management_project.databinding.FragmentPageRegisterBinding;
import com.example.queue_management_project.dbmodel.firebaseDp;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PageRegister#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PageRegister extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @NonNull
    private FragmentPageRegisterBinding binding;
    public PageRegister() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PageRegister.
     */
    // TODO: Rename and change types and number of parameters
    public static PageRegister newInstance(String param1, String param2) {
        PageRegister fragment = new PageRegister();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentPageRegisterBinding.inflate(getLayoutInflater());
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
        //return inflater.inflate(R.layout.fragment_page_register, container, false);
        binding.buttonRegisterBack.setOnClickListener(this);
        binding.buttonRegisterSubmit.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==binding.buttonRegisterBack.getId()) {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_pageRegister_to_page1Fragment2);
        }
        else {
            firebaseDp firebase = firebaseDp.getInstance(getActivity());
            String email = binding.rgisterEmail.getText().toString();
            String password = binding.registerPassword.getText().toString();
            String fullName = binding.registerFullName2.getText().toString();
            String phone = binding.registerPhone.getText().toString();
            String id = binding.registerID.getText().toString();
            User user = new User(fullName,id,phone, email, password);

            if (firebase.createUser(email, password)) {
                firebase.funcAddData(user);
                Navigation.findNavController(binding.getRoot()).navigate(R.id.action_pageRegister_to_page2Fragment2);
            }
            else {
                // pop up to user that FAIL REGISTER!!!!!!!!
            }
        }

    }


}