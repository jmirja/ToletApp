package com.rit.basa_bari.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rit.basa_bari.MainActivity;
import com.rit.basa_bari.R;
import com.rit.basa_bari.models.OwnerProfile;
import com.rit.basa_bari.models.RenterProfile;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText email,password;
    public static boolean LOGCHECKER=false;
    Button login;
    List<OwnerProfile> ownerProfileList;
    List<RenterProfile> renterProfileList;
    DatabaseReference oDatabaseReference,rDatabaseReference;
    OwnerProfile ownerProfile;
    RenterProfile renterProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Sign In");
        initializationView();


        ownerProfileList=new ArrayList<>();
        renterProfileList=new ArrayList<>();
        oDatabaseReference= FirebaseDatabase.getInstance().getReference("OwnerProfile");
        rDatabaseReference=FirebaseDatabase.getInstance().getReference("RenterProfile");


        oDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot setData:dataSnapshot.getChildren()){
                    OwnerProfile ownerProfile=setData.getValue(OwnerProfile.class);
                    ownerProfileList.add(ownerProfile);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText( LoginActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        rDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot setData:dataSnapshot.getChildren()){
                    RenterProfile renterProfile=setData.getValue(RenterProfile.class);
                    renterProfileList.add(renterProfile);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText( LoginActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        clickButton();

    }
    public void initializationView(){
        email=findViewById(R.id.emailEt);
        password=findViewById(R.id.passwordEt);
        login=findViewById(R.id.loginBtn);

    }
    public void clickButton(){
        login.setOnClickListener(this);

    }

    public boolean checkValidity() {
        View focusView = null;
        boolean cancel = false;
        String uEmail = email.getText().toString();
        String uPassword = password.getText().toString();

        if (TextUtils.isEmpty(uEmail)) {
            // focusView=userName;
            cancel = true;
            email.setError("Enter a valid email");
        } else if (TextUtils.isEmpty(uPassword)) {
            // focusView = pass;
            cancel = true;
            password.setError("Enter a valid password");
        }
        return cancel;
    }

    @Override
    public void onClick(View view) {
        String uEmail=email.getText().toString().trim();
        String uPassword=password.getText().toString().trim();

        if(view.getId()==R.id.loginBtn){

            if(checkValidity()){
                Toast.makeText( this,"Ooopss!! you might have left something",Toast.LENGTH_LONG ).show();

            }else {
                Intent intent1=new Intent(LoginActivity.this, MainActivity.class);
                intent1.putExtra("Intent1_EMAIL",uEmail);
                intent1.putExtra("Intent1_PASSWORD",uPassword);
                startActivity(intent1);

                int i,j;
                for(i=0;i<ownerProfileList.size();i++){
                    ownerProfile=ownerProfileList.get(i);
                }
                String oEmail=ownerProfile.getEmail();
                String oPass=ownerProfile.getPassword();
                String oName=ownerProfile.getName();
                String imageName="Owner's Profile";

                if(oEmail.equals(uEmail)&& oPass.equals(uPassword)){
                    LOGCHECKER=true;
                    //Toast.makeText( LoginActivity.this,"Owner Matched!!!",Toast.LENGTH_LONG ).show();
                    Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
                    intent.putExtra("IMAGE_NAME",imageName);
                    intent.putExtra("NAME",oName);
                    intent.putExtra("PASSWORD",oPass);
                    intent.putExtra("EMAIL",oEmail);
                    startActivity(intent);

                }else {
                    for(j=0;j<renterProfileList.size();j++){
                        renterProfile=renterProfileList.get(j);
                    }
                    String rEmail=renterProfile.getEmail();
                    String rPass=renterProfile.getPassword();
                    String rName=renterProfile.getName();
                    String imagName="Renter's Profile";

                    if(rEmail.equals(uEmail)&& rPass.equals(uPassword)){
                        LOGCHECKER=true;
                        Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
                        intent.putExtra("IMAGE_NAME",imagName);
                        intent.putExtra("NAME",rName);
                        intent.putExtra("PASSWORD",rPass);
                        intent.putExtra("EMAIL",rEmail);
                        startActivity(intent);

                       // Toast.makeText( LoginActivity.this,"Renter Matched!!!",Toast.LENGTH_LONG ).show();

                    }else{
                        Toast.makeText( this,"Ooopss!! your given credentials didn't matched",Toast.LENGTH_LONG ).show();
                    }

                }


            }


        }

    }
}
