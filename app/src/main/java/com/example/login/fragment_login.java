package com.example.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class fragment_login extends Fragment {

    Button btn_ingresar;
    TextView registar;
    EditText matricula,password;


    public fragment_login() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        btn_ingresar = view.findViewById(R.id.btn_ingresar);
        registar = view.findViewById(R.id.registrar);
        matricula = view.findViewById(R.id.login_mat);
        password = view.findViewById(R.id.login_pass);


        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mat;
                if (matricula.getText().toString().isEmpty()){
                    mat = 0;
                }else{
                mat = Integer.valueOf(matricula.getText().toString());
                }
                String pass = password.getText().toString();
                if (mat == 0 || pass.isEmpty()){
                    matricula.setError("Campo vacio");
                    password.setError("Campo vacio");
                }else{
                    User user = MainActivity.myAppDatabase.myDao().login(mat,pass);
                    if (user==null){
                        Toast.makeText(getActivity(),"Credenciales invalidas",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(),"Bienvenido",Toast.LENGTH_SHORT).show();
                        navController.navigate(R.id.fragment_datos);
                    }
                }


            }
        });

        registar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.fragment_register);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }



}