package eg.com.dailyforecast.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import eg.com.dailyforecast.db.AppDatabase
import eg.com.dailyforecast.db.dao.CityForecastDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }
    @Provides
    fun provideCityForecastDao(appDatabase: AppDatabase): CityForecastDao {
        return appDatabase.cityForecastDao()
    }
}
