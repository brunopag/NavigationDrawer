package com.example.brunopaganini.navigationdrawer;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AlertsFragment extends Fragment {

    private TextView textViewAlert;
    private FloatingActionButton fab;

    public AlertsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_alerts, container, false);

        textViewAlert = (TextView) view.findViewById(R.id.textViewAlert);
        fab = (FloatingActionButton) view.findViewById(R.id.fabAlert);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertForAlertsScreen("Alerts", "Set alerts enabled/disabled", view);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    //DIALOG
    private void showAlertForAlertsScreen(String title, String message, View view) {
        // Create Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        // Set Title andMessage
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        // Inflate Layout
        View viewInflater = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_alert, null);
        builder.setView(viewInflater);

        // Get Switch text from layout
        final Switch aSwitchAlert = (Switch) viewInflater.findViewById(R.id.switchAlert);

        // Add positive buton and on click behavior
        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if (aSwitchAlert.isChecked()) {
                    textViewAlert.setText("Alerts Enabled");
                } else{
                    textViewAlert.setText("Alerts Disabled");
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        // Create and show
        builder.create().show();
    }
}
