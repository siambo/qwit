object AppConfig {
    const val appId = "com.jonecx.qwit"
    const val appMinSdk = 29
    const val appTargetSdk = 31
    const val appVersionCode = 1
    const val appVersionName = "qeggs"
    const val appCompileSdkVersion = 31
}

object Versions {
    const val compose_version = "1.0.3"
    const val compose_navigation = "2.5.0"
    const val compose_activity = "1.5.0-alpha02"
    const val compose_accompanist_navigation_animation = "0.23.0"
    const val core_ktx_version = "1.7.0"
    const val kotlin_version = "1.5.30"
    const val ktlint_version = "0.42.1"
    const val spotless_version = "6.1.2"
    const val koin_version = "3.1.5"
    const val retrofit_version = "2.9.0"
    const val okhttp_bom_version = "4.9.3"
    const val mockito_version = "4.5.1"
}

object Koin {
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin_version}"
    const val koinCore = "io.insert-koin:koin-core:${Versions.koin_version}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin_version}"
}

object Kotlin {
    const val coreKtx = "androidx.core:core-ktx:${Versions.core_ktx_version}"
}

object Appcompat {
    const val appcompat = "androidx.appcompat:appcompat:1.4.0"
}

object MaterialDesign {
    const val material = "com.google.android.material:material:1.5.0"
}

object Timber {
    const val timber = "com.jakewharton.timber:timber:5.0.1"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    const val moshConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_version}"
}

object OkHttp {
    const val okttpBom = "com.squareup.okhttp3:okhttp-bom:${Versions.okhttp_bom_version}"
}

object Testing {
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val junit = "junit:junit:4.13.2"
    const val junitExt = "androidx.test.ext:junit:1.1.3"
    const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito_version}"
}

object Compose {
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose_version}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose_version}"
    const val composePreviewTool = "androidx.compose.ui:ui-tooling-preview:${Versions.compose_version}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose_version}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Versions.compose_version}"
    const val composeActivity = "androidx.activity:activity-compose:${Versions.compose_activity}"
    const val composeNavigation = "androidx.navigation:navigation-compose:${Versions.compose_navigation}"
    const val composeNavigationCommon = "androidx.navigation:navigation-common:${Versions.compose_navigation}"
    const val composeManifestTest = "androidx.compose.ui:ui-test-manifest:${Versions.compose_version}"
    const val composeAccompanistNavigationAnimation = "com.google.accompanist:accompanist-navigation-animation:${Versions.compose_accompanist_navigation_animation}"
}

object LifeCycle {
    const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1 "
}

object DataStore {
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:1.0.0"
}
