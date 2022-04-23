package br.com.douglas.navigationcomponentapp.di

import br.com.douglas.navigationcomponentapp.data.DefaultRepository
import br.com.douglas.navigationcomponentapp.data.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideLocalDataSource(repository: DefaultRepository): Repository
}