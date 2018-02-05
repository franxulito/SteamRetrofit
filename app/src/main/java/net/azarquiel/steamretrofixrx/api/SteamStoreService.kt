package net.azarquiel.steamretrofixrx.api

import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 * Created by pacopulido on 04/2/18.
 */

// Esta interface es un poco especial. Como el Json que obtenemos
// no nos gusta y deseamos quitarle contenido:
/**quitamos GsonConverterFactory*/// para que no actúe GSON
// y como queremos obtener el Json en formato String
// el método getdata devolverá un
/**ResposeBody*///Observable

interface SteamStoreService {

    @GET("appdetails/") // ?appids=appid
    fun getData(@Query("appids") appid: String): Observable<ResponseBody>

    companion object {
        fun create(): SteamStoreService {
            val retrofit = Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("http://store.steampowered.com/api/")
                    .build()
            return retrofit.create(SteamStoreService::class.java)
        }
    }

}