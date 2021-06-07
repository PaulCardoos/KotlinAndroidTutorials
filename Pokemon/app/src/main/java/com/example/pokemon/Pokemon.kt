package com.example.pokemon

import java.io.FileDescriptor

class Pokemon {
    var name:String?=null
    var description:String?=null
    var image:Int=0
    var power:Double?=null
    var lat:Double=0.0
    var lon:Double=0.0
    var isCaught:Boolean? = null

    constructor(name:String, image:Int, description:String, power:Double, lat:Double, lon:Double){
        this.name = name
        this.image = image
        this.description = description
        this.power = power
        this.lat = lat
        this.lon = lon
        this.isCaught = false

    }
}