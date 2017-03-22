package uca.database.example.ui.adapters;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import uca.database.example.MainActivity;
import uca.database.example.R;
import uca.database.example.models.TweetModel;
import uca.database.example.models.UserModel;


public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.ViewHolder> {
    private RealmResults<TweetModel> tweetModels;
    private MainActivity mainActivity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView user;
        public TextView tweet;
        public LinearLayout item;
        public CardView card;
        public ViewHolder(View view) {
            super(view);
            user = (TextView) view.findViewById(R.id.user);
            tweet = (TextView) view.findViewById(R.id.tweet);
            item = (LinearLayout) view.findViewById(R.id.item);
            card = (CardView) view.findViewById(R.id.card_view);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public TweetsAdapter(RealmResults<TweetModel> tweetModels, MainActivity mainActivity) {
        this.tweetModels = tweetModels;
        this.mainActivity = mainActivity;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public TweetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        TweetModel tweetModel = this.tweetModels.get(position);

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        // holder.user.setText(tweetModel.getUserModel().getName());
        holder.tweet.setText(tweetModel.getText());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(position);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.tweetModels.size();
    }

    private void deleteItem(int position) {
        // Get a Realm instance for this thread
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        TweetModel tweetModel = this.tweetModels.get(position);
        tweetModel.deleteFromRealm();

        realm.commitTransaction();


        mainActivity.loadData();
    }
}

