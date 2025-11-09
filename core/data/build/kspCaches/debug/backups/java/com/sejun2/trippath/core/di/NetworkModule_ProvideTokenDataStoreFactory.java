package com.sejun2.trippath.core.di;

import android.content.Context;
import com.sejun2.trippath.data.local.TokenDataStore;
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
public final class NetworkModule_ProvideTokenDataStoreFactory implements Factory<TokenDataStore> {
  private final Provider<Context> contextProvider;

  private NetworkModule_ProvideTokenDataStoreFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public TokenDataStore get() {
    return provideTokenDataStore(contextProvider.get());
  }

  public static NetworkModule_ProvideTokenDataStoreFactory create(
      Provider<Context> contextProvider) {
    return new NetworkModule_ProvideTokenDataStoreFactory(contextProvider);
  }

  public static TokenDataStore provideTokenDataStore(Context context) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideTokenDataStore(context));
  }
}
