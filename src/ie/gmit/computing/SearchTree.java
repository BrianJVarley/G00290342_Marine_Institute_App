package ie.gmit.computing;

import ie.gmit.computing.model.Node;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/**
 * @author Brian Varley
 *
 */

public class SearchTree extends ActionBarActivity implements OnClickListener{

	//Search Tree not successfully implemented to button views.
    //switch statement used as a work around, but not ideal as
	//discussed in the README file.
	 private static final Node root = new Node("root", null);
	 {
		Node root = new Node("root", null);
		Node solid = new Node("solid", root);
		Node flexible = new Node("flexible", root);
		Node hard = new Node("hard", solid);
		Node squashable = new Node("Can be Squashed?", flexible);
		Node styrene = new Node("styrene", flexible);
		Node fibre = new Node("fibre",flexible);
		Node resin_pellet = new Node("resin_pellet", flexible);
		Node film = new Node("film", flexible);
		Node wood = new Node("wood", hard);
		Node ceramic = new Node("ceramic", hard);
		Node aluminium = new Node("aluminium", hard);
		Node cardboard = new Node("cardboard", hard);

	        //initialise node children
		solid.addChild(hard);
		hard.addChild(wood);
		hard.addChild(ceramic);
		hard.addChild(aluminium);
		flexible.addChild(squashable);
		squashable.addChild(cardboard);
		squashable.addChild(fibre);
		squashable.addChild(resin_pellet);
		squashable.addChild(fibre);
		
	   }

	  //button fields
	  private Button hardBtn;   
	  private Button flexibleBtn;  
	  private Button squashableBtn;   
	  private Button filmBtn;     
	  private Button ceramicBtn;      
	  private Button aluminiumBtn;       
	  private Button woodBtn;      
	  private Button styreneBtn;   
	  private Button copperBtn;
	  private Button acrylicBtn;   
	
	  
	
