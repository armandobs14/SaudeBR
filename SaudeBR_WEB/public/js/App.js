( function(window) {
		DESKTOP_RESOLUTION = 768;
		var fill = '#000';
		var activeState = null;
		function Application(canvas) {
			this.canvas = canvas;
			this.fill = '#000';
		};
		Application.prototype.init = function() {
			$(canvas).load("img/Map.svg", function(evt) {
				if (window.innerHeight >= DESKTOP_RESOLUTION || window.innerWidth >= DESKTOP_RESOLUTION) {
					$('svg').attr('width', '90mm');
				} else {
					$('svg').attr('width', '85mm');
				}

				if(activeState)	$(activeState)[0].style.fill = '#000';
				
				$(".str1").click(function(){
					location.href = 'ubs/'+this.id;
				});

				$(".str1").mouseover(function() {
					fill = this.style.fill;
					this.style.fill = '#000';
				});
				
				$(".str1").mouseout(function() {
					this.style.fill = fill;
				});
			});
		};

		Application.prototype.setState = function(state) {
			activeState = state;
		};

		window.App = Application;

	}(window)); 