package com.codepath.android.booksearch.activities;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.ShareActionProvider;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.models.Book;

import org.parceler.Parcels;

import java.lang.annotation.Target;

public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivBookCover;
    private TextView tvTitle;
    private TextView tvAuthor;
    Book book;
    private ShareActionProvider miShareAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        // Fetch views
        ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);

        // unwrap book passed in via Intent, use simple name as key
        book = Parcels.unwrap(getIntent().getParcelableExtra(Book.class.getSimpleName()));

        Toolbar toolbar = findViewById(R.id.toolbar2); // or getActionBar();
        setSupportActionBar(toolbar);

        toolbar.setTitle(book.getTitle()); // set the top title

        // Use book object to populate data into views
        Glide.with(this)
                .load(book.getCoverUrl())
                /*.listener(new RequestListener<Drawable>() {
                              @Override
                              public boolean onException(Exception e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                  return false;
                              }

                              @Override
                              public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                  prepareShareIntent(((BitmapDrawable) resource).getBitmap());
                                  attachShareIntentAction();
                                  // Let Glide handle resource load
                                  return false;
                              }
                          }
                )*/
                .into(ivBookCover);

        tvTitle.setText(book.getTitle());
        tvAuthor.setText(book.getAuthor());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);

        // Locate MenuItem with ShareActionProvider
        MenuItem item = menu.findItem(R.id.menu_item_share);
        // Fetch reference to the share action provider
        miShareAction = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        // Return true to display menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
