package com.example.trabalho2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ItemAdapter adapter;
    private ListView listView;
    private ActionMode actionMode;
    private HashSet<Integer> selectedItems = new HashSet<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        adapter = new ItemAdapter(this, items);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selectedItems.add(position);
                } else {
                    selectedItems.remove(position);
                }
                adapter.toggleSelection(position);
                updateActionModeTitle();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                actionMode = mode;
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        editSelectedItem();
                        mode.finish();
                        return true;
                    case R.id.menu_delete:
                        deleteSelectedItems();
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                adapter.clearSelection();
                selectedItems.clear();
                actionMode = null;
            }
        });
    }

    private void updateActionModeTitle() {
        if (actionMode != null) {
            int count = selectedItems.size();
            actionMode.setTitle(String.valueOf(count));
        }
    }

    private void editSelectedItem() {
        if (selectedItems.size() == 1) {
            int position = selectedItems.iterator().next();
            String item = items.get(position);
            showAddEditDialog(true, item, position);
        }
    }

    private void deleteSelectedItems() {
        ArrayList<Integer> positions = new ArrayList<>(selectedItems);
        Collections.sort(positions, Collections.reverseOrder());
        for (int position : positions) {
            items.remove(position);
        }
        adapter.notifyDataSetChanged();
        selectedItems.clear();
        actionMode = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            showAddEditDialog(false, "", -1);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void showAddEditDialog(boolean isEditMode, String currentText, final int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final EditText editText = new EditText(this);
        editText.setText(currentText);
        dialogBuilder.setTitle(isEditMode ? "Editar item" : "Adicionar item");
        dialogBuilder.setView(editText);
        dialogBuilder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newItem = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(newItem)) {
                    if (isEditMode) {
                        items.set(position, newItem);
                    } else {
                        items.add(newItem);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", null);
        dialogBuilder.show();
    }
}