package com.lcszulpo.oladoutor.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import com.lcszulpo.oladoutor.R;
import com.lcszulpo.oladoutor.model.Locale;

public class LocaleFormActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locale_form);

        initToolBar();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Locale locale =
                (Locale) getIntent().getSerializableExtra(LocaleListFragment.FIELD_LOCALE);
        if(locale != null) {
            LocaleFormFragment localeFormFragment = (LocaleFormFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_locale_form);
            localeFormFragment.setLocale(locale);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_locale_form, menu);

        return true;
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        toolbar.setTitle(getString(R.string.title_locale_form));

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

}
