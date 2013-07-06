package com.mimolet.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageButton;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.mimolet.android.fragment.AddPhotoFragment;
import com.mimolet.android.fragment.ChooseStyleFragment;

public class AddBookActivity extends SherlockFragmentActivity {
  private static final String TAG = "AddBookActivity";

  /* These numbers should correlate with tab order */
  private static final int CHOOSE_STYLE_TAB = 1;
  private static final int ADD_PHOTO_TAB = 2;
  private static final int STYLE_PAGE_TAB = 3;
  private static final int PREVIEW_TAB = 4;

  private int selectedTab;
  
  private final BottomMenu bottomMenu = new BottomMenu();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_book);
    final ActionBar actionbar = getSupportActionBar();
    actionbar.setDisplayShowTitleEnabled(false);
    actionbar.setDisplayShowHomeEnabled(false);
    actionbar.setDisplayShowTitleEnabled(true);
    actionbar.setTitle(R.string.creating_book);

    chooseStyleTabClick(null);
    final Fragment addPhotoFragment = new AddPhotoFragment();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    final MenuInflater inflater = getSupportMenuInflater();
    inflater.inflate(R.menu.add_book, menu);
    return super.onCreateOptionsMenu(menu);
  }

  private boolean isTabAccessible(int tabIndex) {
    return selectedTab == tabIndex - 1 || selectedTab > tabIndex;
  }

  private void switchFragment(Fragment fragment) {
    final FragmentTransaction fragmentTransaction = getSupportFragmentManager()
        .beginTransaction();
    fragmentTransaction.replace(R.id.fragment_container, fragment);
    fragmentTransaction.commit();
  }

  public void chooseStyleTabClick(View view) {
    if (isTabAccessible(CHOOSE_STYLE_TAB)) {
      selectedTab = CHOOSE_STYLE_TAB;
      final ImageButton chooseStyleTab = (ImageButton) findViewById(R.id.chooseStyleTab);
      chooseStyleTab
          .setBackgroundResource(R.drawable.choose_style_tab_selected);
      final ImageButton addPhotoTab = (ImageButton) findViewById(R.id.addPhotoTab);
      addPhotoTab.setBackgroundResource(R.drawable.add_photo_tab);
      final ImageButton stylePageTab = (ImageButton) findViewById(R.id.stylePageTab);
      stylePageTab.setBackgroundResource(R.drawable.style_page_tab);
      final ImageButton previewTab = (ImageButton) findViewById(R.id.previewTab);
      previewTab.setBackgroundResource(R.drawable.preview_tab);

      switchFragment(new ChooseStyleFragment());
    }
  }

  public void addPhotoTabClick(View view) {
    if (isTabAccessible(ADD_PHOTO_TAB)) {
      selectedTab = ADD_PHOTO_TAB;
      final ImageButton chooseStyleTab = (ImageButton) findViewById(R.id.chooseStyleTab);
      chooseStyleTab
          .setBackgroundResource(R.drawable.choose_style_tab_done);
      final ImageButton addPhotoTab = (ImageButton) findViewById(R.id.addPhotoTab);
      addPhotoTab
          .setBackgroundResource(R.drawable.add_photo_tab_selected);
      final ImageButton stylePageTab = (ImageButton) findViewById(R.id.stylePageTab);
      stylePageTab.setBackgroundResource(R.drawable.style_page_tab);
      final ImageButton previewTab = (ImageButton) findViewById(R.id.previewTab);
      previewTab.setBackgroundResource(R.drawable.preview_tab);

      switchFragment(new AddPhotoFragment());
    }
  }

  public void stylePageTabClick(View view) {
    if (isTabAccessible(STYLE_PAGE_TAB)) {
      selectedTab = STYLE_PAGE_TAB;
      final ImageButton chooseStyleTab = (ImageButton) findViewById(R.id.chooseStyleTab);
      chooseStyleTab
          .setBackgroundResource(R.drawable.choose_style_tab_done);
      final ImageButton addPhotoTab = (ImageButton) findViewById(R.id.addPhotoTab);
      addPhotoTab.setBackgroundResource(R.drawable.add_photo_tab_done);
      final ImageButton stylePageTab = (ImageButton) findViewById(R.id.stylePageTab);
      stylePageTab
          .setBackgroundResource(R.drawable.style_page_tab_selected);
      final ImageButton previewTab = (ImageButton) findViewById(R.id.previewTab);
      previewTab.setBackgroundResource(R.drawable.preview_tab);
    }
  }

  public void previewTabClick(View view) {
    if (isTabAccessible(PREVIEW_TAB)) {
      selectedTab = PREVIEW_TAB;
      final ImageButton chooseStyleTab = (ImageButton) findViewById(R.id.chooseStyleTab);
      chooseStyleTab
          .setBackgroundResource(R.drawable.choose_style_tab_done);
      final ImageButton addPhotoTab = (ImageButton) findViewById(R.id.addPhotoTab);
      addPhotoTab.setBackgroundResource(R.drawable.add_photo_tab_done);
      final ImageButton stylePageTab = (ImageButton) findViewById(R.id.stylePageTab);
      stylePageTab.setBackgroundResource(R.drawable.style_page_tab_done);
      final ImageButton previewTab = (ImageButton) findViewById(R.id.previewTab);
      previewTab.setBackgroundResource(R.drawable.preview_tab_selected);
    }
  }

  public void myOrders(View view) {
    bottomMenu.openMyOrders(this);
  }

  public void createBook(View view) {
    // already here, do nothing
  }

  public void paidOrders(View view) {
    bottomMenu.openMyPaidOrders(this);
  }
}
