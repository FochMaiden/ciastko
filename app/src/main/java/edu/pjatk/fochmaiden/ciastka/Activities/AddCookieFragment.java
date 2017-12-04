package edu.pjatk.fochmaiden.ciastka.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import edu.pjatk.fochmaiden.ciastka.Data.DBManager;
import edu.pjatk.fochmaiden.ciastka.R;

/**
 * Created by FochMaiden on 04/12/2017.
 */

public class AddCookieFragment extends Fragment {

    private Button addBtn;
    private EditText subjectEditText;
    private EditText descEditText;

    private DBManager dbManager;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_add_record,container,false);


        subjectEditText = (EditText) v.findViewById(R.id.subject_edittext);
        descEditText = (EditText) v.findViewById(R.id.description_edittext);

        addBtn = (Button) v.findViewById(R.id.add_record);

        dbManager = new DBManager(this.getContext());
        dbManager.open();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.add_record:
                        final String name = subjectEditText.getText().toString();
                        final String desc = descEditText.getText().toString();

                        DBManager.insert(name, desc);

                        Intent main =  new Intent(v.getContext(), CookiesListActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(main);
                        break;
                }
            }
        });


        return v;
    }

}
