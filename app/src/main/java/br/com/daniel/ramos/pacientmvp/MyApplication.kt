package br.com.daniel.ramos.pacientmvp

import android.app.Application
import br.com.daniel.ramos.pacientmvp.data.db.RealmUpgrade
import br.com.daniel.ramos.pacientmvp.di.AppModule
import io.realm.BuildConfig
import io.realm.Realm
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(modules = AppModule.module)
        }
    }

    companion object {
        fun configurarRealm() {
            val configuration =
                if (!BuildConfig.DEBUG)
                    RealmUpgrade.getRealmConfigNoMigration()
                else RealmUpgrade.getRealmConfig()
            Realm.setDefaultConfiguration(configuration)
        }
    }
}