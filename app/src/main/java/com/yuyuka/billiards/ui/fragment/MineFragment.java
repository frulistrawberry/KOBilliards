package com.yuyuka.billiards.ui.fragment;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.uikit.common.util.file.FileUtil;
import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.base.BaseRefreshFragment;
import com.yuyuka.billiards.image.ImageManager;
import com.yuyuka.billiards.image.support.ImageListener;
import com.yuyuka.billiards.image.support.LoadOption;
import com.yuyuka.billiards.mvp.contract.mine.MineContract;
import com.yuyuka.billiards.mvp.presenter.mine.MinePresenter;
import com.yuyuka.billiards.pojo.BilliardsTotalReturnBaseDto;
import com.yuyuka.billiards.pojo.CustomNoticePojo;
import com.yuyuka.billiards.pojo.MinePojo;
import com.yuyuka.billiards.utils.BarUtils;
import com.yuyuka.billiards.utils.BitmapUtils;
import com.yuyuka.billiards.utils.DataOptionUtils;
import com.yuyuka.billiards.utils.FileUtils;
import com.yuyuka.billiards.widget.StateButton;

import java.io.File;

import butterknife.BindInt;
import butterknife.BindView;
import jp.wasabeef.glide.transformations.internal.FastBlur;

public class MineFragment extends BaseRefreshFragment<MinePresenter> implements MineContract.IMineView {
    @BindView(R.id.v_status)
    View statusbar;
    @BindView(R.id.iv_head)
    ImageView headIv;
    @BindView(R.id.tv_user_name)
    TextView userName;
    @BindView(R.id.xinyubi)
    TextView xinyubi;
    @BindView(R.id.tv_level)
    TextView level;
    @BindView(R.id.xingbie)
    ImageView xingbie;
    @BindView(R.id.renzheng)
    ImageView renzheng;
    @BindView(R.id.zhanqu)
    TextView zhanqu;
    @BindView(R.id.zhanli)
    TextView zhanli;
    @BindView(R.id.danqianduanwei)
    ImageView dangqianduanwei;
    @BindView(R.id.zuigaoduanwei)
    ImageView zuigaoduanwei;
    @BindView(R.id.fabu)
    TextView fabu;
    @BindView(R.id.guanzhu)
    TextView guanzhu;
    @BindView(R.id.fensi)
    TextView fensi;
    @BindView(R.id.huozan)
    TextView huozan;
    @BindView(R.id.quanbuduishou)
    TextView quanbuduishou;
    @BindView(R.id.quanbuduiju)
    TextView qubuduiju;
    @BindView(R.id.duijushenglv)
    TextView duijushenglv;
    @BindView(R.id.jibaiduishou)
    TextView jibaiduishou;
    @BindView(R.id.dabaiguowo)
    TextView dabaiguowo;
    @BindView(R.id.blur)
    ImageView blur;
    @BindView(R.id.renzhengbtn)
    StateButton renzhengBtn;
    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.fragment_mine,parent,false);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        super.initView();
        statusbar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, BarUtils.getStatusBarHeight(getContext())));
        onRefresh();

    }

    @Override
    public void onRefresh() {
        getPresenter().getMineInfo();
    }

    @Override
    protected MinePresenter getPresenter() {
        return new MinePresenter(this);
    }



    @Override
    public void showMineData(MinePojo data) {
        ImageManager.getInstance().getBitmap(getContext(), data.getHeadImage(), new ImageListener<Bitmap>() {
            @Override
            public void onSuccess(Bitmap result) {
                Bitmap bitmap = FastBlur.blur(result,10,true);
                blur.setImageBitmap(bitmap);
            }

            @Override
            public void onFail(Throwable throwable) {

            }
        });
        ImageManager.getInstance().loadNet(data.getHeadImage(),headIv,new LoadOption().setIsCircle(true));
        userName.setText(data.getUserName());
        xinyubi.setText("信誉币:"+data.getCreditScore());
        level.setText("Lv25");
        if (data.getSex() == 0){
            xingbie.setImageResource(R.mipmap.nan);
        }else {
            xingbie.setImageResource(R.mipmap.nv);
        }

        if (data.getAuthentication() == 0){
            renzheng.setVisibility(View.GONE);
            renzhengBtn.setVisibility(View.VISIBLE);
        }
        else{
            renzheng.setVisibility(View.VISIBLE);
            renzhengBtn.setVisibility(View.GONE);
        }

        zhanqu.setText("战区:"+data.getWarZone());
        zhanli.setText("战力:"+DataOptionUtils.getZhanli(data.getCombatEffectiveness()));
        CustomNoticePojo.Duan currentDuan = data.getRankingConfigurtion();
        CustomNoticePojo.Duan hisDuan = data.getHisRankingConfigurtion();
        dangqianduanwei.setImageResource(DataOptionUtils.getduanwei(currentDuan.getName()));
        zuigaoduanwei.setImageResource(DataOptionUtils.getduanwei(hisDuan.getName()));
        fabu.setText(data.getReleaseTotal()+"");
        fensi.setText(data.getFansTotal()+"");
        guanzhu.setText(data.getFollowTotal()+"");
        huozan.setText(data.getPraiseTotal()+"");
        BilliardsTotalReturnBaseDto.Data baseDto = data.getBilliardsTotalReturnBaseDto().getData();
        quanbuduishou.setText(baseDto.getPlayWithPers()+"");
        qubuduiju.setText(baseDto.getTotalInnings()+"");
        duijushenglv.setText(DataOptionUtils.getStringWithRound1(Double.valueOf(baseDto.getWinRate())*100+"")+"%");
        jibaiduishou.setText(baseDto.getFuckPers()+"");
        dabaiguowo.setText(baseDto.getFuckedPers()+"");


    }
}
