package com.trade.rrenji.utils.reservoir;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;

/**
 * The main reservoir class.
 */
public class Reservoir {

    private static SimpleDiskCache cache;

    private static File cacheDir;

    private static boolean initialised = false;

    private static Gson sGson;

    /**
     * Initialize Reservoir
     *
     * @param context context.
     * @param maxSize the maximum size in bytes.
     * @throws IOException thrown if the cache cannot be initialized.
     */
    public static synchronized void init(final Context context, final long maxSize)
            throws IOException {
        init(context, maxSize, new Gson());
    }

    /**
     * Initialize Reservoir
     *
     * @param context context.
     * @param maxSize the maximum size in bytes.
     * @param gson    the Gson instance.
     * @throws IOException thrown if the cache cannot be initialized.
     */
    public static synchronized void init(final Context context, final long maxSize, final Gson gson)
            throws IOException {

        //Create a directory inside the application specific cache directory. This is where all
        // the key-value pairs will be stored.
        cacheDir = new File(context.getCacheDir() + "/Reservoir");
        createCache(cacheDir, maxSize);
        sGson = gson;
        initialised = true;
    }

    /**
     * Checks if init method has been called and throws an IllegalStateException if it hasn't.
     *
     * @throws IllegalStateException thrown if init method hasn't been called.
     */
    private static void failIfNotInitialised() {
        if (!initialised) {
            throw new IllegalStateException("Init hasn't been called! You need to initialise " +
                    "Reservoir before you call any other methods.");
        }
    }

    private static boolean failIfNotInitialisedAsync(ReservoirCallback callback) {
        if (!initialised && callback != null) {
            callback.onFailure(new IllegalStateException("Init hasn't been called! You need to initialise " +
                    "Reservoir before you call any other methods."));
        }

        return !initialised;
    }

    /**
     * Creates the cache.
     *
     * @param cacheDir the directory where the cache is to be created.
     * @param maxSize  the maximum cache size in bytes.
     * @throws IOException thrown if the cache cannot be created.
     */
    private static synchronized void createCache(final File cacheDir, final long maxSize) throws
            IOException {
        boolean success = true;
        if (!cacheDir.exists()) {
            success = cacheDir.mkdir();
        }
        if (!success) {
            throw new IOException("Failed to create cache directory!");
        }
        cache = SimpleDiskCache.open(cacheDir, 1, maxSize);
    }

    /**
     * Check if an object with the given key exists in the Reservoir.
     *
     * @param key the key string.
     * @return true if object with given key exists.
     * @throws IllegalStateException thrown if init method hasn't been called.
     * @throws IOException           thrown if cache cannot be accessed.
     */
    public static boolean contains(final String key) throws IOException {
        failIfNotInitialised();
        return cache.contains(key);
    }

    /**
     * Put an object into Reservoir with the given key. This a blocking IO operation. Previously
     * stored object with the same
     * key (if any) will be overwritten.
     *
     * @param key    the key string.
     * @param object the object to be stored.
     * @throws IllegalStateException thrown if init method hasn't been called.
     * @throws IOException           thrown if cache cannot be accessed.
     */
    public static void put(final String key, final Object object) throws IOException {
        failIfNotInitialised();
        String json = sGson.toJson(object);
        cache.put(key, json);
    }

    /**
     * Put an object into Reservoir with the given key asynchronously. Previously
     * stored object with the same
     * key (if any) will be overwritten.
     *
     * @param key      the key string.
     * @param object   the object to be stored.
     * @param callback a callback of type {@link ReservoirCallback}
     *                 which is called upon completion.
     * @throws IllegalStateException thrown if init method hasn't been called.
     */
    public static void putAsync(final String key, final Object object,
                                final ReservoirCallback callback) {
        if (failIfNotInitialisedAsync(callback)) return;
        new PutTask(key, object, callback).execute();
    }

    /**
     * Get an object from Reservoir with the given key. This a blocking IO operation.
     *
     * @param <T>      the type of the object to get.
     * @param key      the key string.
     * @param classOfT the class type of the expected return object.
     * @return the object of the given type if it exists.
     * @throws IllegalStateException thrown if init method hasn't been called.
     * @throws IOException           thrown if cache cannot be accessed.
     */
    public static <T> T get(final String key, final Class<T> classOfT) throws IOException {
        failIfNotInitialised();
        String json = cache.getString(key).getString();
        T value = sGson.fromJson(json, classOfT);
        if (value == null)
            throw new NullPointerException();
        return value;
    }

    /**
     * Get an object from Reservoir with the given key. This a blocking IO operation.
     *
     * @param <T>     the type of the object to get.
     * @param key     the key string.
     * @param typeOfT the type of the expected return object.
     * @return the object of the given type if it exists.
     * @throws IllegalStateException thrown if init method hasn't been called.
     * @throws IOException           thrown if cache cannot be accessed.
     */
    public static <T> T get(final String key, final Type typeOfT) throws IOException {
        failIfNotInitialised();
        String json = cache.getString(key).getString();
        T value = sGson.fromJson(json, typeOfT);
        if (value == null)
            throw new NullPointerException();
        return value;
    }

    /**
     * Get an object from Reservoir with the given key asynchronously.
     *
     * @param <T>      the type of the object to get.
     * @param key      the key string.
     * @param classOfT the class type of the expected return object.
     * @param callback a callback of type {@link ReservoirCallback}
     *                 which is called upon completion.
     * @throws IllegalStateException thrown if init method hasn't been called.
     */
    public static <T> void getAsync(final String key, final Class<T> classOfT,
                                    final ReservoirCallback<T> callback) {
        if (failIfNotInitialisedAsync(callback)) return;
        new GetTask<>(key, classOfT, callback).execute();
    }

