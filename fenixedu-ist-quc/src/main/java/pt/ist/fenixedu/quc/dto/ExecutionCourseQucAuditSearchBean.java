/**
 * Copyright © 2013 Instituto Superior Técnico
 *
 * This file is part of FenixEdu IST QUC.
 *
 * FenixEdu IST QUC is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu IST QUC is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu IST QUC.  If not, see <http://www.gnu.org/licenses/>.
 */
package pt.ist.fenixedu.quc.dto;

import java.util.Collection;

import org.fenixedu.academic.domain.ExecutionCourse;
import org.fenixedu.academic.domain.ExecutionDegree;
import org.fenixedu.academic.domain.ExecutionSemester;
import org.fenixedu.academic.domain.executionCourse.ExecutionCourseSearchBean;

import pt.ist.fenixedu.quc.domain.InquiryResult;
import pt.ist.fenixedu.quc.domain.InquiryResultType;

public class ExecutionCourseQucAuditSearchBean extends ExecutionCourseSearchBean {

    private static final long serialVersionUID = 1L;
    private ExecutionCourse selectedExecutionCourse;

    @Override
    public Collection<ExecutionCourse> search(final Collection<ExecutionCourse> result) {
        final ExecutionDegree executionDegree = getExecutionDegree();
        final ExecutionSemester executionSemester = getExecutionPeriod();

        if (executionDegree == null && executionSemester != null) {
            for (InquiryResult inquiryResult : executionSemester.getInquiryResultsSet()) {
                if (InquiryResultType.AUDIT.equals(inquiryResult.getResultType())) {
                    result.add(inquiryResult.getExecutionCourse());
                }
            }
            return result;
        } else {
            return super.search(result);
        }
    }

    public void setSelectedExecutionCourse(ExecutionCourse selectedExecutionCourse) {
        this.selectedExecutionCourse = selectedExecutionCourse;
    }

    public ExecutionCourse getSelectedExecutionCourse() {
        return selectedExecutionCourse;
    }
}
