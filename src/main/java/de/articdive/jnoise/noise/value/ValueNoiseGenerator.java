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

package de.articdive.jnoise.noise.value;

import de.articdive.jnoise.api.Interpolation;
import de.articdive.jnoise.api.NoiseGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

import static de.articdive.jnoise.util.HashUtil.W_PRIME;
import static de.articdive.jnoise.util.HashUtil.X_PRIME;
import static de.articdive.jnoise.util.HashUtil.Y_PRIME;
import static de.articdive.jnoise.util.HashUtil.Z_PRIME;

/**
 * The bounds of this Value Noise implementation are: [-1, 1].
 *
 * @author Lukas Mansour
 */
public final class ValueNoiseGenerator extends NoiseGenerator {
    private final Interpolation interpolation;
    private final double frequency;
    
    ValueNoiseGenerator(int seed, @NotNull Interpolation interpolation, double frequency) {
        super(seed);
        this.interpolation = interpolation;
        this.frequency = frequency;
    }
    
    @Override
    public double evaluateNoise(double x) {
        x *= frequency;
        int iX = (int) Math.floor(x);
        x -= iX;
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(x);
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(evaluateCoord1D(iX));
        doubles.add(evaluateCoord1D(iX + 1));
        return interpolation.lerp(fractals, doubles);
    }
    
    @Override
    public double evaluateNoise(double x, double y) {
        x *= frequency;
        y *= frequency;
        int iX = (int) Math.floor(x);
        int iY = (int) Math.floor(y);
        x -= iX;
        y -= iY;
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(x);
        fractals.add(y);
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(evaluateCoord2D(iX, iY));
        doubles.add(evaluateCoord2D(iX + 1, iY));
        doubles.add(evaluateCoord2D(iX, iY + 1));
        doubles.add(evaluateCoord2D(iX + 1, iY + 1));
        return interpolation.lerp(fractals, doubles);
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        int iX = (int) Math.floor(x);
        int iY = (int) Math.floor(y);
        int iZ = (int) Math.floor(z);
        x -= iX;
        y -= iY;
        z -= iZ;
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(x);
        fractals.add(y);
        fractals.add(z);
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(evaluateCoord3D(iX, iY, iZ));
        doubles.add(evaluateCoord3D(iX + 1, iY, iZ));
        doubles.add(evaluateCoord3D(iX, iY + 1, iZ));
        doubles.add(evaluateCoord3D(iX + 1, iY + 1, iZ));
        
        doubles.add(evaluateCoord3D(iX, iY, iZ + 1));
        doubles.add(evaluateCoord3D(iX + 1, iY, iZ + 1));
        doubles.add(evaluateCoord3D(iX, iY + 1, iZ + 1));
        doubles.add(evaluateCoord3D(iX + 1, iY + 1, iZ + 1));
        return interpolation.lerp(fractals, doubles);
    }
    
    @Override
    public double evaluateNoise(double x, double y, double z, double w) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        int iX = (int) Math.floor(x);
        int iY = (int) Math.floor(y);
        int iZ = (int) Math.floor(z);
        int iW = (int) Math.floor(w);
        x -= iX;
        y -= iY;
        z -= iZ;
        w -= iW;
        LinkedList<Double> fractals = new LinkedList<>();
        fractals.add(x);
        fractals.add(y);
        fractals.add(z);
        fractals.add(w);
        LinkedList<Double> doubles = new LinkedList<>();
        doubles.add(evaluateCoord4D(iX, iY, iZ, iW));
        doubles.add(evaluateCoord4D(iX + 1, iY, iZ, iW));
        doubles.add(evaluateCoord4D(iX, iY + 1, iZ, iW));
        doubles.add(evaluateCoord4D(iX + 1, iY + 1, iZ, iW));
        
        doubles.add(evaluateCoord4D(iX, iY, iZ + 1, iW));
        doubles.add(evaluateCoord4D(iX + 1, iY, iZ + 1, iW));
        doubles.add(evaluateCoord4D(iX, iY + 1, iZ + 1, iW));
        doubles.add(evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW));
        
        doubles.add(evaluateCoord4D(iX, iY, iZ, iW + 1));
        doubles.add(evaluateCoord4D(iX + 1, iY, iZ, iW + 1));
        doubles.add(evaluateCoord4D(iX, iY + 1, iZ, iW + 1));
        doubles.add(evaluateCoord4D(iX + 1, iY + 1, iZ, iW + 1));
        
        doubles.add(evaluateCoord4D(iX, iY, iZ + 1, iW + 1));
        doubles.add(evaluateCoord4D(iX + 1, iY, iZ + 1, iW + 1));
        doubles.add(evaluateCoord4D(iX, iY + 1, iZ + 1, iW + 1));
        doubles.add(evaluateCoord4D(iX + 1, iY + 1, iZ + 1, iW + 1));
        return interpolation.lerp(fractals, doubles);
    }
    
    private double evaluateCoord1D(int x) {
        int n = seed ^ (X_PRIME * x);
        
        return (n * n * n * 60493) / 2147483648.0;
    }
    
    private double evaluateCoord2D(int x, int y) {
        int n = seed ^ (X_PRIME * x);
        n ^= Y_PRIME * y;
        
        return (n * n * n * 60493) / 2147483648.0;
    }
    
    private double evaluateCoord3D(int x, int y, int z) {
        int n = seed ^ (X_PRIME * x);
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;
        
        return (n * n * n * 60493) / 2147483648.0;
    }
    
    private double evaluateCoord4D(int x, int y, int z, int w) {
        int n = seed ^ (X_PRIME * x);
        n ^= Y_PRIME * y;
        n ^= Z_PRIME * z;
        n ^= W_PRIME * w;
        
        return (n * n * n * 60493) / 2147483648.0;
    }
    
}
