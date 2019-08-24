 package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class MainActivity extends AppCompatActivity {

     public static final String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";
     private String choice = "moo";
     public HashMap<String, ArrayList<String>> choices = new HashMap<String, ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StringBuffer text = new StringBuffer();

//        text.append(" ${"_".repeat(choice.name.length + 2)} \n")
//          text.append(String.format(" %s \n", "_".repeat(this.choice.length() + 2)));
//          text.append("/ $choice /\n");
//          text.append(" ${"-".repeat(choice.name.length + 2)} \n");
//          text.append("    \\   ^__^\n");
//          text.append("     \\  (oo)\\_______\n");
//          text.append("        (__)\\       )\\/\\\n");
//          text.append("            ||----w |\n");
//          text.append("            ||     ||\n");

        text.append(String.format(" %s \n", this.repeatString("_", choice.length() + 2)))
        .append(String.format("/ %s /\n", this.choice))
        .append(String.format(" %s \n", this.repeatString("-", choice.length() + 2)))
        .append("    \\   ^__^\n")
        .append("     \\  (oo)\\_______\n")
        .append("        (__)\\       )\\/\\\n")
        .append("            ||----w |\n")
        .append("            ||     ||\n");

        TextView cowText = findViewById(R.id.cow);
        cowText.setText(text.toString());

        this.addSpinnerItems();
    }

    public void addSpinnerItems(){
      Spinner spinner = findViewById(R.id.group_choice);
      List<String> list = new ArrayList<String>();

      this.choices.forEach((key,value) -> System.out.println(key + " = " + value));

      list.add("list 1");
      list.add("list 2");
      ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
      dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
      spinner.setAdapter(dataAdapter);
    }

//    Called when user taps edit send button
//    public void editChoices(View view){
//        Intent intent = new Intent(this, EditActivity.class);
//        EditText editText = (EditText) findViewById(R.id.edit_btn);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }

   /**
    * Returns a string repeated n times
    * @param repeat - String to be repeated.
    * @param n - Number of times to be repeated.
    * @return - The string repeated n times.
    */
    public static String repeatString(String repeat, int n){
      if (n < 1){
        return "";
      }
      String result = "";
      for(int i = 0; i < n; i++){
        result = result + repeat;
      }
      return result;
    }
}
