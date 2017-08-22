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
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailsFragment extends Fragment {

    private FloatingActionButton fab;
    private TextView textViewEmail;

    public EmailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_emails, container, false);

        textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
        fab = (FloatingActionButton) view.findViewById(R.id.fabEmail);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertForCreatingBoard("New Email", "Write new email:", view);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    //DIALOG
    private void showAlertForCreatingBoard(String title, String message, View view) {
        // Create Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

        // Set Title andMessage
        if (title != null) builder.setTitle(title);
        if (message != null) builder.setMessage(message);

        // Inflate Layout
        View viewInflater = LayoutInflater.from(view.getContext()).inflate(R.layout.dialog_create_email, null);
        builder.setView(viewInflater);

        // Get Edit text from layout
        final EditText editText = (EditText) viewInflater.findViewById(R.id.editTextEmailDialog);

        // Add positive buton and on click behavior
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String emailName = editText.getText().toString().trim();

                textViewEmail.setText(emailName);
            }
        });
        builder.setNegativeButton("Cancel", null);
        // Create and show
        builder.create().show();
    }

}
