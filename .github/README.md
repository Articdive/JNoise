# JNoise

![banner](banner.png)

[![license](https://img.shields.io/github/license/articdive/JNoise.svg?style=for-the-badge)](../LICENSE)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/Articdive/JNoise?style=for-the-badge)](https://github.com/Articdive/JNoise/releases)
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=for-the-badge)](https://github.com/RichardLitt/standard-readme)
[![Discord](https://img.shields.io/discord/525595722859675648?label=discord&style=for-the-badge)](https://discord.gg/c26nC7FxU6)
[![javadocs](https://img.shields.io/badge/documentation-javadocs-4d7a97?style=for-the-badge)](https://jnoise.articdive.de/)

JNoise is a simple to use java-library for generating noise (including gradient noise) in Java.

JNoise was created in early 2020 by Articdive. It was created for a project in Minecraft for custom terrain generation.
It works for all Java 17+ apps and is built using [Gradle](https://gradle.org/).

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

Repository (Maven) (Only required if using a snapshot version of JNoise):

```
<repository>
    <id>sonatypeSnapshots</id>
    <name>Sonatype Snapshots</name>
    <url>https://oss.sonatype.org/content/repositories/snapshots</url>
    <releases>
        <enabled>false</enabled>
    </releases>
    <snapshots>
        <enabled>true</enabled>
    </snapshots>
</repository>
```

Dependency (Maven):

```
<dependency>
    <groupId>de.articdive</groupId>
    <artifactId>jnoise-pipeline</artifactId>
    <version>VERSION</version>
</dependency>
```

Repository (Gradle Kotlin DSL):

```
repositories {
    mavenCentral()
    // If using a snapshot version of JNoise: maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}
```

Dependency (Gradle Kotlin DSL):

```
dependencies {
    // JNoise Library
    implementation("de.articdive:jnoise-pipeline:VERSION")
}
```

## Usage

### Picking your Noise Algorithm.

The JNoise library supports "Perlin", "OpenSimplex", "Value", "Worley" (Cellular) and "White" noise.

It also supports modules with which you can octavate (fractalize) and combine different noise types.

Every noise-type has different customizable features, e.g. Perlin Noise has different types of interpolation to choose
from and Worley Noise's point distribution can be altered.

Normally if you are using an IDE, the code-completition is intuitive enough to use this library without having to check
the source-code. There are also Javadocs available [here](https://jnoise.articdive.de/).

Nevertheless, the [Github Wiki](https://github.com/Articdive/JNoise/wiki) contains more than enough information to
acquire achieved results.

Example: Creating a noise-generator using Perlin Noise with cosine interpolation.

```java
public PerlinNoiseGenerator perlinCosine=PerlinNoiseGenerator.newBuilder().setSeed(3301).setInterpolation(Interpolation.COSINE).build();
```

Example using the JNoise Pipeline:

```java
public JNoise noisePipeline=JNoise.newBuilder().perlin(3301,Interpolation.COSINE,FadeFunction.QUINTIC_POLY).build();
```

### Getting Noise Values

The Noise's dimension has to do with the amount of parameters. If you add two doubles after the evaluateNoise method,
you
will receive 2 dimensional noise.

All Noise Implementations support 1D, 2D, 3D and 4D noise.

Example: Getting 2D Perlin-Noise:

```java
    // 1D Noise at x = 1.0 and y = 0.5 in a 2D plane.
    return perlinLinear.evaluateNoise(1.0,0.5);
```

Example: Getting 3D Perlin-Noise:

```java
    // 1D Noise at x = 1.0, y = 0.5 and z = 1.22 in a 3D plane.
    return perlinLinear.evaluateNoise(1.0,0.5,1.22);
```

### Advantages of the JNoise Pipeline

The main advantage of the JNoise Pipeline in contrary to just using the generators is the ability to easily apply
modifiers, modules and transformers, this does come at a small performance cost for the abstraction.

#### Transformers

Transformers change the input coordinates. The most used transformer is
the [ScaleTransformer](../transformers/src/main/java/de/articdive/jnoise/transformers/scale/ScaleTransformer.java)

To apply the scale transformer via the Jnoise Pipeline (with a scaling factor of 0.5):

```java
public JNoise noisePipeline=Jnoise.newBuilder().scale(0.5).[...].build();
```

#### Modules

Modules are basically noise generators that use other noise generators as an input. Currently, the 2 most used noise
modules are:

- OctavationModule (Octavate (Fractalize) a noise generator)
- CombinationModule (Combine 2 noise generators)

Please note that modules are also noise generators, so a CombinationModule can have a different CombinationModule as an
input.

##### Getting Octavated (Fractal) Noise Values

In this case, the way to get octavated noise values is the exact same. However, we must add a **NoiseModule** (
the [OctavationModule](../modules/src/main/java/de/articdive/jnoise/modules/octavation/OctavationModule.java)) to our
JNoise instance.

Example: Creating a noise-generator using octavated Perlin Noise with cosine interpolation.

```java
public PerlinNoiseGenerator perlinCosine=PerlinNoiseGenerator.newBuilder().setSeed(3301).setInterpolation(Interpolation.COSINE).build();
// In most cases, one would inline the perlinCosine value into the builder chain.
public OctavationModule octavtedPerlin=OctavationModule.newBuilder().setNoiseSource(perlinCosine).setOctaves(4).setPersistence(1.0).setLacunarity(1.0).build()
```

Example using the JNoise Pipeline (Variant 1):

```java
public PerlinNoiseGenerator perlinCosine=PerlinNoiseGenerator.newBuilder().setSeed(3301).setInterpolation(Interpolation.COSINE).build();
// In most cases, one would inline the perlinCosine value into the builder chain.
public JNoise noisePipeline=JNoise.newBuilder().octavation(perlinCosine,4,1.0,1.0,FractalFunction.FBM,false).build();
```

Example using the JNoise Pipeline (Variant 2):

```java
public JNoise noisePipeline=JNoise.newBuilder().perlin(3301,Interpolation.COSINE,FadeFunction.QUINTIC_POLY).octavate(4,1.0,1.0,FractalFunction.FBM,false).build();
```

#### Modifiers

Modifiers change the output value. They are mostly included to reduce the amount of modification a developer using
JNoise has to do. There are various different modifiers, e.g.:

- AbsoluteValueModifier (absolute value of the noise output.)
- ClampModifier (clamps the noise output between two values.)
- InvertModifier (inverts the noise output.)

Most modifiers can be applied directly in the JNoise pipeline and their order is respected, e.g.:

```java
public JNoise noisePipeline=Jnoise.newBuilder().[...].abs().clamp(0.5,0.75).invert().build();
```

### Customizable Features

Most noise types have a customizable seed.

#### Perlin & Value Noise

- Interpolation function
- Fade function

#### Worley Noise

- Distance function
- Feature point amount
- Combination of shortest distances.

#### OpenSimplex Noise

- Fast & SuperSimplex algorithms
- Simplex variants

#### White Noise

### Gaussian White Noise

- Mean
- Standard Deviation

#### Octavation Module

- The underlying noise type to be octavated
- Amount of octaves
- Lacunarity
- Gain (Persistence)
- Fractal functions (FBM, Turbelent & Ridged)
- Seed incrementation per octave (Increases the seed by 1 each octave)

#### Combination Moudle

- Addition
- Min/Max
- Multiplication
- Power

### Notes on using Worley Noise

Since Worley Noise has a return type in form of a Tuple (The nearest point and numeric value) it must be accessed
differentely. By using the method evaluateNoiseResult(...) defined in JNoiseDetailed you can access a small object
containing more than just the numeric value.

```java
public JNoiseDetailed<WorleyNoiseResult<Vector>>worleyNoise=JNoise.newBuilder().worley(WorleyNoiseGenerator.newBuilder().[...].build()).buildDetailed();
    {
    worleyNoise.evaluateNoiseResult(x,y).getClosestPoint();
    worleyNoise.evaluateNoiseResult(x,y).getValue()
    }
```

## Maintainers

[@Articdive](https://www.github.com/Articdive/)

## Acknowledgements

[@Ken Perlin](https://mrl.nyu.edu/~perlin/)'s work on Perlin Noise.

[@Kurt Spencer](https://www.github.com/KDotJpg)'s work on OpenSimplex2
located [here](https://github.com/KdotJPG/OpenSimplex2).

[@Steven Worley](http://weber.itn.liu.se/~stegu/TNM084-2017/worley-originalpaper.pdf)'s work on Worley Noise.

[@Inigo Quilez](https://iquilezles.org)'s work on Smooth Voronoi (Worley) Noise and domain warping.

## Contributing

See [the contributing file](CONTRIBUTING.md)!

## License

[GNU General Public License v3.0 or later © Articdive ](../LICENSE)