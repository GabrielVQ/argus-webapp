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
    
    var urlBase = 'https://argus-webapp.herokuapp.com/numerooperacion/'+$scope.bl;

    $http.get(urlBase)
    .then(function(response) {
        $scope.BLMaster = response.data[0];
        console.log('id: ',$scope.BLMaster);
        $scope.fechaZarpeValue = parseFecha($scope.BLMaster.fechaZarpe);
        $scope.fechaLlegadaValue = parseFecha($scope.BLMaster.fechaLlegada);
        console.log("falops 1 ",$scope.BLMaster.agenteAduana);
        var urlBase6 = 'https://argus-webapp.herokuapp.com/empresas/nombreabrev/'+ $scope.BLMaster.agenteAduana;
        $http.get(urlBase6).then(function(response){  // campo: destino
            $scope.razon_social = response.data[0].razon_social;
            console.log("falops ",$scope.razon_social);
        })
    });

    var token = $scope.bl;
    localStorage.setItem("token", token);


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
       // console.log('fecha zarpeeee final : ',$scope.BLMaster.fechaZarpe);
       // console.log('BL : ',$scope.BLMaster);
        $http.post("https://argus-webapp.herokuapp.com/blmasters",$scope.BLMaster);
        //console.log($scope.BLMaster);
        $scope.mensaje = 'BL Editada con exito!';
        $window.alert($scope.mensaje);

    }

    /*$scope.ciudades = function() {
        $http.get('http://localhost:8080/ciudades').then(function (response) {
            $scope.ciudades = response.data;});
            //console.log($scope.ciudades[0]);
    }*/



        var urlBase2 = 'https://argus-webapp.herokuapp.com/blhouses/numerooperacion/'+$scope.bl;
        console.log("ME EJECUTE")
        $http.get(urlBase2)
            .then(function(response) {

                if (response.data.length != 0){
                    var token = parseInt(response.data[response.data.length - 1].numeroBLHouse) + 1;
                    //var token = servicioNumeroBLHouse.numeroBLHouse;
                    localStorage.setItem("token3", token);
                    console.log('hay bl houses:', localStorage.getItem("token3"))
                }
                else{
                    var token2 = "1";
                    localStorage.setItem("token3", token2);
                    console.log('no hay bl houses', localStorage.getItem("token3"))
                }
            });

    $scope.newContenedor={
        "tipo":"",
        "sigla":"" ,
        "numeroContenedor":"",
        "selloEmpresa":"",
        "selloCliente":"",
        "selloAduana":"",
        "descripcionLarga":"",
        "digito":""
    }

    $scope.agregarContenedor = function(){
        $scope.newContenedor.sigla = $scope.newContenedor.sigla.toUpperCase();
        $scope.newContenedor.selloEmpresa = $scope.newContenedor.selloEmpresa.toUpperCase();
        $scope.newContenedor.selloAduana = $scope.newContenedor.selloAduana.toUpperCase();
        $scope.newContenedor.selloCliente = $scope.newContenedor.selloCliente.toUpperCase();
        $scope.newContenedor.descripcionLarga = $scope.newContenedor.sigla+'-'+$scope.newContenedor.numeroContenedor+$scope.newContenedor.digito+"\n"+$scope.newContenedor.selloEmpresa+'\n'+$scope.newContenedor.selloCliente+'\n'+$scope.newContenedor.selloAduana;

        $http.post("https://argus-webapp.herokuapp.com/containers",$scope.newContenedor);
        $scope.mensaje = 'Conetenedor agregado con exito!';
        $window.alert($scope.mensaje);
    }
    
    $http.get('https://argus-webapp.herokuapp.com/ciudades').then(function(response){  // campo: destino
        $scope.ciudades = response.data;
        //console.log($scope.ciudades);
    })
    //console.log($scope.ciudades);

    $http.get('https://argus-webapp.herokuapp.com/navieras').then(function(response){
        $scope.navieras = response.data;
        //console.log($scope.navieras);
    })

    $http.get('https://argus-webapp.herokuapp.com/naves').then(function(response){
        $scope.naves = response.data;
        //console.log($scope.naves);
    })

    $http.get('https://argus-webapp.herokuapp.com/puertos').then(function(response){   //campos: pto origen y descarga
        $scope.puertos = response.data;
        //console.log($scope.puertos);
    })

    $http.get('https://argus-webapp.herokuapp.com/empresas').then(function(response){ // campo: agente
        $scope.empresas = response.data;
        //console.log($scope.puertos);
    })
    
    
    

    $scope.selectedItemChanged = function(){
        var urlBase5 = 'https://argus-webapp.herokuapp.com/empresas/nombreabrev/'+ $scope.BLMaster.agenteAduana;
        $http.get(urlBase5).then(function(response){  // campo: destino
        $scope.razon_social = response.data[0].razon_social;
    })
    }

}]);