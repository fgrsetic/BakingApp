package com.franjo.android.bakingapp.data.di;

import com.franjo.android.bakingapp.BuildConfig;
import com.franjo.android.bakingapp.data.network.Endpoint;
import com.franjo.android.bakingapp.data.network.RecipeApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetworkModule {

    @Provides
    @Singleton
    static Gson provideGson() {
        return new GsonBuilder()
                .setLenient() // Retrofit was marking malformed urls
                .create();
    }

    // OkHttp logging messages -> verbose logcat
    @Provides
    @Singleton
    static HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        logger.setLevel(HttpLoggingInterceptor.Level.BODY);
        return logger;
    }

    @Provides
    @Singleton
    static OkHttpClient provideOkhttp() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(new HttpLoggingInterceptor());
        }
        return builder.build();
    }


    @Provides
    @Singleton
    static Retrofit provideRetrofit(OkHttpClient client, Gson gson) {
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(Endpoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    static RecipeApiService provideService(Retrofit retrofit) {
        return retrofit.create(RecipeApiService.class);
    }

}
