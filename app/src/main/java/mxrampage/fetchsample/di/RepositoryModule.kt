package mxrampage.fetchsample.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import mxrampage.fetchsample.home.data.DefaultItemsRepository
import mxrampage.fetchsample.home.data.ItemsRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindRepository(impl: DefaultItemsRepository): ItemsRepository
}
