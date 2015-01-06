package ie.gmit.computing;
 
//import com.example.cameratest.R;
 
import ie.gmit.computing.model.Node;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
 
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Files;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
 
 
public class MainActivity extends ActionBarActivity implements OnClickListener{
	
	
	private static final String TAG = "CallCamera";
	private static final int CAPTURE_IMAGE_ACTIVITY_REQ = 0;
	
	
	private Uri fileUri = null;
	private ImageView photoImage = null;
	
	//counter var to add index to camera images
	//can be used to cross reference csv record with image number.
	private int i = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		photoImage = (ImageView) findViewById(R.id.photo_image);
		photoImage.setImageDrawable(null);
	
		
		Button mClickButton1 = (Button)findViewById(R.id.cameraBtn);
		mClickButton1.setOnClickListener(this);
		Button mClickButton2 = (Button)findViewById(R.id.searchBtn);
		mClickButton2.setOnClickListener(this);
		Button mClickButton3 = (Button)findViewById(R.id.resBtn);
		mClickButton3.setOnClickListener(this);
		

	    
	}
	
	
    
	
	
	
	//handle button clicks
	public void onClick(View v) {
	    switch (v.getId()) {
	        case  R.id.cameraBtn: {
	            // start camera intent
	        	 Toast.makeText(this, "camera clicked", Toast.LENGTH_SHORT).show();
	        	 Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	 	         try {
					File file = getOutputPhotoFile();
 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	         try {
					fileUri = Uri.fromFile(getOutputPhotoFile());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 	         i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
	 	         startActivityForResult(i, CAPTURE_IMAGE_ACTIVITY_REQ );
	            break;
	        }
	        case  R.id.resBtn: {
	            // start camera intent
	        	 Toast.makeText(this, "result clicked", Toast.LENGTH_SHORT).show();
	        	 Intent myIntentO = new Intent(MainActivity.this, SearchResult.class);
	             MainActivity.this.startActivity(myIntentO);   
	            break;
	        }
 
	        case R.id.searchBtn: {
	            // search tree for matching debri
	        	 Toast.makeText(this, "search clicked", Toast.LENGTH_SHORT).show();
	        	 Intent myIntent = new Intent(MainActivity.this, SearchTree.class);
	             MainActivity.this.startActivity(myIntent);   
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
	
	
	
	
	
	private File getOutputPhotoFile() throws IOException {
		  File directory = new File(Environment.getExternalStoragePublicDirectory(
		                Environment.DIRECTORY_PICTURES), getPackageName());
		  if (!directory.exists()) {
		    if (!directory.mkdirs()) {
		      Log.e(TAG, "Failed to create storage directory.");
		      return null;
		    }
		  }
		  
		  
		  String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
		 
		  
		  
		  File[] files = directory.listFiles();
		  i++;
		  File origFile =  new File(directory.getPath(), "IMG_" + "# " +i + "_" + timeStamp + ".jpg");
		  
		  
		  
		  if(files.length!=0) {
		      File newestFile = files[files.length-1];
		      origFile =  new File(directory.getPath() + File.separator + newestFile.getName());
		  }
		   
	
	  return origFile; 
		  
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
		      try {
				showPhoto(photoUri);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    } else if (resultCode == RESULT_CANCELED) {
		      Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show();
		    } else {
		      Toast.makeText(this, "Callout for image capture failed!", 
		                     Toast.LENGTH_LONG).show();
		    }
		  }
		}
	  
	  
	 
	
	  
	  private void showPhoto(Uri photoUri) throws IOException {
		  String filePath = photoUri.getEncodedPath();
		  File imageFile = new File(filePath);
		  
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