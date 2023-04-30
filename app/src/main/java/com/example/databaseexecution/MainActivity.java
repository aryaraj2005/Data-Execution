package com.example.databaseexecution;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDatabase myDatabase = new MyDatabase(this );
//        myDatabase.addContact("Priyanka Raj" , "9026420905");
//        myDatabase.addContact("Arya  Raj" , "9026420005");
//        myDatabase.addContact("Renu Raj" , "9026420905");
//        myDatabase.addContact("Yash Raj" , "9026420605");

        // for update the data
         ContactModel model = new ContactModel();
         model.id =1;
         model.phone_no = "12341234";
         myDatabase.update_contact(model);


          ArrayList<ContactModel> contactModels = myDatabase.fetchContact();
          for (int i = 0 ; i<contactModels.size(); i++){

              // log pr isliye kiye hai kyiki hamare pass abhi loi ui nhi hai jha ise fetch kar sake
              Log.d("CONTACT_INFO" , "Name:" + contactModels.get(i).Name + ", Phone No "+contactModels.get(i).phone_no );

          }

    }
}