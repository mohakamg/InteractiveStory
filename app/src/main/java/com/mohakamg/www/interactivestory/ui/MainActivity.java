package com.mohakamg.www.interactivestory.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mohakamg.www.interactivestory.R;

public class MainActivity extends AppCompatActivity {

    private EditText mNameField;
    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameField = (EditText) findViewById(R.id.nameEditText);
        mStartButton = (Button) findViewById(R.id.startButton);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name =  mNameField.getText().toString();

                if(name.length() == 0){
                    Toast.makeText(MainActivity.this, "Please Enter your Name to Continue!", Toast.LENGTH_SHORT).show();
                    name =  mNameField.getText().toString();
                }
                else {
                    Toast.makeText(MainActivity.this, "Welcome " + name + " !", Toast.LENGTH_SHORT).show();
                    startStory(name);
                }
            }


        });
    }
            public void startStory(String name){
                Intent intent = new Intent(this, StoryActivity.class);
                intent.putExtra(getString(R.string.key_name), name);
                startActivity(intent);
            }

    @Override
    protected void onResume() {
        super.onResume();
        mNameField.setText("");
    }
}
