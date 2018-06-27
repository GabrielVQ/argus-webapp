app.controller('BLHouseController', ['$scope', '$location', '$http','$window', 'servicioNumeroBL', function($scope, $location, $http,$window, servicioNumeroBL) {


    $scope.nombre = 'Nacho';
    $scope.tipoBL = ['Exportación', 'Importación'];
    $scope.numeroOperacion = servicioNumeroBL.numeroBL;
    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();
    $scope.numeroBLMaster = "123";

    var d = new Date();
    var fechaIngreso = d.getDate()  + "/" + (d.getMonth()+1) + "/" + d.getFullYear() + " a las " + ("0" + d.getHours()).slice(-2) + ":" + ("0" + d.getMinutes()).slice(-2);

    function parseFecha(date){
        var fecha = date.getDate()  + "/" + (date.getMonth()+1) + "/" + date.getFullYear() + " a las " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2);
        return fecha;
    }
    $scope.isActive = function(route) {
        return route === $location.path();

    }

    $scope.isActive = function(route) {
        return route === $location.path();
    }

    $scope.newBLHouse= {

        "tipoHouse":"",
        "shipper":0,
        "clienteExtranjero":0,
        "notify":0,
        "ppcc":"",
        "moneda":"",
        "bultos":"",
        "carga":"",
        "kilos":"",
        "volumen":"",
        "flete":"",
        "almacenista":0,
        "preCarriage":"",
        "lugarRecepcion":"",
        "destino":"", //ciudad llegada
        "tipoMovimiento":"",
        "contacto":"",
        "telefono":"",
        "fechaStacking":"",
        "observacion":""
    };

    $scope.send = function(){

        $scope.newBLHouse.shipper= {"id":parseInt($scope.newBLHouse.shipper)};
        $scope.newBLHouse.clienteExtranjero= {"id":parseInt($scope.newBLHouse.clienteExtranjero)};
        $scope.newBLHouse.notify= {"id":parseInt($scope.newBLHouse.notify)};
        $scope.newBLHouse.almacenista= {"id":parseInt($scope.newBLHouse.almacenista)};
        /*$scope.newBLMaster.destino= {"id":parseInt($scope.newBLMaster.destino)};*/

        $scope.newBLHouse.fechaStacking =  parseFecha($scope.newBLHouse.fechaStacking);
        //$scope.newBLMaster.fechaLlegada =  parseFecha($scope.newBLMaster.fechaLlegada);
        //console.log(' fecha de hoy: ',$scope.fecha);
        //console.log('fecha zarpeeee: ',$scope.fechaZarpeAux);

        //$scope.newBLMaster.fechaZarpe = $scope.fechaZarpeAux;
        //console.log('fecha zarpeeee final : ',$scope.newBLMaster.fechaZarpe);
        //console.log('BL : ',$scope.newBLMaster);
        $http.post("http://localhost:8080/blhouses",$scope.newBLHouse);
        //console.log($scope.newBLMaster);
        $scope.mensaje = 'BL House generado con exito!';
        $window.alert($scope.mensaje);



        //aca newVotation esta listo para ser utilizado en el método POST, en teoría

    }

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