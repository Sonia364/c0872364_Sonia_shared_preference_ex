package MAD_2124.Android.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import MAD_2124.Android.model.Contact;

public class SharedPreferenceHelper {
    private static final String PREF_NAME = "pref_contacts";
    private static final String KEY_CONTACTS = "contacts";

    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPreferenceHelper(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveContacts(ArrayList<Contact> contacts) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(contacts);
        editor.putString(KEY_CONTACTS, json);
        editor.apply();
    }

    public ArrayList<Contact> getContacts() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_CONTACTS, null);
        Type type = new TypeToken<ArrayList<Contact>>() {}.getType();
        ArrayList<Contact> contacts = gson.fromJson(json, type);
        return contacts;
    }
}