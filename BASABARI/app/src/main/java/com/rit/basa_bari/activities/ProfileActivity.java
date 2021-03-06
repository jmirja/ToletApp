package com.rit.basa_bari.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.rit.basa_bari.MainActivity;
import com.rit.basa_bari.R;

public class ProfileActivity extends AppCompatActivity {

    TextView name,password,email;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initializationView();
        Intent intent=getIntent();

        String uImageName=intent.getStringExtra("IMAGE_NAME");
        String uName=intent.getStringExtra("NAME");
        String uPassword=intent.getStringExtra("PASSWORD");
        String uEmail=intent.getStringExtra("EMAIL");

        if(uImageName.equals("Owner's Profile")){
            imageView.setImageResource(R.drawable.owner_image);
            name.setText(uName);
            password.setText(uPassword);
            email.setText(uEmail);

        }else {
            imageView.setImageResource(R.drawable.renter_image);
            name.setText(uName);
            password.setText(uPassword);
            email.setText(uEmail);
        }

    }

    public void initializationView(){
        name=findViewById(R.id.nameEt);
        password=findViewById(R.id.passwordEt);
        email=findViewById(R.id.emailEt);
        imageView=findViewById(R.id.imageView);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
