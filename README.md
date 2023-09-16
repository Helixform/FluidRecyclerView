# FluidRecyclerView

Brings the iOS scrolling experience to Android.

## Introduction

`FluidRecyclerView` is a fork version of `RecyclerView` from Android Jetpack, which can reside with the original version side by side. It provides the same public APIs of `RecyclerView`, and you can use it without any code change.

The major differences from the original version:
- Replaced the fling animation to match how it feels in iOS.
- Added the rubber band effect.
- Optimized the velocity calculation algorithm.

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