package uca.database.example;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import uca.database.example.models.TweetModel;
import uca.database.example.models.UserModel;
import uca.database.example.ui.adapters.TweetsAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // call add tweet
                addTweet();
            }
        });

        loadData();
    }

    private void loadData() {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();
        final RealmResults<TweetModel> tweetModels = realm.where(TweetModel.class).findAll();

        Log.i("TAG", "" + tweetModels.size());

        // specify an adapter (see also next example)
        mAdapter = new TweetsAdapter(tweetModels);
        mRecyclerView.setAdapter(mAdapter);
    }

    private UserModel addUser() {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //
        UserModel userModel = new UserModel();
        userModel.setName("Juan Perez");
        //
        realm.commitTransaction();

        return userModel;
    }

    private void addTweet() {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        //
        TweetModel tweetModel = realm.createObject(TweetModel.class);
        tweetModel.setText("Este es un tweet.");
        //tweetModel.setUserModel(addUser());
        //
        realm.commitTransaction();

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
