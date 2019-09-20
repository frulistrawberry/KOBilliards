package com.yuyuka.billiards.ui.fragment.message;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yuyuka.billiards.R;
import com.yuyuka.billiards.base.BaseFragment;
import com.yuyuka.billiards.pojo.User;
import com.yuyuka.billiards.ui.activity.message.YanzhengMessageActivity;
import com.yuyuka.billiards.ui.adapter.message.AddressbookAdapter;
import com.yuyuka.billiards.ui.adapter.message.TianjiaActivity;
import com.yuyuka.billiards.utils.SideBar;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class AddressbookFragment extends BaseFragment {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.side_bar)
    SideBar sideBar;
    @BindView(R.id.yanzheng_message)
    LinearLayout yanzhengMessage;
    @BindView(R.id.tianjia_haoyou)
    LinearLayout tianjiaHaoyou;
    private ArrayList<User> list;
    private AddressbookAdapter adapter;

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.layout_addressbookfragment, parent, false);
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        adapter = new AddressbookAdapter(getContext(), list);
        list.add(new User("亳州")); // 亳[bó]属于不常见的二级汉字
        list.add(new User("大娃"));
        list.add(new User("二娃"));
        list.add(new User("三娃"));
        list.add(new User("四娃"));
        list.add(new User("五娃"));
        list.add(new User("六娃"));
        list.add(new User("七娃"));
        list.add(new User("喜羊羊"));
        list.add(new User("美羊羊"));
        list.add(new User("懒羊羊"));
        list.add(new User("沸羊羊"));
        list.add(new User("暖羊羊"));
        list.add(new User("慢羊羊"));
        list.add(new User("灰太狼"));
        list.add(new User("红太狼"));
        list.add(new User("孙悟空"));
        list.add(new User("黑猫警长"));
        list.add(new User("舒克"));
        list.add(new User("贝塔"));
        list.add(new User("海尔"));
        list.add(new User("阿凡提"));
        list.add(new User("邋遢大王"));
        list.add(new User("哪吒"));
        list.add(new User("没头脑"));
        list.add(new User("不高兴"));
        list.add(new User("蓝皮鼠"));
        list.add(new User("大脸猫"));
        list.add(new User("大头儿子"));
        list.add(new User("小头爸爸"));
        list.add(new User("蓝猫"));
        list.add(new User("淘气"));
        list.add(new User("叶峰"));
        list.add(new User("楚天歌"));
        list.add(new User("江流儿"));
        list.add(new User("Tom"));
        list.add(new User("Jerry"));
        list.add(new User("12345"));
        list.add(new User("54321"));
        list.add(new User("_(:з」∠)_"));
        list.add(new User("……%￥#￥%#"));
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
    }

    @Override
    protected void initView() {
        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
        listView.setAdapter(adapter);
    }

    @OnClick({R.id.yanzheng_message, R.id.tianjia_haoyou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.yanzheng_message:
                YanzhengMessageActivity.launcher(getContext());
                break;
            case R.id.tianjia_haoyou:
                TianjiaActivity.launcher(getContext());
                break;
        }
    }
}
