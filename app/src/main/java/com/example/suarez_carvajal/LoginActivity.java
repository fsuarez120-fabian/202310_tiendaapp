package com.example.suarez_carvajal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etPassword;
    private SharedPreferences misPreferencias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        referenciar();

        misPreferencias = getSharedPreferences("tienda_app",MODE_PRIVATE);

        //VERIFICAR SI ESTA LOGUEADO

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            Intent miIntent = new Intent(this,MainActivity.class);
            startActivity(miIntent);
            finish();
        }

        /*if(misPreferencias.getBoolean("logueado",false)==true){
            Intent miIntent = new Intent(this,MainActivity.class);
            startActivity(miIntent);
            finish();
        }*/
    }

    private void referenciar() {
        etUsuario = findViewById(R.id.et_user_login);
        etPassword = findViewById(R.id.et_user_contrasena);
    }

    public void clickIniciarSesion(View view) {

        /*String PASS = "123456";
        String USER = "fabian";*/

        String passUser = etPassword.getText().toString();
        String userUser = etUsuario.getText().toString();

        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signInWithEmailAndPassword(userUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Bienvenido...", Toast.LENGTH_SHORT).show();
                    Intent miIntent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(miIntent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, "ESTAN INCORRECTOS LOS DATOS", Toast.LENGTH_SHORT).show();
                }
            }
        });


       /* if(PASS.equals(passUser)&&USER.equals(userUser)){

            SharedPreferences.Editor myEditor = misPreferencias.edit();
            myEditor.putBoolean("logueado",true);
            myEditor.apply();

            Intent miIntent = new Intent(this,MainActivity.class);
            startActivity(miIntent);
            finish();
        }else{
            Toast.makeText(this, "CREDENCIALES INCORRECTAS", Toast.LENGTH_SHORT).show();
        }*/

    }
}