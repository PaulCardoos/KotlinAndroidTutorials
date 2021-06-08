package com.example.pokemon

import android.location.Location

class Pokemon {
    var name:String?=null
    var description:String?=null
    var image:Int=0
    var power:Double?=null
    var lat:Double=0.0
    var lon:Double=0.0
    var location: Location?=null
    var isCaught:Boolean? = null

    constructor(name:String, image:Int, description:String, power:Double, lat:Double, lon:Double){
        this.name = name
        this.image = image
        this.description = description
        this.power = power
        this.location = Location(name)
        this.location!!.latitude = lat
        this.location!!.longitude = lon
        this.isCaught = false

    }
}