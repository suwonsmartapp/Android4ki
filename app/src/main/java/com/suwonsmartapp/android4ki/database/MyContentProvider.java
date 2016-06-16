package com.suwonsmartapp.android4ki.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class MyContentProvider extends ContentProvider {

    // Creates a UriMatcher object.
    private static final UriMatcher sUriMatcher;

    public static final int ALL = 1;

    public static final int ITEM = 2;
    public static final String AUTHORITY = "com.suwonsmartapp.android4ki.provider";

    public static final Uri URI = Uri.parse("content://" + AUTHORITY + "/" + FeedReaderContract.FeedEntry.TABLE_NAME);

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY, FeedReaderContract.FeedEntry.TABLE_NAME, ALL);
        sUriMatcher.addURI(AUTHORITY, FeedReaderContract.FeedEntry.TABLE_NAME + "/#", ITEM);
    }

    private FeedReaderDbHelper mDbHelper;

    public MyContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case ALL:
                return "vnd.android.cursor.dir/vnd.com.suwonsmartapp.android4ki.provider." + FeedReaderContract.FeedEntry.TABLE_NAME;
            case ITEM:
                return "vnd.android.cursor.item/vnd.com.suwonsmartapp.android4ki.provider." + FeedReaderContract.FeedEntry.TABLE_NAME;
            default:
                return null;
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public boolean onCreate() {
        mDbHelper = FeedReaderDbHelper.getInstance(getContext());
        return mDbHelper != null;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (sUriMatcher.match(uri)) {
            case ALL:
                break;
            case ITEM:
                selection = "_id=" + ContentUris.parseId(uri);
                selectionArgs = null;
                break;
            case UriMatcher.NO_MATCH:
                return null;
        }

        Cursor cursor = mDbHelper.getReadableDatabase().query(FeedReaderContract.FeedEntry.TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        // 데이터 변경이 감지 되도록 resolver 에 알림
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
