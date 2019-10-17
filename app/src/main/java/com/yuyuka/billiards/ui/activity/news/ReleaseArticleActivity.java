package com.yuyuka.billiards.ui.activity.news;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chinalwb.are.AREditText;
import com.chinalwb.are.spans.AreImageSpan;
import com.chinalwb.are.strategies.ImageStrategy;
import com.chinalwb.are.styles.ARE_Image;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Bold;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Hr;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Image;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_ListBullet;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_ListNumber;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Quote;
import com.chinalwb.are.styles.toolitems.ARE_ToolItem_Underline;
import com.chinalwb.are.styles.toolitems.IARE_ToolItem;
import com.chinalwb.are.styles.toolitems.styles.ARE_Style_Image;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseActivity;
import com.yuyuka.billiards.base.BaseMvpActivity;
import com.yuyuka.billiards.mvp.contract.news.ReleaseContract;
import com.yuyuka.billiards.mvp.presenter.news.ReleasePresenter;
import com.yuyuka.billiards.net.ProgressListener;
import com.yuyuka.billiards.pojo.Tag;
import com.yuyuka.billiards.pojo.UploadResult;
import com.yuyuka.billiards.ui.activity.common.PhotoPickerActivity;
import com.yuyuka.billiards.utils.CollectionUtils;
import com.yuyuka.billiards.utils.KeyboardUtils;
import com.yuyuka.billiards.utils.SizeUtils;
import com.yuyuka.billiards.utils.ToastUtils;
import com.yuyuka.billiards.utils.log.LogUtil;
import com.yuyuka.billiards.widget.TagLayout;
import com.yuyuka.billiards.widget.richtext.LinearToolBar;
import com.yuyuka.billiards.widget.richtext.MyToolItemBold;
import com.yuyuka.billiards.widget.richtext.MyToolItemHr;
import com.yuyuka.billiards.widget.richtext.MyToolItemImage;
import com.yuyuka.billiards.widget.richtext.MyToolItemListBullet;
import com.yuyuka.billiards.widget.richtext.MyToolItemListNumber;
import com.yuyuka.billiards.widget.richtext.MyToolItemQuote;
import com.yuyuka.billiards.widget.richtext.MyToolItemUnderline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ReleaseArticleActivity extends BaseMvpActivity<ReleasePresenter> implements KeyboardUtils.OnKeyboardVisibilityListener, ReleaseContract.IReleaseView {

    @BindView(R.id.tool_bar)
    LinearToolBar mToolbar;
    @BindView(R.id.areditor)
    AREditText mRichEditor;
    @BindView(R.id.et_title)
    EditText mTitleEt;
    MyToolItemImage itemImage;
    private int selectedCount;
    private List<String> result;
    private List<UploadResult> uploadResults;
    private int uploadCount;
    private String cover = "";
    private String tags;
    @BindView(R.id.tag_layout)
    TagLayout tagLayout;
    List<Tag> tagList;





    @Override
    protected void initTitle() {
        super.initTitle();
        getTitleBar().getRightTitleTv().setTextColor(getResourceColor(R.color.text_color_6));
        getTitleBar().setLeftText("取消", view -> finish()).setRightText("发布", view -> {
            String title = mTitleEt.getText().toString();
            String contentInfo = mRichEditor.getHtml();
            if (TextUtils.isEmpty(title)){
                ToastUtils.showToast(getContext(),"请输入标题");
                return;
            }
            if (TextUtils.isEmpty(contentInfo)){
                ToastUtils.showToast(getContext(),"请输入正文");
                return;
            }
            if (TextUtils.isEmpty(tags)){
                ToastUtils.showToast(getContext(),"请选择标签");
            }
            LogUtil.d("html",contentInfo);

            getPresenter().releaseNews(0,0,"",cover,contentInfo,title,tags);

        }).setTitle("发布文章").hideDivider().show();
    }



    @Override
    protected void initView() {
        setTitleStyle(1);
        fullScreen = false;
        setContentView(R.layout.activity_release_article);
        KeyboardUtils.setKeyboardListener(this,this);
        TextView bold = new TextView(this);
        int padding = SizeUtils.dp2px(this,10);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        bold.setPadding(padding,padding,padding,padding);
        bold.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        bold.setLayoutParams(params);
        bold.setGravity(Gravity.CENTER);
        bold.setText("加粗");
        TextView underLine = new TextView(this);
        underLine.setPadding(padding,padding,padding,padding);
        underLine.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        underLine.setLayoutParams(params);
        underLine.setGravity(Gravity.CENTER);
        underLine.setText("下划线");
        TextView quote = new TextView(this);
        quote.setPadding(padding,padding,padding,padding);
        quote.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        quote.setLayoutParams(params);
        quote.setGravity(Gravity.CENTER);
        quote.setText("“引用”");
        TextView numberLine = new TextView(this);
        numberLine.setPadding(padding,padding,padding,padding);
        numberLine.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        numberLine.setLayoutParams(params);
        numberLine.setGravity(Gravity.CENTER);
        numberLine.setText("有序列表");
        TextView bulletLine = new TextView(this);
        bulletLine.setPadding(padding,padding,padding,padding);
        bulletLine.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        bulletLine.setLayoutParams(params);
        bulletLine.setGravity(Gravity.CENTER);
        bulletLine.setText("无序列表");

        TextView line = new TextView(this);
        line.setPadding(padding,padding,padding,padding);
        line.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
        line.setLayoutParams(params);
        line.setGravity(Gravity.CENTER);
        line.setText("分割线");
        IARE_ToolItem boldItem = new MyToolItemBold(bold);
        IARE_ToolItem underLineItem = new MyToolItemUnderline(underLine);
        IARE_ToolItem quteItem = new MyToolItemQuote(quote,mRichEditor);
        IARE_ToolItem numberLineItem = new MyToolItemListNumber(mRichEditor,numberLine);
        IARE_ToolItem bulletLineItem = new MyToolItemListBullet(mRichEditor,bulletLine);
        IARE_ToolItem hrItem = new MyToolItemHr(line,mRichEditor);
        itemImage = new MyToolItemImage(mRichEditor,mToolbar.getmImgBtn());
        itemImage.setOnClickListener(view -> {
            PhotoPickerActivity.launcher(this,0,selectedCount);
        });

        mRichEditor.setImageStrategy(new ImageStrategy() {
            @Override
            public void uploadAndInsertImage(Uri uri, ARE_Style_Image areStyleImage) {

            }
        });
        mToolbar.addToolbarItem(boldItem);
        mToolbar.addToolbarItem(underLineItem);
        mToolbar.addToolbarItem(numberLineItem);
        mToolbar.addToolbarItem(bulletLineItem);
        mToolbar.addToolbarItem(hrItem);
        mToolbar.addToolbarItem(itemImage);
        mRichEditor.setToolbar(mToolbar);
        getPresenter().getTags();

    }

    @Override
    protected void initData() {

    }




    @Override
    public void onVisibilityChanged(boolean visible) {
        if (visible)
        mToolbar.hide();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        mToolbar.onActivityResult(requestCode, resultCode, data);
        try {
            result = data.getStringArrayListExtra("result");
            if (!CollectionUtils.isEmpty(result)){
                uploadResults = new ArrayList<>();
                uploadCount = 0;
                for (int i = 0; i < result.size(); i++) {
                    getPresenter().upload(result.get(i), i, new ProgressListener() {
                        @Override
                        public void onProgress(long hasWrittenLen, long totalLen, boolean hasFinish) {

                        }
                    });
                }
            }
        }catch (Exception e){
            result = null;
        }



    }

    @Override
    protected ReleasePresenter getPresenter() {
        return new ReleasePresenter(this);
    }

    @Override
    public void showUploadSuccess(UploadResult url) {
        uploadResults.add(url);
        uploadCount++;
        if (uploadCount >= result.size()){
            completeUpload();
            dismissProgressDialog();
        }
    }

    @Override
    public void showUploadFailure(int index) {
        uploadCount++;
        if (uploadCount >= result.size()){
            completeUpload();
            dismissProgressDialog();
        }


    }

    void completeUpload(){
        Collections.sort(uploadResults);
        for (UploadResult uploadResult : uploadResults) {
            ((ARE_Style_Image)itemImage.getStyle()).insertImage(uploadResult.getUrl(), AreImageSpan.ImageType.URL);

        }
    }

    @Override
    public void showReleaseSuccess(String msg) {
        ToastUtils.showToast(this,msg);
        finish();
    }

    @Override
    public void showReleaseFailure(String msg) {
        ToastUtils.showToast(this,msg);
    }

    @Override
    public void showTags(List<Tag> tags) {
        tagLayout.removeAllViews();
        tagList = tags;
        for (int i = 0; i < tagList.size(); i++) {
            int margin = SizeUtils.dp2px(this,5);
            TextView textView = new TextView(this);
            ViewGroup.LayoutParams parentParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(parentParams);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(SizeUtils.dp2px(this,60),SizeUtils.dp2px(this,22));
            params.setMargins(margin,margin,margin,margin);
            textView.setLayoutParams(params);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            textView.setTextColor(Color.WHITE);
            textView.setBackgroundResource(R.drawable.bg_tag_comment_negative);
            textView.setGravity(Gravity.CENTER);
            textView.setText(tagList.get(i).getTagName());
            linearLayout.addView(textView);
            boolean isSelected = tagList.get(i).isSelected();
            if (tagList.get(i).isSelected()){
                textView.setBackgroundResource(R.drawable.bg_tag_comment_positive);
            }else {
                textView.setBackgroundResource(R.drawable.bg_tag_comment_negative);
            }
            int finalI = i;
            textView.setOnClickListener(view -> {
                String tagStr = "";
                tagList.get(finalI).setSelected(!isSelected);
                showTags(tagList);
                for (int j = 0; j < tagList.size(); j++) {
                    if (tagList.get(j).isSelected()){
                        tagStr += tagList.get(j).getMark()+",";

                    }
                }
                if (TextUtils.isEmpty(tagStr))
                    ReleaseArticleActivity.this.tags = "";
                else
                    ReleaseArticleActivity.this.tags = tagStr.substring(0,tagStr.lastIndexOf(","));
            });
            tagLayout.addView(linearLayout);
        }
    }
}
