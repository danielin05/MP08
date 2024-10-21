package com.example.hundirlaflota;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int NUM_ROWS = 10;  // Files (0-9)
    private static final int NUM_COLS = 10;  // Columnes (A-J)
    private static final String[] COL_LETTERS = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout tableLayout = findViewById(R.id.tableLayout);
        createBoard(tableLayout);
    }

    private void createBoard(TableLayout tableLayout) {
        // Primer afegim la capçalera de lletres (A-J)
        TableRow headerRow = new TableRow(this);
        // Afegim una cel·la buida a la cantonada superior esquerra
        TextView emptyCorner = new TextView(this);
        emptyCorner.setLayoutParams(new TableRow.LayoutParams(100, 100));
        headerRow.addView(emptyCorner);

        // Afegim les lletres a la primera fila (A, B, C, ...)
        for (int col = 0; col < NUM_COLS; col++) {
            TextView headerCell = new TextView(this);
            headerCell.setLayoutParams(new TableRow.LayoutParams(100, 100));
            headerCell.setText(COL_LETTERS[col]);
            headerCell.setGravity(android.view.Gravity.CENTER);
            headerRow.addView(headerCell);
        }
        tableLayout.addView(headerRow); // Afegim la fila de capçalera

        // Ara afegim les files amb els números i els botons
        for (int row = 0; row < NUM_ROWS; row++) {
            TableRow tableRow = new TableRow(this);

            // Primer afegim el número a la primera columna de la fila
            TextView rowHeader = new TextView(this);
            rowHeader.setLayoutParams(new TableRow.LayoutParams(100, 100));
            rowHeader.setText(String.valueOf(row));
            rowHeader.setGravity(android.view.Gravity.CENTER);
            tableRow.addView(rowHeader);

            // Afegim els botons per a cada columna
            for (int col = 0; col < NUM_COLS; col++) {
                Button button = new Button(this);

                // Definim les dimensions 80x80 px
                TableRow.LayoutParams params = new TableRow.LayoutParams(100, 100);
                button.setLayoutParams(params);

                // Assignem una etiqueta identificativa basada en la posició
                String cellId = COL_LETTERS[col] + row;  // A0, B1, C5, etc.

                // Establim el text del botó per mostrar la seva ubicació (A0, A1, B3, etc.)
                button.setText("?");
                button.setTextSize(10); // Ajustem la mida del text per a que encaixi
                button.setPadding(12, 12, 12, 12);

                // Afegim el listener per capturar el clic i mostrar el valor al terminal
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, ("Casella seleccionada: " + cellId), Toast.LENGTH_SHORT).show();
                        Log.d("BatallaNaval", "Casella seleccionada: " + cellId);
                    }
                });

                // Afegim el botó a la fila
                tableRow.addView(button);
            }

            // Afegim la fila al layout de la taula
            tableLayout.addView(tableRow);
        }
    }
}
