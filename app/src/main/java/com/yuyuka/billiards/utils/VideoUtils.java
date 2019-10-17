package com.yuyuka.billiards.utils;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;

public class VideoUtils {
    public static Bitmap getVideoThumb(String path) {
        MediaMetadataRetriever media = new MediaMetadataRetriever();
        media.setDataSource(path);
        return  media.getFrameAtTime();
    }
}
