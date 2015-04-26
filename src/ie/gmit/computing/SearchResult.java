package ie.gmit.computing;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

/**
 * @author Brian Varley
 *
 */
public class SearchResult extends Activity implements OnClickListener,LocationListener, MyMessageDialog.DialogCallback  {
	
	private static int RESULT_LOAD_IMAGE = 1;
	private static final String TAG = "File Output";
	
	
	private LocationManager locationManager;
	private String provider;
	
	//location variables, declared gloabally as need
	//to access when outputting to csv.
	private int lat;
	private int lng;
	
	//variables used to store sample and generic info
	private String shipName = "null";
	private String analystName = "null";
	private String analystEmail = "null";
	private String sampleVolume = "null";
	private String sampleColour = "null";
	private String sampleMaterial = "null";
	private String latitudeValue = "null";
	private String longitudeValue = "null";
	private Date date;
	
	//record counter
	private int recordCntr = 0;
	
	private ImageView imageView; 
	
	//Image file path
	private String filePath;
	
	//debri match field
	String message;
	
	//Create instance of alert dialog, to allow access in this class.
	MyMessageDialog dialog =new MyMessageDialog(); 
	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		Button mClickSelectBtn = (Button)findViewById(R.id.exportBtn);
		mClickSelectBtn.setOnClickListener(this);
		Button mClickEmailBtn = (Button)findViewById(R.id.emailBtn);
		mClickEmailBtn.setOnClickListener(this);
		
		imageView = (ImageView) findViewById(R.id.capturedDebriImageView);
		
		
	    // Get the location manager
	    locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
	    // Define the criteria for selecting the locatioin provider.
	    Criteria criteria = new Criteria();
	    provider = locationManager.getBestProvider(criteria, false);
	    Location location = locationManager.getLastKnownLocation(provider);
 
	    // Initialize the location fields
	    if (location != null) {
	      System.out.println("Provider " + provider + " has been selected.");
	      onLocationChanged(location);
	    } else {
	       //no location available
            Toast.makeText(this, "Location unavailable!", Toast.LENGTH_SHORT).show();

	      }
	    
	    //Decided to pass the string value of matched image, instead of passing the actual image
	    //between activities to save resources.
	    Bundle bundle = getIntent().getExtras();
	    message = bundle.getString("message");
	    
	    TextView capturedDebriTextView = (TextView) findViewById(R.id.capturedDebriTextView);    
	    capturedDebriTextView.setText(message);
	    
	    int id = getResources().getIdentifier(message, "drawable", this.getPackageName());
	    if(id != 0){
	       imageView.setImageResource(id);
	    }else{
	       imageView.setImageResource(R.drawable.aluminium);
	    }
	    
	  
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
	    // TODO Auto-generated method stub
	    String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
	    String fileName = "AnalysisData.csv";
	    filePath = baseDir + File.separator + fileName;
	    File f = new File(filePath);
	    switch (v.getId()) {
	        case  R.id.exportBtn: {
	            Toast.makeText(this, "export clicked", Toast.LENGTH_SHORT).show();
	            //write sample data to csv file using open csv lib.
	            date = new Date();



	            CSVWriter writer = null;

	            // File exist
	            if(f.exists() && !f.isDirectory()){
	                FileWriter mFileWriter = null;
	                try {
	                    mFileWriter = new FileWriter(filePath , true);
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	                writer = new CSVWriter(mFileWriter);
	            }
	            else {
	                try {
	                    writer = new CSVWriter(new FileWriter(filePath));
	                } catch (IOException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	            }
	            String data [] = new String[] {"Record Number","Ship Name","Scientist Name","Scientist Email","Sample Volume","Sample Colour","Sample Material","Latitude","Longitude","Date","\r\n"};
	            writer.writeNext(data);

	        
	        //retrieve record cntr from prefs
	        SharedPreferences settings = getSharedPreferences("RECORD_PREF", 0);
	        recordCntr = settings.getInt("RECORD_COUNT", 0); //0 is the default value
	        

	            //increment record count
	            recordCntr++;

	        
	        //save record cntr from prefs
	        settings = getSharedPreferences("RECORD_PREF", 0);
	        SharedPreferences.Editor editor = settings.edit();
	        editor.putInt("RECORD_COUNT",recordCntr);
	        editor.commit();
	        
	            data = new String[]{Integer.toString(recordCntr),shipName,analystName,analystEmail,sampleVolume,
	                    sampleColour,sampleMaterial,latitudeValue.toString(),longitudeValue.toString(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date),"\r\n"};

	            writer.writeNext(data);
	            try {
	                writer.close();
	                Toast.makeText(this, "Data exported succesfully!", Toast.LENGTH_SHORT).show();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	                Toast.makeText(this, "Error exporting data!", Toast.LENGTH_SHORT).show();
	            }
	            break;
	        }

	        case R.id.emailBtn: {

	        	//Email intent used to allow email of csv data
	        	//Intent may not fire depending on device's Api, if so 
	        	//change action to, `ACTION_SENDTO`
	            Toast.makeText(this, "email clicked", Toast.LENGTH_SHORT).show();
	            if (f.exists() && !f.isDirectory()) {
	                Uri uri = Uri.fromFile(f);
	                Intent emailIntent = new Intent(Intent.ACTION_SEND, Uri.fromParts(
	                        "mailto","abc@gmail.com", null));
	                emailIntent.setType("text/plain");
	                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "EXTRA_SUBJECT");
	                emailIntent.putExtra(Intent.EXTRA_STREAM, uri);
	                startActivity(Intent.createChooser(emailIntent, "Email data..."));
	            }


	            break;
	        }
	    }

	}
	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

	
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_settings:
        	
        	//Open info alert dialog
        	dialog.displayMessage(SearchResult.this, "Sample Info", "Required");
        	
     
            return true;
      
        default:
          return super.onOptionsItemSelected(item);
        } 
    } 
	
	
	 /* Request updates at startup */
	  @Override
	  protected void onResume() {
	    super.onResume();
	    locationManager.requestLocationUpdates(provider, 400, 1, this);
	  }

	  /* Remove the locationlistener updates when Activity is paused */
	  @Override
	  protected void onPause() {
	    super.onPause();
	    locationManager.removeUpdates(this);
	  }




	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		lat = (int) (location.getLatitude());
	    lng = (int) (location.getLongitude());
	   
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "Sorry Location provider is disabled", Toast.LENGTH_SHORT).show();
	}

	/* (non-Javadoc)
	 * @see ie.gmit.computing.MyMessageDialog.DialogCallback#onValuesSet()
	 */
	@Override
	public void onValuesSet() {
		// TODO Auto-generated method stub
		 // store / use the values from MyMessageDialog
    	shipName = dialog.getShipString();
    	analystName = dialog.getScientistNameString();
    	analystEmail = dialog.getScientistEmailString();
    	sampleVolume = dialog.getVolumeString();
    	sampleColour = dialog.getColourString();
    	longitudeValue = String.valueOf(lng);
    	latitudeValue = String.valueOf(lat);
        sampleMaterial = message;
		
	}
	
	
	

}
