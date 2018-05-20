 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="row informs-wrapper">
	<div class="col-sm-6 col-sm-offset-6 number-projects">
		<div class="number">${fn:length(project_list)}</div>
		<div class="text">Projects</div>
	</div>
</div>

<div class="row informs-list-wrapper">
	<ul> 
		<li class="item-list-inform header">
			<div class="title">Nombre del proyecto</div>
			<div class="code">Project Code</div>
			<div class="link" style="flex: 1; text-align: right;">Link</div>
		</li>
		<div class="items-list">
			<c:forEach items="${project_list}" var="project">
				<form id="generate-${project.project_code}" class="hidden" method="post" target="_blank" action="PDFServlet">
     				<input type="hidden" name="project_id" value="${project.id}" />
				</form>
				<form id="view-${project.project_code}" class="hidden" method="post" target="_blank" action="ShowPDFServlet">
     				<input type="hidden" name="project_id" value="${project.id}" />
				</form>
				<li class="item-list-inform">
					<div class="title">${project.titulo}</div>
					<div class="code">${project.project_code}</div>
					<div class="download">
						<button type="submit" form="generate-${project.project_code}"><span class="glyphicon glyphicon-open"></span></button>
						<c:if test="${not empty project.document}">
							<button type="submit" form="view-${project.project_code}"><span class="glyphicon glyphicon-eye-open"></span></button>
						</c:if>
					</div>
				</li>
			</c:forEach>
		</div>
	</ul>
</div>