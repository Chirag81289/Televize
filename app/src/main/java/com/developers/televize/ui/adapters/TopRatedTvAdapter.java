package com.developers.televize.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.developers.televize.R;
import com.developers.televize.data.model.TopRatedTvModel.TopRatedResult;
import com.developers.televize.ui.fragments.PopularTv.PopularTvView;
import com.developers.televize.ui.fragments.TopRatedTv.TopRatedTvView;
import com.developers.televize.util.Constants;
import com.developers.televize.util.ViewCallBack;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Amanjeet Singh on 22/8/17.
 */

public class TopRatedTvAdapter extends RecyclerView.Adapter<TopRatedTvAdapter.TopRatedRecyclerViewHolder> implements ViewCallBack {
    private Context context;
    private List<TopRatedResult> topRatedResults;
    private TopRatedTvView topRatedView;

    public TopRatedTvAdapter(Context context, List<TopRatedResult> topRatedResults) {
        this.context = context;
        this.topRatedResults = topRatedResults;
    }

    @Override
    public TopRatedRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.top_list_row, parent, false);
        return new TopRatedRecyclerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TopRatedRecyclerViewHolder holder, final int position) {
        Picasso.with(context)
                .load(Constants.BASE_URL_IMAGES + topRatedResults.get(position).getBackdropPath())
                .into(holder.topRatedBanner);
        holder.topRatedTitle.setText(topRatedResults.get(position).getName());
        holder.topRatedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRatedView.launchDetailActivity(topRatedResults.get(position),
                        holder.topRatedBanner, holder.topRatedTitle);
            }
        });
        holder.shareRatedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                topRatedView.launchShareActivity("LOOK at " +
                        topRatedResults.get(position).getName() + " with " +
                        topRatedResults.get(position).getPopularity() + " popularity.");
            }
        });
    }

    @Override
    public int getItemCount() {
        return topRatedResults.size();
    }

    @Override
    public void setPopularTvView(PopularTvView view) {

    }

    @Override
    public void setTopRatedTvView(TopRatedTvView topRatedTvView) {
        this.topRatedView = topRatedTvView;
    }

    public class TopRatedRecyclerViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.top_rated_image_view)
        ImageView topRatedBanner;
        @BindView(R.id.top_rated_title_textview)
        TextView topRatedTitle;
        @BindView(R.id.top_rated_card_view)
        CardView topRatedCard;
        @BindView(R.id.share_rated_btn)
        ImageButton shareRatedButton;

        public TopRatedRecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
