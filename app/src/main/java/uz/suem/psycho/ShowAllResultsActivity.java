package uz.suem.psycho;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ShowAllResultsActivity extends Activity implements View.OnClickListener {
    private List<View> allEds = new ArrayList();
    DatabaseHelper sqlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_all_results_activity);

        final LinearLayout linear = findViewById(R.id.linear);
        final View view = getLayoutInflater().inflate(R.layout.custom_row_layout, null);

        sqlHelper = new DatabaseHelper(this);
        final Cursor cursor = sqlHelper.selectAll();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            Button deleteField = view.findViewById(R.id.button2);
            deleteField.setId(cursor.getInt(0));
            deleteField.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        sqlHelper.deleteRow(v.getId());

                        ((LinearLayout) view.getParent()).removeView(view);
                        allEds.remove(view);
                    } catch (IndexOutOfBoundsException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            TextView text = view.findViewById(R.id.editText);
            text.setText(cursor.getString(1));
            allEds.add(view);
            linear.addView(view);
            cursor.moveToNext();
        }

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
