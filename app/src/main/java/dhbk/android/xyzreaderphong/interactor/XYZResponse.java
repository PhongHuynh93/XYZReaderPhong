package dhbk.android.xyzreaderphong.interactor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Getter;

/**
 * Created by huynhducthanhphong on 9/4/16.
 */
public class XYZResponse {
    public static final String DB_NAME = "XYZ_reader_database";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "items";

    @SerializedName("id")
    @Expose
    @Getter
    private String id;
    @SerializedName("photo")
    @Expose
    @Getter
    private String photo;
    @SerializedName("thumb")
    @Expose
    @Getter
    private String thumb;
    @SerializedName("aspect_ratio")
    @Expose
    @Getter
    private Double aspectRatio;
    @SerializedName("author")
    @Expose
    @Getter
    private String author;
    @SerializedName("title")
    @Expose
    @Getter
    private String title;
    @SerializedName("published_date")
    @Expose
    @Getter
    private String publishedDate;
    @SerializedName("body")
    @Expose
    @Getter
    private String body;

}