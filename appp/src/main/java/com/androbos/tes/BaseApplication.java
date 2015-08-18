package com.androbos.tes;

import android.app.Application;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

import io.realm.Realm;
import io.realm.exceptions.RealmMigrationNeededException;

/**
 * Created by tommy on 18/08/15.
 */
public class BaseApplication extends Application {
    private RequestQueue requestQueue;
    private static BaseApplication instance;
    private static final int TIMEOUT_MS = 30000; // 50second

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        try {
            Realm.getInstance(this);
        } catch (RealmMigrationNeededException expected) {
            Realm.deleteRealmFile(this); // Delete old realm
        }
        // Add your initialization code here
        Parse.initialize(this, "MrHkdJiEcUHQpKZ8nguq9YqSr7k5VWUFlXNTJ1rl", "r0YBIu2812EnHkmOQOlwzOkZc91PbyMmwGaulY3n");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

    /**
     * get instance application
     *
     * @return
     */
    public static synchronized BaseApplication getInstance() {
        return instance;
    }

    /**
     * get requestQueue from volley
     *
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(instance);
        return requestQueue;
    }

    /**
     * Add request to queue using specific tag
     *
     * @param request
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> request, String tag) {
        // set retry policy
        Log.d("debug", request.getUrl());
        request.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(request);
    }

    /**
     * cancelling all pending request by TAG
     *
     * @param tag
     */
    public void cancelPendingRequest(Object tag) {
        if (requestQueue != null)
            requestQueue.cancelAll(tag);
    }
}
