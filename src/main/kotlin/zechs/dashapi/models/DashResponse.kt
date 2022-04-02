package zechs.dashapi.models

data class DashResponse(
    val error: String?,
    val streams: List<Stream>
)

