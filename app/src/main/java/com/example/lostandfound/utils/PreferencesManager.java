package com.example.lostandfound.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.lostandfound.models.Item;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PreferencesManager {

    private static final String PREF_NAME       = "LostFoundPrefs";
    private static final String KEY_ITEMS        = "items";
    private static final String KEY_USER_NAME    = "user_name";
    private static final String KEY_USER_PHONE   = "user_phone";
    private static final String KEY_USER_ID      = "user_id";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";

    private final SharedPreferences prefs;
    private final Gson gson;

    public PreferencesManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson  = new Gson();
    }

    // ── Items ──────────────────────────────────────────────

    public List<Item> getAllItems() {
        String json = prefs.getString(KEY_ITEMS, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<Item>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public void saveItem(Item item) {
        List<Item> items = getAllItems();
        items.add(0, item);   // newest first
        prefs.edit().putString(KEY_ITEMS, gson.toJson(items)).apply();
    }

    public void updateItem(Item updatedItem) {
        List<Item> items = getAllItems();
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(updatedItem.getId())) {
                items.set(i, updatedItem);
                break;
            }
        }
        prefs.edit().putString(KEY_ITEMS, gson.toJson(items)).apply();
    }

    public void deleteItem(String itemId) {
        List<Item> items = getAllItems();
        items.removeIf(item -> item.getId().equals(itemId));
        prefs.edit().putString(KEY_ITEMS, gson.toJson(items)).apply();
    }

    public List<Item> getItemsByType(String type) {
        List<Item> all = getAllItems();
        List<Item> filtered = new ArrayList<>();
        for (Item item : all) {
            if (item.getType().equalsIgnoreCase(type)) filtered.add(item);
        }
        return filtered;
    }

    public List<Item> searchItems(String query, String category, String type) {
        List<Item> all = getAllItems();
        List<Item> result = new ArrayList<>();
        for (Item item : all) {
            boolean matchesQuery    = query.isEmpty() ||
                    item.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                    item.getDescription().toLowerCase().contains(query.toLowerCase());
            boolean matchesCategory = category.equals("All") ||
                    item.getCategory().equalsIgnoreCase(category);
            boolean matchesType     = type.equals("All") ||
                    item.getType().equalsIgnoreCase(type);
            if (matchesQuery && matchesCategory && matchesType) result.add(item);
        }
        return result;
    }

    public List<Item> getItemsByUser(String userId) {
        List<Item> all = getAllItems();
        List<Item> result = new ArrayList<>();
        for (Item item : all) {
            if (item.getPostedByUserId().equals(userId)) result.add(item);
        }
        return result;
    }

    // ── User profile ───────────────────────────────────────

    public void saveUserProfile(String userId, String name, String phone) {
        prefs.edit()
                .putString(KEY_USER_ID, userId)
                .putString(KEY_USER_NAME, name)
                .putString(KEY_USER_PHONE, phone)
                .putBoolean(KEY_IS_LOGGED_IN, true)
                .apply();
    }

    public String getUserId()    { return prefs.getString(KEY_USER_ID, ""); }
    public String getUserName()  { return prefs.getString(KEY_USER_NAME, ""); }
    public String getUserPhone() { return prefs.getString(KEY_USER_PHONE, ""); }
    public boolean isLoggedIn()  { return prefs.getBoolean(KEY_IS_LOGGED_IN, false); }

    public void logout() {
        prefs.edit()
                .remove(KEY_USER_ID)
                .remove(KEY_USER_NAME)
                .remove(KEY_USER_PHONE)
                .putBoolean(KEY_IS_LOGGED_IN, false)
                .apply();
    }
}