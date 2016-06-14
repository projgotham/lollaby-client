package com.teamlollaby.lollaby.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.teamlollaby.lollaby.R;

/**
 * Created by MinGu on 2015-10-26.
 */
public class SignupActivity extends AppCompatActivity{

    private Button signupFinishBtn;
    private Button signupCancelBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupFinishBtn = (Button) findViewById(R.id.signup_btn);
        signupCancelBtn = (Button) findViewById(R.id.cancel_btn);

        signupFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        signupCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원가입이 취소되었습니다!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}
