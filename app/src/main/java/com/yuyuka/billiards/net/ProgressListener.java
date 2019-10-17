package com.yuyuka.billiards.net;

public interface ProgressListener {

    void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish);
}