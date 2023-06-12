package com.example.exercicio5;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.TextView;

public class ProcessarTask extends AsyncTask<Integer, Integer, Void> {

    Button btnIniciar;
    TextView txtContar;

    public ProcessarTask(Button btnIniciar, TextView txtContar) {
        this.btnIniciar = btnIniciar;
        this.txtContar = txtContar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        btnIniciar.setEnabled(false);
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        btnIniciar.setEnabled(true);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        Integer c = values[0];
        txtContar.setText(c.toString());
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        int min = integers[0];
        for (int i = 10; i >= min; i--){
            SystemClock.sleep(1000);
            publishProgress(i);
        }
        return null;
    }

}
