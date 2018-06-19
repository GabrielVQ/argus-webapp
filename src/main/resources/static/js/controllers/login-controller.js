app.controller('loginController', ['$scope', '$location', '$http','$window','$interval', function($scope, $location, $http,$window,ConsultaService,digitalClock, MisDatos) {

    $scope.date = new Date();




    $scope.send = function(){
        
        //$scope.numeroOperacion += 1;


        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    /*$scope.ciudades = function() {
        $http.get('http://localhost:8080/ciudades').then(function (response) {
            $scope.ciudades = response.data;});
            //console.log($scope.ciudades[0]);
    }*/




}]);