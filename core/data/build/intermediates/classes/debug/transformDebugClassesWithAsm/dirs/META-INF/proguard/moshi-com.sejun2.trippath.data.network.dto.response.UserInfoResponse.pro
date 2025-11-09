-if class com.sejun2.trippath.data.network.dto.response.UserInfoResponse
-keepnames class com.sejun2.trippath.data.network.dto.response.UserInfoResponse
-if class com.sejun2.trippath.data.network.dto.response.UserInfoResponse
-keep class com.sejun2.trippath.data.network.dto.response.UserInfoResponseJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
