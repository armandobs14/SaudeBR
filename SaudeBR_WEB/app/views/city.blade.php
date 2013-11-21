@extends('layouts.master')

@section('content')
    <div id="canvas"></div>
    <div id="stateDescrition">
    		<h1 id="title">{{$addresses[1]}}:</h1>
    	<p id="description">
    		<br/>
    		Total investido em UBS: R$ {{$investiment}}
    		<br/>
    		Total de UBS: {{$ubsQuantity}}
    		<br/>
    		Total de UBS I:{{$ubsProvided[0]}}
    		<br/>
    		Total de UBS II:{{$ubsProvided[1]}}
    		<br/>
    		Total de UBS III:{{$ubsProvided[2]}}
    		<br/>
    		Total de UBS IV:{{$ubsProvided[3]}}
            <br/>
            Total de Ampliações de UBS: {{$ubsProvided[4]}}
    		<hr/>
				<button id="ubsView" type="button" class="btn btn-default btn-success">Visualisar UBS</button>
		</p>
    </div>
    <div id="ubsContainer" class="stContainer">
    	<h2>UBS de {{$addresses[1]}}:</h2>
	    	<table class="table">
            <thead>
            	<tr>
                	<th>Nome</th>
                	<th>Telefone</th>
                	<th>Distrito</th>
                	<th>Rua</th>
            	</tr>
            	<tr>
                    <th><input type="text" id="txtColuna1"/></th>
                    <th><input type="text" id="txtColuna2"/></th>
                     <th><input type="text" id="txtColuna1"/></th>
                    <th><input type="text" id="txtColuna2"/></th>
                </tr> 
            </thead>
            <tbody>
                @foreach($ubss as $ubs)
                    <?php
                        $name = utf8_encode($ubs['name']);
                        $phone = utf8_encode($ubs['phone']);
                        $district = utf8_encode($ubs['district']);
                        $street = utf8_encode($ubs['street']);

                    ?>
                    <tr>
                        <td>{{$name}}</td>
                        <td>{{$phone}}</td>
                        <td>{{$district}}</td>
                        <td>{{$street}}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
	    </div>
@stop
