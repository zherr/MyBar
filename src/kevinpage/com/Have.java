package kevinpage.com;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Have extends Activity {
    
	private void fillData(ListView lv, String[] array) {
		ArrayAdapter<String> help=
			new ArrayAdapter<String>(getApplicationContext(), R.layout.check, array);
		lv.setAdapter(help);
	}

	static ListView lvH;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
	  	super.onCreate(savedInstanceState);
        setContentView(R.layout.ingredients);
        data.al2 = (String []) data.ownedIngredients.toArray (new String [data.ownedIngredients.size ()]);
		lvH = (ListView) findViewById(R.id.ingredient_list);
		fillData(lvH, data.al2);
	    lvH.setTextFilterEnabled(true);
		lvH.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
						Toast.makeText(getApplicationContext(), "Removed "+(((TextView) view).getText())+" from Inventory",
					          Toast.LENGTH_SHORT).show();
						data.ownedIngredients.remove((((TextView) view).getText()));
						data.missingIngs.add((((TextView) view).getText()).toString());
				        data.al = (String []) data.missingIngs.toArray (new String [data.missingIngs.size ()]);
				        data.al2 = (String []) data.ownedIngredients.toArray (new String [data.ownedIngredients.size ()]);
				        fillData(lvH,data.al2);
				        fillData(DontHave.lvD,data.al);
			}
		});
	    
	    final Button mainButton = (Button) findViewById(R.id.main_menu);
        mainButton.setOnClickListener(new View.OnClickListener() {
      	public void onClick(View v) {
      		data.canMakeDrinks.clear();
            for(Drink drink : data.allDrinks) {
            	if(drink.canMake()) {
            		if(!data.canMakeDrinks.contains(drink))
            			data.canMakeDrinks.add(drink);
            	}
            }
            finish();
      	}
        });
        
    }
}