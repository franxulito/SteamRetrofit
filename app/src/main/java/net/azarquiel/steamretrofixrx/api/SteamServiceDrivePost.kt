package net.azarquiel.steamretrofixrx.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import rx.Observable

/**
 * Created by pacopulido on 04/2/18.
 */

// Esta interface también es especial.
// Resulta que cuando se haga el Post para subir los datos
// la respuesta debería ser también un json.
// Pero como se trata del formulario de Drive, cuando termina
// sale una página informando que se ha enviado la respuesta
// y que si queremos enviar otra.
// Es por ello que tenemos que indicar la Gson
// que la salida va a ser un json mal formado con
/** setLenient */ //pero que no nos importa
// y que sea tolerante con nosotros los pobres developers.
// Como podemos observar, el método nos devuelve un
/** String Observable*/

interface SteamServiceDrivePost {

    @FormUrlEncoded
    @POST("formResponse")
    fun saveGame(
            @Field("entry.419496072") id: String,
            @Field("entry.530586549") name: String,
            @Field("entry.1649109160") description: String,
            @Field("entry.1379836168") image: String,
            @Field("entry.2053329122") link: String
    ): Observable<String>

    // común a todas las instancias de esa clase pues será un singleton
    companion object {
        fun create(): SteamServiceDrivePost {

            // setLenient en el Gson para que nos dé error la request
            val gson = GsonBuilder()
                    .setLenient()
                    .create()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl("https://docs.google.com/forms/d/e/1FAIpQLSekqnqr1A94IkDc2jaZ6188LfY8GI-wUasZI8LgTaoZhWlamA/")
                    .build()

            return retrofit.create(SteamServiceDrivePost::class.java)
        }
    }
}