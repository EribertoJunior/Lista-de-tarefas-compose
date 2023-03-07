package com.example.listacompose.di

import com.example.listacompose.data.dataSource.ListDataSource
import com.example.listacompose.data.dataSource.ListDataSourceImpl
import com.example.listacompose.data.database.RoomConfig
import com.example.listacompose.data.repository.ListRepositoryImpl
import com.example.listacompose.domain.repository.ListRepository
import com.example.listacompose.domain.usercase.ListUserCase
import com.example.listacompose.domain.usercase.ListUserCaseImpl
import com.example.listacompose.ui.viewmodels.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {

    factory { RoomConfig.getDataBase(androidContext()) }
    factory { get<RoomConfig>().taskDao() }

}

val dataSourceModule = module {
    factory<ListDataSource> { ListDataSourceImpl(taskDao = get()) }
}

val repositoryModule = module {
    factory<ListRepository> { ListRepositoryImpl(listDataSource = get()) }
}

val userCaseModule = module {
    factory<ListUserCase> { ListUserCaseImpl(listRepository = get()) }
}

val viewModelModule = module {
    viewModel {ListViewModel(listUserCase = get())}
}