function realCustomerValidation(input) {
    var nationalCode = /^\d{5}$/;
    if(input.match(nationalCode)){

    document.forms[0].submit();

    }
    else{
        alert("National Code invalid ");
    }
}
