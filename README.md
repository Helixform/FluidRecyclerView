# FluidRecyclerView

Brings the iOS scrolling experience to Android.

## Introduction

`FluidRecyclerView` is a fork version of `RecyclerView` from Android Jetpack, which can reside with the original version side by side. It provides the same public APIs of `RecyclerView`, and you can use it without any code change.

The major differences from the original version:
- Replaced the fling animation to match how it feels in iOS.
- Added the rubber band effect.
- Optimized the velocity calculation algorithm.

This library takes code from [fluid-scroll](https://github.com/ktiays/fluid-scroll), which ports many important algorithms like spring simulation, velocity calculation, etc. Check it out if you are looking for implementations for other platforms.

📽️ Let's see it in action:

https://github.com/ktiays/fluid-scroll/assets/44366293/0d4c988f-8258-4966-8dda-92121b8db3de

## Getting Started

To use `FluidRecyclerView` in your project, add the JitPack repository to your `settings.gradle.kts` file:

```kotlin
dependencyResolutionManagement {
    repositories {
        // ...
        maven {
            url = URI("https://jitpack.io")
        }
    }
}
```

Then, add the dependency:

```kotlin
dependencies {
    // ...
    implementation("com.github.Helixform:FluidRecyclerView:main-SNAPSHOT")
}
```

Now replace all the packages of `RecyclerView` and related classes with `androidx.fluidrecyclerview.widget.*`:

```patch
- import androidx.recyclerview.widget.RecyclerView
- import androidx.recyclerview.widget.RecyclerView.Adapter
- import androidx.recyclerview.widget.RecyclerView.ViewHolder
+ import androidx.fluidrecyclerview.widget.RecyclerView
+ import androidx.fluidrecyclerview.widget.RecyclerView.Adapter
+ import androidx.fluidrecyclerview.widget.RecyclerView.ViewHolder
```

That's it!

## FAQ

### Which version of RecyclerView is this library based on?

The library is forked from `androidx.recyclerview:recyclerview:1.3.1` ([commit](https://android.googlesource.com/platform/frameworks/support/+/68f4660cd40b87c9383c5c7d86ae26ebccbf93e8)), and currently have no plans to synchronize with higher versions.

### Is there any public API changes?

No. But note that some internal methods & classes have been changed or removed. If you are using some private APIs via reflection, your app may behave abnormally.

### Why is it still using `androidx` package name?

The library uses some package-visible APIs from other AndroidX components. To reduce unnecessary modifications and impacts of using this library, we just renamed the child packages.

### Can I change the scrolling behavior dynamically?

It's not supported currently. Since this library can coexist with the original `RecyclerView`, you can implement that feature at app level.

## License

```
Copyright 2018 The Android Open Source Project
Copyright (C) 2023 Helixform

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```