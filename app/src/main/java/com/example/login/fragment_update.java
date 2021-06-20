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

public class fragment_update extends Fragment {

    Button btn_cancelar,btn_confirmar;
    EditText update_pass,update_conf;

    public fragment_update() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        btn_cancelar = view.findViewById(R.id.btn_cancelar);
        btn_confirmar = view.findViewById(R.id.btn_confirmar);
        update_pass = view.findViewById(R.id.update_pass);
        update_conf = view.findViewById(R.id.udpate_conf);

        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = update_pass.getText().toString();
                String confirm = update_conf.getText().toString();
                User user = MainActivity.myAppDatabase.myDao().passwd(password);
                if (user == null){
                    Toast.makeText(getActivity(),"Error 404",Toast.LENGTH_SHORT).show();
                }else{
                    if (password.equals(confirm)){

                        update_conf.setError("Las contraseñas no pueden ser iguales");
                    }
                    else{
                        user.setPassword(confirm);
                        MainActivity.myAppDatabase.myDao().updateuser(user);
                        Toast.makeText(getActivity(),"Actualizado",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.fragment_datos);
            }
        });
    }
}