package ren.com.dazhongdianping.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ren.com.dazhongdianping.R;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ptrlv_main)
    PullToRefreshListView ptrListView;

    ListView listView;
    List<String> datas;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initListView();

    }



    private void initListView() {
        listView = ptrListView.getRefreshableView();
        datas = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datas);
        listView.setAdapter(adapter);
        //添加下拉送售后刷新
        ptrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.add(0,"hfdgs");
                        adapter.notifyDataSetChanged();
                        ptrListView.onRefreshComplete();
                    }
                },1500);
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
    private void refresh() {
        datas.add("aa");
        datas.add("bb");
        datas.add("cc");
        adapter.notifyDataSetChanged();
    }
}
