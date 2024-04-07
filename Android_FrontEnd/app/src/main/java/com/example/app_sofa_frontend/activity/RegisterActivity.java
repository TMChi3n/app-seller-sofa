package com.example.app_sofa_frontend.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_sofa_frontend.R;
import com.example.app_sofa_frontend.api.APIClient;
import com.example.app_sofa_frontend.api.APIInterface;
import com.example.app_sofa_frontend.model.UserAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private TextView txtLogin;
    private Button btnRegister;
    private APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_user);

        txtLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.btn_login);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
    }

    private void registerAccount() {
        // Get input values
        String username = ""; // Get username from EditText
        String email = ""; // Get email from EditText
        String password = ""; // Get password from EditText

        // Call API for registration
        Call<UserAccount> call = apiInterface.registerAccount(username, email, password);
        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.code() == 201) {
                    // Registration successful
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

                    // Redirect to login page
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish(); // Optional: Finish the current activity to prevent going back to it using the back button
                } else {
                    // Registration failed
                    Toast.makeText(RegisterActivity.this, "Đăng ký không thành công!", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                // Registration failed due to network error
                Toast.makeText(RegisterActivity.this, "Lỗi kết nối. Vui lòng thử lại sau!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
