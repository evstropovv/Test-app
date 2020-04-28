package com.testtask.di

import com.testtask.data.cache.DataSourceCacheImpl
import com.testtask.data.datasource.DataSourceCache
import com.testtask.data.datasource.DataSourceRemote
import com.testtask.data.remote.DataSourceRemoteImpl
import com.testtask.data.repository.RepositoryImpl
import com.testtask.domain.repository.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideRepository(repository: RepositoryImpl?): Repository?

    @Binds
    @Singleton
    abstract fun provideDataSourceCache(repository: DataSourceCacheImpl?): DataSourceCache?

    @Binds
    @Singleton
    abstract fun provideDataSourceRemote(repository: DataSourceRemoteImpl?): DataSourceRemote?

}
