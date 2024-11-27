package com.example.mibocata

data class Bocadillo (
    val nombre: String,
    val ingredientes: List<String>
)

data class DayMenu (
    val caliente: Bocadillo,
    val frio: Bocadillo
)

data class Menu (
    val lunes: DayMenu,
    val martes: DayMenu,
    val miercoles: DayMenu,
    val jueves: DayMenu,
    val viernes: DayMenu
)