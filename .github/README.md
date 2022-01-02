# JNoise

![banner](banner.png)

[![license](https://img.shields.io/github/license/articdive/JNoise.svg?style=for-the-badge)](../LICENSE)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/Articdive/JNoise?style=for-the-badge)](https://github.com/Articdive/JNoise/releases)
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=for-the-badge)](https://github.com/RichardLitt/standard-readme)
[![Discord](https://img.shields.io/discord/525595722859675648?label=discord&style=for-the-badge)](https://discord.gg/c26nC7FxU6)

JNoise is a simple to use java-library for generating noise (including gradient noise) in Java.

JNoise was created in early 2020 by Articdive. It was created for a project in Minecraft for custom terrain generation.
It works for all Java 11+ apps and is built using [Gradle](https://gradle.org/).

## Table of Contents

- [Install](#install)
- [Usage](#usage)
- [Maintainers](#maintainers)
- [Acknowledgements](#acknowledgements)
- [Contributing](#contributing)
- [License](#license)

## Install

### Maven & Gradle

To add JNoise to your project using [Gradle](https://gradle.org/) or [Maven](http://maven.apache.org/):

Dependency (Maven):

```
<dependency>
    <groupId>de.articdive</groupId>
    <artifactId>jnoise</artifactId>
    <version>VERSION</version>
</dependency>
```

Repository (Gradle Kotlin DSL)

```
repositories {
    mavenCentral()
}
```

Dependency (Gradle Kotlin DSL)

```
dependencies {
    // JNoise Library
    implementation("de.articdive:jnoise:VERSION")
}
```

## Usage

### Picking your Noise Algorithm.

The JNoise library supports "Perlin", "OpenSimplex", "Value", "Worley" and "White" noise.

It also supports modules with which you can octavate (fractalize) and combine different noise types.

Every noise-type has different customizable features, e.g. Perlin Noise has different types of interpolation to choose
from and Worley Noise's point distribution can be altered.

Normally if you are using an IDE, the code-completition is intuitive enough to use this library without having to check
the source-code.

Nevertheless, the [Github Wiki](https://github.com/Articdive/JNoise/wiki) contains more than enough information to
acquire achieved results.

Example: Creating a noise-generator using Perlin Noise with cosine interpolation.

```java
public JNoise perlinCosine = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.COSINE).setSeed(1729).build();
```

### Getting Noise Values

The Noise's dimension has to do with the amount of parameters. If you add two doubles after the getNoise method, you
will receive 2 dimensional noise.

All Noise Implementations support 1D, 2D, 3D and 4D noise.

Example: Getting 2D Perlin-Noise:

```java
public JNoise perlinLinear = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(1629).build();
public double getNoise(double x, double y){
    // 1D Noise
    return perlinLinear.getNoise(x, y);
}
```

Example: Getting 3D Perlin-Noise:

```java
public JNoise perlinLinear = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(1629).build();
public double getNoise(double x, double y, double z){
    // 3D Noise
    return perlinLinear.getNoise(x, y, z);
}
```

### Getting Octavated (Fractal) Noise Values

In this case, the way to get octavated noise values is the exact same. However, we must add a **NoiseModule** (
the [OctavationModule](../src/main/java/de/articdive/jnoise/modules/octavation/OctavationModule.java)) to our JNoise
instance.

Example: Creating a noise-generator using octavated Perlin Noise with cosine interpolation.

```java
public JNoise octavatedPerlin = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.COSINE).addModule(OctavationModule.newBuilder().setOctaves(4).setPersistence(1.0).setLacunarity(1.0).build()).build();
```

### Customizable Features

All noise types have a customizable seed.

#### Perlin & Value Noise

- Frequency
- Interpolation function
- Fade function

#### Worley Noise

- Frequency
- Distance function
- Feature point amount

#### OpenSimplex Noise

- Frequency
- Fast & SuperSimplex algorithms
- Simplex variants

#### White Noise

- Frequency

#### Octavation Module

- The underlying noise type to be octavated
- Amount of octaves
- Lacunarity
- Persistance / Gain
- Fractal functions (FBM, Billow & Ridged)
- Seed incrementation per octave (Increases the seed by 1 each octave)

#### Combination Moudle

- Addition
- Min/Max
- Multiplication
- Power

## Maintainers

[@Articdive](https://www.github.com/Articdive/)

## Acknowledgements

[@Ken Perlin](https://mrl.nyu.edu/~perlin/)'s work on Perlin Noise.

[@Kurt Spencer](https://www.github.com/KDotJpg)'s work on OpenSimplex2 located [here](https://github.com/KdotJPG/OpenSimplex2).

[@Steven Worley](http://weber.itn.liu.se/~stegu/TNM084-2017/worley-originalpaper.pdf)'s work on Worley Noise.

## Contributing

See [the contributing file](CONTRIBUTING.md)!

## License

[GNU General Public License v3.0 or later © Articdive ](../LICENSE)