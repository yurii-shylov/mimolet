package com.mimolet.android;

import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

public class PurchaseActivity extends Activity {
	  private static final String TAG = "PurchaseActivity";

	
	private static final int BOOK_PRICE = 14;
	private static final int PAGE_PRICE = 1;
	
	ImageView orderCover;
	TextView orderName;
	TextView bindingType;
	TextView coverType;
	TextView paperType;
	TextView printType;
	TextView blockSize;
	TextView pagesCount;
	TextView amountValue;
	TextView additionalPagesValue;
	TextView singleBookPrice;
	TextView additionalPagesPrice;
	TextView overalPrice;
	
	protected Bitmap coverFromURL;
	boolean checker = true;
	
	protected void setBitmap(Bitmap image) {
		this.coverFromURL = image;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_purchase);
	    Intent itemIntent = getIntent();
	    orderCover = (ImageView) findViewById(R.id.purchaseOrderCover);
	    final String imageURL = itemIntent.getStringExtra("image");
	    NotUiThread noUI = new NotUiThread(imageURL);
	    noUI.start();
	    while (checker) {
	    	if (coverFromURL != null) {
	    		orderCover.setImageBitmap(coverFromURL);
	    		checker = false;
	    	}
	    }
//	    (new Thread() {
//	    	@Override
//			public void run() {
//	            try {
//	                InputStream in = new java.net.URL(imageURL).openStream();
//	                coverFromURL = BitmapFactory.decodeStream(in);
//	                orderCover.setImageBitmap(coverFromURL);
//	            } catch (Exception e) {
//	                Log.e(TAG, "Something goes wrong!");
//	                e.printStackTrace();
//	            }
//			}
//	    }).start();
        orderName = (TextView) findViewById(R.id.purchaseOrderName);
		orderName.setText(itemIntent.getStringExtra("orderName"));
		bindingType = (TextView) findViewById(R.id.purchaseBindingValueText);
		bindingType.setText(itemIntent.getStringExtra("binding"));
		coverType = (TextView) findViewById(R.id.purchaseCoverValueText);
		coverType.setText(itemIntent.getStringExtra("cover"));
		paperType = (TextView) findViewById(R.id.purchasePaperValueText);
		paperType.setText(itemIntent.getStringExtra("paper"));
		printType = (TextView) findViewById(R.id.purchasePrintValueText);
		printType.setText(itemIntent.getStringExtra("print"));
		blockSize = (TextView) findViewById(R.id.purchaseBlockSizeValueText);
		blockSize.setText(itemIntent.getStringExtra("blockSize"));
		pagesCount = (TextView) findViewById(R.id.purchasePagesValueTextText);
		pagesCount.setText(itemIntent.getStringExtra("pages"));
		amountValue = (TextView) findViewById(R.id.purchaseAmountValueText);
		additionalPagesValue = (TextView) findViewById(R.id.purchaseAdditionalPagesValueText);
		singleBookPrice = (TextView) findViewById(R.id.purchaseSingleBookPriceValueText);
		additionalPagesPrice = (TextView) findViewById(R.id.purchaseAdditionalPagesPriceText);
		overalPrice = (TextView) findViewById(R.id.purchaseOveralPriceText);
		int additionalPagesValueFromIntent = 0; // Need to resolve!!!
		amountValue.setText("1 шт");
		singleBookPrice.setText(BOOK_PRICE + ".00 $");
		additionalPagesValue.setText(additionalPagesValueFromIntent + " шт");
		additionalPagesPrice.setText(additionalPagesValueFromIntent * PAGE_PRICE + ".00 $");
		overalPrice.setText(BOOK_PRICE + (additionalPagesValueFromIntent * PAGE_PRICE) + ".00 $"); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.purchase, menu);
		return true;
	}

	private class NotUiThread extends Thread {
		String urlPath;
		
		public NotUiThread (String path) {
			this.urlPath = path;
		}
		
		@Override
		public void run() {
            try {
                InputStream in = new java.net.URL(urlPath).openStream();
                coverFromURL = BitmapFactory.decodeStream(in);
                //orderCover.setImageBitmap(coverFromURL);
            } catch (Exception e) {
                Log.e(TAG, "Something goes wrong!");
                checker = false;
                e.printStackTrace();
            }
		}
	}
}
