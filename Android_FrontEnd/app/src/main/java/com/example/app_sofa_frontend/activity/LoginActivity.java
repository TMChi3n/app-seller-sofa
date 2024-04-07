package com.example.app_sofa_frontend.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.app_sofa_frontend.MainActivity;
import com.example.app_sofa_frontend.R;
import com.example.app_sofa_frontend.api.APIClient;
import com.example.app_sofa_frontend.api.APIInterface;
import com.example.app_sofa_frontend.model.UserAccount;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText editUsername, editPassword;
    private Button btnLogin;
    private TextView txtRegister;

    private APIInterface api_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_user);

        // Ánh xạ các thành phần giao diện
        editUsername = findViewById(R.id.editTextUsername);
        editPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.register);

        // Khởi tạo Retrofit Interface
        api_login = APIClient.getClient().create(APIInterface.class);

        // Xử lý sự kiện khi nhấn nút Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy thông tin đăng nhập từ EditText
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                // Gọi phương thức đăng nhập từ API
                Call<UserAccount> call = api_login.loginAccount(username, password);
                call.enqueue(new Callback<UserAccount>() {
                    @Override
                    public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            String message = "Login successful!";
                            // Login successful
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                        } else {
                            String message = "Login Failed";
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                    }

                    @Override
                    public void onFailure(Call<UserAccount> call, Throwable t) {
                        // Đã xảy ra lỗi trong quá trình gọi API
                        Toast.makeText(LoginActivity.this, "Đã xảy ra lỗi. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Xử lý sự kiện khi nhấn vào đăng nhập
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến màn hình đăng ký tài khoản
                // (Bạn cần viết logic để chuyển đến màn hình đăng ký tài khoản ở đây)
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}

