package com.mimolet.android.global;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;
import entity.PhotoData;

public class GlobalVariables extends Application {
  private Integer ownerID;

  public Integer getOwnerID() {
    return ownerID;
  }

  public void setOwnerID(Integer ownerID) {
    this.ownerID = ownerID;
  }
  
  public static ArrayList<Integer> imagesList;
  public static HashMap<Integer, PhotoData> imagesListData;
  public static Bitmap previewImageBitmap;
  
  public static final String BUNDLE_ORDER = "ORDER";
  
  public static final String MIMOLET_FOLDER = Environment.getExternalStorageDirectory().toString() + File.separator
      + "mimolet" + File.separator;
  public static final String IMAGE_FOLDER = MIMOLET_FOLDER + ".mimolet_imagies" + File.separator;
  public static final String PREVIEW_FOLDER = MIMOLET_FOLDER + ".mimolet_previews" + File.separator;
  public static final String AUTH_DATA_FILE = "aset.dat";
  
  public static final String[] bindingType = new String[]{"?!?!", "На скобе"};
  public static final String[] coverType = new String[]{"?!?!", "Мягкая"};
  public static final String[] paperType = new String[]{"?!?!", "Качественная"};
  public static final String[] printType = new String[]{"?!?!", "Цифровая"};
  public static final String[] blockSizeType = new String[]{"?!?!", "20x20"};  
  
}
