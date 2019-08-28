package com.felix.apiintegrationapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void GetDataClicked(View view) {

        new YourAsyncTask(MainActivity.this).execute("");

    }


    private class YourAsyncTask extends AsyncTask<String, Void, String> {
        private ProgressDialog dialog;

        public YourAsyncTask(MainActivity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Doing something, please wait.");
            dialog.show();
        }

        protected String doInBackground(String... args) {


//            String url = "http://api.androidhive.info/contacts/";
            String url = "http://api.myjson.com/bins/d5y1e";
            String jsonStr = "";
            try {
                // Making a request to url and getting response
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(url));
                HttpResponse response = client.execute(request);
                jsonStr = EntityUtils.toString(response.getEntity());
            } catch (Exception e) {
                Log.e("Main Activity", "Exception: " + e.getMessage());
            }


            return jsonStr;
        }

        protected void onPostExecute(String result) {

//
//            try {

                Gson gson = new Gson();

                Type listType = new TypeToken<List<Product>>() {}.getType();

                List<Product> pList= gson.fromJson(result, listType);


//
//                JSONArray pArr=new JSONArray(result);
//
//                List<Product> productList=new ArrayList<>();
//
//                for(int i=0;i<pArr.length();i++){
//                    JSONObject pObj=pArr.getJSONObject(i);
//                    Product product=new Product();
//
//                    product.setProductName(pObj.getString("productName"));
//                    product.setImageUrl(pObj.getString("imageUrl"));
//                    product.setQuantity(pObj.getInt("quantity"));
//                    product.setUnit(pObj.getString("unit"));
//                    product.setPrice(pObj.getInt("price"));
//
//
//                    productList.add(product);
//
//                }





//                JSONObject mainObj=new JSONObject(result);
//
//                JSONArray contactsArr=mainObj.getJSONArray("contacts");
//
//                List<Contacts> contactsList=new ArrayList<>();
//
//
//                for(int i=0;i<contactsArr.length();i++){
//
//                    Contacts contact=new Contacts();
//
//
//                    JSONObject obj=contactsArr.getJSONObject(i);
//
//                    contact.set_id(obj.getString("id"));
//
//                    contact.set_name(obj.getString("name"));
//                    contact.set_email(obj.getString("email"));
//                    contact.set_address(obj.getString("address"));
//                    contact.set_gender(obj.getString("gender"));
//
////                    String name=obj.getString("name");
////                    Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
//
//                    JSONObject objPhone=obj.getJSONObject("phone");
//
//                    contact.set_mobile(objPhone.getString("mobile"));
//                    contact.set_home(objPhone.getString("home"));
//                    contact.set_office(objPhone.getString("office"));
//
//                    contactsList.add(contact);
//
//                }


//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
            // do UI work here
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

        }
    }
}
