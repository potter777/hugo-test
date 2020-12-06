package cadenas.rene.hugotest.core

import androidx.room.Room
import cadenas.rene.hugotest.tasks.task5.UserViewModel
import cadenas.rene.hugotest.tasks.task5.db.AppDatabase
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moduleDb = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "hugo-test").build() }
}

val moduleVM = module {
    viewModel { UserViewModel(get()) }
}