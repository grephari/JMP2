<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<!-- let's have bootstrap styling -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />

<c:set var="pageTitle" value="Accident List" />
<title>${pageTitle}</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>${pageTitle}</h1>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>Date/Time</th>
					<th>Longitude</th>
					<th>Latitude</th>
					<th>#. Vehicles</th>
					<th>#. Casualties</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="accident" items="${accidentList}">
					<tr>
						<td><a href="edit?id=${accident.accidentIndex}">${accident.accidentIndex}</a></td>
						<td>${accident.date}&nbsp;${accident.time}</td>
						<td>${accident.longitude}</td>
						<td>${accident.latitude}</td>
						<td>${accident.numberOfVehicles}</td>
						<td>${accident.numberOfCasualties}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="add" class="btn btn-primary"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Add</a>
	</div>
</body>
</html>