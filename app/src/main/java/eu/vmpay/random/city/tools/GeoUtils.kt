package eu.vmpay.random.city.tools

import com.google.android.gms.maps.model.LatLng

enum class FixedCities(val latLng: LatLng) {
    UNKNOWN(LatLng(55.5265895, -4.5121251)),
    GDANSK(LatLng(54.366667, 18.633333)),
    WARSZAWA(LatLng(52.233333, 21.016667)),
    POZNAN(LatLng(52.4, 16.916667)),
    BIALYSTOK(LatLng(53.135278, 23.145556)),
    WROCLAW(LatLng(51.11, 17.0325)),
    KATOWICE(LatLng(50.258333, 19.0275)),
    KRAKOW(LatLng(50.0466814, 19.864792)),
}

fun String.getFixedCity(): LatLng = when (this) {
    "Gdańsk" -> FixedCities.GDANSK.latLng
    "Warszawa" -> FixedCities.WARSZAWA.latLng
    "Poznań" -> FixedCities.POZNAN.latLng
    "Białystok" -> FixedCities.BIALYSTOK.latLng
    "Wrocław" -> FixedCities.WROCLAW.latLng
    "Katowice" -> FixedCities.KATOWICE.latLng
    "Kraków" -> FixedCities.KRAKOW.latLng
    else -> FixedCities.UNKNOWN.latLng
}
