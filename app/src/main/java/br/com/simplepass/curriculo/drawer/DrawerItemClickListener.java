package br.com.simplepass.curriculo.drawer;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.provider.SyncStateContract;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import br.com.simplepass.curriculo.R;
import br.com.simplepass.curriculo.activity.LoginActivity;
import br.com.simplepass.curriculo.activity.RxActivity;
import br.com.simplepass.curriculo.utils.Constants;


/* Listener que é chamado quando usuário perta botão do drawer */
public class DrawerItemClickListener implements NavigationView.OnNavigationItemSelectedListener{
    private Activity mActivity;
    private DrawerLayout mDrawerLayout;

    public DrawerItemClickListener(Activity activity, DrawerLayout drawerLayout){
        mActivity = activity;
        mDrawerLayout = drawerLayout;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mDrawerLayout.closeDrawers();

        switch (item.getItemId()){
            case R.id.drawer_item_share:
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, mActivity.getString(R.string.share_message));
                shareIntent.setType("text/plain");
                mActivity.startActivity(shareIntent);
                return true;
            case R.id.drawer_item_leave:
                SharedPreferences preferences =
                        mActivity.getSharedPreferences(Constants.PREFS_NAME, Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean(Constants.PREFS_KEYS.LOGED_ID, false);
                editor.apply();

                Intent intentLogin = new Intent(mActivity, LoginActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                            mActivity);
                    mActivity.startActivity(intentLogin, activityOptions.toBundle());
                } else {
                    mActivity.startActivity(intentLogin);
                }

                mActivity.finish();
                return true;
            case R.id.drawer_item_rx:
                Intent intentRx = new Intent(mActivity, RxActivity.class);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(
                            mActivity);
                    mActivity.startActivity(intentRx, activityOptions.toBundle());
                } else {
                    mActivity.startActivity(intentRx);
                }
            return true;
            default:
                return true;
        }
    }
}
