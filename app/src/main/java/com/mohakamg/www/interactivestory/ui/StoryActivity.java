package com.mohakamg.www.interactivestory.ui;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mohakamg.www.interactivestory.R;
import com.mohakamg.www.interactivestory.model.Page;
import com.mohakamg.www.interactivestory.model.Story;

public class StoryActivity extends MainActivity {

    private Story mStory = new Story();
    private ImageView mImageView;
    private TextView mTextView;
    private Button mChoice1;
    private Button mChoice2;
    private String mName;
    Page mPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        Intent intent = getIntent();
        mImageView = (ImageView) findViewById(R.id.storyImageView);
        mTextView = (TextView) findViewById(R.id.storyTextView);
        mChoice1 = (Button) findViewById(R.id.choiceButton1);
        mChoice2 = (Button) findViewById(R.id.choiceButton2);

        mName = intent.getStringExtra(getString(R.string.key_name));
        Toast.makeText(this,"Your Journey awaits You", Toast.LENGTH_SHORT).show();

        loadPage(0);

    }

    private void loadPage(int choice){
        mPage = mStory.getPage(choice);
        Drawable drawable = getResources().getDrawable(mPage.getImageId());
        mImageView.setImageDrawable(drawable);
        String pageText = mPage.getText();
        pageText = String.format(pageText, mName);
        mTextView.setText(pageText);

        if(mPage.isFinal()){

            mChoice1.setVisibility(View.INVISIBLE);
            mChoice2.setText("Play Again");
            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        } else {

            mChoice1.setText(mPage.getChoice1().getText());
            mChoice2.setText(mPage.getChoice2().getText());

            mChoice1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mPage.getChoice1().getNextPage();
                    loadPage(nextPage);
                }
            });

            mChoice2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int nextPage = mPage.getChoice2().getNextPage();
                    loadPage(nextPage);
                }
            });
        }

    }

}
