app.controller('descripcionController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL', function($scope, $location, $http,$window, servicioNumeroBL) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();




    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newDescripcion = {
        "numeroOperacion": $scope.numeroOperacion,
        "blHouse":0,
        "contenedor":0,
        "markNumbers":"",
        "numerPackages":"",
        "groosWeight":"",
        "measurement":"",
        "descriptionGoods":""
    }

    $scope.send = function(){

        $scope.newDescripcion.blHouse= {"id":parseInt(1)};
        $scope.newDescripcion.contenedor= {"id":parseInt($scope.newDescripcion.contenedor)};



        $http.post("http://localhost:8080/cargaments",$scope.newDescripcion);
        $scope.mensaje = 'Descripción agregada con exito!';
        $window.alert($scope.mensaje);

        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    $http.get('http://localhost:8080/containers').then(function(response){
        $scope.containers = response.data;
        //console.log($scope.containers);
    })

    $http.get('http://localhost:8080/cargaments').then(function(response){
        $scope.cargaments = response.data;
        console.log($scope.cargaments);
    })
}]);