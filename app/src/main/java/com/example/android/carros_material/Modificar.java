package com.example.android.carros_material;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Modificar extends AppCompatActivity {
    private String placa_bun,id;
    private double precio_bun;
    private int color,modelo,marca,fot;
    private Intent i;
    private Bundle bundle;
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
        setContentView(R.layout.activity_modificar);
        res = this.getResources();
        precio = (EditText)findViewById(R.id.txtprecio);
        placa= (EditText)findViewById(R.id.txtplaca);

        sp_color = (Spinner)findViewById(R.id.spcolor);
        sp_modelo = (Spinner)findViewById(R.id.spmodelo);
        sp_marca = (Spinner)findViewById(R.id.spmarca);


        v_color = res.getStringArray(R.array.array_color);
        ArrayAdapter<String> adapter_color = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,v_color);
        sp_color.setAdapter(adapter_color);

        v_modelo = res.getStringArray(R.array.array_madelo);
        ArrayAdapter<String> adapter_modelo= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,v_modelo);
        sp_modelo.setAdapter(adapter_modelo);

        v_marca = res.getStringArray(R.array.array_marca);
        ArrayAdapter<String> adapter_marca = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,v_marca);
        sp_marca.setAdapter(adapter_marca);
        i = getIntent();

        bundle = i.getBundleExtra("datos");
        placa_bun = bundle.getString("placa");
        precio_bun = bundle.getDouble("precio");
        id = bundle.getString("id");
        color = bundle.getInt("color");
        modelo = bundle.getInt("modelo");
        marca = bundle.getInt("marca");
        fot = bundle.getInt("foto");

        placa.setText(placa_bun);
        precio.setText(precio_bun+"");
        sp_color.setSelection(color);
        sp_modelo.setSelection(modelo);
        sp_marca.setSelection(marca);

    }
    public void Modificar(View v){


        if (placa.getText().length() ==0 || precio.getText().length() ==0){
            Toast.makeText(this, res.getString(R.string.mensaje_error_placa), Toast.LENGTH_SHORT).show();
        } if (precio.getText().length() ==0){
            Toast.makeText(this, res.getString(R.string.mensaje_error_precio), Toast.LENGTH_SHORT).show();
        }else {
            String placa_caja;
            double precio_caja;
            int modelo_caja, marca_caja, color_caja;
            placa_caja = placa.getText().toString();
            precio_caja = Double.parseDouble(precio.getText().toString());
            modelo_caja = sp_modelo.getSelectedItemPosition();
            marca_caja = sp_marca.getSelectedItemPosition();
            color_caja = sp_color.getSelectedItemPosition();
          Boolean sw = false;

            if (placa_caja.equalsIgnoreCase(placa_bun)){
                sw =true;
            }else if (!datos.ExistePlacaIndex(placa_caja)){

                sw =true;
            }
          if (sw==true){

            Carro p = new Carro(placa_caja, marca_caja, modelo_caja, color_caja, precio_caja,fot,id);
            p.Modificar();

            Toast.makeText(this, res.getString(R.string.mensaje_modificado), Toast.LENGTH_SHORT).show();
            Cancelar();
         }else{
              Toast.makeText(this, res.getString(R.string.existe_placa), Toast.LENGTH_SHORT).show();
              placa.setText("");
              placa.requestFocus();
          }
        }
    }


    public void Cancelar(View v) {
        Cancelar();
    }

    public void Cancelar(){
        String placa_caja;
        double precio_caja;
        int modelo_caja, marca_caja, color_caja;
        placa_caja = placa.getText().toString();
        precio_caja = Double.parseDouble(precio.getText().toString());
        modelo_caja = sp_modelo.getSelectedItemPosition();
        marca_caja = sp_marca.getSelectedItemPosition();
        color_caja = sp_color.getSelectedItemPosition();
        Intent i = new Intent(Modificar.this,Detalle_Carro.class);
        Bundle b = new Bundle();
        b.putInt("marca",marca_caja);
        b.putInt("modelo",modelo_caja);
        b.putString("placa",placa_caja);
        b.putString("id",id);
        b.putDouble("precio",precio_caja);
        b.putInt("color",color_caja);
        b.putInt("foto",fot);
        i.putExtra("datos",b);

        startActivity(i);
    }
}
