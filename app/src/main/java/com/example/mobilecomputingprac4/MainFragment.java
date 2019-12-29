package com.example.mobilecomputingprac4;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainFragment extends Fragment implements View.OnClickListener {

    ImageView imageView;
    Button button1, button2, button3;

    ArrayList<Integer> celebrityNames= new ArrayList<>(Arrays.asList(R.string.celebrity1, R.string.celebrity2,
            R.string.celebrity3, R.string.celebrity4, R.string.celebrity5, R.string.celebrity6));

    ArrayList<Integer> celebrityImages = new ArrayList<>(Arrays.asList(R.drawable.celebrity1,
            R.drawable.celebrity2, R.drawable.celebrity3, R.drawable.celebrity4, R.drawable.celebrity5, R.drawable.celebrity6));

    ArrayList<Integer> selectableOptions = new ArrayList<>();

    ArrayList<Button> buttons = new ArrayList<>();

    int numOfQuestions = 3;

    public int chosenIndex;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // depending on the orientation, set true or false
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("Main Fragment is visible again");
        populateUserChooseableOptions();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = (ImageView) getView().findViewById(R.id.imageView);

        populateUserChooseableOptions();
    }



    public void populateUserChooseableOptions() {
        LinearLayout llForButtons = getView().findViewById(R.id.LinearLayoutForButtons);
        llForButtons.removeAllViews();

        System.out.println("Num of Questions in MainFragment: " + numOfQuestions);

        Random random = new Random();
        chosenIndex = Math.abs(random.nextInt() % 6);

        selectableOptions.clear();

//        int selectedIndex = randomIndex;

        imageView.setImageResource(celebrityImages.get(chosenIndex));

//        chosenIndex = selectedIndex;
        Context context = getActivity().getApplicationContext();

        selectableOptions.add(chosenIndex);

        while (selectableOptions.size() < numOfQuestions) {
            int randomIndex = Math.abs(random.nextInt() % 6);

            if (!selectableOptions.contains(randomIndex))
                    selectableOptions.add(randomIndex);

        }

        Collections.shuffle(selectableOptions);

        for (int i = 0; i < numOfQuestions; i++) {
            String name = context.getString(celebrityNames.get(selectableOptions.get(i)));

//            buttons.get(i).setText(name);

            Button button = new Button(getView().getContext());
            button.setText(name);
            button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            button.setOnClickListener(this);

            llForButtons.addView(button);
        }
    }

    @Override
    public void onClick(View v) {
        Button tempButton = (Button) v;
        Context context = getActivity().getApplicationContext();

        String chosenOption = tempButton.getText().toString();

        System.out.println(chosenOption);

        if (chosenOption.equals(context.getString(celebrityNames.get(chosenIndex)))) {
            tempButton.setBackgroundColor(getResources().getColor(R.color.green, null));
            System.out.println("You made the right choice");

            Toast toast = Toast.makeText(context, "Correct! You Genius!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 10);
            toast.show();
        } else {
            System.out.println("You made the wrong choice");

            tempButton.setBackgroundColor(getResources().getColor(R.color.red, null));

            Toast toast = Toast.makeText(context, "Wrong! You Idiot!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 10);
            toast.show();
        }

    }
}
