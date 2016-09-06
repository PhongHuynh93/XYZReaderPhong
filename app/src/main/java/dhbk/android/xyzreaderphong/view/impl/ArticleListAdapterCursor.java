package dhbk.android.xyzreaderphong.view.impl;

import android.content.Context;
import android.database.Cursor;
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
public class ArticleListAdapterCursor extends CursorRecyclerViewAdapter<ArticleListAdapterCursor.ViewHolder> {

    private Context mContext;
    private List<XYZResponse> mXYZList;

    public ArticleListAdapterCursor(Context context, Cursor cursor) {
        super(cursor);
        mContext = context;
        mXYZList = new ArrayList<>(); // create an empty list
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {
        // get the column index depend on the key
        int thumb_urlIndex = cursor.getColumnIndexOrThrow("thumb_url");
        int aspect_ratioIndex = cursor.getColumnIndexOrThrow("aspect_ratio");
        int authorIndex = cursor.getColumnIndexOrThrow("author");
        int _idIndex = cursor.getColumnIndexOrThrow("_id");
        int idIndex = cursor.getColumnIndexOrThrow("id");
        int photo_urlIndex = cursor.getColumnIndexOrThrow("photo_url");
        int titleIndex = cursor.getColumnIndexOrThrow("title");
        int bodyIndex = cursor.getColumnIndexOrThrow("body");
        int published_dateIndex = cursor.getColumnIndexOrThrow("published_date");

        if (thumb_urlIndex >= 0) {
            // clear the previous pending image
            Glide.clear(holder.thumbnailView);

            //  - load new image and cache this image + set palette background depend on bitmap which has downloaded
            //  - pass activity context so the image will depend on the activity lifecycle and can pause or start loading image -> so not leak memory.
            //  - diskCacheStrategy(DiskCacheStrategy.ALL) can let Glide cache 2 image (full hd image and fit image), so if we have the different image, it won't download it again.
            Glide.with(mContext)
                    .load(cursor.getString(thumb_urlIndex))
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
        if (aspect_ratioIndex >= 0) {
            //  - set the image depend on aspect radio
            holder.thumbnailView.setAspectRatio((float) cursor.getDouble(aspect_ratioIndex));
        }
        if (authorIndex >= 0) {
            holder.authorView.setText(cursor.getString(authorIndex));
        }
//        if (_idIndex >= 0) {
//        }
//        if (idIndex >= 0) {
//        }
//        if (photo_urlIndex >= 0) {
//        }
//        if (titleIndex >= 0) {
//        }
//        if (bodyIndex >= 0) {
//        }
//        if (published_dateIndex >= 0) {
//        }
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
