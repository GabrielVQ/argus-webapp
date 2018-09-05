app.controller('ingresosController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL','servicioNumeroBLHouse','$routeParams', function($scope, $location, $http,$window, servicioNumeroBL,servicioNumeroBLHouse, $routeParams) {
    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    //$scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

   // $scope.numeroBLHouse = servicioNumeroBLHouse.numeroBLHouse;

    $scope.numeroOperacion = $routeParams.bl;
    $scope.numeroBLHouse = $routeParams.blhouse;

    var urlBase4 = 'http://localhost:8080/blmasters/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBase4).then(function(response){
        console.log('entre numero master');
        $scope.blMaster = response.data;
        $scope.numeroBLMaster = $scope.blMaster[0].blmasterNumero;
        console.log('numero master:', $scope.numeroBLMaster);
    })

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
        "prepaid": "0",
        "collect":"0",
        "blHouse":0,
        "costo":"0"

    }

    var urlBase1 = 'http://localhost:8080/blhouses/numerooperacion/'+$scope.numeroOperacion;

    $http.get(urlBase1)
        .then(function(response) {
            $scope.BLHouseId = response.data[0].id;
            // console.log('status: ',response.status);
        });

    var urlBaseIngreso = 'http://localhost:8080/ingresos/numerooperacion/'+$scope.numeroOperacion;

    $scope.send3 = function(){

        $scope.newIngresos.blHouse= {"id":parseInt($scope.BLHouseId)};
        $scope.newIngresos.cobro= {"id":parseInt($scope.newIngresos.cobro)};
        $scope.newIngresos.facturara = {"id":parseInt($scope.newIngresos.facturara)};
        $scope.newIngresos.prepaid = parseFloat($scope.newIngresos.prepaid).toFixed(2);
        $scope.newIngresos.collect = parseFloat($scope.newIngresos.collect).toFixed(2);
        $scope.newIngresos.costo = parseFloat($scope.newIngresos.costo).toFixed(2);

        $http.post("http://localhost:8080/ingresos",$scope.newIngresos);
        $scope.mensaje = 'Ingresos agregado con exito!';
        $window.alert($scope.mensaje);

        $http.get(urlBaseIngreso)
        .then(function(response) {
            $scope.ingresosBYoperacion = response.data;
            console.log("ingresos por operacion ADENTRO ",$scope.ingresosBYoperacion);
        });

        //aca newVotation esta listo para ser utilizado en el método POST, en teoría
        $window.location.reload();
    }



   // console.log("controlador ingresos")

    $scope.borrar = function(id){
        var opcion = confirm("¿Seguro que desea eliminar el ingreso?");
        if (opcion === true) {
            $http.delete("http://localhost:8080/ingresos/ingresoBorrar/" + id);
            $window.alert("Ingreso eliminado");
            $window.location.reload();
        }
    }

    $http.get(urlBaseIngreso)
        .then(function(response) {
            $scope.ingresosBYoperacion = response.data;
            console.log("ingresos por operacion AFUERA ",$scope.ingresosBYoperacion);
        });

    $http.get('http://localhost:8080/containers').then(function(response){
        $scope.containers = response.data;
        //console.log($scope.containers);
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