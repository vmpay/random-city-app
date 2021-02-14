package eu.vmpay.random.city.tools

import java.text.SimpleDateFormat
import java.util.*

const val LOADER_DELAY = 2500L
const val PRODUCER_DELAY = 5000L

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "city_app.db"

val sdf = SimpleDateFormat("dd.MM.yy HH:mm:ss", Locale.ROOT)
