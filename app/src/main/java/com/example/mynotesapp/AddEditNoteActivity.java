package com.example.mynotesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.example.mynotesapp.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.example.mynotesapp.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.example.mynotesapp.EXTRA_DESCRIPTION";

    private EditText editTexttitle;
    private EditText editTextdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTexttitle =  findViewById(R.id.edit_text_title);
        editTextdescription = findViewById(R.id.edit_text_description);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent= getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTexttitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }
        else {
            setTitle("Add Note");
        }
    }

    private void saveNote(){
        String title = editTexttitle.getText().toString();
        String description = editTextdescription.getText().toString();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this, "Please Insert Title and Content", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id!=-1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}