package com.cursoandroid.tarefasassincronas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
    }

    public void iniciarAsyncTask (View view){

        MyAsyncTask task = new MyAsyncTask();
        task.execute(10);
    }
    /*
    * 1 -> Parâmetro a ser passado para a classe / void
    * 2 -> Tipo de valor que será utilizado para
    * o progresso da tarefa
    * 3 -> Retorno após tarefa finalizada
    */
    public void executar(String... String){
        String nome = String[0];
        Log.d("executar" , "executar: " + nome);
    }

    class MyAsyncTask extends AsyncTask<Integer , Integer , String> {

        @Override
        protected void onPreExecute() {
            //Executado na UI Thread
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(Integer... integers) {
            //Executado em uma Thread Paralela
            int numero = integers[0];
            for(int i=0 ; i < numero ; i++) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            return "Finalizado";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //Executado na UI Thread
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String string) {
            //Executado na UI Thread
            super.onPostExecute(string);
            Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
            progressBar.setProgress(0);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}