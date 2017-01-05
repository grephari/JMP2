app.controller('accident', function($scope, $http, $resource) {

	$scope.currentPage = 0;
	$scope.pageSize = 10;
	$scope.data = [];
	$scope.numberOfPages = function() {
		return Math.ceil($scope.data.length / $scope.pageSize);
	}
	for (var i = 0; i < 150; i++) {
		$scope.data.push("Item " + i);
	}

	$http.get('http://localhost:8080/accidents').then(function(response) {
		$scope.accidentData = response;
	});

	$scope.selected = {};

	$scope.getTemplate = function(accidentData) {
		if (accidentData.accidentId === $scope.selected.accidentId) {
			return 'edit';
		} else
			return 'display';
	};

	$scope.editAccidentData = function(accidentData) {
		$scope.selected = angular.copy(accidentData);
	};

	$scope.reset = function() {
		$scope.selected = {};
	};

	$scope.updateAccidentData = function(accidentData) {
		var Accident = $resource('http://localhost:8080/update', {}, {
			save : {
				method : 'PUT',
				headers : {
					'Content-Type' : 'application/json'
				}
			}
		});
		Accident.save(accidentData, function(response) {
			$scope.accidentData = response;
			alert("Record updated successfully for accident id - "+accidentData.accidentId);
		});
	};

	$scope.deleteAccidentData = function(accidentData) {
		$http({
			url : "http://localhost:8080/deleteAccidentData/" + accidentData.accidentId,
			method : "DELETE"
		}).then(function(response) {
			$scope.accidentData = response;
			alert("Record deleted successfully for accident id - "+accidentData.accidentId);
		});
	};

});

app.filter('startFrom', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	}
});
