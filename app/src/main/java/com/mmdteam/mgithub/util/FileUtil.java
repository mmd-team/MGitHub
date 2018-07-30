package com.mmdteam.mgithub.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.File;

/**
 * 文件工具类
 */
public class FileUtil {

    private static final String AUDIO_CACHE_DIR_NAME = "audio";
    private static final String SIGN_IMAGE_CACHE_DIR_NAME = "sign_image";
    private static final String HTTP_CACHE_DIR_NAME = "http_response";

    public static File getCacheDir(@NonNull Context context, @NonNull String dirName) {

        File rootDir = context.getExternalCacheDir();
        File cacheDir = new File(rootDir, dirName);
        if (!cacheDir.exists()) {
            cacheDir.mkdir();
        }
        return cacheDir;

    }

    /**
     * 获取网络请求缓存文件夹
     *
     * @param context 上下文
     * @return 网络请求缓存文件夹
     */
    public static File getHttpImageCacheDir(@NonNull Context context) {
        return getCacheDir(context, HTTP_CACHE_DIR_NAME);
    }

}
