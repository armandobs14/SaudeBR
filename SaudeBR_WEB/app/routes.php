<?php
/*
 |--------------------------------------------------------------------------
 | Application Routes
 |--------------------------------------------------------------------------
 |
 | Here is where you can register all of the routes for an application.
 | It's a breeze. Simply tell Laravel the URIs it should respond to
 | and give it the Closure to execute when that URI is requesvcted.
 |
 */
Route::pattern('state','([A-Za-zÀ-ú]+)(\s([A-Za-zÀ-ú]+))*');
Route::pattern('city','([A-Za-zÀ-ú]+)(\s([A-Za-zÀ-ú]+))*');

Route::group(array('prefix'=>'ubs'),function() {
    
    Route::get('/',function(){
        $client = new nusoap_client('http://localhost:8080/DadosAbertos1/DadosAbertosServicos?wsdl',true);
        $states = $client->call('retrieveAllState');
        $ubss = $client->call('retrieveAllUBS');
        $addresses = array('ubs');

        return View::make('ubs')->with('states',$states['return'])->with('ubss',$ubss['return'])->with('addresses',$addresses);
    }); 

    Route::get('/{state}',function($state) {
    	$addresses = array($state);
        $client=new nusoap_client('http://localhost:8080/DadosAbertos1/DadosAbertosServicos?wsdl',true);
        $params = array('nameState' => utf8_decode($state));

        $cities = $client->call('retrieveAllCity_ByState',$params);
        $states = $client->call('retrieveAllState');
        $ubs = $client->call('retrieveAllUBS_ByState',$params);
        $quantity = $client->call('retrieveAllUBS_ByState',$params);
        $ubsReturn = array();

        if(isset($ubs) && $ubs != ''){
            if(array_keys($ubs['return']) !== range(0, count($ubs['return']) - 1)) $ubsReturn[0] = $ubs['return'];    
        }

        return View::make('state')->with('states',$states['return'])->with('cities',$cities['return'])->with('addresses',$addresses)->with('ubss',$ubsReturn); //Colocar with($ubs)
    });
    Route::get('/{state}/{city}',function($state,$city) {
        echo "Eteste";
    });
});

Route::get('/',function() {
        $client = new nusoap_client('http://localhost:8080/DadosAbertos1/DadosAbertosServicos?wsdl',true);
        $states = $client->call('retrieveAllState');
        //print_r($states['return']);
        return View::make('home')->with('states',$states['return']);
});


