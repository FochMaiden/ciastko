package edu.pjatk.fochmaiden.ciastka.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import edu.pjatk.fochmaiden.ciastka.Data.DBManager;
import edu.pjatk.fochmaiden.ciastka.R;

/**
 * Created by FochMaiden on 02/12/2017.
 */

public class ModifyCookieActivity extends Activity implements OnClickListener{

    public EditText titleText;
    private Button updateBtn, deleteBtn;
    private EditText descText;

    private long _id;

    private DBManager dbManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Modify record");

        setContentView(R.layout.activity_modify_record);
        dbManager = new DBManager(this);
        dbManager.open();

        titleText = (EditText) findViewById(R.id.subject_edittext);
        descText = (EditText) findViewById(R.id.description_edittext);

        updateBtn = (Button) findViewById(R.id.btn_update);
        deleteBtn = (Button) findViewById(R.id.btn_delete);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        _id = Long.parseLong(id);

        titleText.setText(name);
        descText.setText(desc);

        updateBtn.setOnClickListener(this);
        deleteBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_update:

                String title = titleText.getText().toString();
                String desc = descText.getText().toString();

                dbManager.update(_id, title, desc);
                this.returnHome();
                break;

            case R.id.btn_delete:

                dbManager.delete(_id);
                this.returnHome();
                break;
        }
    }

    private void returnHome() {
        Intent home_intent = new Intent(getApplicationContext(), CookiesListActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(home_intent);
    }


}
