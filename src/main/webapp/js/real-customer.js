function realCustomerValidation(input) {
    var nationalCode = /^\d{10}$/;
    var sum = 0;
    if (input.match(nationalCode)) {
        for (var i = 2; i < 11; i++) {

            sum += input.charAt(10 - i) * i;

        }
        if ((sum % 11) == (input.charAt(9)) || (sum % 11) == (11 - input.charAt(9))) {

            document.forms[0].submit();
        }
        else {
            alert("National Code invalid ");

        }

    }
    else {
        alert("National Code invalid ");
    }
}
