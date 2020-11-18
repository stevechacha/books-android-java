package com.example.mybooks;


import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class ApiUtil {
    private static final String QUERY_PARAMETER_KEY = "q";

    private ApiUtil (){}

    public static final String BASE_API_URL= "https://www.googleapis.com/books/v1/volumes";

    public static final String KEY="key";
    public static final String API_KEY="AIzaSyDhb6tV52TsLZQvQfBn5cDCHze1NMaktik";
    // building a querry

    public static URL  buildUrl(String title){

        URL url=null;
        Uri uri= Uri.parse ( BASE_API_URL ).buildUpon ()
                .appendQueryParameter ( QUERY_PARAMETER_KEY,title )
                .build ();
        try {

            url =new URL ( uri.toString () );
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return url;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection =(HttpURLConnection ) url.openConnection ();

        try { InputStream stream=connection.getInputStream ();
            Scanner scanner = new Scanner ( stream );
            scanner.useDelimiter ( "\\A" );
            boolean hashData =scanner.hasNext ();
            if (hashData){
                return scanner.next ();

            } else {
                return  null;

            }
        }

       catch (Exception e){
           Log.d ("Error",e.toString ());
           return null;
       }
        finally {
            connection.disconnect ();
        }
    }

    public static ArrayList<Book> getBooksFromJson(String json){
        final String ID="id";
        final String TITLE="title";
        final String SUBTITLE="subTitle";
        final String AUTHOR="authors";
        final String PUBLISHER="publisher" ;
        final String PUBLISHER_DATE="publishedDate";
        final String ITEMS="items"  ;
        final String VOLUMEINFO="volumeINfo" ;

        ArrayList<Book>books =new ArrayList<Book> (  );
        try {
            JSONObject jsonBooks=new JSONObject(json) ;
            JSONArray  arrayBooks=jsonBooks.getJSONArray ( ITEMS );
            int numberOfBooks=arrayBooks.length ();
            for (int i=0; i<numberOfBooks;i++){
                JSONObject bookJSON=arrayBooks.getJSONObject ( i );
                JSONObject volumeInfoJSON=bookJSON.getJSONObject ( VOLUMEINFO );

                int authorNum=volumeInfoJSON.getJSONArray ( AUTHOR ).length ();
                String[] authors=new String[authorNum];
                for (int j=0;j<authorNum;j++){
                    authors[j]=volumeInfoJSON.getJSONArray ( AUTHOR ).get ( j ).toString ();
                }

                Book book =new Book ( bookJSON.getString ( ID ) ,
                        volumeInfoJSON.getString ( TITLE ),(volumeInfoJSON.isNull ( SUBTITLE )?""
                        :volumeInfoJSON.getString ( SUBTITLE )),authors,
                        volumeInfoJSON.getString ( PUBLISHER ),
                        volumeInfoJSON.getString ( PUBLISHER_DATE ));

                books.add ( book );
                }


        } catch (JSONException e){
            e.printStackTrace ();
        }
        return books;
    }

}
