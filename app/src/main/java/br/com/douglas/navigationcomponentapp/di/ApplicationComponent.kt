package br.com.douglas.navigationcomponentapp.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

// Definition of the Application graph
@Singleton
@Component(modules = [DataModule::class, ViewModelBuilderModule::class])
interface ApplicationComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }
}