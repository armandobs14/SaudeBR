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
@stop

