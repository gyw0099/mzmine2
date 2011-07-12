/*
 * Copyright 2006-2011 The MZmine 2 Development Team
 * 
 * This file is part of MZmine 2.
 * 
 * MZmine 2 is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * MZmine 2 is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * MZmine 2; if not, write to the Free Software Foundation, Inc., 51 Franklin St,
 * Fifth Floor, Boston, MA 02110-1301 USA
 */

package net.sf.mzmine.modules.peaklistmethods.identification.formulaprediction.restictions;

import java.util.HashMap;
import java.util.Map;

import net.sf.mzmine.parameters.ParameterSet;
import net.sf.mzmine.util.Range;

import org.openscience.cdk.formula.MolecularFormula;
import org.openscience.cdk.interfaces.IIsotope;

public class RDBERestrictionChecker {

	/**
	 * This table defines the ground valence states. Typically, in most
	 * molecules atoms will have the lowest (ground) valence.
	 */
	private static final Map<String, Integer> valences = new HashMap<String, Integer>();
	static {
		valences.put("H", 1);
		valences.put("C", 4);
		valences.put("N", 3);
		valences.put("O", 2);
		valences.put("F", 1);
		valences.put("Si", 4);
		valences.put("P", 3);
		valences.put("S", 2);
		valences.put("Cl", 1);
		valences.put("Br", 1);
	}

	/**
	 * Calculates possible RDBE (degree of unsaturation) values according to the
	 * formula:
	 * 
	 * RDBE = 1 + Sum(ni x vi - 2) / 2
	 * 
	 * where ni is the number of atoms with valence vi. If multiple valences are
	 * allowed (e.g. N may have valence 3 or 5), there may be multiple results
	 * for RDBE.
	 * 
	 */
	public static double calculateRDBE(MolecularFormula formula) {

		double sum = 0;
		
		for (IIsotope isotope : formula.isotopes()) {

			Integer maxValence = valences.get(isotope.getSymbol());
			if (maxValence == null)
				continue;
			sum += (maxValence - 2) * formula.getIsotopeCount(isotope);
		}

		sum /= 2;
		sum += 1;

		return sum;
	}

	public static boolean checkRDBE(double rdbeValue, ParameterSet parameters) {

		boolean mustBeInteger = parameters.getParameter(
				RDBERestrictionParameters.rdbeWholeNum).getValue();
		Range rdbeRange = parameters.getParameter(
				RDBERestrictionParameters.rdbeRange).getValue();

		if ((mustBeInteger) && (Math.floor(rdbeValue) != rdbeValue))
			return false;

		return rdbeRange.contains(rdbeValue);

	}

}