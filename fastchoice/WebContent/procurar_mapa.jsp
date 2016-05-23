<!DOCTYPE html>
<html>
<head>
	<title>FastChoice</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
	<meta charset="utf-8">
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/css/styleChat.css">
<style>
html, body {
		height: 100%;
		margin: 0;
		padding: 0;
}

#map {
		height: 100%;
}

.controls {
		margin-top: 10px;
		border: 1px solid transparent;
		border-radius: 2px 0 0 2px;
		box-sizing: border-box;
		-moz-box-sizing: border-box;
		height: 32px;
		outline: none;
		box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
}

#pac-input {
		background-color: #fff;
		font-family: Roboto;
		font-size: 15px;
		font-weight: 300;
		margin-left: 12px;
		padding: 0 11px 0 13px;
		text-overflow: ellipsis;
		width: 300px;
}

#pac-input:focus {
		border-color: #4d90fe;
}

.pac-container {
		font-family: Roboto;
}

#type-selector {
		color: #fff;
		background-color: #4d90fe;
		padding: 5px 11px 0px 11px;
}

#type-selector label {
		font-family: Roboto;
		font-size: 13px;
		font-weight: 300;
}
</style>
</head>
<body>
	<div id="campos"><input id="pac-input" class="controls" type="text"
		placeholder="Digite a localização">
	</div>
	<div id="map" style="margin: auto; width: 100%; background-color:orange; height:300px;"></div>

<script>

var endGoogle;

function CenterControl(controlDiv, map) {
	
// 	  document.getElementById("endGoogle").disabled = true;

	  // Set CSS for the control border.
// 	  var controlUI = document.createElement('div');
// 	  controlUI.style.borderRadius = '13px';
// 	  controlUI.style.cursor = 'pointer';
// 	  controlUI.style.marginBottom = '22px';
// 	  controlUI.style.lineHeight = '4.5';
// 	  controlUI.style.textAlign = 'center';
// 	  controlUI.title = 'Salvar o endereço';
// 	  controlDiv.appendChild(controlUI);
	  
// 	  controlUI.style.border = '2px solid #77ff33';
// 	  controlUI.style.backgroundColor = '#33ffad';
// 	  controlUI.style.boxShadow = '0 2px 6px rgba(0,100,0,.3)';

	  // Set CSS for the control interior.
// 	  var controlText = document.createElement('button');
// 	  controlText.style.backgroundColor = '#79ff4d';
// 	  controlText.style.color = 'rgb(25,25,25)';
// 	  controlText.style.background = 'rgb(153, 255, 102)';
// 	  controlText.style.fontFamily = 'Roboto,Arial,sans-serif';
// 	  controlText.style.fontSize = '16px';
// 	  controlText.style.paddingLeft = '5px';
// 	  controlText.style.paddingRight = '5px';
// 	  controlText.className = 'btn btn-default';
// 	  controlText.innerHTML = 'Confirmar';
// 	  controlUI.appendChild(controlText);
	  
// 	  controlText.type = 'button';
// 	  controlText.style.lineHeight = '25px';
// 	  controlText.setAttribute("href", "asds");

	  // Setup the click event listeners: simply set the map to -34.397, 150.644.
	  // var latlng = new google.maps.LatLng(-34.397, 150.644);
	  controlText.addEventListener('click', function() {
// 	    map.setCenter(latlng);
		
// 		document.getElementById("endGoogle").value = endGoogle;
// 		document.getElementById("endGoogleHidden").value = endGoogle;

		
	  });

	}
	
function initMap() {
// document.getElementById("endGoogle").disabled = true;

  var map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -33.8688, lng: 151.2195},
    zoom: 13
  });
  var input = /** @type {!HTMLInputElement} */(
      document.getElementById('pac-input'));
  map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);
  
  // elemento botao custom
//   var centerControlDiv = document.createElement('div');
//   var centerControl = new CenterControl(centerControlDiv, map);
//   centerControlDiv.index = 1;
//   map.controls[google.maps.ControlPosition.TOP_CENTER].push(centerControlDiv);

  var autocomplete = new google.maps.places.Autocomplete(input);
  autocomplete.bindTo('bounds', map);

  var infowindow = new google.maps.InfoWindow();
  var marker = new google.maps.Marker({
    map: map,
    anchorPoint: new google.maps.Point(0, -29)
  });

  autocomplete.addListener('place_changed', function() {
    infowindow.close();
    marker.setVisible(false);
    var place = autocomplete.getPlace();

    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {
      map.fitBounds(place.geometry.viewport);
    } else {
      map.setCenter(place.geometry.location);
      map.setZoom(17);  // Why 17? Because it looks good.
    }
    marker.setIcon(/** @type {google.maps.Icon} */({
      url: place.icon,
      size: new google.maps.Size(71, 71),
      origin: new google.maps.Point(0, 0),
      anchor: new google.maps.Point(17, 34),
      scaledSize: new google.maps.Size(35, 35)
    }));
    marker.setPosition(place.geometry.location);
    marker.setVisible(true);

    var address = '';
    if (place.address_components) {
      address = [
        (place.address_components[0] && place.address_components[0].short_name || ''),
        (place.address_components[1] && place.address_components[1].short_name || ''),
        (place.address_components[2] && place.address_components[2].short_name || '')
      ].join(' ');
      endGoogle = address;
      document.getElementById("endGoogle").value = endGoogle;
	  document.getElementById("endGoogleHidden").value = endGoogle;
    }

    infowindow.setContent('<div><strong>' + place.name + '</strong><br>' + address);
    infowindow.open(map, marker);
    
//     document.getElementById("confirmarMapa").disabled = false;
  });

}

    </script>
	<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATXeSuocS-DqM7PBgr1VcTkmGmXq-icjI&signed_in=true&libraries=places&callback=initMap"
	async defer></script>
</body>
</html>