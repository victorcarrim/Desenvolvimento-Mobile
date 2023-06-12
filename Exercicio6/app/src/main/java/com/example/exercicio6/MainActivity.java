package com.example.exercicio6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.actAddContact:
                String mensagem = getString(R.string.addContactToast);
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
                break;

            case R.id.actEditContact:
                String mensagem2 = getString(R.string.editContactToast);
                Toast.makeText(this, mensagem2, Toast.LENGTH_SHORT).show();
                break;

            case R.id.actRemove:
                String mensagem3 = getString(R.string.removeContactToast);
                Toast.makeText(this, mensagem3, Toast.LENGTH_SHORT).show();
                break;

            case R.id.actHelp:
                Toast.makeText(this, getString(R.string.help), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}