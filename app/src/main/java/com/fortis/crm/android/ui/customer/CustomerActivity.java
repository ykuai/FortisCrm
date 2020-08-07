package com.fortis.crm.android.ui.customer;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.fortis.crm.android.R;
import com.king.view.slidebar.SlideBar;

import java.util.ArrayList;
import java.util.List;

public class CustomerActivity extends AppCompatActivity {

    CustomerViewModel customerViewModel;
    Toolbar myToolbar;
    RecyclerView customerRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        // 初始化界面
        init();
        // 加载数据
        loadData(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 加载数据
        loadData(intent);
    }


    private void loadData(Intent intent) {
        String query = "";
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            query = intent.getStringExtra(SearchManager.QUERY);
        }
        customerViewModel.loadCustomer(query);
    }

    private void init() {
        // 设置标题栏
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("客户列表");
        // 设置客户列表
        customerRecyclerView = findViewById(R.id.customer_list);
        customerRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        CustomerRecyclerViewAdapter customerRecyclerViewAdapter = new CustomerRecyclerViewAdapter();
        customerRecyclerView.setAdapter(customerRecyclerViewAdapter);
        // 设置字母导航栏
        SlideBar slideBar = findViewById(R.id.slide_bar);
        slideBar.setOnTouchLetterChangeListenner((isTouch, letter) -> {
            if (isTouch) {
                int pos = customerRecyclerViewAdapter.getPositionByLetter(letter);
                LinearLayoutManager mLayoutManager = (LinearLayoutManager) customerRecyclerView.getLayoutManager();
                mLayoutManager.scrollToPositionWithOffset(pos, 0);
            }
        });
        // 设置视图模型
        customerViewModel = new ViewModelProvider(this).get(CustomerViewModel.class);
        customerViewModel.getCustomers().observe(this, customerRecyclerViewAdapter::setCustomerList);
        customerViewModel.getFirstLetters().observe(this, slideBar::setLetters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.customer_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }
}