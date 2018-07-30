package com.mmdteam.mgithub.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

public class StringUtils {

    public static boolean isBlank(@Nullable String str) {
        return str == null || str.trim().equals("");
    }


    public static String listToString(@NonNull List<String> list, @NonNull String separator) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (list.size() == 0 || isBlank(separator)) {
            return stringBuilder.toString();
        }
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
            if (i != list.size() - 1) {
                stringBuilder.append(separator);
            }
        }
        return stringBuilder.toString();
    }


}
