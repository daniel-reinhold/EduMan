package com.eduman.core.di

import android.content.Context
import androidx.room.Room
import com.eduman.data.room.core.*
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
    )
    .addMigrations(
        MIGRATION_1_2,
        MIGRATION_2_3,
        MIGRATION_3_4
    )
    .build()

    @Singleton
    @Provides
    fun provideCoreDao(database: AppDatabase) = database.getCoreDAO()

    @Singleton
    @Provides
    fun provideSubjectDao(database: AppDatabase) = database.getSubjectDAO()

    @Singleton
    @Provides
    fun provideGradeDao(database: AppDatabase) = database.getGradeDAO()

    @Singleton
    @Provides
    fun provideTestDao(database: AppDatabase) = database.getTestDAO()

}