    /**
     * Get an object from Reservoir with the given key asynchronously.
     *
     * @param <T>      the type of the object to get.
     * @param key      the key string.
     * @param typeOfT  the type of the expected return object.
     * @param callback a callback of type {@link ReservoirCallback}
     *                 which is called upon completion.
     * @throws IllegalStateException thrown if init method hasn't been called.
     */
    public static <T> void getAsync(final String key, final Type typeOfT,
                                    final ReservoirCallback<T> callback) {
        if (failIfNotInitialisedAsync(callback)) return;
        new GetTask<>(key, typeOfT, callback).execute();
    }

    /**
     * Delete an object from Reservoir with the given key. This a blocking IO operation. Previously
     * stored object with the same
     * key (if any) will be deleted.
     *
     * @param key the key string.
     * @throws IllegalStateException thrown if init method hasn't been called.
     * @throws IOException           thrown if cache cannot be accessed.
     */
    public static void delete(final String key) throws IOException {
        failIfNotInitialised();
        cache.delete(key);
    }

    /**
     * Delete an object into Reservoir with the given key asynchronously. Previously
     * stored object with the same
     * key (if any) will be deleted.
     *
     * @param key      the key string.
     * @param callback a callback of type {@link ReservoirCallback}
     *                 which is called upon completion.
     * @throws IllegalStateException thrown if init method hasn't been called.
     */
    public static void deleteAsync(final String key, final ReservoirCallback callback) {
        if (failIfNotInitialisedAsync(callback)) return;
        new DeleteTask(key, callback).execute();
    }

    /**
     * Clears the cache. Deletes all the stored key-value pairs synchronously.
     *
     * @throws IllegalStateException thrown if init method hasn't been called.
     * @throws IOException           thrown if cache cannot be accessed.
     */
    public static void clear() throws IOException {
        failIfNotInitialised();
        long maxSize = cache.getMaxSize();
        cache.destroy();
        createCache(cacheDir, maxSize);
    }

    /**
     * Clears the cache. Deletes all the stored key-value pairs asynchronously.
     *
     * @param callback a callback of type {@link ReservoirCallback}
     *                 which is called upon completion.
     * @throws IllegalStateException thrown if init method hasn't been called.
     */
    public static void clearAsync(final ReservoirCallback callback) {
        if (failIfNotInitialisedAsync(callback)) return;
        new ClearTask(callback).execute();
    }

    /**
     * Returns the number of bytes being used currently by the cache.
     *
     * @throws IllegalStateException thrown if init method hasn't been called.
     * @throws IOException           thrown if cache cannot be accessed.
     */
    public static long bytesUsed() throws IOException {
        failIfNotInitialised();
        return cache.bytesUsed();
    }

    /**
     * AsyncTask to perform put operation in a background thread.
     */
    private static class PutTask extends AsyncTask<Void, Void, Void> {

        private final String key;
        private Exception e;
        private final ReservoirCallback callback;
        final Object object;

        private PutTask(String key, Object object, ReservoirCallback callback) {
            this.key = key;
            this.callback = callback;
            this.object = object;
            this.e = null;
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                String json = sGson.toJson(object);
                cache.put(key, json);
            } catch (Exception e) {
                this.e = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                if (e == null) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure(e);
                }
            }
        }

    }

    /**
     * AsyncTask to perform get operation in a background thread.
     */
    private static class GetTask<T> extends AsyncTask<Void, Void, T> {

        private final String key;
        private final ReservoirCallback callback;
        private final Class<T> classOfT;
        private final Type typeOfT;
        private Exception e;

        private GetTask(String key, Class<T> classOfT, ReservoirCallback callback) {
            this.key = key;
            this.callback = callback;
            this.classOfT = classOfT;
            this.typeOfT = null;
            this.e = null;
        }

        private GetTask(String key, Type typeOfT, ReservoirCallback callback) {
            this.key = key;
            this.callback = callback;
            this.classOfT = null;
            this.typeOfT = typeOfT;
            this.e = null;
        }

        @Override
        protected T doInBackground(Void... params) {
            try {
                String json = cache.getString(key).getString();
                T value = sGson.fromJson(json, classOfT != null ? classOfT : typeOfT);
                if (value == null) {
                    throw new NullPointerException();
                }
                return value;
            } catch (Exception e) {
                this.e = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(T object) {
            if (callback != null) {
                if (e == null) {
                    callback.onSuccess(object);
                } else {
                    callback.onFailure(e);
                }
            }
        }

    }

    /**
     * AsyncTask to perform delete operation in a background thread.
     */
    private static class DeleteTask extends AsyncTask<Void, Void, Void> {

        private final String key;
        private Exception e;
        private final ReservoirCallback callback;

        private DeleteTask(String key, ReservoirCallback callback) {
            this.key = key;
            this.callback = callback;
            this.e = null;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                cache.delete(key);
            } catch (Exception e) {
                this.e = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                if (e == null) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure(e);
                }
            }
        }

    }

    /**
     * AsyncTask to perform clear operation in a background thread.
     */
    private static class ClearTask extends AsyncTask<Void, Void, Void> {

        private Exception e;
        private final ReservoirCallback callback;

        private ClearTask(ReservoirCallback callback) {
            this.callback = callback;
            this.e = null;
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                clear();
            } catch (Exception e) {
                this.e = e;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (callback != null) {
                if (e == null) {
                    callback.onSuccess(null);
                } else {
                    callback.onFailure(e);
                }
            }
        }

    }

}
