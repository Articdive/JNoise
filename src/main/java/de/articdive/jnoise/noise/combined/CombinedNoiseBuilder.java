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

package de.articdive.jnoise.noise.combined;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.api.NoiseBuilder;
import de.articdive.jnoise.api.Operator;
import de.articdive.jnoise.operators.OperatorType;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Lukas Mansour
 */
public final class CombinedNoiseBuilder extends NoiseBuilder<CombinedNoiseBuilder> {
    private List<JNoise> noises = new LinkedList<>();
    private Operator operatorType = OperatorType.ADD;
    
    @Override
    @NotNull
    protected CombinedNoiseBuilder self() {
        return this;
    }
    
    /**
     * Sets the list of noises that will be combined
     *
     * @param noises the new list of {@link JNoise} for the {@link CombinedNoiseGenerator}
     * @return {@link CombinedNoiseGenerator} this
     */
    @NotNull
    public CombinedNoiseBuilder setNoises(@NotNull List<JNoise> noises) {
        this.noises = noises;
        return this;
    }
    
    /**
     * Adds a noise generator to the combined noise generator.
     *
     * @param noise a new {@link JNoise} for the {@link CombinedNoiseGenerator}
     * @return {@link CombinedNoiseGenerator} this
     */
    @NotNull
    public CombinedNoiseBuilder addNoise(@NotNull JNoise noise) {
        noises.add(noise);
        return this;
    }
    
    /**
     * Sets the operator type that will be combining the noise values.
     *
     * @param operatorType the new {@link OperatorType} for the {@link CombinedNoiseGenerator}
     * @return {@link CombinedNoiseGenerator} this
     */
    @NotNull
    public CombinedNoiseBuilder setOperatorType(@NotNull OperatorType operatorType) {
        this.operatorType = operatorType;
        return this;
    }
    
    
    @Override
    @NotNull
    public JNoise build() {
        return JNoise.build(new CombinedNoiseGenerator(seed, noises, operatorType));
    }
}
