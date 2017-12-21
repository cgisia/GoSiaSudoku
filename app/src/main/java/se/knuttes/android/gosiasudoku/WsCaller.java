package se.knuttes.android.gosiasudoku;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by antonssons on 2017-12-21.
 */

public class WsCaller {

    private MainActivity parent;
    private TextView textView;

    public WsCaller(MainActivity activity) {
        parent = activity;
        textView = parent.getTextView();
    }

    protected void getList(final Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
//        getSettingExternalDatabase(context);
        String url = "http://openshifttest-goran.193b.starter-ca-central-1.openshiftapps.com/rest/sudoku/list";
        Log.d(this.getClass().getName(), "URL: " + url);

        JsonArrayRequest jsRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d(this.getClass().getName(), "Response:\n" + response.toString(2));
                    parent.getTextView().setText("");
                    parent.addText(response.toString(2) + '\n');
//                    JSONObject xsearch = response.getJSONObject("xsearch");
//                    int nrRecords = xsearch.getInt("records");
//                    JSONArray list = xsearch.getJSONArray("list");
//                    for(int i = 0; i < nrRecords; i++) {
//                        JSONObject item = list.getJSONObject(i);
//                        if(!item.isNull("title")) {
//                            String title = item.getString("title");
//                            title = TitleHelper.FormatTitle(title);
//                            bookBaseDTO.setTitle(title);
//                        }
//                        if(!item.isNull("creator")) {
//                            String author = item.getString("creator");
//                            author = AuthorHelper.FormatAuthor(author);
//                            bookBaseDTO.setAuthor(author);
//                        }
//                        if(!item.isNull("language")) {
//                            String language = item.getString("language");
//                            bookBaseDTO.setLanguage(language);
//                        }
//                        if(!item.isNull("isbn")) {
//                            String isbn = item.getString("isbn");
//                            if(SplitJsonList.isJsonList(isbn)) {
//                                bookBaseDTO.setIsbn(SplitJsonList.getFirst(isbn));
//                            } else {
//                                bookBaseDTO.setIsbn(item.getString("isbn"));
//                            }
//                        }
//                    }
//                    setChanged();
//                    notifyObservers(bookBaseDTO);
                    Toast.makeText(context, "WS request sent!", Toast.LENGTH_LONG).show();
                } catch(JSONException e) {
                    Log.e(this.getClass().getName(), "Det blev ett exception! " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(this.getClass().getName(), "That didn't work! " + error.getMessage());
                if(error.getMessage().contains("UnknownHostException")) {
                    Toast.makeText(context, "URL not valid!", Toast.LENGTH_LONG).show();
                }
            }
        }
        );

        jsRequest.setRetryPolicy(new DefaultRetryPolicy(2 * 1000, 2, 2.0f));
        queue.add(jsRequest);
    }



//    public void getBookFromIsbn(final Context context, String isbn) {
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(context);
//        getSettingExternalDatabase(context);
//        String url = externalDatabase + "?q=isbn:" + isbn.trim() + "&format=json";
//        Log.d(this.getClass().getName(), "URL: " + url);
//
//        JsonObjectRequest jsRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    Log.d(this.getClass().getName(), "Response:\n" + response.toString(2));
//                    JSONObject xsearch = response.getJSONObject("xsearch");
//                    int nrRecords = xsearch.getInt("records");
//                    JSONArray list = xsearch.getJSONArray("list");
//                    for(int i = 0; i < nrRecords; i++) {
//                        JSONObject item = list.getJSONObject(i);
//                        if(!item.isNull("title")) {
//                            String title = item.getString("title");
//                            title = TitleHelper.FormatTitle(title);
//                            bookBaseDTO.setTitle(title);
//                        }
//                        if(!item.isNull("creator")) {
//                            String author = item.getString("creator");
//                            author = AuthorHelper.FormatAuthor(author);
//                            bookBaseDTO.setAuthor(author);
//                        }
//                        if(!item.isNull("language")) {
//                            String language = item.getString("language");
//                            bookBaseDTO.setLanguage(language);
//                        }
//                        if(!item.isNull("isbn")) {
//                            String isbn = item.getString("isbn");
//                            if(SplitJsonList.isJsonList(isbn)) {
//                                bookBaseDTO.setIsbn(SplitJsonList.getFirst(isbn));
//                            } else {
//                                bookBaseDTO.setIsbn(item.getString("isbn"));
//                            }
//                        }
//                    }
//                    setChanged();
//                    notifyObservers(bookBaseDTO);
//                } catch(JSONException e) {
//                    Log.e(this.getClass().getName(), "Det blev ett exception! " + e.getMessage());
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(this.getClass().getName(), "That didn't work! " + error.getMessage());
//                if(error.getMessage().contains("UnknownHostException")) {
//                    Toast.makeText(context, R.string.external_database_not_valid, Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//        );
//
//        jsRequest.setRetryPolicy(new DefaultRetryPolicy(2 * 1000, 2, 2.0f));
//        queue.add(jsRequest);
//    }

//    @Override
//    public void getBookIsbnsFromAuthorAndTitle(Context context, String author, String title) {
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(context);
//        getSettingExternalDatabase(context);
//        String url = externalDatabase + "?q=forf:" + author.trim() + " tit:" + title.trim() + "&format=json";
//        Log.d(this.getClass().getName(), "URL: " + url);
//
//        JsonObjectRequest jsRequest = new JsonObjectRequest(Request.Method.GET, url, new JSONObject(), new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                List<String> isbns = new ArrayList<>();
//                try {
//                    Log.d(this.getClass().getName(), "Response:\n" + response.toString(2));
//                    JSONObject xsearch = response.getJSONObject("xsearch");
//                    int nrRecords = xsearch.getInt("records");
//                    JSONArray list = xsearch.getJSONArray("list");
//                    for(int i = 0; i < nrRecords; i++) {
//                        JSONObject item = list.getJSONObject(i);
//                        if(!item.isNull("language")) {
//                            String language = item.getString("language");
//                            if("swe".equals(language) || "eng".equals(language)) {
//                                if(!item.isNull("isbn")) {
//                                    String isbn = item.getString("isbn");
//                                    if(SplitJsonList.isJsonList(isbn)) {
//                                        int nrIsbns = SplitJsonList.getSize(isbn);
//                                        for(int index = 0; index < nrIsbns; index++) {
//                                            isbns.add(SplitJsonList.getIndex(isbn, index).trim());
//                                        }
//                                    } else {
//                                        isbns.add(isbn.trim());
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    setChanged();
//                    notifyObservers(isbns);
//                } catch(JSONException e) {
//                    Log.e(this.getClass().getName(), "Det blev ett exception! " + e.getMessage());
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(this.getClass().getName(), "That didn't work! " + error.getMessage());
//            }
//        }
//        );
//
//        jsRequest.setRetryPolicy(new DefaultRetryPolicy(2 * 1000, 2, 2.0f));
//        queue.add(jsRequest);
//    }

//    private void getSettingExternalDatabase(Context context) {
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        externalDatabase = prefs.getString("external_database", "http://libris.kb.se/xsearch");
//    }

}
