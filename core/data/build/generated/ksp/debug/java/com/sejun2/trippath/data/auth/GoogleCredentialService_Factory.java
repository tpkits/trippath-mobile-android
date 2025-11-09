package com.sejun2.trippath.data.auth;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class GoogleCredentialService_Factory implements Factory<GoogleCredentialService> {
  @Override
  public GoogleCredentialService get() {
    return newInstance();
  }

  public static GoogleCredentialService_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GoogleCredentialService newInstance() {
    return new GoogleCredentialService();
  }

  private static final class InstanceHolder {
    static final GoogleCredentialService_Factory INSTANCE = new GoogleCredentialService_Factory();
  }
}
