package zechs.dashapi.api

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import zechs.dashapi.api.interfaces.Drive
import zechs.dashapi.utils.Constants.Companion.GOOGLE_DRIVE_URL

class RetrofitInstance {

    companion object {
        val drive: Drive by lazy {
            Retrofit.Builder()
                .baseUrl(GOOGLE_DRIVE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(Drive::class.java)
        }
    }

}
