package com.example.app_sofa_frontend.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.app_sofa_frontend.MainActivity;
import com.example.app_sofa_frontend.R;
import com.example.app_sofa_frontend.api.APIClient;
import com.example.app_sofa_frontend.api.APIInterface;
import com.example.app_sofa_frontend.model.UserAccount;
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

        editUsername = findViewById(R.id.editTextUsername);
        editPassword = findViewById(R.id.editTextPassword);
        btnLogin = findViewById(R.id.btnLogin);
        txtRegister = findViewById(R.id.register);
        api_login = APIClient.getClient().create(APIInterface.class);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve username and password from EditText fields
                String username = editUsername.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                // Validate input fields
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đúng tên hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Call username and password in UserAccount
                UserAccount userAccount = new UserAccount();
                userAccount.setUsername(username);
                userAccount.setPassword(password);

                // Call login API
                Call<UserAccount> call = api_login.loginAccount(userAccount);
                call.enqueue(new Callback<UserAccount>() {
                    @Override
                    public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            // Login successful
                            String message = "Đăng nhập thành công";
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish(); // Close LoginActivity after successful login
                        } else {
                            // Login failed
                            String message = "Đăng nhập thất bại! Vui lòng kiểm tra và thử lại";
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UserAccount> call, Throwable t) {
                        // API call failed
                        Toast.makeText(LoginActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Handle click event for the registration text
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
