# JNoise
![banner](banner.png)

[![license](https://img.shields.io/github/license/articdive/JNoise.svg?style=for-the-badge)](../LICENSE)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/Articdive/JNoise?style=for-the-badge)](https://github.com/Articdive/JNoise/releases)
[![standard-readme compliant](https://img.shields.io/badge/readme%20style-standard-brightgreen.svg?style=for-the-badge)](https://github.com/RichardLitt/standard-readme)
[![Discord](https://img.shields.io/discord/525595722859675648?label=discord&style=for-the-badge)](https://discord.gg/JCx2eV4aWC)
[![TeamCity Simple Build Status](https://img.shields.io/teamcity/build/s/JNoise_PublishJAR?server=https%3A%2F%2Fci.minestom.com&style=for-the-badge)](https://ci.minestom.com/viewType.html?buildTypeId=JNoise_PublishJAR)
JNoise is a simple to use java-library for generating noise (including gradient noise) in Java.

JNoise was created in early 2020 by Lukas Mansour (Articdive). It was created for a project in Minecraft for custom terrain generation.
It works for all Java 8+ apps and is built using [Gradle](https://gradle.org/).

## Table of Contents
- [Install](#install)
- [Usage](#usage)
- [Maintainers](#maintainers)
- [Acknowledgements](#acknowledgements)
- [Contributing](#contributing)
- [License](#license)

## Install
### Maven and Gradle
To add JNoise to your project using [Gradle](https://gradle.org/) or [Maven](http://maven.apache.org/):

Repository (Maven):
```
<repository>
    <id>minestom-repo</id>
    <url>https://repo.minestom.com/repository/maven-public/</url>
</repository>
```
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
    maven {
        name = "Articdive's nexus repository"
        url = uri("https://repo.minestom.com/repository/maven-public/")
    }
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

### Picking your Noise-Type.
The JNoise library supports "Perlin", "OpenSimplex", "Value", "Worley" and "White" noise.

It also supports octavated (fractal) versions of all noise types.

Every noise-type has different customizable features, e.g. Perlin Noise has different types of interpolation to choose from and Worley Noise's point distribution can be altered.

These are all documented in the respective builders/generators of the noise-type.

Normally if you are using an IDE, the code-completition is intuitive enough to use this library without having to check the source-code.

Example: Creating a noise-generator using Perlin Noise with cosine interpolation.
```java
        JNoise perlinCosine = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.COSINE).setSeed(1729).build();
```

### Getting Normal Noise Values
The Noise's dimension has to do with the amount of parameters. If you add two doubles after the getNoise method, you will receive 2 dimensional noise.

Nearly All Noise Implementations support 2D, 3D and 4D noise.

Example: Getting 2D Perlin-Noise:
```java
    public JNoise perlinLinear = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(1629).build();
    public double getNoise(double x, double y) {
        // 1D Noise
        return perlinLinear.getNoise(x, y)
    }
```
Example: Getting 3D Perlin-Noise:
```java
    public JNoise perlinLinear = JNoise.newBuilder().perlin().setInterpolation(InterpolationType.LINEAR).setSeed(1629).build();
    public double getNoise(double x, double y, double z) {
        // 3D Noise
        return perlinLinear.getNoise(x, y, z)
    }
```

### Getting Octavated (Fractal) Noise Values
In this case way to get noise values is the exact same (using getNoise()), except in the creation of your builder you use the octavated noise type
and supply a noise-type to octavate.

Example: Creating a noise-generator using octavated Perlin Noise with cosine interpolation.
```java
        public JNoise octavatedPerlin = JNoise.newBuilder().octavated().setNoise(
            JNoise.newBuilder().perlin().setInterpolation(InterpolationType.COSINE).setSeed(1629).build()
        ).setOctaves(4).setPersistence(0.5).setLacunarity(0.5).build();
```

## Maintainers
[@Articdive](https://www.github.com/Articdive/)

## Acknowledgements
[@Ken Perlin](https://mrl.nyu.edu/~perlin/)'s work on Perlin Noise.

[@Kurt Spencer](https://www.github.com/KDotJpg)'s work on OpenSimplex2 located [here](https://github.com/KdotJPG/OpenSimplex2).

[@Steven Worley](http://weber.itn.liu.se/~stegu/TNM084-2017/worley-originalpaper.pdf)'s work on Worley Noise.

## Contributing
See [the contributing file](CONTRIBUTING.md)!

## License
[GNU General Public License v3.0 or later © Lukas Mansour (Articdive) ](../LICENSE)