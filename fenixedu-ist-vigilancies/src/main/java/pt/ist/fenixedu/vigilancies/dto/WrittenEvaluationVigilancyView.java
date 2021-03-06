/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST Vigilancies.
 *
 * FenixEdu IST Vigilancies is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST Vigilancies is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST Vigilancies.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.vigilancies.dto;

import java.util.ArrayList;
import java.util.List;

import org.fenixedu.academic.domain.WrittenEvaluation;

import pt.ist.fenixedu.vigilancies.domain.OtherCourseVigilancy;
import pt.ist.fenixedu.vigilancies.domain.Vigilancy;
import pt.ist.fenixedu.vigilancies.domain.VigilantWrapper;

public class WrittenEvaluationVigilancyView {

    private WrittenEvaluation writtenEvaluation;

    public WrittenEvaluationVigilancyView(WrittenEvaluation evaluation) {
        writtenEvaluation = evaluation;
    }

    public WrittenEvaluation getWrittenEvaluation() {
        return writtenEvaluation;
    }

    public void setWrittenEvaluation(WrittenEvaluation writtenEvaluation) {
        this.writtenEvaluation = writtenEvaluation;
    }

    public int getTotalVigilancies() {
        return getWrittenEvaluation().getVigilanciesSet().size();
    }

    public int getVigilanciesFromTeachers() {
        return getTotalVigilancies() - getVigilanciesFromOthers();
    }

    public int getVigilanciesFromOthers() {
        return Vigilancy.getOthersVigilancies(getWrittenEvaluation()).size();
    }

    public List<VigilantWrapper> getTeachersVigilants() {
        List<VigilantWrapper> vigilants = new ArrayList<VigilantWrapper>();
        for (Vigilancy vigilancy : Vigilancy.getTeachersVigilancies(getWrittenEvaluation())) {
            vigilants.add(vigilancy.getVigilantWrapper());
        }
        return vigilants;
    }

    public List<VigilantWrapper> getOtherVigilants() {
        List<VigilantWrapper> vigilants = new ArrayList<VigilantWrapper>();
        for (Vigilancy vigilancy : Vigilancy.getOthersVigilancies(getWrittenEvaluation())) {
            vigilants.add(vigilancy.getVigilantWrapper());
        }
        return vigilants;
    }

    public List<VigilantWrapper> getCancelledConvokes() {
        List<VigilantWrapper> vigilants = new ArrayList<VigilantWrapper>();
        for (Vigilancy vigilancy : getWrittenEvaluation().getVigilanciesSet()) {
            if (!vigilancy.isActive()) {
                vigilants.add(vigilancy.getVigilantWrapper());
            }
        }
        return vigilants;
    }

    public int getNumberOfCancelledConvokes() {
        return getCancelledConvokes().size();
    }

    public List<VigilantWrapper> getConfirmedConvokes() {
        List<VigilantWrapper> vigilants = new ArrayList<VigilantWrapper>();
        for (Vigilancy vigilancy : Vigilancy.getOthersVigilancies(getWrittenEvaluation())) {
            OtherCourseVigilancy otherCourseVigilancy = (OtherCourseVigilancy) vigilancy;
            if (otherCourseVigilancy.isConfirmed()) {
                vigilants.add(otherCourseVigilancy.getVigilantWrapper());
            }
        }
        return vigilants;
    }

    public int getNumberOfConfirmedConvokes() {
        return getConfirmedConvokes().size();
    }

    public List<VigilantWrapper> getAttendedConvokes() {
        List<VigilantWrapper> vigilants = new ArrayList<VigilantWrapper>();
        for (Vigilancy vigilancy : getWrittenEvaluation().getVigilanciesSet()) {
            if (vigilancy.isAttended()) {
                vigilants.add(vigilancy.getVigilantWrapper());
            }
        }
        return vigilants;
    }

    public int getNumberOfAttendedConvokes() {
        return getAttendedConvokes().size();
    }

}
