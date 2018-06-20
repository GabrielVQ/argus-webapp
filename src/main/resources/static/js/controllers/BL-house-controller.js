app.controller('BLHouseController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL', function($scope, $location, $http,$window, servicioNumeroBL) {


    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = "123";
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();
    $scope.numeroBLMaster = servicioNumeroBL.numeroBL;



    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newBLHouse= {

        "numeroOperacion": "ARG00"+$scope.numeroOperacion,
        "tipoHouse": "",
        "shipper":"",
        "clienteExtranjero":"",
        "notify":"",
        "ppcc":"",
        "moneda":"",
        "bultos":"",
        "carga":"",
        "kilos":"",
        "volumen":"",
        "flete":"",
        "almacenista":"",
        "preCarriage":"",
        "lugarRecepcion":"",
        "destino":"",
        "tipoMovimiento":"",
        "contacto":"",
        "telefono":"",
        "fechaStacking":"",
        "horaStacking":" ",
        "observacion":""
    };

    //peticion de servicios

    $http.get('http://localhost:8080/ciudades').then(function(response){  // campo: destino
        $scope.ciudades = response.data;
        //console.log($scope.ciudades);
    })

    $http.get('http://localhost:8080/navieras').then(function(response){
        $scope.navieras = response.data;
        //console.log($scope.navieras);
    })

    $http.get('http://localhost:8080/naves').then(function(response){
        $scope.naves = response.data;
        //console.log($scope.naves);
    })

    $http.get('http://localhost:8080/puertos').then(function(response){   //campos: pto origen y descarga
        $scope.puertos = response.data;
        //console.log($scope.puertos);
    })

    $http.get('http://localhost:8080/empresas').then(function(response){ // campo: agente
        $scope.empresas = response.data;
        //console.log($scope.puertos);
    })
}]);