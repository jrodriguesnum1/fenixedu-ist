<%--

    Copyright © 2013 Instituto Superior Técnico

    This file is part of FenixEdu IST Integration.

    FenixEdu IST Integration is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    FenixEdu IST Integration is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with FenixEdu IST Integration.  If not, see <http://www.gnu.org/licenses/>.

--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<html:html xhtml="true">
	<head>
		<title>
			<bean:message key="dot.title" bundle="GLOBAL_RESOURCES" /> - <bean:message key="message.inquiries.title" bundle="INQUIRIES_RESOURCES"/>
		</title>

		<link href="<%= request.getContextPath() %>/CSS/logdotist.css" rel="stylesheet" type="text/css" />

		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>
	<body>
		<div id="container">
			<div id="dotist_id">
				<img alt="<%=org.fenixedu.bennu.portal.domain.PortalConfiguration.getInstance().getApplicationTitle().getContent() %>"
						src="<bean:message key="dot.logo" bundle="GLOBAL_RESOURCES" arg0="<%= request.getContextPath() %>"/>" />
			</div>
			<div id="txt">
				<h1><bean:message key="message.inquiries.title" bundle="INQUIRIES_RESOURCES"/></h1>
				<div class="mtop1">
					<bean:write name="inquiryTemplate" property="inquiryMessage" filter="false"/>
				</div>
				

				<div class="infoop2">
					<p style="margin: 0.5em 0 0.75em 0;"><strong><bean:message key="label.regentInquiries.coursesToAnswer" bundle="INQUIRIES_RESOURCES"/>:</strong></p>
	
					<logic:iterate id="executionCourse" name="executionCourses">
						<p style="margin: 0 0 0.5em 0;">
							<bean:write name="executionCourse" property="executionPeriod.semester" />
							<bean:message bundle="PUBLIC_DEGREE_INFORMATION" locale="pt_PT" key="public.degree.information.label.ordinal.semester.abbr" />
							<bean:write name="executionCourse" property="executionPeriod.executionYear.year" />				
							<html:link page="/teacher/regentInquiry.do?method=showInquiriesPrePage" paramId="executionCourseID" paramName="executionCourse" paramProperty="externalId">
								<strong>
									<bean:write name="executionCourse" property="nome"/>
									(<logic:iterate id="executionDegree" name="executionCourse" property="executionDegrees" indexId="iter">
										<logic:notEqual name="iter" value="0">
										,
										</logic:notEqual>
										<bean:write name="executionDegree" property="degree.sigla"/>
									</logic:iterate>)
									»
								</strong>
							</html:link>
						</p>
					</logic:iterate>
				</div>

				
				<form method="post" action="<%= request.getContextPath() %>/respondToRegentInquiriesQuestion.do">
					<html:hidden property="method" value="respondLater"/>
					<p style="margin-top: 2.5em; text-align: center;">
						<html:submit bundle="HTMLALT_RESOURCES" altKey="inquiries.respond.later" property="ok">
							<bean:message key="button.inquiries.respond.later" />
						</html:submit>
					</p>
				</form>
			</div>
		</div>
	</body>
</html:html>
