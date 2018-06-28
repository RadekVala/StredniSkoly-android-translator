package fai.utb.cz.androidtranslator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

  public RequestQueue queue;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });

    this.queue = Volley.newRequestQueue(this);

    // button onclick listener
    Button btnTranslate = findViewById(R.id.btnTranslate);
    btnTranslate.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        TextView input = findViewById(R.id.input);
        CharSequence text = input.getText();

        final TextView resultText = findViewById(R.id.resultText);
        resultText.setText(text);


        String url = "https://api.mymemory.translated.net/get?q="+text+"&langpair=cs|en";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
          (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
              try {
                JSONObject responseData = response.getJSONObject("responseData");
                CharSequence translatedResult = responseData.getString("translatedText");
                resultText.setText(translatedResult);
              } catch (JSONException e) {
                e.printStackTrace();
              }


            }
          }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
              // TODO: Handle error

            }
          });

        MainActivity.this.queue.add(jsonObjectRequest);

      }
    });

    Context context = getApplicationContext();
    CharSequence text = "Aktivita: onCreate";
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(context, text, duration);
    toast.show();

  }

  @Override
  protected void onStart() {
    super.onStart();
    Context context = getApplicationContext();
    CharSequence text = "Aktivita: onStart";
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(context, text, duration);
    toast.show();
  }

  @Override
  protected void onResume() {
    super.onResume();

    Context context = getApplicationContext();
    CharSequence text = "Aktivita: onResume - jdeme na popředí";
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(context, text, duration);
    toast.show();
  }

  @Override
  protected void onPause() {
    super.onPause();
    Context context = getApplicationContext();
    CharSequence text = "Aktivita: onPause";
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(context, text, duration);
    toast.show();
  }

  @Override
  protected void onStop() {
    super.onStop();
    Context context = getApplicationContext();
    CharSequence text = "Aktivita: onStop";
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(context, text, duration);
    toast.show();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Context context = getApplicationContext();
    CharSequence text = "Aktivita: onDestroy";
    int duration = Toast.LENGTH_LONG;
    Toast toast = Toast.makeText(context, text, duration);
    toast.show();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