	/* (non-Javadoc)
	 * @see android.support.v7.app.ActionBarActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_tree);
		
		hardBtn = (Button) findViewById(R.id.hardBtn);   
		flexibleBtn = (Button) findViewById(R.id.flexibleBtn);  
		squashableBtn = (Button) findViewById(R.id.squashableBtn);   
		filmBtn = (Button) findViewById(R.id.filmBtn);     
	    ceramicBtn = (Button) findViewById(R.id.ceramicBtn);      
		aluminiumBtn = (Button) findViewById(R.id.aluminiumBtn);       
		woodBtn = (Button) findViewById(R.id.woodBtn);      
		styreneBtn = (Button) findViewById(R.id.styreneBtn); 
		acrylicBtn = (Button) findViewById(R.id.acrylicBtn); 
		copperBtn = (Button) findViewById(R.id.copperBtn); 
		
		
		//set up buttons click listener
	    hardBtn.setOnClickListener(this);
	    flexibleBtn.setOnClickListener(this);  
	    squashableBtn.setOnClickListener(this);  
	    filmBtn.setOnClickListener(this);
	    ceramicBtn.setOnClickListener(this); 
	    aluminiumBtn.setOnClickListener(this);
	    woodBtn.setOnClickListener(this); 
	    styreneBtn.setOnClickListener(this);
	    acrylicBtn.setOnClickListener(this);
	    copperBtn.setOnClickListener(this);
	    
		
	}
	
	
	
	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		 switch (v.getId()) {
	        case  R.id.flexibleBtn: {
	            
	        	Toast.makeText(this, "flexible", Toast.LENGTH_SHORT).show();
	        	flexibleBtn.setVisibility(View.INVISIBLE);
	        	hardBtn.setVisibility(View.INVISIBLE);
	        	squashableBtn.setVisibility(View.INVISIBLE);
	        	ceramicBtn.setVisibility(View.INVISIBLE);
	        	woodBtn.setVisibility(View.INVISIBLE);
	        	aluminiumBtn.setVisibility(View.INVISIBLE);
	        	styreneBtn.setVisibility(View.VISIBLE);
	        	filmBtn.setVisibility(View.VISIBLE);
	        	acrylicBtn.setVisibility(View.VISIBLE);
	            break;
	        }

	        case R.id.hardBtn: {
	            
	        	Toast.makeText(this, "hard", Toast.LENGTH_SHORT).show();
	        	flexibleBtn.setVisibility(View.INVISIBLE);
	        	hardBtn.setVisibility(View.INVISIBLE);
	        	squashableBtn.setVisibility(View.INVISIBLE);
	        	ceramicBtn.setVisibility(View.VISIBLE);
	        	woodBtn.setVisibility(View.VISIBLE);
	        	aluminiumBtn.setVisibility(View.VISIBLE);
	        	copperBtn.setVisibility(View.VISIBLE);
	            break;
	        }
	        case R.id.squashableBtn: {
	            
	        	Toast.makeText(this, "squashable", Toast.LENGTH_SHORT).show();
	        	Toast.makeText(this, "flexible", Toast.LENGTH_SHORT).show();
	        	flexibleBtn.setVisibility(View.INVISIBLE);
	        	hardBtn.setVisibility(View.INVISIBLE);
	        	squashableBtn.setVisibility(View.INVISIBLE);
	        	ceramicBtn.setVisibility(View.INVISIBLE);
	        	woodBtn.setVisibility(View.INVISIBLE);
	        	aluminiumBtn.setVisibility(View.INVISIBLE);
	        	styreneBtn.setVisibility(View.VISIBLE);
	        	filmBtn.setVisibility(View.VISIBLE);
	            break;
	        }
	        case R.id.styreneBtn: {
	            
	        	Toast.makeText(this, "styrene", Toast.LENGTH_SHORT).show();
	        	filmBtn.setVisibility(View.INVISIBLE);
	        	
	        	String message = "styrene";
	        	Intent intent = new Intent(SearchTree.this, SearchResult.class);
	        	intent.putExtra("message",message );
	        	startActivity(intent);
	        	
	            break;
	        }
	        case R.id.aluminiumBtn: {
	            
	        	Toast.makeText(this, "aluminium", Toast.LENGTH_SHORT).show();
	        	woodBtn.setVisibility(View.INVISIBLE);
	        	ceramicBtn.setVisibility(View.INVISIBLE);
	        	
	        	String message = "aluminium";
	        	Intent intent = new Intent(SearchTree.this, SearchResult.class);
	        	intent.putExtra("message",message);
	        	startActivity(intent);
	        	
	            break;
	        }
	        case R.id.filmBtn: {
	            
	        	Toast.makeText(this, "film", Toast.LENGTH_SHORT).show();
	        	styreneBtn.setVisibility(View.INVISIBLE);
	        	
	        	String message = "film";
	        	Intent intent = new Intent(SearchTree.this, SearchResult.class);
	        	intent.putExtra("message",message );
	        	startActivity(intent);
	        	
	            break;
	        }
	        case R.id.ceramicBtn: {
	            
	        	Toast.makeText(this, "ceramic", Toast.LENGTH_SHORT).show();
	        	woodBtn.setVisibility(View.INVISIBLE);
	        	aluminiumBtn.setVisibility(View.INVISIBLE);
	        	
	        	String message = "ceramic";
	        	Intent intent = new Intent(SearchTree.this, SearchResult.class);
	        	intent.putExtra("message",message );
	        	startActivity(intent);
	        	
	            break;
	        }
	        case R.id.woodBtn: {
	            
	        	Toast.makeText(this, "wood", Toast.LENGTH_SHORT).show();
	        	ceramicBtn.setVisibility(View.INVISIBLE);
	        	aluminiumBtn.setVisibility(View.INVISIBLE);
	        	
	        	String message = "wood";
	        	Intent intent = new Intent(SearchTree.this, SearchResult.class);
	        	intent.putExtra("message",message );
	        	startActivity(intent);
	        	
	            break;
	        }
	        case R.id.copperBtn: {
	            
	        	Toast.makeText(this, "copper", Toast.LENGTH_SHORT).show();
	        	ceramicBtn.setVisibility(View.INVISIBLE);
	        	aluminiumBtn.setVisibility(View.INVISIBLE);
	        	woodBtn.setVisibility(View.INVISIBLE);
	        	
	        	String message = "copper";
	        	Intent intent = new Intent(SearchTree.this, SearchResult.class);
	        	intent.putExtra("message",message );
	        	startActivity(intent);
	        	
	            break;
	        }
	        case R.id.acrylicBtn: {
	            
	        	Toast.makeText(this, "acrylic", Toast.LENGTH_SHORT).show();
	        	filmBtn.setVisibility(View.INVISIBLE);
	        	styreneBtn.setVisibility(View.INVISIBLE);
	        	
	        	String message = "acrylic";
	        	Intent intent = new Intent(SearchTree.this, SearchResult.class);
	        	intent.putExtra("message",message );
	        	startActivity(intent);
	        	
	            break;
	        }
		
	}

	}
}
