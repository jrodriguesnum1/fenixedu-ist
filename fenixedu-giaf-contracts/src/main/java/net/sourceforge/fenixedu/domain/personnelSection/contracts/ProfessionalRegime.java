/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Core.
 *
 * FenixEdu Core is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Core is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Core.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.academic.domain.personnelSection.contracts;

import java.math.BigDecimal;

import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.domain.teacher.CategoryType;

import org.fenixedu.bennu.core.domain.Bennu;

import pt.ist.fenixframework.Atomic;
import pt.utl.ist.fenix.tools.util.i18n.MultiLanguageString;

public class ProfessionalRegime extends ProfessionalRegime_Base {

    public ProfessionalRegime(final String giafId, final MultiLanguageString name, final Integer weighting,
            final BigDecimal fullTimeEquivalent, final CategoryType categoryType) {
        super();
        String[] args1 = {};
        if (giafId == null || giafId.isEmpty()) {
            throw new DomainException("", args1);
        }
        String[] args = {};
        if (name == null) {
            throw new DomainException("", args);
        }
        setRootDomainObject(Bennu.getInstance());
        setGiafId(giafId);
        setName(name);
        setWeighting(weighting);
        setFullTimeEquivalent(fullTimeEquivalent);
        setCategoryType(categoryType);
    }

    @Atomic
    public void edit(final MultiLanguageString name, final Integer weighting, final BigDecimal fullTimeEquivalent,
            final CategoryType categoryType) {
        String[] args = {};
        if (name == null) {
            throw new DomainException("", args);
        }
        setName(name);
        setWeighting(weighting);
        setFullTimeEquivalent(fullTimeEquivalent);
        setCategoryType(categoryType);
    }
}
