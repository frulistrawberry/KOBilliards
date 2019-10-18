package com.yuyuka.billiards.utils;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.pojo.ImagePojo;

import java.util.List;


public class ViewUtils {


    /**
     * 格式化手机号 3-4-4
     * @param editText 编辑框
     */
    public static void phoneAddSpace(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;
            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int spaceNumB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();
                if (buffer.length()>0){
                    buffer.delete(0,buffer.length());
                }
                spaceNumB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' '){
                        spaceNumB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged){
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged){
                    location = editText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()){
                        if (buffer.charAt(index) == ' '){
                            buffer.deleteCharAt(index);
                        }else {
                            index++;
                        }
                    }
                    index = 0;
                    int spaceNumC = 0;
                    while (index < buffer.length()){
                        if (index == 3 || index == 8){
                            buffer.insert(index,' ');
                            spaceNumC++;
                        }
                        index++;
                    }
                    if (spaceNumC > spaceNumB){
                        location += (spaceNumC - spaceNumB);
                    }
                    tempChar = new char[buffer.length()];
                    buffer.getChars(0,buffer.length(),tempChar,0);
                    String str = buffer.toString();
                    if (location > str.length()){
                        location = str.length();
                    }else if (location < 0){
                        location = 0;
                    }

                    editText.setText(str);
                    Editable editable = editText.getText();
                    Selection.setSelection(editable,location);
                    isChanged = false;
                }
            }
        });
    }

    /**
     * 格式化邀请码/验证码 3-3
     * @param editText
     */
    public static void codeAddSpace(final EditText editText){
        editText.addTextChangedListener(new TextWatcher() {
            int beforeTextLength = 0;
            int onTextLength = 0;
            boolean isChanged = false;
            int location = 0;// 记录光标的位置
            private char[] tempChar;
            private StringBuffer buffer = new StringBuffer();
            int spaceNumB = 0;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                beforeTextLength = s.length();
                if (buffer.length()>0){
                    buffer.delete(0,buffer.length());
                }
                spaceNumB = 0;
                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == ' '){
                        spaceNumB++;
                    }
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                onTextLength = s.length();
                buffer.append(s.toString());
                if (onTextLength == beforeTextLength || onTextLength <= 3 || isChanged){
                    isChanged = false;
                    return;
                }
                isChanged = true;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isChanged){
                    location = editText.getSelectionEnd();
                    int index = 0;
                    while (index < buffer.length()){
                        if (buffer.charAt(index) == ' '){
                            buffer.deleteCharAt(index);
                        }else {
                            index++;
                        }
                    }
                    index = 0;
                    int spaceNumC = 0;
                    while (index < buffer.length()){
                        if (index == 3){
                            buffer.insert(index,' ');
                            spaceNumC++;
                        }
                        index++;
                    }
                    if (spaceNumC > spaceNumB){
                        location += (spaceNumC - spaceNumB);
                    }
                    tempChar = new char[buffer.length()];
                    buffer.getChars(0,buffer.length(),tempChar,0);
                    String str = buffer.toString();
                    if (location > str.length()){
                        location = str.length();
                    }else if (location < 0){
                        location = 0;
                    }

                    editText.setText(str);
                    Editable editable = editText.getText();
                    Selection.setSelection(editable,location);
                    isChanged = false;
                }
            }
        });
    }

    /**
     * 获取RecyclerView 滑动距离
     * @return 滑动距离
     */
    public static int getRecyclerViewScrollY(RecyclerView recyclerView, int headerHeight){
        LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        // 获取第一个可见item的位置
        int position = layoutManager.findFirstVisibleItemPosition();
        // 获取第一个可见item
        View firstVisibleChildView = layoutManager.findViewByPosition(position);
        // 获取第一个可见item的高度
        int itemHeight = firstVisibleChildView.getHeight();
        // 获取第一个可见item的位置
        int distance;
        if (position == 0) {
            distance = (position) * itemHeight - firstVisibleChildView.getTop();
        } else {
            distance = (position) * itemHeight - firstVisibleChildView.getTop() + headerHeight;
        }
        return distance;
    }


    /**
     * 缺省页视图
     * @param res 图片
     * @param msg 提示文字
     */

    public static View genEmptyView(Context context, int res, String msg){
        return genEmptyView(context,res,msg,null);
    }

    /**
     *
     * 缺省页视图
     * @param res 图片
     * @param msg 提示文字
     * @param subMsg 说明文字
     */
    public static View genEmptyView(Context context, int res, String msg, String subMsg){
        View emptyView = View.inflate(context, R.layout.layout_empty,null);
        TextView msgTv = emptyView.findViewById(R.id.tv_msg);
        ImageView emptyIv = emptyView.findViewById(R.id.iv_empty);
        TextView subMsgTv = emptyView.findViewById(R.id.tv_sub_msg);
        emptyIv.setImageResource(res);
        msgTv.setText(msg);
        if (!TextUtils.isEmpty(subMsg)){
            subMsgTv.setText(subMsg);
            subMsgTv.setVisibility(View.VISIBLE);
        }else {
            subMsgTv.setVisibility(View.GONE);
        }
        return emptyView;
    }

    public static View genLoadingView(Context context){
        return View.inflate(context, R.layout.layout_loading,null);
    }

    /**
     *
     * @param context 上下文
     * @return
     */
    public static View genErrorView(Context context, int res, String msg, String subMsg){
        View emptyView = View.inflate(context, R.layout.layout_empty,null);
        TextView msgTv = emptyView.findViewById(R.id.tv_msg);
        ImageView emptyIv = emptyView.findViewById(R.id.iv_empty);
        TextView subMsgTv = emptyView.findViewById(R.id.tv_sub_msg);
        emptyIv.setImageResource(res);
        msgTv.setText(msg);
        if (!TextUtils.isEmpty(subMsg)){
            subMsgTv.setText(subMsg);
            subMsgTv.setVisibility(View.VISIBLE);
        }else {
            subMsgTv.setVisibility(View.GONE);
        }
        return emptyView;
    }

    public static void loadBanner(List<ImagePojo> bannerList, ConvenientBanner<ImagePojo> banner, boolean canLoop, boolean pointViewVisible, final boolean isCornerRound){
        if (banner == null) {
            return;
        }
        if (CollectionUtils.isEmpty(bannerList)){
            banner.setVisibility(View.GONE);
        }else {
            banner.setVisibility(View.VISIBLE);
            if (bannerList.size()>1 && canLoop){
                banner.setCanLoop(true);
                banner.startTurning(3000);
            }else {
                banner.setCanLoop(false);
            }
            if (bannerList.size()>1&&pointViewVisible){
                banner.setPointViewVisible(true)
                        .setPageIndicator(new int[]{R.drawable.banner_indecator_nav, R.drawable.banner_indicator_act})
                        .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);
            }
            banner.setPages((CBViewHolderCreator<BannerHolder>) () -> new BannerHolder(isCornerRound),bannerList);


        }
    }

    public static class BannerHolder implements Holder<ImagePojo> {
        private ImageView mImageView;
        private LoadOption mOption;
        private boolean isCornerRound;

        public BannerHolder(boolean isCornerRound) {
            mOption = new LoadOption();
            this.isCornerRound = isCornerRound;
            if (isCornerRound)
                mOption.setRoundRadius(SizeUtils.dp2px(AppUtils.getAppContext(),5));
        }

        @Override
        public View createView(Context context) {
            mImageView= new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );
            mImageView.setLayoutParams(params);
            if (!isCornerRound)
                mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return mImageView;
        }

        @Override
        public void UpdateUI(final Context context, int position, ImagePojo data) {
            String url = data.getImageUrl();
            final String toAction = data.getToAction();
            if (!url.startsWith("http"))
                ImageManager.getInstance().loadAsset(url,mImageView,mOption);
            else{
                mImageView.setScaleType(ImageView.ScaleType.FIT_XY);
                ImageManager.getInstance().loadNet(url,mImageView,mOption);
            }
            mImageView.setOnClickListener(v -> {

            });
        }





    }
}
