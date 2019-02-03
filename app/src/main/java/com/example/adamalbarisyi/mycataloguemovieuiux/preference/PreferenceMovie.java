package com.example.adamalbarisyi.mycataloguemovieuiux.preference;

import android.content.Intent;
import android.provider.Settings;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.preference.Preference;
import android.widget.Toast;

import com.example.adamalbarisyi.mycataloguemovieuiux.R;
import com.example.adamalbarisyi.mycataloguemovieuiux.notifications.AlarmReceiver;
import com.example.adamalbarisyi.mycataloguemovieuiux.notifications.TaskScheduler;


public class PreferenceMovie extends PreferenceFragment implements Preference.OnPreferenceClickListener,Preference.OnPreferenceChangeListener {

    private  String remainder_daily,remainder_upcoming,setting_locale;
    private TaskScheduler taskScheduler;
    private AlarmReceiver alarmReceiver = new AlarmReceiver();

    public PreferenceMovie() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.movie_preference);
        remainder_daily = getString(R.string.key_daily_reminder);
        remainder_upcoming = getString(R.string.key_upcoming_reminder);
        setting_locale = getString(R.string.key_setting_locale);

        findPreference(remainder_daily).setOnPreferenceChangeListener(this);
        findPreference(remainder_upcoming).setOnPreferenceChangeListener(this);
        findPreference(setting_locale).setOnPreferenceClickListener(this);

        taskScheduler = new TaskScheduler(getActivity());
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object o) {
        String key = preference.getKey();
        boolean isOn = (boolean) o;
        if (key.equals(remainder_daily)){
            if (isOn){
                alarmReceiver.setRepeatingAlarm(getActivity(),"07:00");
            }else {
                alarmReceiver.cancelAlarm(getActivity());
            }
            Toast.makeText(getActivity(), getString(R.string.daily_reminder_title) + " " +(isOn ? getString(R.string.activated) : getString(R.string.deactivated)), Toast.LENGTH_SHORT).show();
            return true;
        }
        if (key.equals(remainder_upcoming)){
            if (isOn){
                taskScheduler.createPeriodicTask();
            }else taskScheduler.cancelPeriodicTask();
            Toast.makeText(getActivity(), getString(R.string.upcoming_reminder_title) + " " + (isOn ? getActivity().getString(R.string.activated) : getString(R.string.deactivated)), Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        if (key.equals(setting_locale)){
            try {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
            return true;
        }
        return false;
    }
}
