package uz.suem.psycho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MoodEvalStartActivity extends Activity implements View.OnClickListener {
    Button button_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_evaluation_start);

        button_start = (Button) findViewById(R.id.button_start_test);
        button_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_start_test:
                startActivity(new Intent(this, MoodEvalActivity.class));
                break;

        }
    }
}
