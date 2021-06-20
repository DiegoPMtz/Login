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
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

public class fragment_register extends Fragment {

    Button btn_registrar,btn_cancelar;
    EditText matricula,password,comprobar;


    public fragment_register() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        btn_registrar = view.findViewById(R.id.btn_registrar);
        btn_cancelar = view.findViewById(R.id.btn_cancelar_registro);
        matricula = view.findViewById(R.id.matricula_registro);
        password = view.findViewById(R.id.contraseña_registro);
        comprobar = view.findViewById(R.id.comprobar_registro);



        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int matri = Integer.parseInt(matricula.getText().toString());
                String pass = password.getText().toString();
                String comp = comprobar.getText().toString();
                User user = new User();
                User check = MainActivity.myAppDatabase.myDao().check(matri);
                if (check != null){
                    Toast.makeText(getActivity(),"Usuario Existente",Toast.LENGTH_SHORT).show();
                }else{
                user.setMatricula(matri);
                    if (pass.equals(comp)){
                        user.setPassword(pass);
                        MainActivity.myAppDatabase.myDao().adduser(user);
                        Toast.makeText(getActivity(), "Usuario registrado", Toast.LENGTH_SHORT).show();
                        matricula.setText("");
                        password.setText("");
                        comprobar.setText("");
                        navController.navigate(R.id.fragment_datos);
                    }
                    else if (pass != comp){
                        comprobar.setError("Las contraseñas no coinciden");
                        password.setText("");
                        comprobar.setText("");
                    }
                }




            }
        });


        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.fragment_login);
            }
        });
    }
}