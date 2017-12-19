package uz.suem.psycho;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SaveResults extends Activity implements View.OnClickListener {
    String testName, testResult;
    DatabaseHelper sqlHelper;
    Button saveBtn, cancelBtn;
    EditText name, notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        testName = getIntent().getStringExtra("test_name");
        testResult = getIntent().getStringExtra("result");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_results);

        saveBtn = findViewById(R.id.button_save);
        saveBtn.setOnClickListener(this);
        cancelBtn = findViewById(R.id.button_cancel);
        cancelBtn.setOnClickListener(this);
        name = findViewById(R.id.editName);
        notes = findViewById(R.id.editNotes);

        sqlHelper = new DatabaseHelper(this);
    }

    @Override
    public void onClick(View v) {
        String _name = name.getText().toString();
        String _notes = notes.getText().toString();

        Long tsLong = System.currentTimeMillis() / 1000;
        String time = tsLong.toString();
        switch (v.getId()) {
            case R.id.button_save: {
                sqlHelper.addNewNote(_name, testName, testResult, _notes, time);
                break;
            }

            case R.id.button_cancel: {
                break;
            }

        }
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
