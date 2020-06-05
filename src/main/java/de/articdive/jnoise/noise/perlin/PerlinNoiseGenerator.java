/*
 * JNoise
 * Copyright (C) 2020 Articdive (Lukas Mansour)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.articdive.jnoise.noise.perlin;

import de.articdive.jnoise.api.Interpolation;
import de.articdive.jnoise.api.NoiseGenerator;
import de.articdive.jnoise.util.HashUtil;
import de.articdive.jnoise.util.vectors.Vector2D;
import de.articdive.jnoise.util.vectors.Vector3D;
import de.articdive.jnoise.util.vectors.Vector4D;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * Based on Ken Perlin's implementation of Perlin Noise.
 * The bounds of this Perlin Noise implementation are: [-sqrt(n/2), sqrt(n/2)] (n being the dimension).
 *
 * @author Lukas Mansour
 */
public final class PerlinNoiseGenerator extends NoiseGenerator {
    private static final double[] VECTOR_1D = new double[]{
            1.0, -1.0
    };
    private static final Vector2D[] VECTOR_2D = new Vector2D[]{
            new Vector2D(1, 1), new Vector2D(-1, 1), new Vector2D(1, -1), new Vector2D(-1, -1),
            
            new Vector2D(0, 1), new Vector2D(0, -1), new Vector2D(1, 0), new Vector2D(-1, 0)
    };
    private static final Vector3D[] VECTOR_3D = new Vector3D[]{
            new Vector3D(1, 1, 1), new Vector3D(-1, 1, 1), new Vector3D(1, -1, 1), new Vector3D(-1, -1, 1),
            new Vector3D(1, 1, -1), new Vector3D(-1, 1, -1), new Vector3D(1, -1, -1), new Vector3D(-1, -1, -1),
            
            new Vector3D(0, 1, 1), new Vector3D(0, -1, 1), new Vector3D(0, 1, -1), new Vector3D(0, -1, -1),
            new Vector3D(1, 0, 1), new Vector3D(-1, 0, 1), new Vector3D(1, 0, -1), new Vector3D(-1, 0, -1),
            new Vector3D(1, 1, 0), new Vector3D(-1, 1, 0), new Vector3D(1, -1, 0), new Vector3D(-1, -1, 0)
    };
    private static final Vector4D[] VECTOR_4D = new Vector4D[]{
            new Vector4D(1, 1, 1, 1), new Vector4D(1, 1, -1, 1), new Vector4D(1, -1, 1, 1), new Vector4D(1, -1, -1, 1),
            new Vector4D(-1, 1, 1, 1), new Vector4D(-1, 1, -1, 1), new Vector4D(-1, -1, 1, 1), new Vector4D(-1, -1, -1, 1),
            new Vector4D(1, 1, 1, -1), new Vector4D(1, 1, -1, -1), new Vector4D(1, -1, 1, -1), new Vector4D(1, -1, -1, -1),
            new Vector4D(-1, 1, 1, -1), new Vector4D(-1, 1, -1, -1), new Vector4D(-1, -1, 1, -1), new Vector4D(-1, -1, -1, -1),
            
            new Vector4D(0, 1, 1, 1), new Vector4D(0, 1, -1, 1), new Vector4D(0, -1, 1, 1), new Vector4D(0, -1, -1, 1),
            new Vector4D(0, 1, 1, -1), new Vector4D(0, 1, -1, -1), new Vector4D(0, -1, 1, -1), new Vector4D(0, -1, -1, -1),
            new Vector4D(1, 0, 1, 1), new Vector4D(1, 0, -1, 1), new Vector4D(-1, 0, 1, 1), new Vector4D(-1, 0, -1, 1),
            new Vector4D(1, 0, 1, -1), new Vector4D(1, 0, -1, -1), new Vector4D(-1, 0, 1, -1), new Vector4D(-1, 0, -1, -1),
            new Vector4D(1, 1, 0, 1), new Vector4D(1, -1, 0, 1), new Vector4D(-1, 1, 0, 1), new Vector4D(-1, -1, 0, 1),
            new Vector4D(1, 1, 0, -1), new Vector4D(1, -1, 0, -1), new Vector4D(-1, 1, 0, -1), new Vector4D(-1, -1, 0, -1),
            new Vector4D(1, 1, 1, 0), new Vector4D(1, -1, 1, 0), new Vector4D(-1, 1, 1, 0), new Vector4D(-1, -1, 1, 0),
            new Vector4D(1, 1, -1, 0), new Vector4D(1, -1, -1, 0), new Vector4D(-1, 1, -1, 0), new Vector4D(-1, -1, -1, 0)
    };
    
    private final Interpolation interpolation;
    private final double frequency;
    
    PerlinNoiseGenerator(int seed, @NotNull Interpolation interpolation, double frequency) {
        super(seed);
        this.interpolation = interpolation;
        this.frequency = frequency;
    }
    
