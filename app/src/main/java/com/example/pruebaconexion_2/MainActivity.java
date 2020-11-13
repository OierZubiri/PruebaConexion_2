package com.example.pruebaconexion_2;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    String sResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview = findViewById(R.id.textview);

    }

    public void conectar(View v) {
        new ConnectMySql().execute();
    }
    public void mostrarDato(View v) {
        Log.i("mmmmm", sResultado);
        textview.setText(sResultado);
    }

    // ***************************** INICIO PROCESO BASE DE DATOS *********************************
    private class ConnectMySql extends AsyncTask<String, Void, String> {

        String res = "";

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        //proceso para conectar a la base de datos y ver si ese usuario y pass estan bien
        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(String... variableNoUsada) {


            try {
                Class.forName("com.mysql.jdbc.Driver");
                //Class.forName("com.mysql.cj.jdbc.Driver");


                //String url =  "jdbc:mysql://192.168.88.10:3306/pruebaoier?serverTimezone=UTC";
                //String url =  "jdbc:mysql://db4free.net:3306/prueba_oier?serverTimezone=UTC";
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://192.168.2.16:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                        "root", "");
                String sql = "SELECT * FROM ubicacion";
                PreparedStatement st = con.prepareStatement(sql);
                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    String var1 = rs.getString("Nombre");
                    Log.i("XXXXXXX", var1);
                    sResultado = var1;
                }
                rs.close();
                st.close();
                con.close();
            } catch (ClassNotFoundException e) {
                Log.i("111111", "");
                e.printStackTrace();
            } catch (SQLException e) {
                Log.i("22222", "");
                e.printStackTrace();
            } catch (Exception e) {
                Log.i("33333", "");
                e.printStackTrace();
            }


            return res;
        }
    }
    //****************************** FIN PROCESO BASE DE DATOS ***********************************

}
