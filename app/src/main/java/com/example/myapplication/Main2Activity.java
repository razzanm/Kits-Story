package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    TextView textView;
    Button button;
    Button Share_btn;
    Button nbtn,pbtn;
    int position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        textView = findViewById(R.id.txt);
        button = findViewById(R.id.copybtn);
        Share_btn = findViewById(R.id.sharebutton);
        nbtn = findViewById(R.id.nextbtn);
        pbtn = findViewById(R.id.prevbtn);
      //final String dStory = getIntent().getStringExtra("story");
        final String  [] dStory = getIntent().getStringArrayExtra("story");
        position = getIntent().getIntExtra("position",0);

       textView.setText(dStory[position]);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
               ClipData clip = ClipData.newPlainText("Text",dStory[position]);
               clipboardManager.setPrimaryClip(clip);
               Toast.makeText(Main2Activity.this, "copied",Toast.LENGTH_SHORT).show();
           }
       });

       Share_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent();
               intent.setAction(Intent.ACTION_SEND);
               intent.putExtra(Intent.EXTRA_TEXT,dStory[position]);
               intent.setType("text/plain");
               intent = Intent.createChooser(intent,"share by");
               startActivity(intent);
           }
       });
       nbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               position = (position + 1)%dStory.length;
               textView.setText(dStory[position]);
           }
       });
       pbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               position = (position-1)%dStory.length;
               textView.setText(dStory[position]);
           }
       });
    }
}
