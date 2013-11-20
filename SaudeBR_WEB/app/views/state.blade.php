@extends('layouts.master')

@section('content')
    <div id="canvas"></div>
    <div id="stateDescrition">
    	<h1 id="title">{{$addresses[0]}}:</h1>
    	<p id="description">
    		Total de X habitantes;
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
		                <th>UBS Completos</th>
		                <th>UBS Providos</th>
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
	    	<table class="table">
            <thead>

               <!-- <th>Department</th> -->
                <th>Cidade</th>
                <th>Estado</th>
                <th>Tipo</th>
                <th>Status</th>
            </thead>
            <tbody>
                @foreach($ubss as $ubs)
                    <?php
                        $department = utf8_encode($ubs['department']);
                        $data = explode('-',$ubs['name']);
                        $data[0] = utf8_encode($data[0]);
                    ?>
                    <tr>
                        <!--<td>{{$department}}</td>-->
                        <td>{{$data[0]}}</td>
                        <td>{{$data[1]}}</td>
                        <td>{{$ubs['type']}}</td>
                        <td>{{$ubs['status']}}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
	    </div>
    </div>
    <script type="text/javascript">
    	$(".stContainer").hide();
    </script>
@stop
