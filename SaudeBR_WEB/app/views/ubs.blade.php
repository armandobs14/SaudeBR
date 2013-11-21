@extends('layouts.master')

@section('content')
    <div id="canvas"></div>
    <div id="stateDescrition">
    	<h1 id="title">UBS:</h1>
    	<p id="description">
    		Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.  Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    	</p>
    </div>
    <div id="content">
        <table class="table">
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Telefone</th>
                </tr>
                <tr>
                    <th><input type="text" id="txtColuna1"/></th>
                    <th><input type="text" id="txtColuna2"/></th>
                </tr> 
            </thead>
            <tbody>
                @foreach($ubss as $ubs)
                    <?php
                        $name = utf8_encode($ubs['name']);
                        $phone = utf8_encode($ubs['phone']);
                    ?>
                    <tr>
                        <td>{{$name}}</td>
                        <td>{{$phone}}</td>
                    </tr>
                @endforeach
            </tbody>
        </table>
    </div>
@stop

