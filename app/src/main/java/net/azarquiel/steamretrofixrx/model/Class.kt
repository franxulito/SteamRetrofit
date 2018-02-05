package net.azarquiel.steamretrofixrx.model

import com.google.gson.annotations.SerializedName

/**
 * Created by pacopulido on 04/2/18.
 */

// Class para la SteamApiService Get todos los games
data class Games(val applist:Apps)
data class Apps(val apps:List<Game>)
data class Game(val appid:String,
                val name:String)

// Class para la SteamStoreService Get un solo game
data class GameStore(val data:Data)
data class Data(val name:String,
                val short_description:String,
                val header_image:String,
                val website:String)

// Call para la SteamServiceDriveGet Get todos nuestros pokemos de Drive
data class DriveResponse (var table:Table)
data class Table (var rows : List<Rows>)
data class Rows (@SerializedName("c") var column : List<Column>)
data class Column (@SerializedName("v") var value : String, @SerializedName("f") var format : String)
// class para volcar Games de Drive que están en Rows y Columns
// en Objetos mas bonitos. O uno de SteamStore que está en Data
data class GameDrive(var id:String="", var name:String = "",var descripcion:String="", var image:String="", var link:String="")

