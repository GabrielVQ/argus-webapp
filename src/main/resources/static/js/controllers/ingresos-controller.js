app.controller('ingresosController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','servicioNumeroBLHouse', function($scope, $location, $http,$window, servicioNumeroBL,servicioNumeroBLHouse) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

   // $scope.numeroBLHouse = servicioNumeroBLHouse.numeroBLHouse;


    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newIngresos = {
        "numeroOperacion": $scope.numeroOperacion,
        "numeroBLHouse": $scope.numeroBLHouse,
        "facturar": "",
        "impuesto":"",
        "facturara": 0,
        "cobro":0,
        "llevarFormulario": "",
        "prepaid": 0,
        "collect":0,
        "blHouse":0

    }

    var urlBase1 = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBase1)
        .then(function(response) {
            $scope.BLHouseId = response.data[0].id;
            // console.log('status: ',response.status);
        });

    $scope.send3 = function(){

        $scope.newIngresos.blHouse= {"id":parseInt($scope.BLHouseId)};
        $scope.newIngresos.cobro= {"id":parseInt($scope.newIngresos.cobro)};
        $scope.newIngresos.facturara = {"id":parseInt($scope.newIngresos.facturara)};
        $http.post("http://localhost:8080/ingresos",$scope.newIngresos);
        $scope.mensaje = 'Ingresos agregado con exito!';
        $window.alert($scope.mensaje);

        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

    $scope.numeroBLMaster = localStorage.getItem("token4");
    $scope.numeroOperacion = localStorage.getItem("token");
    $scope.numeroBLHouse = localStorage.getItem("token3");
    console.log("controlador ingresos")

    var urlBaseIngreso = 'http://localhost:8080/ingresos/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBaseIngreso)
        .then(function(response) {
            $scope.ingresosBYoperacion = response.data;
        });

    $http.get('http://localhost:8080/containers').then(function(response){
        $scope.containers = response.data;
        console.log($scope.containers);
    })

    $http.get('http://localhost:8080/empresas').then(function(response){ // campo: agente
        $scope.empresas = response.data;
        //console.log($scope.puertos);
    })
    $http.get('http://localhost:8080/cobros').then(function(response){ // campo: agente
        $scope.cobros = response.data;
        //console.log($scope.puertos);
    })
}]);