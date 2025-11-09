-if class com.sejun2.trippath.data.network.dto.response.LoginResponse
-keepnames class com.sejun2.trippath.data.network.dto.response.LoginResponse
-if class com.sejun2.trippath.data.network.dto.response.LoginResponse
-keep class com.sejun2.trippath.data.network.dto.response.LoginResponseJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
