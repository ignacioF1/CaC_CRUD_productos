// Disabling form submissions if there are invalid fields



(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.getElementsByClassName('needs-validation');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('keyup', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
          form.classList.remove('is-valid');
          //$("#login").attr( 'disabled', true );
        }
        form.classList.add('was-validated'); 
        
       if (form.checkValidity() === true) {
       // $("#login").removeAttr( 'disabled' );
       form.classList.add('is-valid');
        }
        
      }, false);
    });
  }, false);
})();

