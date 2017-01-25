package multiplelay.android.stanislav.com.cloudtasks;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ListTasks extends AppCompatActivity{

    private FirebaseAuth mAuth;
    private DatabaseReference myRef;

    FirebaseUser user = mAuth.getInstance().getCurrentUser();

    FirebaseListAdapter mAdapter;

    private EditText ET_new_task;
    private Button Btn_new_task;

    ListView ListUserTasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks2);

        ListUserTasks = (ListView) findViewById(R.id.discr_for_task);

        myRef = FirebaseDatabase.getInstance().getReference();

        mAdapter = new FirebaseListAdapter <String>(this,String.class,android.R.layout.simple_list_item_1,myRef.child(user.getUid()).child("Tasks")) {
            @Override
            protected void populateView(View v, String s, int position) {
                TextView text = (TextView) v.findViewById(android.R.id.text1);
                text.setText(s);
            }
        };
        ListUserTasks.setAdapter(mAdapter);

        Btn_new_task = (Button)findViewById(R.id.btn_add);
        ET_new_task = (EditText)findViewById(R.id.et_new_tasks);

        Btn_new_task.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                myRef.child(user.getUid()).child("Tasks").push().setValue(ET_new_task.getText().toString());
            }
        });

    }
}
