-if class com.sejun2.trippath.data.network.dto.request.LoginRequest
-keepnames class com.sejun2.trippath.data.network.dto.request.LoginRequest
-if class com.sejun2.trippath.data.network.dto.request.LoginRequest
-keep class com.sejun2.trippath.data.network.dto.request.LoginRequestJsonAdapter {
    public <init>(com.squareup.moshi.Moshi);
}
