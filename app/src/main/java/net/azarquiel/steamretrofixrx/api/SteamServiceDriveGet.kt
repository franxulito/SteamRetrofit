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
// no nos gusta y deseamos quitarle la mi..da:
/**quitamos GsonConverterFactory*/// para que no actúe GSON
// y como queremos obtener el Json en formato String
// el método getdata devolverá un
/**ResposeBody*///Observable

interface SteamServiceDriveGet {
    @GET("spreadsheets/d/115nOOJtNAkTNKHd0SYxr5jHTIOCAf0iueVmPB33zijY/gviz/tq")
    fun getData(): Observable<ResponseBody>

    companion object {
        fun create(): SteamServiceDriveGet {
            val retrofit = Retrofit.Builder()
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("https://docs.google.com/")
                    .build()
            return retrofit.create(SteamServiceDriveGet::class.java)
        }
    }

}