    @Override
    public double evaluateNoise(double x) {
        x *= frequency;
        // Find unit square
        int iX = (int) Math.floor(x);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(fade(x));
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(dotProduct(HashUtil.hash1D(seed, iX), x));
        doubles.add(dotProduct(HashUtil.hash1D(seed, iX + 1), x - 1));
        return interpolation.lerp(fractals, doubles);
    }
    
    @Override
    public double evaluateNoise(double x, double y) {
        x *= frequency;
        y *= frequency;
        // Find unit square
        int iX = (int) Math.floor(x);
        int iY = (int) Math.floor(y);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        // Vector2D distanceVector = new Vector2D(x - iX,y - iY);
        x -= Math.floor(x);
        y -= Math.floor(y);
        // Compute fade values (fractal values for interpolation to remove artifacts)
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(fade(x));
        fractals.add(fade(y));
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(dotProduct(HashUtil.hash2D(seed, iX, iY), x, y));
        doubles.add(dotProduct(HashUtil.hash2D(seed, iX + 1, iY), x - 1, y));
        
        doubles.add(dotProduct(HashUtil.hash2D(seed, iX, iY + 1), x, y - 1));
        doubles.add(dotProduct(HashUtil.hash2D(seed, iX + 1, iY + 1), x - 1, y - 1));
        
        return interpolation.lerp(fractals, doubles);
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        // Find unit square
        int iX = (int) Math.floor(x);
        int iY = (int) Math.floor(y);
        int iZ = (int) Math.floor(z);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(fade(x));
        fractals.add(fade(y));
        fractals.add(fade(z));
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX, iY, iZ), x, y, z));
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX + 1, iY, iZ), x - 1, y, z));
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX, iY + 1, iZ), x, y - 1, z));
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX + 1, iY + 1, iZ), x - 1, y - 1, z));
        
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX, iY, iZ + 1), x, y, z - 1));
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX + 1, iY, iZ + 1), x - 1, y, z - 1));
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX, iY + 1, iZ + 1), x, y - 1, z - 1));
        doubles.add(dotProduct(HashUtil.hash3D(seed, iX + 1, iY + 1, iZ + 1), x - 1, y - 1, z - 1));
        return interpolation.lerp(fractals, doubles);
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        // Find unit square
        int iX = (int) Math.floor(x);
        int iY = (int) Math.floor(y);
        int iZ = (int) Math.floor(z);
        int iW = (int) Math.floor(w);
        // Find relative X,Y,Z in the unit cube (distance vector, also: local pixel position of the unit square.).
        x -= iX;
        y -= iY;
        z -= iZ;
        w -= iW;
        // Compute fade values (fractal values for interpolation to remove artifacts)
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(fade(x));
        fractals.add(fade(y));
        fractals.add(fade(z));
        fractals.add(fade(w));
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY, iZ, iW), x, y, z, w));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY, iZ, iW), x - 1, y, z, w));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY + 1, iZ, iW), x, y - 1, z, w));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW), x - 1, y - 1, z, w));
        
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY, iZ + 1, iW), x, y, z - 1, w));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW), x - 1, y, z - 1, w));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW), x, y - 1, z - 1, w));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW), x - 1, y - 1, z - 1, w));
        
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY, iZ, iW + 1), x, y, z, w - 1));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY, iZ, iW + 1), x - 1, y, z, w - 1));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY + 1, iZ, iW + 1), x, y - 1, z, w - 1));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY + 1, iZ, iW + 1), x - 1, y - 1, z, w - 1));
        
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY, iZ + 1, iW + 1), x, y, z - 1, w - 1));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY, iZ + 1, iW + 1), x - 1, y, z - 1, w - 1));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX, iY + 1, iZ + 1, iW + 1), x, y - 1, z - 1, w - 1));
        doubles.add(dotProduct(HashUtil.hash4D(seed, iX + 1, iY + 1, iZ + 1, iW + 1), x - 1, y - 1, z - 1, w - 1));
        
        return interpolation.lerp(fractals, doubles);
    }
    
    /**
     * Fade method used to remove interpolation artifacts.
     *
     * @param t value to fade.
     * @return faded t value.
     */
    private static double fade(double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }
    
    private static double dotProduct(int hash, double x) {
        return ((x * VECTOR_1D[hash & (VECTOR_1D.length - 1)]));
    }
    
    private static double dotProduct(int hash, double x, double y) {
        Vector2D gradientVector = VECTOR_2D[hash & (VECTOR_2D.length - 1)];
        return ((x * gradientVector.getX()) + (y * gradientVector.getY()));
    }
    
    private static double dotProduct(int hash, double x, double y, double z) {
        Vector3D gradientVector = VECTOR_3D[hash & (VECTOR_3D.length - 1)];
        return ((x * gradientVector.getX()) + (y * gradientVector.getY()) + (z * gradientVector.getZ()));
    }
    
    private static double dotProduct(int hash, double x, double y, double z, double w) {
        Vector4D gradientVector = VECTOR_4D[hash & (VECTOR_4D.length - 1)];
        return ((x * gradientVector.getX()) + (y * gradientVector.getY()) + (z * gradientVector.getZ()) + (w * gradientVector.getW()));
    }
}