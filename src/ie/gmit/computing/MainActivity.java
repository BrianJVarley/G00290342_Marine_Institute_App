package ie.gmit.computing;
 
//import com.example.cameratest.R;
 
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
 
 
@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	
	private static final String TAG = "CallCamera";
	private static final int CAPTURE_IMAGE_ACTIVITY_REQ = 0;
	
	
	private Uri fileUri = null;
	private ImageView photoImage = null;
	
	//counter var to add index to camera images
	//can be used to cross reference csv record number with image number.
	private int imageCntr = 0;
	
	//delay counter for button clickable
	private int buttonDelay;
	
	//declare button fields 
	private Button camClickButton;
	private Button searchClickButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		photoImage = (ImageView) findViewById(R.id.photo_image);
		photoImage.setImageDrawable(null);
	
		//find button views
		camClickButton = (Button)findViewById(R.id.cameraBtn);
		camClickButton.setOnClickListener(this);
		searchClickButton = (Button)findViewById(R.id.searchBtn);
		searchClickButton.setOnClickListener(this);
		
	    
	}
	
	
    
	
	
	
	//handle button clicks
	public void onClick(View v) {
	    switch (v.getId()) {
	        case  R.id.cameraBtn: {
	            // start camera intent
	        	 Toast.makeText(this, "camera clicked", Toast.LENGTH_SHORT).show();
	        	 Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	 	         File file = getOutputPhotoFile();
	 	         fileUri = Uri.fromFile(getOutputPhotoFile());
	 	         i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	 	         startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQ );
	            break;
	        }
	        case R.id.searchBtn: {
	        	
	        	boolean hasDrawable = (photoImage.getDrawable() != null);
	        	if(hasDrawable) {
	        	    // imageView has image in it, allow debri search
	        		// search tree for matching debri
		        	 Toast.makeText(this, "search clicked", Toast.LENGTH_SHORT).show();
		        	 Intent myIntent = new Intent(MainActivity.this, SearchTree.class);
		             MainActivity.this.startActivity(myIntent);
	        		
	        	}
	        	else {
	        	    // no image assigned to image view, disable button click for 'n' seconds
	        		Toast.makeText(this, "please capture debri image first!", Toast.LENGTH_SHORT).show();
	        		searchClickButton.setAlpha(.5f);
	        		searchClickButton.setClickable(false);
	        		try {
						Thread.sleep(1000, 1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		Toast.makeText(this, "Please capture debri image first!", Toast.LENGTH_SHORT).show();
	        		searchClickButton.setClickable(true);
	        		searchClickButton.setAlpha(255);
	        	}
	               
	            break;
	        }
	    }
	}
	
	
	
	public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
	
	
	public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.add:
          Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
          return true;
        case R.id.delete:
          Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
          return true;
        
        default:
          return super.onOptionsItemSelected(item);
        } 
    } 
	
	
	
	
	
	private File getOutputPhotoFile() {
		  File directory = new File(Environment.getExternalStoragePublicDirectory(
		                Environment.DIRECTORY_PICTURES), getPackageName());
		  if (!directory.exists()) {
		    if (!directory.mkdirs()) {
		      Log.e(TAG, "Failed to create storage directory.");
		      return null;
		    }
		  }
		  
		  /*
		  //retrieve image cntr from prefs
          SharedPreferences settings = getSharedPreferences("IMAGE_RECORD_PREF", 0);
          imageCntr = settings.getInt("IMAGE_RECORD_COUNT", 0); //0 is the default value
	       */ 
		  
		  
		  String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
		  File result = new File(directory.getPath() + File.separator + "IMG_"  
		                    + timeStamp + ".jpg");
		  
		   imageCntr ++;
		  
		   /*
		    //save image cntr from prefs
	        settings = getSharedPreferences("IMAGE_RECORD_PREF", 0);
	        SharedPreferences.Editor editor = settings.edit();
	        editor.putInt("IMAGE_RECORD_COUNT",imageCntr);
	        editor.commit();
	        */
	        return result;
	        
	}
	
	
	  
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQ) {
		    if (resultCode == RESULT_OK) {
		      Uri photoUri = null;
		      if (data == null) {
		        Toast.makeText(this, "Image saved successfully", 
		                       Toast.LENGTH_LONG).show();
		        photoUri = fileUri;
		      } else {
		        photoUri = data.getData();
		        Toast.makeText(this, "Image saved successfully in: " + data.getData(), 
		                       Toast.LENGTH_LONG).show();
		      }
		      showPhoto(photoUri);
		    } else if (resultCode == RESULT_CANCELED) {
		      Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
		    } else {
		      Toast.makeText(this, "Callout for image capture failed!", 
		                     Toast.LENGTH_LONG).show();
		    }
		  }
		}
	  
	  
	 
	
	  
	  private void showPhoto(Uri photoUri) {
		  String filePath = photoUri.getEncodedPath();
		  File imageFile = new File(filePath);
		  //File imageFile = new File(photoUri.getPath());
		  if (imageFile.exists()){
			 Drawable oldDrawable = photoImage.getDrawable(); 
			 if (oldDrawable != null) { 
				 ((BitmapDrawable)oldDrawable).getBitmap().recycle();
			}
		     Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
		     BitmapDrawable drawable = new BitmapDrawable(this.getResources(), bitmap);
		     photoImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
		     photoImage.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 130, 110, false));
		  }       
		}

	  
	
}