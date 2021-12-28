package com.eduman.core.di

import android.content.Context
import androidx.room.Room
import com.eduman.data.room.core.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "eduman_db"
    ).build()

    @Singleton
    @Provides
    fun provideSubjectDao(database: AppDatabase) = database.getSubjectDAO()

}