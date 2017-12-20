package uz.suem.psycho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LyusherStartActivity extends Activity implements View.OnClickListener {
    Button button_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyusher_start);

        button_start = findViewById(R.id.button_start_test);
        button_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start_test:
                startActivity(new Intent(this, LyusherActivity.class));
                break;

        }
    }
}
