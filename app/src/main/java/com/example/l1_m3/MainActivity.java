package com.example.l1_m3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.SearchEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnMessage, btnCall, btnSearch, btnGet;
    EditText etNumber;
    ListView lv_contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGet = findViewById(R.id.btn_getcontacts);
        btnMessage = findViewById(R.id.btn_message);
        btnSearch = findViewById(R.id.btn_search);
        btnCall = findViewById(R.id.btn_call);
        etNumber = findViewById(R.id.et_number);
        lv_contacts = findViewById(R.id.lv_contacts);

        btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etNumber.getText().toString().trim();
                String url = "https://api.whatsapp.com/send?phone=" + phone;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = etNumber.getText().toString().trim();
                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                startActivity(intentCall);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_WEB_SEARCH);
                webIntent.putExtra(SearchManager.QUERY, etNumber.getText().toString().trim());
                startActivity(webIntent);

            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
                startManagingCursor(cursor);

                String[] from = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone._ID };
                int[] to = {android.R.id.text1, android.R.id.text2};

                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(MainActivity.this, android.R.layout.simple_list_item_2, cursor, from, to);
                lv_contacts.setAdapter(simpleCursorAdapter);
                lv_contacts.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);




            }
        });

        // intent действия (открыть камеру, переход на др активити)
        // putExtra  параметры для действий

    }
}