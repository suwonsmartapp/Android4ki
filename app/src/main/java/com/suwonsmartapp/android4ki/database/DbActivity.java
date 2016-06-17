package com.suwonsmartapp.android4ki.database;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.suwonsmartapp.android4ki.R;

public class DbActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private FeedReaderDbHelper mDbHelper;
    private EditText mEntryId;
    private EditText mTitle;
    private EditText mSubtitle;
    private MyCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

        mEntryId = (EditText) findViewById(R.id.COLUMN_NAME_ENTRY_ID);
        mTitle = (EditText) findViewById(R.id.COLUMN_NAME_TITLE);
        mSubtitle = (EditText) findViewById(R.id.COLUMN_NAME_SUBTITLE);

        ListView listView = (ListView) findViewById(R.id.query_list);
        mAdapter = new MyCursorAdapter(this, null);
        listView.setAdapter(mAdapter);

        // 헬퍼 생성
        mDbHelper = FeedReaderDbHelper.getInstance(this);

        findViewById(R.id.insert_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 삽입
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, mEntryId.getText().toString());
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, mTitle.getText().toString());
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, mSubtitle.getText().toString());

                long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,
                        null,
                        values);

                if (newRowId != -1) {
                    Toast.makeText(DbActivity.this, "정상 삽입", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DbActivity.this, "삽입 에러", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, mTitle.getText().toString());
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE, mSubtitle.getText().toString());

                String where = FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + "=?";

                int updatedRow = db.update(FeedReaderContract.FeedEntry.TABLE_NAME,
                        values,
                        where,
                        new String[]{ mEntryId.getText().toString() });

                if (updatedRow != 0) {
                    Toast.makeText(DbActivity.this, "수정 되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DbActivity.this, "수정 에러", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete
                SQLiteDatabase db = mDbHelper.getWritableDatabase();

                String where = FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID + "=?";

                int deletedRow = db.delete(FeedReaderContract.FeedEntry.TABLE_NAME,
                        where,
                        new String[] { mEntryId.getText().toString() });


                if (deletedRow != 0) {
                    Toast.makeText(DbActivity.this, "삭제 되었습니다", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DbActivity.this, "삭제 에러", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.query_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = getContentResolver().query(MyContentProvider.URI,
                        null,
                        null,
                        null,
                        null);
                // cursor 가 처음에 -1 번째를 가리키고 있기 때문에
//                cursor.moveToFirst();

                if (cursor != null) {

                    mAdapter.swapCursor(cursor);
                }
            }
        });


        // Loader 초기화 -> onCreateLoader 호출 됨
        getLoaderManager().initLoader(0, null, this);

    }

    @WorkerThread
    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        // provider 를 통해서 query한 것을
        // CursorLoader 만들어서 리턴
        // 리턴 결과는 onLoadFinished 로 전달 됨
        return new CursorLoader(this, MyContentProvider.URI, null, null, null, null);
    }

    @UiThread
    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
}
