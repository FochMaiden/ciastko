package edu.pjatk.fochmaiden.ciastka.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.pjatk.fochmaiden.ciastka.Data.DBManager;
import edu.pjatk.fochmaiden.ciastka.R;

/**
 * Created by FochMaiden on 02/12/2017.
 */

public class AddCookieActivity extends AppCompatActivity implements View.OnClickListener {

    private Button addBtn;
    private EditText subjectEditText;
    private EditText descEditText;

    private DBManager dbManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);


//        FragmentManager fm = getSupportFragmentManager();
//
//        Fragment fragment = new AddCookieFragment();
//        fragment.setArguments(new Bundle());
//
//        fm.beginTransaction()
//                .add(R.id.addContainer, fragment)
//                .commit();

        setTitle("Add record");

        subjectEditText = (EditText) findViewById(R.id.subject_edittext);
        descEditText = (EditText) findViewById(R.id.description_edittext);

        addBtn = (Button) findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_record:
                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                DBManager.insert(name, desc);

                Intent main =  new Intent(this , CookiesListActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;
        }

    }
}
