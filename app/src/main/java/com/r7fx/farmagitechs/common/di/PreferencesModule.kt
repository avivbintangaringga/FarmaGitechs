package com.r7fx.farmagitechs.common.di

import android.content.Context
import com.r7fx.farmagitechs.common.utils.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {
    @Provides
    fun providePreference(@ApplicationContext context: Context) =
        Preferences(context)
}