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
        $client = new nusoap_client('http://nees.com.br:8080/DadosAbertos1/Services?wsdl',true);
        $states = $client->call('retrieveAllState');
        //$ubss = $client->call('retrieveAllUBS');
        $addresses = array('ubs');

        return View::make('ubs')->with('states',$states['return'])->with('ubss',$ubss['return'])->with('addresses',$addresses);
    }); 

    Route::get('/{state}',function($state) {
    	$addresses = array($state);
        $client=new nusoap_client('http://nees.com.br:8080/DadosAbertos1/Services?wsdl',true);
        $params2 = array('nameState' => utf8_decode($state));
        $params = array('stateName'=>utf8_decode($state));

        $states = $client->call('retrieveAllState');
        $ubs = $client->call('retrieveAllUBS_ByState',$params);
        $cities = $client->call('retrieveAllCity_ByState',$params);
        $investiment = $client->call('investmentUBS_ByState',$params);
        $ubsQuantity = $client->call('currentQuantityUBS_ByState',$params);
        $ubsProvided = $client->call('providedQuantityUBS_ByState',$params);

        return View::make('state')
                    ->with('states',$states['return'])
                    ->with('cities',$cities['return'])
                    ->with('addresses',$addresses)
                    ->with('ubss',$ubs['return'])
                    ->with('investiment',$investiment['return'])
                    ->with('ubsQuantity',$ubsQuantity['return'])
                    ->with('ubsProvided',$ubsProvided['return']);
    });
    Route::get('/{state}/{city}',function($state,$city) {
        $addresses = array($state,$city);

        $client=new nusoap_client('http://nees.com.br:8080/DadosAbertos1/Services?wsdl',true);
        $params = array('stateName' => utf8_decode($state),'cityName'=>utf8_decode($city));
        $params2 = array('nameState' => utf8_decode($state),'nameCity'=>utf8_decode($city));

        $states = $client->call('retrieveAllState');    
        $ubs = $client->call('retrieveAllUBS_ByCity',$params);

        if(!isset($ubs['return']))
            $ubs['return'] = array();

        $investment = $client->call('investmentUBS_ByCity',$params);
        $currentQuantity = $client->call('currentQuantityUBS_ByCity',$params);
        $providedQuantity = $client->call('providedQuantityUBS_ByCity',array('stateName' => utf8_decode($state),'cityName'=>utf8_decode($city)));

        return View::make('city')
                            ->with('ubss',$ubs['return'])
                            ->with('addresses',$addresses)
                            ->with('states',$states['return'])
                            ->with('investiment',$investment['return'])
                            ->with('ubsQuantity',$currentQuantity['return'])
                            ->with('ubsProvided',$providedQuantity['return']);
    });
});

Route::get('/',function() {
        $client = new nusoap_client('http://nees.com.br:8080/DadosAbertos1/Services?wsdl',true);
        $states = $client->call('retrieveAllState');
        return View::make('home')->with('states',$states['return']);
});


