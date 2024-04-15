package com.thiagoperea.inchurchandroidchallenge.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.dao.MovieFavoriteDao
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.entity.MovieFavoriteEntity
import com.thiagoperea.inchurchandroidchallenge.data.datasource.local.typeconverter.StringListTypeConverter

@TypeConverters(
    StringListTypeConverter::class
)
@Database(
    entities = [
        MovieFavoriteEntity::class
    ],
    version = 1
)
abstract class InChurchDatabase : RoomDatabase() {

    abstract fun movieFavoriteDao(): MovieFavoriteDao
}