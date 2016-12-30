<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- let's have bootstrap styling -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous" />
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<c:set var="pageTitle" value="Accident Detail" />
<title>${pageTitle}</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
			<h1>${pageTitle}</h1>
		</div>
		<form class="form-horizontal" id="accidentForm">
			<div class="form-group">
				<label class="col-sm-2 control-label">ID</label>
				<div class="col-sm-10">
					<p class="form-control-static">${accident.accidentId}</p>
					<input type="hidden" name="accidentId" value="${accident.accidentId}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Date/Time</label>
				<div class="col-sm-10">
					<p class="form-control-static">${accident.date}&nbsp;${accident.time}</p>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Longitude</label>
				<div class="col-sm-10">
					<p class="form-control-static">${accident.longitude}</p>
					<input type="hidden" name="longitude" value="${accident.longitude}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Latitude</label>
				<div class="col-sm-10">
					<p class="form-control-static">${accident.latitude}</p>
					<input type="hidden" name="latitude" value="${accident.latitude}" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Number of Vehicles</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" name="numberOfVehicles" min="0" value="${accident.numberOfVehicles}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">Number of Casualties</label>
				<div class="col-sm-10">
					<input type="number" class="form-control" name="numberOfCasualties" min="0" value="${accident.numberOfCasualties}">
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="button" class="btn btn-primary" id="saveAccidentBtn">
						<span class="glyphicon glyphicon-ok" aria-hidden="true"></span> Save
					</button>
					<a href="list" class="btn btn-default"><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						Back </a>
				</div>
			</div>
		</form>
	</div>

	<div class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Modal title</h4>
				</div>
				<div class="modal-body">
					<p>One fine body&hellip;</p>
				</div>
				<div class="modal-footer">
					<a href="list" class="btn btn-default"><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						Back to list view</a>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Stay on this page</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(
		function() {
			$('#saveAccidentBtn').click(
				function() {
					var form = $('form#accidentForm');
					var formData = {
						"accidentId" : form.find('input[name="accidentId"]').val(),
						"longitude" : parseFloat(form.find('input[name="longitude"]').val()),
						"latitude" : parseFloat(form.find('input[name="latitude"]').val()),
						"numberOfVehicles" : parseInt(form.find('input[name="numberOfVehicles"]').val()),
						"numberOfCasualties" : parseInt(form.find('input[name="numberOfCasualties"]').val())
					};
					$.ajax({
						type : 'POST',
						url : 'roadaccidentmgmt/accidents/' + formData.accidentId,
						contentType : 'application/json',
						data : JSON.stringify(formData),
						success : function(data, textStatus, jqXHR) {
							$('.modal .modal-title').text('Success!');
							$('.modal .modal-body>p').text('Accident "' + formData.accidentId + '" is saved.');
							$('.modal').modal('show');
						},
						error : function(jqXHR, textStatus, errorThrown) {
							console.log(errorThrown);
							$('.modal .modal-title').text('Error!');
							$('.modal .modal-body>p').text(
								'Failed saving accident "' + formData.accidentId + '", please try again later..');
							$('.modal').modal('show');
						}
					});
				});
		});
</script>
</html>