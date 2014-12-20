package ie.gmit.computing;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.item1:
          Toast.makeText(this, "Option1", Toast.LENGTH_SHORT).show();
          return true;
        case R.id.item2:
          Toast.makeText(this, "Option2", Toast.LENGTH_SHORT).show();
          return true;
        case R.id.item3:
          Toast.makeText(this, "Option3", Toast.LENGTH_SHORT).show();
          return true;           
        case R.id.item4:
          Toast.makeText(this, "Option4", Toast.LENGTH_SHORT).show();
          return true;
        case R.id.item5:
          Toast.makeText(this, "Option5", Toast.LENGTH_SHORT).show();
          return true;
        case R.id.item6:
          Toast.makeText(this, "Option6", Toast.LENGTH_SHORT).show();
          return true;           
        default:
          return super.onOptionsItemSelected(item);
        } 
    } 

	
}
