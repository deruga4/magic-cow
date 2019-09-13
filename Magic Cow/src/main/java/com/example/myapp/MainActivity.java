package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity{

    /**
     * List name to list pair.
     */
    public HashMap<String, ArrayList<String>> lists = new HashMap<String, ArrayList<String>>();
    /**
     * Key value for the intent corresponding to the lists attribute.
     */
    public static final String EXTRA_LISTS_MESSAGE = "com.example.myapp.CHOICES";

    /**
     * The current list that is selected.
     */
    public String currentListName = null;
    /**
     * Key value for the intent corresponding to the currentListName attribute
     */
    public static final String EXTRA_CURRENT_LIST_MESSAGE = "com.example.myapp.CURRENT_CHOICE";

    /**
     * Keeps track of the names of all lists
     */
    public ArrayList<String> listNames = new ArrayList<String>();
    /**
     * Key value for the intent corresponding to the listNames attribute
     */
    public static final String EXTRA_LIST_NAMES_MESSAGE = "com.example.myapp.LIST_NAMES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("STATE", "checking to make sure this works");
        Log.d("STATE", "this should display when the app starts");
        Log.d("STATE", "notice me senpai");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Simulate loading a list
        */
        String newName = "Lunch";
        String[] newListObjects = new String[]{"Wendy's", "Subway", "Smoke's Poutine", "Jerk Chicken",
                "Mandarin", "Bang me baby", "PopEyes", "csgo", "Starve"};
        ArrayList<String> newList = new ArrayList<>();
        for (String item : newListObjects) {
            newList.add(item);
        }

        String breakfastName = "Breakfast";
        String[] breakfastListObjects = new String[]{"Cereal", "Pancakes", "Granola bar", "Coffee", "Nothing"};
        ArrayList<String> breakfastList = new ArrayList<String>();
        for (String item : breakfastListObjects){
            breakfastList.add(item);
        }

        listNames.add(newName);
        listNames.add(breakfastName);
        lists.put(newName, newList);
        lists.put(breakfastName, breakfastList);


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

        if (!lists.isEmpty()){

        }

        cowsay("moo");

        this.addSpinnerItems();


    }

    /**
     * Initializes the spinner items
     */
    private void addSpinnerItems() {
        Spinner spinner = findViewById(R.id.group_choice);
        List<String> list = new ArrayList<String>();

        for (String item : lists.keySet()) {
            list.add(item);
        }
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(dataAdapter);
        this.setCurrentListName(list.get(0));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // TODO: change current list upon selection.
                Log.d("STATE", "onItemSelected");
                Object[] listNames = lists.keySet().toArray();
                setCurrentListName((String) listNames[i]);
//                Log.d("STATE", listNames.toString());
                setCurrentListName(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
                Log.d("STATE", "onNothingSelected");
            }
        });
    }

    /**
     * Sets the next choice by picking a random item from the current list.
     * @param view - the context in which the Textiew is associated with.
     */
    public void rollChoices(View view) {
        Log.d("STATE", "Roll button pressed");
        Log.d("STATE", "Current List: " + this.currentListName);
        Random random = new Random();
        ArrayList<String> currentList = this.lists.get(this.currentListName);
        int index = random.nextInt(currentList.size());
        cowsay(currentList.get(index));
    }

    /**
     * Displays the text for the cow in the TextView
     * @param say - the string text to be displayed
     */
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
        intent.putExtra(MainActivity.EXTRA_LIST_NAMES_MESSAGE, this.listNames);
        startActivity(intent);
//        Intent intent = new Intent(this, DisplayMessageActivity.class);
//        EditText editText = (EditText) findViewById(R.id.editText);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
    }

    /**
     * Returns a string repeated n times.
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

    /**
     * Sets the currentListName attribute to the one it was changed to.
     * @param name - The name of the set list.
     */
    public void setCurrentListName(String name){
        if (this.listNames.contains(name)){
            this.currentListName = name;
        }
        else{
            Log.d("ERROR", "umm this shouldn't happen but you set an invalid list name");
        }
    }
}
