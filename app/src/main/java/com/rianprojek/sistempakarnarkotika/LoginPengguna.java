package com.rianprojek.sistempakarnarkotika;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rianprojek.sistempakarnarkotika.Api.ApiRequestPengguna;
import com.rianprojek.sistempakarnarkotika.Api.RetroServer;
import com.rianprojek.sistempakarnarkotika.Model.ResponseUser;
import com.rianprojek.sistempakarnarkotika.Model.ModelPengguna;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPengguna extends AppCompatActivity {

    private Button btnLogin;
    private String username, password;
    private EditText etUsername, etPass;
    private Boolean status;
    private ProgressDialog pd;
    private List<ModelPengguna> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pengguna);

        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.username);
        etPass = findViewById(R.id.password);
        pd = new ProgressDialog(this);

        final ApiRequestPengguna api = RetroServer.getClient().create(ApiRequestPengguna.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = etUsername.getText().toString();
                password = etPass.getText().toString();

                Call<ResponseUser> login = api.login(username, password);

                login.enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                        pd.setMessage("Tunggu sebentar ...");
                        pd.setCancelable(false);
                        pd.show();

                        status = response.body().getResult();
                        if (status == true){
                            Call<ResponseUser> selectClient = api.select_client(username);
                            selectClient.enqueue(new Callback<ResponseUser>() {
                                @Override
                                public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                                    mItems = response.body().getData();
                                    ModelPengguna dm = mItems.get(0);

                                    ResponseUser.setUsername(username);
                                    ResponseUser.setEmail(dm.getEmail());
                                    doLogin();
                                    pd.hide();
                                    finish();
                                }

                                @Override
                                public void onFailure(Call<ResponseUser> call, Throwable t) {
                                    pd.hide();
                                    Toast.makeText(LoginPengguna.this,"Error 1!",Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        else{
                            pd.hide();
                            etUsername.setText("");
                            etPass.setText("");
                            Toast.makeText(LoginPengguna.this,"Username atau Password Salah!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseUser> call, Throwable t) {
                        Toast.makeText(LoginPengguna.this,"Error 2!",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void doLogin(){
        Intent intentMainActivityUser = new Intent(this,MainActivityUser.class);
        startActivity(intentMainActivityUser);
    }
}
