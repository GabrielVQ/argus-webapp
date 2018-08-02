app.controller('partnerController', ['$scope', '$location', function($scope, $location) {
    $scope.title = "Inicio";
    $scope.nombre_usuario = "costa";

	$scope.isActive = function(route) {
        return route === $location.path();
    }
}]);