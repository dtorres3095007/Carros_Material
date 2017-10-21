package com.example.android.carros_material;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Guardar_Carros extends AppCompatActivity {
    private EditText precio,placa;
    private Resources res;
    private Spinner sp_color;
    private Spinner sp_marca;
    private Spinner sp_modelo;
    private String [] v_marca;
    private String [] v_modelo;
    private String [] v_color;
    private ArrayList<Integer> fotos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar__carros);
        precio = (EditText)findViewById(R.id.txtprecio);
        placa= (EditText)findViewById(R.id.txtplaca);

        sp_color = (Spinner)findViewById(R.id.spcolor);
        sp_modelo = (Spinner)findViewById(R.id.spmodelo);
        sp_marca = (Spinner)findViewById(R.id.spmarca);

        res = this.getResources();

        fotos = new ArrayList<>();
        fotos.add(R.drawable.imagen1);
        fotos.add(R.drawable.imagen2);
        fotos.add(R.drawable.imagen3);

        v_color = res.getStringArray(R.array.array_color);
        ArrayAdapter<String> adapter_color = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,v_color);
        sp_color.setAdapter(adapter_color);

        v_modelo = res.getStringArray(R.array.array_madelo);
        ArrayAdapter<String> adapter_modelo= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,v_modelo);
        sp_modelo.setAdapter(adapter_modelo);

        v_marca = res.getStringArray(R.array.array_marca);
        ArrayAdapter<String> adapter_marca = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,v_marca);
        sp_marca.setAdapter(adapter_marca);
    }
    public void guardar(View v){


        if (placa.getText().length() ==0 || precio.getText().length() ==0){
            Toast.makeText(this, res.getString(R.string.mensaje_error), Toast.LENGTH_SHORT).show();
        }else {
            String placa_caja;
            double precio_caja;
            int modelo_caja, marca_caja, color_caja;
            placa_caja = placa.getText().toString();
            precio_caja = Double.parseDouble(precio.getText().toString());
            modelo_caja = sp_modelo.getSelectedItemPosition();
            marca_caja = sp_marca.getSelectedItemPosition();
            color_caja = sp_color.getSelectedItemPosition();


            Carro p = new Carro(placa_caja, marca_caja, modelo_caja, color_caja, precio_caja, datos.fotoAleatoria(fotos));
            p.Guardar();

            Toast.makeText(this, res.getString(R.string.mensaje_guardado), Toast.LENGTH_SHORT).show();
            limpiar();
        }
    }

    public void limpiar(View v){
        limpiar();
    }

    private void limpiar(){
        precio.setText("");
        placa.setText("");

    }
}
