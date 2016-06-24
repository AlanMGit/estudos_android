package com.android.bottomsheet;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mAbrirBottonSheet;
    private Dialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mAbrirBottonSheet = (Button)findViewById(R.id.btn_abrir);

        mAbrirBottonSheet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        View view = getLayoutInflater().inflate(R.layout.bottom_sheet, null);

        TextView tvPrimeiro = (TextView) view.findViewById(R.id.primeiro);
        TextView tvSegundo = (TextView) view.findViewById(R.id.segundo);

        mDialog = new Dialog(this, R.style.MaterialDialogSheet);
        mDialog.setContentView(view);
        mDialog.setCancelable(true);
        mDialog.getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow ().setGravity (Gravity.BOTTOM);
        mDialog.show ();

        tvPrimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Clicou no Primeiro", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });

        tvSegundo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplication(), "Clicou no Segundo", Toast.LENGTH_SHORT).show();
                mDialog.dismiss();
            }
        });
    }
}
