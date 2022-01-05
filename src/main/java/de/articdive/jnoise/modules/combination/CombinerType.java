/*
 * JNoise
 * Copyright (C) 2021 Articdive
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

package de.articdive.jnoise.modules.combination;

/**
 * @author Articdive
 */
public enum CombinerType implements Combiner {
    /**
     * @deprecated Use {@link Combiner#ADD}
     */
    @Deprecated
    ADD {
        @Override
        public double combine(double main, double ext) {
            return main + ext;
        }
    },
    /**
     * @deprecated Use {@link Combiner#MULTIPLY}
     */
    @Deprecated
    MULTIPLY {
        @Override
        public double combine(double main, double ext) {
            return main * ext;
        }
    },
    /**
     * @deprecated Use {@link Combiner#MAX}
     */
    @Deprecated
    MAX {
        @Override
        public double combine(double main, double ext) {
            return Math.max(main, ext);
        }
    },
    /**
     * @deprecated Use {@link Combiner#MIN}
     */
    @Deprecated
    MIN {
        @Override
        public double combine(double main, double ext) {
            return Math.min(main, ext);
        }
    },
    /**
     * @deprecated Use {@link Combiner#POW}
     */
    @Deprecated
    POW {
        @Override
        public double combine(double main, double ext) {
            return Math.pow(main, ext);
        }
    },
}
