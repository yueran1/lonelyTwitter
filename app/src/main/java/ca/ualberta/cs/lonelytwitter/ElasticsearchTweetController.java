package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * Created by esports on 2/17/16.
 */
public class ElasticsearchTweetController {

    private static JestDroidClient client;
    //TODO: A function that gets tweets


    public static class GetTweetsTask extends AsyncTask<String, Void,ArrayList<Tweet>>{
        @Override
        protected ArrayList<Tweet> doInBackground(String... search_strings){
            verifyClient();

            // Start our initial array list(empty)
            ArrayList<Tweet> tweets =new ArrayList<Tweet>();

            //NoteL i am making a huge assumption here, that only the first search term
            //will be used.
            String query;
            if(search_strings[0]!="") {
                query = "{\\\"query \\\":{\\\"term\\\":{\\\"message\\\":\\\"" + search_strings[0] + "\\\"}}}";
            } else{
                query="";
            }


            Search search =new Search.Builder(query)
                    .addIndex("testing")
                    .addType("tweet")
                    .build();
            try {
                SearchResult execute= client.execute(search);
                if (execute.isSucceeded()){
                    //Return our list of tweets
                    List<NormalTweet> returned_tweets= execute.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(returned_tweets);
                } else{
                    //TODO: ADD an error message, because that other thing was puzzling
                    //TODO: RIght here it will trigger if the search fails
                    Log.i("TODO", "We actually failed here, searching for tweets");
                }
            }catch (IOException e){
                e.printStackTrace();
            }

            return tweets;
        }
    }
    //static not work with class,but objects.
    //public static ArrayList<Tweet> getTweets(){
    //   verifyClient();
    //}

    //TODO: A function that adds a tweet
    public static void addTweet(Tweet tweet){
        //make factory,make client
        verifyClient();

        Index index =new Index.Builder(tweet).index("testing").type("tweet").build();
        try {
            DocumentResult result = client.execute(index);
            if (result.isSucceeded()){
                //set the id to tweet that elasticsearch tole me it was
                tweet.setId(result.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public  static  class AddTweetTask extends AsyncTask<NormalTweet,Void,Void>{
        @Override
        protected Void doInBackground(NormalTweet... tweets){
            verifyClient();
            for(int i=0; i<tweets.length; i++){
               NormalTweet tweet=tweets[i];
                Index index=new Index.Builder(tweet).index("testing").type("tweet").build();



                try {
                    DocumentResult result = client.execute(index);
                    if (result.isSucceeded()){
                        //set the id to tweet that elasticsearch tole me it was
                        tweet.setId(result.getId());
                    }else{
                        //TODO:aAdd an error message, beause this was puzzling.
                        //TODO: Right here it will trigger if the insert fails
                        Log.i("TODO", "We actually failed here, adding a tweet");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            return null;
        }

    }

    public static void verifyClient(){
        //1. Verify that 'client' exits.
        if (client == null){
            //2. If it doesn't, make it.
            DroidClientConfig.Builder builder = new DroidClientConfig.Builder("http://cmput301.softwareprocess.es:8080");
            DroidClientConfig config = builder.build();

            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient)factory.getObject();
        }

    }
}
