package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final AndroidLibraryAccessors laccForAndroidLibraryAccessors = new AndroidLibraryAccessors(owner);
    private final AndroidxLibraryAccessors laccForAndroidxLibraryAccessors = new AndroidxLibraryAccessors(owner);
    private final EzLibraryAccessors laccForEzLibraryAccessors = new EzLibraryAccessors(owner);
    private final IndicatorLibraryAccessors laccForIndicatorLibraryAccessors = new IndicatorLibraryAccessors(owner);
    private final KotlinxLibraryAccessors laccForKotlinxLibraryAccessors = new KotlinxLibraryAccessors(owner);
    private final ShortcutLibraryAccessors laccForShortcutLibraryAccessors = new ShortcutLibraryAccessors(owner);
    private final SimpleLibraryAccessors laccForSimpleLibraryAccessors = new SimpleLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for eventbus (org.greenrobot:eventbus)
         * This dependency was declared in catalog libs.versions.toml
         */
        public Provider<MinimalExternalModuleDependency> getEventbus() {
            return create("eventbus");
    }

    /**
     * Returns the group of libraries at android
     */
    public AndroidLibraryAccessors getAndroid() {
        return laccForAndroidLibraryAccessors;
    }

    /**
     * Returns the group of libraries at androidx
     */
    public AndroidxLibraryAccessors getAndroidx() {
        return laccForAndroidxLibraryAccessors;
    }

    /**
     * Returns the group of libraries at ez
     */
    public EzLibraryAccessors getEz() {
        return laccForEzLibraryAccessors;
    }

    /**
     * Returns the group of libraries at indicator
     */
    public IndicatorLibraryAccessors getIndicator() {
        return laccForIndicatorLibraryAccessors;
    }

    /**
     * Returns the group of libraries at kotlinx
     */
    public KotlinxLibraryAccessors getKotlinx() {
        return laccForKotlinxLibraryAccessors;
    }

    /**
     * Returns the group of libraries at shortcut
     */
    public ShortcutLibraryAccessors getShortcut() {
        return laccForShortcutLibraryAccessors;
    }

    /**
     * Returns the group of libraries at simple
     */
    public SimpleLibraryAccessors getSimple() {
        return laccForSimpleLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() {
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    public static class AndroidLibraryAccessors extends SubDependencyFactory {

        public AndroidLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for smsmms (com.github.tibbi:android-smsmms)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSmsmms() {
                return create("android.smsmms");
        }

    }

    public static class AndroidxLibraryAccessors extends SubDependencyFactory {
        private final AndroidxLifecycleLibraryAccessors laccForAndroidxLifecycleLibraryAccessors = new AndroidxLifecycleLibraryAccessors(owner);
        private final AndroidxRoomLibraryAccessors laccForAndroidxRoomLibraryAccessors = new AndroidxRoomLibraryAccessors(owner);

        public AndroidxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for constraintlayout (androidx.constraintlayout:constraintlayout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getConstraintlayout() {
                return create("androidx.constraintlayout");
        }

            /**
             * Creates a dependency provider for swiperefreshlayout (androidx.swiperefreshlayout:swiperefreshlayout)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getSwiperefreshlayout() {
                return create("androidx.swiperefreshlayout");
        }

        /**
         * Returns the group of libraries at androidx.lifecycle
         */
        public AndroidxLifecycleLibraryAccessors getLifecycle() {
            return laccForAndroidxLifecycleLibraryAccessors;
        }

        /**
         * Returns the group of libraries at androidx.room
         */
        public AndroidxRoomLibraryAccessors getRoom() {
            return laccForAndroidxRoomLibraryAccessors;
        }

    }

    public static class AndroidxLifecycleLibraryAccessors extends SubDependencyFactory {

        public AndroidxLifecycleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for process (androidx.lifecycle:lifecycle-process)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getProcess() {
                return create("androidx.lifecycle.process");
        }

    }

    public static class AndroidxRoomLibraryAccessors extends SubDependencyFactory {

        public AndroidxRoomLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for compiler (androidx.room:room-compiler)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCompiler() {
                return create("androidx.room.compiler");
        }

            /**
             * Creates a dependency provider for ktx (androidx.room:room-ktx)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getKtx() {
                return create("androidx.room.ktx");
        }

            /**
             * Creates a dependency provider for runtime (androidx.room:room-runtime)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getRuntime() {
                return create("androidx.room.runtime");
        }

    }

    public static class EzLibraryAccessors extends SubDependencyFactory {

        public EzLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for vcard (com.googlecode.ez-vcard:ez-vcard)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getVcard() {
                return create("ez.vcard");
        }

    }

    public static class IndicatorLibraryAccessors extends SubDependencyFactory {
        private final IndicatorFastLibraryAccessors laccForIndicatorFastLibraryAccessors = new IndicatorFastLibraryAccessors(owner);

        public IndicatorLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at indicator.fast
         */
        public IndicatorFastLibraryAccessors getFast() {
            return laccForIndicatorFastLibraryAccessors;
        }

    }

    public static class IndicatorFastLibraryAccessors extends SubDependencyFactory {

        public IndicatorFastLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for scroll (com.github.tibbi:IndicatorFastScroll)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getScroll() {
                return create("indicator.fast.scroll");
        }

    }

    public static class KotlinxLibraryAccessors extends SubDependencyFactory {
        private final KotlinxSerializationLibraryAccessors laccForKotlinxSerializationLibraryAccessors = new KotlinxSerializationLibraryAccessors(owner);

        public KotlinxLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at kotlinx.serialization
         */
        public KotlinxSerializationLibraryAccessors getSerialization() {
            return laccForKotlinxSerializationLibraryAccessors;
        }

    }

    public static class KotlinxSerializationLibraryAccessors extends SubDependencyFactory {

        public KotlinxSerializationLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for json (org.jetbrains.kotlinx:kotlinx-serialization-json)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getJson() {
                return create("kotlinx.serialization.json");
        }

    }

    public static class ShortcutLibraryAccessors extends SubDependencyFactory {

        public ShortcutLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for badger (me.leolin:ShortcutBadger)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getBadger() {
                return create("shortcut.badger");
        }

    }

    public static class SimpleLibraryAccessors extends SubDependencyFactory {
        private final SimpleMobileLibraryAccessors laccForSimpleMobileLibraryAccessors = new SimpleMobileLibraryAccessors(owner);

        public SimpleLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at simple.mobile
         */
        public SimpleMobileLibraryAccessors getMobile() {
            return laccForSimpleMobileLibraryAccessors;
        }

    }

    public static class SimpleMobileLibraryAccessors extends SubDependencyFactory {
        private final SimpleMobileToolsLibraryAccessors laccForSimpleMobileToolsLibraryAccessors = new SimpleMobileToolsLibraryAccessors(owner);

        public SimpleMobileLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at simple.mobile.tools
         */
        public SimpleMobileToolsLibraryAccessors getTools() {
            return laccForSimpleMobileToolsLibraryAccessors;
        }

    }

    public static class SimpleMobileToolsLibraryAccessors extends SubDependencyFactory {

        public SimpleMobileToolsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for commons (com.github.SimpleMobileTools:Simple-Commons)
             * This dependency was declared in catalog libs.versions.toml
             */
            public Provider<MinimalExternalModuleDependency> getCommons() {
                return create("simple.mobile.tools.commons");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final AndroidVersionAccessors vaccForAndroidVersionAccessors = new AndroidVersionAccessors(providers, config);
        private final AndroidxVersionAccessors vaccForAndroidxVersionAccessors = new AndroidxVersionAccessors(providers, config);
        private final AppVersionAccessors vaccForAppVersionAccessors = new AppVersionAccessors(providers, config);
        private final EzVersionAccessors vaccForEzVersionAccessors = new EzVersionAccessors(providers, config);
        private final GradlePluginsVersionAccessors vaccForGradlePluginsVersionAccessors = new GradlePluginsVersionAccessors(providers, config);
        private final IndicatorVersionAccessors vaccForIndicatorVersionAccessors = new IndicatorVersionAccessors(providers, config);
        private final ShortcutVersionAccessors vaccForShortcutVersionAccessors = new ShortcutVersionAccessors(providers, config);
        private final SimpleVersionAccessors vaccForSimpleVersionAccessors = new SimpleVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: eventbus (3.3.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getEventbus() { return getVersion("eventbus"); }

            /**
             * Returns the version associated to this alias: kotlin (1.9.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlin() { return getVersion("kotlin"); }

            /**
             * Returns the version associated to this alias: kotlinxSerializationJson (1.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinxSerializationJson() { return getVersion("kotlinxSerializationJson"); }

            /**
             * Returns the version associated to this alias: ksp (1.9.0-1.0.12)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKsp() { return getVersion("ksp"); }

            /**
             * Returns the version associated to this alias: room (2.6.0-alpha03)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getRoom() { return getVersion("room"); }

        /**
         * Returns the group of versions at versions.android
         */
        public AndroidVersionAccessors getAndroid() {
            return vaccForAndroidVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.androidx
         */
        public AndroidxVersionAccessors getAndroidx() {
            return vaccForAndroidxVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.app
         */
        public AppVersionAccessors getApp() {
            return vaccForAppVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.ez
         */
        public EzVersionAccessors getEz() {
            return vaccForEzVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.gradlePlugins
         */
        public GradlePluginsVersionAccessors getGradlePlugins() {
            return vaccForGradlePluginsVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.indicator
         */
        public IndicatorVersionAccessors getIndicator() {
            return vaccForIndicatorVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.shortcut
         */
        public ShortcutVersionAccessors getShortcut() {
            return vaccForShortcutVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.simple
         */
        public SimpleVersionAccessors getSimple() {
            return vaccForSimpleVersionAccessors;
        }

    }

    public static class AndroidVersionAccessors extends VersionFactory  {

        public AndroidVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: android.smsmms (c3e678befd)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSmsmms() { return getVersion("android.smsmms"); }

    }

    public static class AndroidxVersionAccessors extends VersionFactory  {

        public AndroidxVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: androidx.constraintlayout (2.1.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getConstraintlayout() { return getVersion("androidx.constraintlayout"); }

            /**
             * Returns the version associated to this alias: androidx.lifecycleprocess (2.6.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLifecycleprocess() { return getVersion("androidx.lifecycleprocess"); }

            /**
             * Returns the version associated to this alias: androidx.swiperefreshlayout (1.1.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getSwiperefreshlayout() { return getVersion("androidx.swiperefreshlayout"); }

    }

    public static class AppVersionAccessors extends VersionFactory  {

        private final AppBuildVersionAccessors vaccForAppBuildVersionAccessors = new AppBuildVersionAccessors(providers, config);
        private final AppVersionVersionAccessors vaccForAppVersionVersionAccessors = new AppVersionVersionAccessors(providers, config);
        public AppVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.app.build
         */
        public AppBuildVersionAccessors getBuild() {
            return vaccForAppBuildVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.app.version
         */
        public AppVersionVersionAccessors getVersion() {
            return vaccForAppVersionVersionAccessors;
        }

    }

    public static class AppBuildVersionAccessors extends VersionFactory  {

        public AppBuildVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: app.build.compileSDKVersion (34)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCompileSDKVersion() { return getVersion("app.build.compileSDKVersion"); }

            /**
             * Returns the version associated to this alias: app.build.javaVersion (VERSION_17)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getJavaVersion() { return getVersion("app.build.javaVersion"); }

            /**
             * Returns the version associated to this alias: app.build.kotlinJVMTarget (17)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getKotlinJVMTarget() { return getVersion("app.build.kotlinJVMTarget"); }

            /**
             * Returns the version associated to this alias: app.build.minimumSDK (23)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMinimumSDK() { return getVersion("app.build.minimumSDK"); }

            /**
             * Returns the version associated to this alias: app.build.targetSDK (34)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getTargetSDK() { return getVersion("app.build.targetSDK"); }

    }

    public static class AppVersionVersionAccessors extends VersionFactory  {

        public AppVersionVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: app.version.appId (com.simplemobiletools.smsmessenger)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAppId() { return getVersion("app.version.appId"); }

            /**
             * Returns the version associated to this alias: app.version.versionCode (85)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersionCode() { return getVersion("app.version.versionCode"); }

            /**
             * Returns the version associated to this alias: app.version.versionName (5.19.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersionName() { return getVersion("app.version.versionName"); }

    }

    public static class EzVersionAccessors extends VersionFactory  {

        public EzVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: ez.vcard (0.11.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVcard() { return getVersion("ez.vcard"); }

    }

    public static class GradlePluginsVersionAccessors extends VersionFactory  {

        public GradlePluginsVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: gradlePlugins.agp (8.1.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getAgp() { return getVersion("gradlePlugins.agp"); }

    }

    public static class IndicatorVersionAccessors extends VersionFactory  {

        private final IndicatorFastVersionAccessors vaccForIndicatorFastVersionAccessors = new IndicatorFastVersionAccessors(providers, config);
        public IndicatorVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

        /**
         * Returns the group of versions at versions.indicator.fast
         */
        public IndicatorFastVersionAccessors getFast() {
            return vaccForIndicatorFastVersionAccessors;
        }

    }

    public static class IndicatorFastVersionAccessors extends VersionFactory  {

        public IndicatorFastVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: indicator.fast.scroll (4524cd0b61)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getScroll() { return getVersion("indicator.fast.scroll"); }

    }

    public static class ShortcutVersionAccessors extends VersionFactory  {

        public ShortcutVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: shortcut.badger (1.1.22)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getBadger() { return getVersion("shortcut.badger"); }

    }

    public static class SimpleVersionAccessors extends VersionFactory  {

        public SimpleVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: simple.commons (b72ded2a75)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCommons() { return getVersion("simple.commons"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

            /**
             * Creates a dependency bundle provider for room which is an aggregate for the following dependencies:
             * <ul>
             *    <li>androidx.room:room-ktx</li>
             *    <li>androidx.room:room-runtime</li>
             * </ul>
             * This bundle was declared in catalog libs.versions.toml
             */
            public Provider<ExternalModuleDependencyBundle> getRoom() {
                return createBundle("room");
            }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for android to the plugin id 'com.android.application'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getAndroid() { return createPlugin("android"); }

            /**
             * Creates a plugin provider for kotlinAndroid to the plugin id 'org.jetbrains.kotlin.android'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKotlinAndroid() { return createPlugin("kotlinAndroid"); }

            /**
             * Creates a plugin provider for kotlinSerialization to the plugin id 'org.jetbrains.kotlin.plugin.serialization'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKotlinSerialization() { return createPlugin("kotlinSerialization"); }

            /**
             * Creates a plugin provider for ksp to the plugin id 'com.google.devtools.ksp'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getKsp() { return createPlugin("ksp"); }

    }

}
