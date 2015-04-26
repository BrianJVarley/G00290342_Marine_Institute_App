package ie.gmit.computing;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Brian Varley
 * Custom alert dialog to accomodate multiple edit texts
 * Adapted from this Stackoverflow solution: 
 * http://stackoverflow.com/questions/3426917/how-to-add-two-edit-text-fields-in-an-alert-dialog
 */
public class MyMessageDialog  {
	
	 public interface DialogCallback {
	        public void onValuesSet();
	    }

    private Context context;
    private EditText shipText, scientistNameText , scientistEmailText , volumeText , colourText ;
    
    private String shipString, scientistNameString , scientistEmailString , volumeString , colourString ;
    
    

    public AlertDialog displayMessage(Context context, String title, String message){
    	this.context = context;
    	
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(message);
        
        LayoutInflater inflater = LayoutInflater.from(context);
        final View v = inflater.inflate(R.layout.custom_view, null);
        builder.setView(v);
        
        shipText = (EditText)v.findViewById(R.id.shipNameEditText);
        scientistNameText = (EditText)v.findViewById(R.id.scientistEditText);
        scientistEmailText = (EditText)v.findViewById(R.id.emailEditText);
        volumeText = (EditText)v.findViewById(R.id.volumeEditText);
        colourText  = (EditText)v.findViewById(R.id.colourEditText);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog= builder.create();
        dialog.show();
        Button tb = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
        tb.setOnClickListener(new CustomListener(dialog));

        return dialog;
    }


    //getter/setters to allow access to string values
    //in SearchResult class
    public EditText getShipText() {
		return shipText;
	}



	public void setShipText(EditText shipText) {
		this.shipText = shipText;
	}



	public EditText getScientistNameText() {
		return scientistNameText;
	}



	public void setScientistNameText(EditText scientistNameText) {
		this.scientistNameText = scientistNameText;
	}



	public EditText getScientistEmailText() {
		return scientistEmailText;
	}
	
	
	public void setScientistEmailText(EditText scientistEmailText) {
		this.scientistEmailText = scientistEmailText;
	}



	public String getShipString() {
		return shipString;
	}


	public void setShipString(String shipString) {
		this.shipString = shipString;
	}


	public String getScientistNameString() {
		return scientistNameString;
	}


	public void setScientistNameString(String scientistNameString) {
		this.scientistNameString = scientistNameString;
	}


	public String getScientistEmailString() {
		return scientistEmailString;
	}


	public void setScientistEmailString(String scientistEmailString) {
		this.scientistEmailString = scientistEmailString;
	}


	public String getVolumeString() {
		return volumeString;
	}


	public void setVolumeString(String volumeString) {
		this.volumeString = volumeString;
	}


	public String getColourString() {
		return colourString;
	}


	public void setColourString(String colourString) {
		this.colourString = colourString;
	}


	public EditText getVolumeText() {
		return volumeText;
	}



	public void setVolumeText(EditText volumeText) {
		this.volumeText = volumeText;
	}



	public EditText getColourText() {
		return colourText;
	}



	public void setColourText(EditText colourText) {
		this.colourText = colourText;
	}



	@SuppressLint("NewApi")
	class CustomListener implements View.OnClickListener {
        private final Dialog dialog;
        public CustomListener(Dialog dialog) {
            this.dialog = dialog;
        }
        @SuppressLint("NewApi")
		@Override
        public void onClick(View v) {

            //error handling for invalid/null input to edit texts, Android's `setError` faculty utilized 
        	//to notify of invalid input.
            if(shipText.getText().toString().isEmpty() && !shipText.getText().toString().equals(null)){
            	shipText.setError("The Field is required");
            
            }else if(scientistNameText.getText().toString().isEmpty() && !scientistNameText.getText().toString().equals(null)){
            	scientistNameText.setError("The Field is required");
            }else if(scientistEmailText.getText().toString().isEmpty() && !scientistEmailText.getText().toString().equals(null)){
            	scientistEmailText.setError("The Field is required");
            }else if(volumeText.getText().toString().isEmpty() && !volumeText.getText().toString().equals(null)){
            	volumeText.setError("The Field is required");
            }else if(colourText.getText().toString().isEmpty() && !colourText.getText().toString().equals(null)){
            	colourText.setError("The Field is required");
            }else{
            	shipText.setError(null);
                scientistNameText.setError(null);
                scientistEmailText.setError(null);
                volumeText.setError(null);
                colourText.setError(null);

                //Assign edit text values to string variables
                shipString = shipText.getText().toString();
                scientistNameString = scientistNameText.getText().toString();
                scientistEmailString = scientistEmailText.getText().toString();
                volumeString = volumeText.getText().toString();
                colourString = colourText.getText().toString();
               
                
                Toast.makeText(dialog.getContext(), "Details added", Toast.LENGTH_SHORT).show();
                
                ((DialogCallback) context).onValuesSet();
                
                //close dialog after values have been set.
                dialog.dismiss();
            }
        }
    }
}

