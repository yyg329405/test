$(document).ready(function(){

	$.ajax( "ajaxTest.do" )
	  .done(function() {
	    alert( "success" );
	  })
	  .fail(function() {
	    alert( "error" );
	  })
	  .always(function() {
	    alert( "complete" );
	  });
	 
});