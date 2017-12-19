package uz.suem.psycho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Results extends Activity implements View.OnClickListener {
    String testName;
    TextView result;
    Button saveTest, endTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        result = findViewById(R.id.mood_eval_result);
        result.setText(getIntent().getStringExtra("result"));
        testName = getIntent().getStringExtra("test_name");

        saveTest = findViewById(R.id.button_save_test);
        saveTest.setOnClickListener(this);
        endTest = findViewById(R.id.button_end_test);
        endTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_save_test:
                Intent intent = new Intent(this, SaveResults.class);
                intent.putExtra("test_name", testName);
                intent.putExtra("result", result.getText().toString());
                startActivity(intent);
                break;

            case R.id.button_end_test:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
}
