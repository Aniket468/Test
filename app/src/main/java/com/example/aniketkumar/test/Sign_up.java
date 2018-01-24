package com.example.aniketkumar.test;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by MNNIT on 1/14/2018.
 */

public class Sign_up extends AppCompatActivity {

    TextView login;
    private String result;
    private EditText password1,password2,name,email,phone;
    Button sign_up;
    TextInputLayout t1;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        sign_up= (Button) findViewById(R.id.sign_press);
        login=findViewById(R.id.login_link);
        name=findViewById(R.id.name);
        phone=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        password1=findViewById(R.id.password);
        password2=findViewById(R.id.re_password);
        t1=findViewById(R.id.re_enter_text_input);
        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    String pass1=password1.getText().toString();
                    String pass2=password2.getText().toString();
                    if(!(pass1.equals(pass2)))
                    {
                        t1.setError("Passwords do not match");
                    }
                    else
                    {
                        t1.setError(null);
                    }
            }
        });
        // On clicking the sign up button
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String password=password1.getText().toString();
                    String pass2=password2.getText().toString();
                    String name2=name.getText().toString();
                    String mobile1=phone.getText().toString();
                    String email1=email.getText().toString();
                    if(!(pass2.equals(password))||email1.equals("")||phone.equals("")||name2.equals("")||mobile1.equals(""))
                    {
                        Toast.makeText(getApplicationContext(),"Please check out the fields",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        BackgroundTask btask=new BackgroundTask();
                        btask.execute(name2,email1,password,mobile1);

                    }

            }
        });

        //On clicking the login link
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sign_up.this,Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public class BackgroundTask extends AsyncTask<String,Void,Void>
    {

        @Override
        protected Void doInBackground(String... strings) {
            String name=strings[0];
            String email=strings[1];
            String password=strings[2];
            String phone=strings[3];
            String connnection="http://192.168.43.210/test_connection/db_register.php";
            try {
                URL url=new URL(connnection);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                OutputStream out=httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
                String data= URLEncoder.encode("NAME","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                            URLEncoder.encode("EMAIL","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                            URLEncoder.encode("PASSWORD","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                            URLEncoder.encode("PHONE","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8");
                Log.d("data",data);
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream=new BufferedInputStream(httpURLConnection.getInputStream());
                result=convertStreamToString(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d("TAG","URL excp");
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG","Excep");
                Log.d("Excep",e.toString());
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);
            if(result!=null)
            Log.d("TAG",result);
            else
                Log.d("TAG","NO result");
        }

        String convertStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb=new StringBuilder("");
            String line;
            while((line=bufferedReader.readLine())!=null){
                sb.append(line+"\n");
            }
            inputStream.close();
            return sb.toString();
        }
    }
}

