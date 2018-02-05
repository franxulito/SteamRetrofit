package net.azarquiel.steamretrofixrx.api

/**
 * Created by pacopulido on 29/1/18.
 */
import net.azarquiel.steamretrofixrx.model.Games
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import rx.Observable

/**
 * Created by pacopulido on 04/2/18.
 */
interface SteamApiService {

    @GET("ISteamApps/GetAppList/v0002/")
    fun getData(): Observable<Games>

    companion object {
        fun create(): SteamApiService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://api.steampowered.com/")
                    .build()
            return retrofit.create(SteamApiService::class.java)
        }
    }
}
