@extends('layouts.master')

@section('content')
    <div id="canvas"></div>
    <div id="stateDescrition">
    	<h1 id="title">{{$addresses[0]}}:</h1>
    	<p id="description">
    		<br/>
    		Total investido em UBS: R$ {{$investiment}}
    		<br/>
    		Total de UBS: {{$ubsQuantity}}
    		<br/>
    		Total de UBS I: {{$ubsProvided[0]}}
            <br/>
            Total de UBS II: {{$ubsProvided[1]}}
            <br/>
            Total de UBS III: {{$ubsProvided[2]}}
            <br/>
            Total de UBS IV: {{$ubsProvided[3]}}
            <br/>
            Total de Ampliações de UBS: {{$ubsProvided[4]}}
    		<hr/>
				<button id="cityView" type="button" class="btn btn-default btn-success">Visualizar cidades</button>
				<button id="ubsView" type="button" class="btn btn-default btn-success">Visualisar UBS</button>
		</p>
    </div>
    <div id="content">
    	<div id="cityCotnainer" class="stContainer">
	    	<h2>Cidades de {{$addresses[0]}}:</h2>
	      	<table class="table">
	            <thead>
	            	<tr>
		                <th>Nome</th>
		                <th>UBS Construídos - PAC</th>
		                <th>UBS em Construção - PAC</th>
		                <th>População</th>
	                </tr>
	                <tr>
	                    <th><input type="text" id="txtColuna1"/></th>
	                    <th><input type="text" id="txtColuna2"/></th>
	                    <th><input type="text" id="txtColuna3"/></th>
	                    <th><input type="text" id="txtColuna4"/></th>
	                </tr> 
	            </thead>
	            <tbody>
	                @foreach($cities as $city)
	                    	<?php $name = utf8_encode($city['name']); ?>
	                    <tr>	
	                        <td><?php echo HTML::link('/ubs/'.$addresses[0].'/'.$name,$name); ?></td>
	                        <td>{{$city['numberOfUBSCompleted']}}</td>
	                        <td>{{$city['numberOfUBSProvided']}}</td>
	                        <td>{{$city['population']}}</td>
	                    </tr>
	                @endforeach
	            </tbody>
	        </table>
	      	<hr/>
	      	
	    </div>
	    <div id="ubsContainer" class="stContainer">
            <h2>UBS de {{$addresses[0]}}:</h2>
	    	<table class="table">
            <thead>
            	<tr>
                	<th>Nome</th>
                    <th>Telefone</th>
                	<th>Cidade</th>
            	</tr>
            	<tr>
                    <th><input type="text" id="txtColuna1"/></th>
                    <th><input type="text" id="txtColuna2"/></th>
                    <th><input type="text" id="txtColuna3"/></th>
                </tr> 
            </thead>
            <tbody>
                @foreach($ubss as $ubs)
                    <?php
                        $name = utf8_encode($ubs['name']);
                        $phone = utf8_encode($ubs['phone']);
                        $city =  utf8_encode($ubs['city']);
                    ?>
                    <tr>
                        <td>{{$name}}</td>
                        <td>{{$phone}}</td>
                        <td>{{$city}}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
	    </div>
    </div>
@stop
