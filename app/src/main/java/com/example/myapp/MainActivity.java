package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EXTRA_MESSAGE = "com.example.myapp.MESSAGE";
    public HashMap<String, ArrayList<String>> choices = new HashMap<String, ArrayList<String>>();
    public String currentListName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Simulate loading a list
        */
        String newName = "Lunch";
        String[] newListObjects = new String[]{"Wendy's", "Subway", "Smoke's Poutine", "Jerk Chicken",
                "Mandarin", "Bang me baby", "PopEyes", "csgo"};
        ArrayList<String> newList = new ArrayList<>();
        for (String item : newListObjects) {
            newList.add(item);
        }

        String breakfastName = "Breakfast";
        String[] breakfastListObjects = new String[]{"Cereal", "Pancakes", "Granola bar"};
        ArrayList<String> breakfastList = new ArrayList<String>();
        for (String item : breakfastListObjects){
            breakfastList.add(item);
        }


        choices.put(newName, newList);
        choices.put(breakfastName, breakfastList);


        /*
        End simulation
        */

//        this.currentList.add("Wendy's");
//        this.currentList.add("Subway");
//        this.currentList.add("Smoke's Poutine");
//        this.currentList.add("Jerk Chicken");
//        this.currentList.add("Mandarin");
//        this.currentList.add("Bang me baby");
//        this.currentList.add("Popeyes");
//        this.currentList.add("csgo");


//        text.append(" ${"_".repeat(choice.name.length + 2)} \n")
//          text.append(String.format(" %s \n", "_".repeat(this.choice.length() + 2)));
//          text.append("/ $choice /\n");
//          text.append(" ${"-".repeat(choice.name.length + 2)} \n");
//          text.append("    \\   ^__^\n");
//          text.append("     \\  (oo)\\_______\n");
//          text.append("        (__)\\       )\\/\\\n");
//          text.append("            ||----w |\n");
//          text.append("            ||     ||\n");

        if (!choices.isEmpty()){

        }

        cowsay("moo");

        this.addSpinnerItems();
    }

    public void addSpinnerItems() {
        Spinner spinner = findViewById(R.id.group_choice);
        List<String> list = new ArrayList<String>();

        for (String item : choices.keySet()) {
            list.add(item);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);
        this.setCurrentListName(list.get(0));
    }

    public void rollChoices(View view) {
        Random random = new Random();
        ArrayList<String> currentList = this.choices.get(this.currentListName);
        int index = random.nextInt(currentList.size());
        cowsay(currentList.get(index));
    }

    public void cowsay(String say) {
        StringBuffer text = new StringBuffer();
        text.append(String.format(" %s \n", this.repeatString("_", say.length() + 2)))
                .append(String.format("/ %s /\n", say))
                .append(String.format(" %s \n", this.repeatString("-", say.length() + 2)))
                .append("    \\   ^__^\n")
                .append("     \\  (oo)\\_______\n")
                .append("        (__)\\       )\\/\\\n")
                .append("            ||----w |\n")
                .append("            ||     ||\n");

        TextView cowText = findViewById(R.id.cow);
        cowText.setText(text.toString());
    }

    /**
     * Called when user taps edit button
     */
    public void editChoices(View view) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
    }

    /**
     * Returns a string repeated n times
     *
     * @param repeat - String to be repeated.
     * @param n      - Number of times to be repeated.
     * @return - The string repeated n times.
     */
    public static String repeatString(String repeat, int n) {
        if (n < 1) {
            return "";
        }
        String result = "";
        for (int i = 0; i < n; i++) {
            result = result + repeat;
        }
        return result;
    }

    public String getCurrentListName(){
        String x = this.currentListName;
        return x;
    }

    public void setCurrentListName(String name){
        this.currentListName = name;
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // TODO: change current list upon selection.
        Log.d("STATE", "onItemSelected");
        String[] listNames = (String[]) this.choices.keySet().toArray();
        Log.d("STATE", listNames.toString());
        System.out.println(adapterView.getItemAtPosition(i).toString());
        this.setCurrentListName(adapterView.getItemAtPosition(i).toString());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d("STATE", "onNothingSelected");
    }
}
