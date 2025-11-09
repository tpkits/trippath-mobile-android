package com.sejun2.trippath.core.di;

import com.sejun2.trippath.data.auth.GoogleCredentialService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
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
public final class NetworkModule_ProvideGoogleCredentialServiceFactory implements Factory<GoogleCredentialService> {
  @Override
  public GoogleCredentialService get() {
    return provideGoogleCredentialService();
  }

  public static NetworkModule_ProvideGoogleCredentialServiceFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GoogleCredentialService provideGoogleCredentialService() {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideGoogleCredentialService());
  }

  private static final class InstanceHolder {
    static final NetworkModule_ProvideGoogleCredentialServiceFactory INSTANCE = new NetworkModule_ProvideGoogleCredentialServiceFactory();
  }
}
