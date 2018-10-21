package hinfo.com.infohealth;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    Intent itLogin, itMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        itLogin = new Intent(this, LoginActivity.class);
        itMaps = new Intent(this,HealthMap.class);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.layout.menu_home, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Showing maps of Secure Health",Toast.LENGTH_LONG).show();
                startActivity(itMaps);
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Weather Impact",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Acess",Toast.LENGTH_SHORT).show();
                startActivity(itLogin);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
