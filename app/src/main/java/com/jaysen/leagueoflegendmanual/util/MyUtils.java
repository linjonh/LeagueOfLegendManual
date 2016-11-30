package com.jaysen.leagueoflegendmanual.util;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by lin on 2016/11/30.
 */

public class MyUtils {
    /**
     * @param items
     * @return
     * @throws JSONException
     */
    public static String getAppandedString(JSONArray items) throws JSONException {
        StringBuilder itemsStr = new StringBuilder();
        for (int j = 0; j < items.length(); j++) {
            itemsStr.append(items.getString(j));
            if (j < items.length() - 1) {
                itemsStr.append("$");
            }
        }
        return itemsStr.toString();
    }
}
