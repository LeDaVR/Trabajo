package com.example.clients;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.clients.BasedeDatos.DatosOpenHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NuevoCliente extends AppCompatActivity {

    private EditText editNombre;
    private EditText editDireccion;
    private EditText editEmail;
    private EditText editTelefono;

    private SQLiteDatabase conexion;
    private DatosOpenHelper datosOpenHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        editNombre= (EditText) findViewById(R.id.editName);
        editDireccion= (EditText) findViewById(R.id.editAdress);
        editEmail= (EditText) findViewById(R.id.editEmail);
        editTelefono= (EditText) findViewById(R.id.editPhone);
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_nuevo_cliente,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        switch (id){
            case R.id.action_ok:
                if(bCamposCorrectos()){
                    try{
                        datosOpenHelper = new DatosOpenHelper(this );
                        conexion = datosOpenHelper.getWritableDatabase();
                        StringBuilder sql = new StringBuilder();
                        sql.append("INSERT INTO CLIENTE (NOMBRE, DIRECCION, EMAIL, TELEFONO) VALUES ('");
                        sql.append(editNombre.getText().toString().trim()+ "', '");
                        sql.append(editDireccion.getText().toString().trim()+ "', '");
                        sql.append(editEmail.getText().toString().trim()+ "', '");
                        sql.append(editTelefono.getText().toString().trim()+ "')");

                        conexion.execSQL(sql.toString());

                        System.out.println("EJECUTADO COMANDO");

                        conexion.close();

                        System.out.println("CONEXION CERRADA");
                        finish();
                    } catch (Exception ex){
                        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                        dlg.setTitle("AVISO ERROR");
                        dlg.setMessage(ex.getMessage());
                        dlg.setNeutralButton("OK",null);
                        dlg.show();
                    }
                }
                else{
                    AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                    dlg.setTitle("Aviso");
                    dlg.setMessage("EXISTEN CAMPOS VACIOS");
                    dlg.setNeutralButton("OK",null);
                    dlg.show();
                }
                //Toast.makeText( this, "BOTONa OK SELECCIONADO",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cancel:
                //Toast.makeText( this, "BOTON CANCEL SELECCIONADO",Toast.LENGTH_SHORT).show();
                finish();
                break;

        }
        return super.onOptionsItemSelected(menuItem);
    }

    private boolean bCamposCorrectos(){
        boolean res = true;
        if(editNombre.getText().toString().trim().isEmpty()){
            editNombre.requestFocus();
            res=false;
        }
        if(editDireccion.getText().toString().trim().isEmpty()){
            editNombre.requestFocus();
            res=false;
        }
        if(editEmail.getText().toString().trim().isEmpty()){
            editNombre.requestFocus();
            res=false;
        }
        if(editTelefono.getText().toString().trim().isEmpty()){
            editNombre.requestFocus();
            res=false;
        }
        return res;
    }
}
