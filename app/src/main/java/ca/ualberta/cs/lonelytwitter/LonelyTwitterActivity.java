package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * The main activity for a small, personal Twitter app to capture, <br>notes and comments.
 * <p>It saves the <pre>input tweets</pre> in the json files.</p>
 * A sample code is as:<br>
 *     <code>
 *         for(int i=0; i<10; i++)
 *         		for (int j=0; j<i; j++)
 *         			doSomething();
 *     </code>
 * The list of im[portant activities in this class are as follows:
 * <ul>
 *     <li>item 1</li>
 *     <li>item 2</li>
 *     <li>item 3</li>
 *     <li>item 4</li>
 * </ul>
 * @since 1.2.1
 * @see LonelyTwitterActivity for more information
 * @author Ali
 * @version 2.3
 * @deprecated
 */
public class LonelyTwitterActivity extends Activity {
	/**
	 * This arraylist is for keeping the tweets and their date of posting. <br>
	 * This is blah blah.
	 * @see #loadFromFile()
	 */
	public ArrayList<String> listOfItems;
	static final String GENERAL_FILE_NAME = "fielName.json";

	private int calculateTweetSize(){
		//
		return -1;
	}

	private String removeStopWords(String text){
		//
		return "";
	}

	/**
	 * This starts the next activity which is blah blah.
	 * @param intent This is the intent to be run immediately after hitting "start" button.
	 */
	private void startSecondActivity(Intent intent){
		//
	}

	/**
	 * This method does something!
	 * @param s is some parameter!
	 * @return The value that is used for some job!
	 * @throws ...
	 * @deprecated
	 */
	public String someMethod(String s){
		return "";
	}

	public boolean evaluateOtherActivity(Intent intent){
		int count = 0;
		String s = "";
		String expression1 = "", expression2 = "", expression3 = "", expression4 = "";

		//This is a call to intent to do ...
		Intent intent1 = new Intent();
		startSecondActivity(intent1);
		String S = someMethod(expression1 + expression2 + expression3 +
							expression4);
		someMethod(expression1 + expression2 + expression3 +
				expression4);

		for (int i=0; i<10; i++)
		try{
			int a = 1;
			int b = 2;
			if (a < 2) {
				someMethod("first choice");
			} else
				someMethod("Second choice");
			while (1 < 2){
				int j = 0;
				//
			}
		}
		catch (Exception e){}
		return true;
	}


	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bodyText = (EditText) findViewById(R.id.body);
		Button saveButton = (Button) findViewById(R.id.save);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);

		saveButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				setResult(RESULT_OK);
				String text = bodyText.getText().toString();
				saveInFile(text, new Date(System.currentTimeMillis()));
				finish();

			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		String[] tweets = loadFromFile();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private String[] loadFromFile() {
		ArrayList<String> tweets = new ArrayList<String>();
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));
			String line = in.readLine();
			while (line != null) {
				tweets.add(line);
				line = in.readLine();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tweets.toArray(new String[tweets.size()]);
	}
	
	private void saveInFile(String text, Date date) {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					Context.MODE_APPEND);
			fos.write(new String(date.toString() + " | " + text)
					.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}