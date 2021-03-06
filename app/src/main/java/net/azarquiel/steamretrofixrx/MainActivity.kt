package net.azarquiel.steamretrofixrx

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import net.azarquiel.steamretrofixrx.R.id.progressBar
import net.azarquiel.steamretrofixrx.model.Games
import net.azarquiel.steamretrofixrx.api.SteamApiService
import net.azarquiel.steamretrofixrx.api.SteamServiceDriveGet
import net.azarquiel.steamretrofixrx.api.SteamServiceDrivePost
import net.azarquiel.steamretrofixrx.api.SteamStoreService
import net.azarquiel.steamretrofixrx.model.DriveResponse
import net.azarquiel.steamretrofixrx.model.GameDrive
import net.azarquiel.steamretrofixrx.model.GameStore
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by pacopulido on 04/2/18.
 */

class MainActivity : AppCompatActivity() {
    companion object {
        val TAG = "**Pacoo**"
    }

    private lateinit var games:Games
    private lateinit var gamesDrive: ArrayList<GameDrive>

    private val steamApiService by lazy {
        SteamApiService.create()
    }
    private val steamStoreService by lazy {
        SteamStoreService.create()
    }
    private val steamServiceDriveGet by lazy {
        SteamServiceDriveGet.create()
    }
    private val steamServiceDrivePost by lazy {
        SteamServiceDrivePost.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar.visibility = View.VISIBLE
        loadGames()
        loadGame("440")
        addGameDrive()
        loadGamesDrive()
    }

    private fun loadGamesDrive() {
        steamServiceDriveGet.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { body ->
                            var jsonTxt = body.string()
                            jsonTxt = limpiaGameDrive(jsonTxt)
                            val driveResponse: DriveResponse = Gson().fromJson(jsonTxt, DriveResponse::class.java)

                            driveResponseToGames(driveResponse)
                            showGamesDrive()
                        },
                        { error ->
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                            Log.e(TAG,error.message)
                        }
                )
    }



    private fun driveResponseToGames(result: DriveResponse) {
        gamesDrive=ArrayList<GameDrive>()
        for (row in result.table.rows) {
            var gameDrive = GameDrive()
            gameDrive.id = row.column[1].value
            gameDrive.name = row.column[2].value
            gameDrive.descripcion = row.column[3].value
            gameDrive.image = row.column[4].value
            gameDrive.link = row.column[5].value
            gamesDrive.add(gameDrive)
        }
    }


    private fun addGameDrive() {
        // Si os dais cuenta cuando introduzco el id del juego le pogo una comilla simple antes
        // Así obligo a que se guarde en formato string en la hoja de calculo
        // y así no hay etiquetas sin dobles comillas en su value en el JSON
        // Pues daría error si no lo hacemos así.
        // En clase le cambiamos el formato de numero a texto a la columna.
        // Pero no vale. Solo se lo cambiamos a las filas que hay en este momento
        // Las nuevas no tendrán ese formato. La mejor solucion, la comilla simple
        // ¡Viva la comilla! ¡Viva! ¡Viva! ¡Viva! ¡Viva! ¡Viva!
        steamServiceDrivePost.saveGame(
                "'440",
                "Team Fortress 2",
                "Nine distinct classes provide a broad range of tactical abilities and personalities.  Constantly updated with new game modes, maps, equipment and, most importantly, hats!",
                "http://cdn.akamai.steamstatic.com/steam/apps/440/header.jpg?t=1513721674",
                "http://www.teamfortress.com"
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { page ->
                            Toast.makeText(this,"insertado game...",Toast.LENGTH_SHORT).show()
                            Log.d("**Paco**", "*** Insertado game 440 ***")
                        },
                        { error ->
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                            Log.e("**Paco**",error.message)
                        }
                )
    }

    private fun loadGame(gameid: String) {
        steamStoreService.getData(gameid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { body ->
                            var jsonTxt = body.string()
                            jsonTxt = limpiaGameStore(jsonTxt)
                            val gameStore: GameStore = Gson().fromJson(jsonTxt, GameStore::class.java)
                            val gameDrive = GameDrive(gameid, gameStore.data.name,gameStore.data.short_description,gameStore.data.header_image, gameStore.data.website)

                            showGame(gameDrive)
                        },
                        { error ->
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                            Log.e(TAG,error.message)
                        }
                )
    }

    private fun loadGames() {
        steamApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { games ->
                            this.games = games
                            showGames()
                            progressBar.visibility = View.GONE
                        },
                        { error ->
                            progressBar.visibility = View.GONE
                            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                            Log.e(TAG,error.message)
                        }
                )
    }

    private fun showGames() {
//        games.applist.apps.forEach{ game ->
//            Log.d(TAG,game.toString())
//        }
        Log.d(TAG, "*** Todos los juegos de la api ***")
        Log.d(TAG, games.applist.apps[0].toString())
        Log.d(TAG, games.applist.apps[games.applist.apps.size-1].toString())
        Log.d(TAG,"sizegames="+games.applist.apps.size)
    }

    private fun showGamesDrive() {
        Log.d(TAG, "*** Todos los juegos de Drive ***")
        gamesDrive.forEach{ game ->
            Log.d(TAG,game.toString())
        }
    }


    private fun showGame(gameDrive: GameDrive) {
        Log.d(TAG, "*** Juego 440: ***")
        Log.d(TAG,gameDrive.toString())
    }

    private fun limpiaGameStore(json:String):String {
        var newjson=json
        newjson = newjson.substring(newjson.indexOf(":"))
        newjson = newjson.substring(1)
        newjson = newjson.substring(0,newjson.length-1)

        return newjson
    }

    private fun limpiaGameDrive(json:String):String {
        var newjson=json
        newjson = newjson.substring(newjson.indexOf("{"))
        newjson = newjson.substring(0, newjson.indexOf(");"))

        return newjson
    }
}
