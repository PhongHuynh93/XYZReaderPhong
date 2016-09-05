package dhbk.android.xyzreaderphong.view.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import dhbk.android.xyzreaderphong.R;
import dhbk.android.xyzreaderphong.interactor.XYZResponse;

/**
 * Created by huynhducthanhphong on 9/4/16.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {

    private Context mContext;
    private List<XYZResponse> mXYZList;

    public ArticleListAdapter(Context context) {
        mContext = context;
        mXYZList = new ArrayList<>(); // create an empty list
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.list_item_article, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // call the dynamic height in the thumbnailView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.titleView.setText(mXYZList.get(position).getMTitle());
        holder.authorView.setText(mXYZList.get(position).getMAuthor());


        // fixme - set the image depend on aspect radio
        holder.thumbnailView.setAspectRatio((float) mXYZList.get(position).getMAspectRadio());
        // end set the image depend on aspect radio

        // clear the previous pending image
        Glide.clear(holder.thumbnailView);

        // fixme - load new image and cache this image + set palette background depend on bitmap which has downloaded
        // fixme - pass activity context so the image will depend on the activity lifecycle and can pause or start loading image -> so not leak memory.
        // fixme - diskCacheStrategy(DiskCacheStrategy.ALL) can let Glide cache 2 image (full hd image and fit image), so if we have the different image, it won't download it again.
        Glide.with(mContext)
                .load(mXYZList.get(position).getMThumbUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        // get the bitmap which has downloaded
                        Bitmap bitmap = ((GlideBitmapDrawable) resource.getCurrent()).getBitmap();
                        Palette palette = Palette.generate(bitmap);
                        int defaultColor = 0xFF333333;
                        int color = palette.getDarkMutedColor(defaultColor);
                        // change the background image of all the item in the recyclerview depend on the bitmap palette color.
                        holder.itemView.setBackgroundColor(color);
                        return false;
                    }
                })
                .into(holder.thumbnailView);
    }

    @Override
    public int getItemCount() {
        return mXYZList.isEmpty() ? 0 : mXYZList.size();
    }

    // replace artists data and notify change
    public void replaceAnotherData(List<XYZResponse> xyzResponses) {
        mXYZList = xyzResponses;
        notifyDataSetChanged();
    }

    // clear the recyclerview
    public void clear() {
        mXYZList.clear();
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public DynamicHeightNetworkImageView thumbnailView;
        public TextView titleView;
        public TextView subtitleView;
        public TextView authorView;

        public ViewHolder(View view) {
            super(view);
            thumbnailView = (DynamicHeightNetworkImageView) view.findViewById(R.id.thumbnail);
            titleView = (TextView) view.findViewById(R.id.article_title);
            subtitleView = (TextView) view.findViewById(R.id.article_subtitle);
            authorView = (TextView) view.findViewById(R.id.article_author);
        }
    }

}
