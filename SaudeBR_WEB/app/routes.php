<?php
/*
 |--------------------------------------------------------------------------
 | Application Routes
 |--------------------------------------------------------------------------
 |
 | Here is where you can register all of the routes for an application.
 | It's a breeze. Simply tell Laravel the URIs it should respond to
 | and give it the Closure to execute when that URI is requested.
 |
 */
Route::pattern('state','([A-Za-zÀ-ú]+)(\s([A-Za-zÀ-ú]+))*');
Route::pattern('city','[a-z]+');

Route::group(array('prefix'=>'ubs'),function() {
    
    Route::get('/',function(){}); 

    Route::get('/{state}',function($state) {
    	$addresses = array($state);
        $client=new nusoap_client('http://localhost:8080/DadosAbertos1/DadosAbertosServicos?wsdl',true);

        $params = array('uf' => $state);
        $result = array();

        //$cities = $client->call('retrieveAllCity_ByState',$params);
        $ubs = $client->call('retrieveAllUBS_ByState',$params); //Erro 

        //array_push($result, $addresses);
        //array_push($result, $cities);
        //array_push($result,$ubs);

        return View::make('state')->with('result',$result);
    });
    Route::get('/{state}/{city}',function($state,$city) {
        $client = new nusoap_client();
        $params = array('uf' => $state, 'city' => $city);
        $ubs = $client->call('retrieveAllUBS_ByCity',$params); 
        print_r($ubs);

        //return View::make('city');
    });
});

Route::get('/',function() {
        $client = new nusoap_client('http://localhost:8080/DadosAbertos1/DadosAbertosServicos?wsdl',true);
        $states = $client->call('retrieveAllState');
        //print_r($states['return']);
        return View::make('home')->with('states',$states['return']);
});
