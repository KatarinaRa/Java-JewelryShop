var slideIndex = 0;
      showSlides();
      function showSlides() {
        var i;
        var slides = document.getElementsByClassName("mySlides");
        for(i = 0; i < slides.length; i++) {
          slides[i].style.display = "none";
        }
        slideIndex++;
        if(slideIndex > slides.length) {
          slideIndex = 1
        }
        slides[slideIndex - 1].style.display = "block";
        setTimeout(showSlides, 2000); // Change image every 2 seconds
      }

$(document).ready(function(){
    $(".cartProductQty").on('change',function(){
        var id=this.id;
        $('#update-product-'+id).css('display', 'inline-block');
    });
});

$(document).ready(function(){
    $(".linkMinus").on('change',function(evt){
        evt.preventDefault();
        productId= $(this).attr("pid");
        quantityInput = $('#update-product-'+productId");
        newQuantity = parseInt(quantityInput.val) - 1;
        if(newQuantity > 0){
            quantityInput.val(newQuantity);
        } else {
            showWarningModal('Minimum quantity is 1')
        }
    });

      $(".linkPlus").on('change',function(evt){
            evt.preventDefault();
            productId= $(this).attr("pid");
            quantityInput = $('#update-product-'+productId");
            newQuantity = parseInt(quantityInput.val) + 1;
            if(newQuantity <= 5){
                quantityInput.val(newQuantity);
            } else {
                showWarningModal('Maximum quantity is 1')
            }
        });


});


