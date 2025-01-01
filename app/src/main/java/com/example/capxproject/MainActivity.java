package com.example.capxproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String api_key="ROUW222MNKJ240W6";
    ArrayList<StockDataModel> dataModelList;
    TextView stockSymbol,stockPrice, priceChange,latestTradingDay,percentChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText searchInput= findViewById(R.id.search_bar);
        Button searchBtn= findViewById(R.id.search_button);
        stockSymbol=findViewById(R.id.stock_name);
        stockPrice=findViewById(R.id.stock_price);
        priceChange=findViewById(R.id.price_change);
        percentChange=findViewById(R.id.percent_change);
        latestTradingDay=findViewById(R.id.trading_day);


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symbol= searchInput.getText().toString();
                fetchStockData(symbol);
            }
        });

    }

    private void fetchStockData(String symbol) {

        if(!symbol.isEmpty()){
            String url="https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol="+symbol+"&apikey="+api_key;
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);

            // Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                dataModelList= new ArrayList<>();
                                JSONObject obj= new JSONObject(response);
                                JSONObject quoteObj= obj.getJSONObject("Global Quote");

                                Log.e("api","onResponse"+quoteObj);
                                StockDataModel model= new StockDataModel(quoteObj.getString("01. symbol"),
                                        quoteObj.getString("02. open"),quoteObj.getString("03. high"),
                                        quoteObj.getString("04. low"),quoteObj.getString("05. price"),
                                        quoteObj.getString("06. volume"),quoteObj.getString("07. latest trading day"),
                                        quoteObj.getString("08. previous close"), quoteObj.getString("09. change"),
                                        quoteObj.getString("10. change percent"));
                                dataModelList.add(model);
                                StockDataModel dataModel=new StockDataModel();

                                // set stock data to textview
                                stockSymbol.setText(dataModelList.get(0).getSymbol());
                                stockPrice.setText(dataModelList.get(0).getPrice());
                                priceChange.setText(dataModelList.get(0).getChange());
                                percentChange.setText(dataModelList.get(0).getChange_percent());
                                latestTradingDay.setText(dataModelList.get(0).getLatest_trading_day());
                                Log.e("api","onResponse:"+dataModelList.size());
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if(error.networkResponse!=null && error.networkResponse.statusCode==404){
                        Toast.makeText(MainActivity.this,"Stock symbol not found.",Toast.LENGTH_LONG).show();
                    }
                    Log.e("API_ERROR", "Error fetching stock data "+ error.getMessage());

                }
            });

            // Add the request to the RequestQueue.
            queue.add(stringRequest);
        }else {
            Toast.makeText(MainActivity.this,"Please enter a valid symbol",Toast.LENGTH_LONG).show();
        }
    }
}