{{-- Stored in app/views/layouts/master.blade.php --}}
<!DOCTYPE HTML>
    <head>
        <title>Campeonato de Dados Abertos</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=yes">
        <?php

            /* SCRIPTS */
            echo HTML::style('css/style.css');
            echo HTML::style('dist/css/bootstrap-theme.min.css');
            echo HTML::style('dist/css/bootstrap.min.css');

            /* SCRIPTS */
            echo HTML::script('js/jquery-2.0.3.min.js'); // Jquery
            echo HTML::script('dist/js/bootstrap.min.js'); // Bootstrap Twitter
            echo HTML::script('js/helpers.js'); // App
            echo HTML::script('js/App.js'); // App
        ?>
        <script type="text/javascript">
            $(document).ready(function() {
                var canvas = "canvas";
                var app = new App(canvas);
                <?php if(isset($addresses)):  ?>
                
                @foreach ($addresses as $address)
                    app.setState('#{{$address}}');
                @endforeach
            <?php endif;  ?>
                app.init();
            });
        </script>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-inverse" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/">CDA</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Estados<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                        
                        @foreach ($states as $state)
                            <?php $encodedName = utf8_encode($state['name']); ?>
                            <li><?php echo HTML::link('ubs/'.$encodedName,$encodedName); ?></li>
                            

                        @endforeach
                        </ul>
                    </li>
                </ul>
                <form class="navbar-form navbar-right" role="search"> 
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search" required> 
                    </div>
                    <button type="submit" class="btn btn-default">
                        Submit
                    </button>
                </form>
            </div><!-- /.navbar-collapse -->
        </nav>
        <ol class="breadcrumb">
            <li>
                <a href="/">Home</a>
            </li>
            <?php if(isset($addresses)): 
                    $size = count($addresses);
             ?>
            @for ($i = 0; $i < $size; $i++)
            <li>
                {{$addresses[$i]}}
            </li>
            @endfor
            <?php endif; ?>
        </ol>

        <div class="container">
           @yield('content')
        </div>
    </body>
</html>