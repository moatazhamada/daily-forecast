package eg.com.dailyforecast.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eg.com.dailyforecast.api.ForecastGateway
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideForecastGateway(): ForecastGateway {
        return ForecastGateway.create()
    }
}
