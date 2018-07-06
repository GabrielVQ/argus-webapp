app.controller('editarBLMasterController', ['$scope', '$location', '$http','$window','$routeParams', function($scope, $location, $http,$window, $routeParams) {
    

    $scope.creador = 'Eduardo Avendaño';
    $scope.fecha = new Date();

    $scope.bl = $routeParams.bl;
    $scope.BLMaster = [];

    
    function isDate(x) 
    { 
    return (null != x) && !isNaN(x) && ("undefined" !== typeof x.getDate); 
    }
    

    function parseFecha(date){
        var aux = date.split(' ')[0].split('/');
        var fecha =  aux[2]+"-"+aux[1]+"-"+aux[0];
        return fecha;
    }

    function parseFechaForDB(date){
        var fecha = date.getDate()  + "/" + (date.getMonth()+1) + "/" + date.getFullYear() + " a las " + ("0" + date.getHours()).slice(-2) + ":" + ("0" + date.getMinutes()).slice(-2);
        return fecha;
    }
    
    var urlBase = 'http://localhost:8080/blmasters/numerooperacion/'+$scope.bl;

    $http.get(urlBase)
    .then(function(response) {
        $scope.BLMaster = response.data[0];
        console.log('id: ',$scope.BLMaster);
        $scope.fechaZarpeValue = parseFecha($scope.BLMaster.fechaZarpe);
        $scope.fechaLlegadaValue = parseFecha($scope.BLMaster.fechaLlegada);
    });


    $scope.send = function(){
        
    
        
        //$scope.BLMaster.nave= {"id":parseInt($scope.BLMaster.nave)};
        //$scope.BLMaster.naviera= {"id":parseInt($scope.BLMaster.naviera)};
        //$scope.BLMaster.puertoOrigen= {"id":parseInt($scope.BLMaster.puertoOrigen)};
        //$scope.BLMaster.puertoDescarga= {"id":parseInt($scope.BLMaster.puertoDescarga)};
        //$scope.BLMaster.destino= {"id":parseInt($scope.BLMaster.destino)};

        if (isDate( $scope.fechaZarpeValue)) {
            $scope.BLMaster.fechaZarpe =  parseFechaForDB($scope.fechaZarpeValue);
        }

        if (isDate( $scope.fechaLlegadaValue)) {
            $scope.BLMaster.fechaLlegada =  parseFechaForDB($scope.fechaLlegadaValue);
        }

        //console.log(' fecha de hoy: ',$scope.fecha);
        //console.log('fecha zarpeeee: ',$scope.fechaZarpeAux);

        //$scope.BLMaster.fechaZarpe = $scope.fechaZarpeAux;
        console.log('fecha zarpeeee final : ',$scope.BLMaster.fechaZarpe);
        console.log('BL : ',$scope.BLMaster);
        $http.post("http://localhost:8080/blmasters",$scope.BLMaster);
        //console.log($scope.BLMaster);
        $scope.mensaje = 'BL Editada con exito!';
        $window.alert($scope.mensaje);

    }

    /*$scope.ciudades = function() {
        $http.get('http://localhost:8080/ciudades').then(function (response) {
            $scope.ciudades = response.data;});
            //console.log($scope.ciudades[0]);
    }*/




    
    $http.get('http://localhost:8080/ciudades').then(function(response){  // campo: destino
        $scope.ciudades = response.data;
        //console.log($scope.ciudades);
    })
    //console.log($scope.ciudades);

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