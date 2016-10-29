package com.open.lee.myeventbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button gotoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyEventBus.INSTANCE.register(this);
        gotoButton = (Button) findViewById(R.id.id_btn_goto_second_activity);
        gotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    @Register(threadMode = ThreadMode.POST)
    public void testFunction(TestEvent event){
        Toast.makeText(this, "Activity1成功收到Activity2消息"+ event.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
