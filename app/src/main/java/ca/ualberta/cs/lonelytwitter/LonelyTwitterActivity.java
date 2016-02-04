package ca.ualberta.cs.lonelytwitter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * The main activity for a small Twitter app to capture, <br>notes and comments.
 * <p>It saves the input tweets in the form of json files.</p><br>
 *     A sample code is as:<br>
 *         <code>
 *             for (int i = 0; i < 10; i++){
 *                 for (j = 0; j < i; j++){
 *                     doSomething();
 *                 }
 *             }
 *         </code> <br>
 * The list of important activities in this calss are as follows:<br>
 *     <ul>
 *         <li>item 1</li>
 *         <li>item 2</li>
 *         <li>item 3</li>
 *         <li>item 4</li>
 *     </ul>
 * @see NormalTweet
 * @see java.awt
 * @author Ali
 * @version 2.1
 * @deprecated
 */
public class LonelyTwitterActivity extends Activity {

	/**
	 * @see Tweet
	 */
	static int MAXIMUM_TWEET_SIZE = 100;
	private static final String FILENAME = "file.sav";
	private EditText bodyText;
	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();
	private ArrayAdapter<Tweet> adapter;

	private int calculateTweetSize(){
		//
		return -1;
	}

	public String removeStopWords(String text){
		//Do something ...
		return "";
	}

	/**
	 * Applies some functions on the input values.
	 * @param text1 The text for the directory name.
	 * @param text2 The file name.
	 * @param text3 The extension.
	 * @param text4
	 * @return concatenation of ...
	 * @exception IllegalAccessError
	 * This happens if ...
	 * @exception java.util.InvalidPropertiesFormatException
	 * This happens if ...
	 * @deprecated
	 */
	public String doSomething(String text1, String text2, String text3, String text4){
		//Do something ...
		return "";
	}

	private void startSecondActivity(Intent intent){
		//Run the second activity
	}

	protected boolean evaluateOtherActivity(Intent intent){
		//Do something ...
		/*
		ldkfjsld
		sdfsdf
		sfsd
		 */
		String expression1 = "",
				expression2 = "",
				expression3 = "",
				expression4 = "";
		int count = 10;
		String expression = doSomething(expression1, expression2,
				doSomething(expression3, expression4, expression3,
						expression4), expression1);

		for (int i = 0; i < count; i++){
			try{
				int j = 1;
				int k = 2;
				int count2 = 0;
				if (i < j) {
					doSomething("", "", "", "");
				}
				else if (true) {
					doSomething("a", "", "", "");
				}
			}
			catch(Exception e){}
		}





		return true;
	}



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
				Tweet newestTweet = new NormalTweet(text);

				tweets.add(newestTweet);
				adapter.notifyDataSetChanged();
				saveInFile();
//				finish();

			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		loadFromFile();
		adapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(adapter);
	}

	private void loadFromFile() {
		try {
			FileInputStream fis = openFileInput(FILENAME);
			BufferedReader in = new BufferedReader(new InputStreamReader(fis));

			Gson gson = new Gson();
			// Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html Jan-21-2016
			Type listType = new TypeToken<ArrayList<NormalTweet>>() {}.getType();
			tweets = gson.fromJson(in, listType);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			tweets = new ArrayList<Tweet>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}

	}
	
	private void saveInFile() {
		try {
			FileOutputStream fos = openFileOutput(FILENAME,
					0);

			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
			Gson gson = new Gson();
			gson.toJson(tweets, out);
			out.flush();

			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}
}