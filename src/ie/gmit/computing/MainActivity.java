package ie.gmit.computing;

//import com.example.cameratest.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Intent;
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


public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	
	private static final String TAG = "CallCamera";
	private static final int CAPTURE_IMAGE_ACTIVITY_REQ = 0;
	
	Uri fileUri = null;
	ImageView photoImage = null;
	
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
	            // search tree for matching debri
	        	 Toast.makeText(this, "search clicked", Toast.LENGTH_SHORT).show();
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
	
	
	
	
	private File getOutputPhotoFile() {
		  File directory = new File(Environment.getExternalStoragePublicDirectory(
		                Environment.DIRECTORY_PICTURES), getPackageName());
		  if (!directory.exists()) {
		    if (!directory.mkdirs()) {
		      Log.e(TAG, "Failed to create storage directory.");
		      return null;
		    }
		  }
		  String timeStamp = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.ENGLISH).format(new Date());
		  return new File(directory.getPath() + File.separator + "IMG_"  
		                    + timeStamp + ".jpg");
		}
	  
	  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		  if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQ) {
		    if (resultCode == RESULT_OK) {
		      Uri photoUri = null;
		      if (data == null) {
		        // A known bug here! The image should have saved in fileUri
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
		     photoImage.setImageDrawable(drawable);
		  }       
		}
  

	
}
