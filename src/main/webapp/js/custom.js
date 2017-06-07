/****************************************************
*
*
*
*
*
*****************************************************/
var login_width = 300;

$(document).ready(function() {
	
  /* Dialog */
  $('#dialog').dialog({
      autoOpen: false,
      width: login_width + "px"
  });

  /* Login button */
  $('#loginbtn').click(function() {
	$("span.ui-dialog-title").text('Login');  
    $('#coverup').show();
    $('#login_form').show();
    $('#register_form').hide();
    $('#Lname').val("");
    $('#Lpassword').val("");
    $('#Rname').val("");
    $('#Rpassword').val("");
    $('#Remail').val("");
    $('#Rpassword').val("");

    $('#dialog').dialog('open');
    $('#name').focus();
  });

  $('#registerbtn').click(
	function() {
      $('#login_form').hide();
      $('#register_form').show();
      $("span.ui-dialog-title").text('Register');
	}
  );
   
  /* Coverup */
  $('#coverup').hide();

  $( "#coverup" ).click(function() {
    $('#dialog').dialog('close');
  });
  /* */  

  $('#dialog').on('dialogclose', function(event) {
    $('#coverup').hide();
  });
  
  $('#loginsubmit').click(
    function() {
      $.get( "login", function( data ) {
        $( "#result" ).html( data );
      });
    }
  );
});
