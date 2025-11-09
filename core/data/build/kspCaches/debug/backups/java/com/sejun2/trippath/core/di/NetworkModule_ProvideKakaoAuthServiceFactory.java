package com.sejun2.trippath.core.di;

import android.content.Context;
import com.sejun2.trippath.data.auth.KakaoAuthService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class NetworkModule_ProvideKakaoAuthServiceFactory implements Factory<KakaoAuthService> {
  private final Provider<Context> contextProvider;

  private NetworkModule_ProvideKakaoAuthServiceFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public KakaoAuthService get() {
    return provideKakaoAuthService(contextProvider.get());
  }

  public static NetworkModule_ProvideKakaoAuthServiceFactory create(
      Provider<Context> contextProvider) {
    return new NetworkModule_ProvideKakaoAuthServiceFactory(contextProvider);
  }

  public static KakaoAuthService provideKakaoAuthService(Context context) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideKakaoAuthService(context));
  }
}
