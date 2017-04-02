package maars.androidschoolwiki;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
    TextView messageView;
    NotificationCompat.Builder notification;
    private static final int uniqueId = 322321;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Text view
        messageView = (TextView) findViewById(R.id.message_view);

        // Menus right top
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        // Bottom bar events
        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        onCreateBottomMenu(bottomBar);

        // Notification
        notification = new NotificationCompat.Builder(this);
        notification.setAutoCancel(true);

        messageView.setText("Updates");
        notification.setSmallIcon(R.drawable.ic_friends);
        notification.setTicker("Alerts");
        notification.setWhen(System.currentTimeMillis());
        notification.setContentTitle("Events");
        notification.setContentText("Suresh, We have Annual celebration on Sunday");

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void onCreateBottomMenu(BottomBar bottomBar) {
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_updates) {
                    if (notification != null) {
                        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        notificationManager.notify(uniqueId, notification.build());
                    }
                } else if (tabId == R.id.tab_bus)
                    messageView.setText("Bus location");
                else if (tabId == R.id.tab_info)
                    messageView.setText("Student information");
                else
                    messageView.setText("Search the data");
            }
        });
    }
}